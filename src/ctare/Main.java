package ctare;

import ctare.core.*;
import ctare.mod.ModLoader;
import ctare.mod.worksystem.StorageStates;
import ctare.mod.worksystem.resource.FoodResourceNode;
import ctare.mod.worksystem.resource.WoodResourceNode;
import ctare.nodes.CentralNode;
import ctare.nodes.VacantNode;
import ctare.nodes.unit.UnitNode;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Main extends PApplet {
    public Graph graph = new Graph();
    NodeSetter nodeSetter = new NodeSetter();
    final int MARGIN = 5;
    private float displayScale = 1.0f;
    private PVector displayVec = new PVector(0, 0);
    public final KeyMap keymap = new KeyMap();

    public Supplier<Node> nodeSupplier;

    private ArrayList<UnitNode> units = new ArrayList<>();
    private ArrayList<UnitNode> addUnits = new ArrayList<>();
    private ArrayList<UnitNode> dropUnits = new ArrayList<>();
    public CentralNode root;
    public final Layer baseLayer = new Layer();
    public final Layer characterLayer = new Layer();
    public final Layer informationLayer = new Layer();
    private final ArrayList<Layer> layers = new ArrayList<>();

    private static Main main;
    public static Main instance() {
        return main;
    }

    public static void main(String[] args) {
        PApplet.main("ctare.Main");
    }

    @Override
    public void settings() {
        this.size(700, 700);
    }

    @Override
    public void setup() {
        Main.main = this;

        layers.add(baseLayer);
        layers.add(characterLayer);
        layers.add(informationLayer);

        textAlign(LEFT, TOP);
        textSize(15);

        final int defaultAmount = 5;
        keymap.register('a', main -> nodeSetter.addAmount(1));
        keymap.register('x', main -> nodeSetter.addAmount(-1));
        keymap.register('2', main -> nodeSupplier = () -> new FoodResourceNode(defaultAmount));
        keymap.register('3', main -> nodeSupplier = () -> new WoodResourceNode(defaultAmount));
        keymap.register('j', main -> {
            displayVec.x += (width / displayScale) / 2;
            displayVec.y += (height / displayScale) / 2;
            displayScale /= 2;
        });
        keymap.register('k', main -> {
            displayScale *= 2;
            displayVec.x -= (width / displayScale) / 2;
            displayVec.y -= (height / displayScale) / 2;
        });
        keymap.register('q', main -> {
            nodeSetter.clear();
            targetEdge = null;
        });
        keymap.register('z', main -> System.out.println(root.states.get(StorageStates.class).storage));

        ModLoader.load();

        root = new CentralNode(7);
        nodeSetter.setNode(root);
        nodeSetter.setPosition(width / 2, height / 2);
        nodeSetter.register(graph);

        for (int i = 0; i < 3; i++) {
            addUnit(root);
        }

        keymap.keyPressed('1', this);
    }

    public void addUnit(UnitNode unit) {
        addUnits.add(unit);
    }

    public void addUnit(VacantNode place) {
        addUnits.add(new UnitNode(place));
    }

    public void dropUnit(UnitNode unit) {
        dropUnits.add(unit);
        unit.deactivate();
    }

    private void execAddUnit() {
        units.addAll(addUnits);
        addUnits.forEach(Node::activate);
        addUnits.clear();
    }

    private void execDropUnit() {
        dropUnits.forEach(unit -> {
            units.remove(unit);
            unit.getBelong().member.remove(unit);
        });
        dropUnits.clear();
    }

    @Override
    public void draw() {
        layers.forEach(Layer::clear);
        background(51, 51, 51);
        fill(255);
        text(String.format("%.2f : %d", frameRate, units.size()), 0, 0);
        scale(displayScale);
        translate(displayVec.x, displayVec.y);
        execAddUnit();
        execDropUnit();

        if (mousePressed) {
            displayVec.x += (mouseX - pmouseX) / displayScale;
            displayVec.y += (mouseY - pmouseY) / displayScale;
        }

//        if (units.size() > 100) { // ---- del
//            int loop = Calc.random.nextInt(units.size());
//            for (int i = 0; i < loop; i++) {
//                units.remove(Calc.random.nextInt(units.size()));
//            }
//        }

        for (Edge edge : graph.edges) {
            edge.draw();
        }

        if (nodeSetter.setPosition(mouseX(), mouseY())) {
            Node node = nodeSetter.getNode();
            Color color = node.getColor();
            fill(color.r, color.g, color.b, 100);
            noStroke();
//            // 接触で色変更
//            if (getCrossEdge(nodeSetter.getNode().getPosition(), nodeSetter.getNode().getRadius()) != null) {
//                fill(255, 0, 0, 100);
//            }
            ellipse(node.getPosition().x, node.getPosition().y, node.getRadius() * 2, node.getRadius() * 2);

            if (targetEdge != null) {
                stroke(200, 200, 200, 100);
                targetEdge.draw();
            }
        }

        for (Node node : graph.nodes) {
            node.draw();
        }

        for (UnitNode unit : units) {
            unit.draw();
        }

        layers.forEach(Layer::exec);
    }

    public boolean isHit(float x1, float y1, float r1, float x2, float y2, float r2) {
        return dist(x1, y1, x2, y2) <= r1 + r2;
    }

    public boolean isHit(Node node1, Node node2) {
        return isHit(node1.getPosition().x, node1.getPosition().y, node1.getRadius(),
                node2.getPosition().x, node2.getPosition().y, node2.getRadius());
    }

    public Node getHitNode(float x, float y, float r) {
        for (Node node : graph.nodes) {
            PVector position = node.getPosition();
            if (isHit(position.x, position.y, r, x, y, node.getRadius() + MARGIN)) {
                return node;
            }
        }
        return null;
    }

    public Node getHitNode(PVector position, float r) {
        return getHitNode(position.x, position.y, r);
    }

    public Node getHitNode(Edge edge) {
        Node start = edge.getStart(), end = edge.getEnd();
        for (Node node : graph.nodes) {
            if (isCross(node.getPosition().x, node.getPosition().y, node.getRadius() + MARGIN,
                    start.getPosition().x, start.getPosition().y, end.getPosition().x, end.getPosition().y)
                    && node != start && node != end) {
                return node;
            }
        }
        return null;
    }

    public Edge getCrossEdge(Edge edge) {
        Node start = edge.getStart(), end = edge.getEnd();
        for (Edge e : graph.edges) {
            if (isCross(start.getPosition().x, start.getPosition().y, end.getPosition().x, end.getPosition().y,
                    e.getStart().getPosition().x, e.getStart().getPosition().y,
                    e.getEnd().getPosition().x, e.getEnd().getPosition().y)) {
                return e;
            }
        }
        return null;
    }

    public Edge getCrossEdge(float x, float y, float r) {
        for (Edge edge : graph.edges) {
            if (isCross(x, y, r + MARGIN,
                    edge.getStart().getPosition().x, edge.getStart().getPosition().y,
                    edge.getEnd().getPosition().x, edge.getEnd().getPosition().y)) {
                return edge;
            }
        }
        return null;
    }

    public Edge getCrossEdge(PVector position, float r) {
        return getCrossEdge(position.x, position.y, r);
    }

    public boolean isCross(float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy) {
        // line x line
        float ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax);
        float tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx);
        float tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx);
        float td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx);

        return tc * td < 0 && ta * tb < 0;
    }

    public boolean isCross(float ax, float ay, float ar, float bx, float by, float cx, float cy) {
        // circle x line
        float d = dist(bx, by, cx, cy);

        float Bx = ax - bx;
        float By = ay - by;
        float Ax = cx - bx;
        float Ay = cy - by;
        float prep = abs((Ax / d) * By - Bx * (Ay / d));
        if (prep > ar) {
            // 線と十分遠い
            return false;
        }

        float Cx = ax - cx;
        float Cy = ay - cy;
        float d90 = PI / 2;
        boolean l1 = Ax * Bx + Ay * By < d90;
        boolean l2 = Ax * Cx + Ay * Cy < d90;
        if (l1 != l2) {
            // 線分範囲内で円と接触
            return true;
        }

        if (dist(bx, by, ax, ay) < ar || dist(cx, cy, ax, ay) < ar) {
            // 線分範囲外で円と接触
            return true;
        }

        return false;
    }

    @Override
    public void mousePressed() {
//         -- ノードの移動 --
//        Node hit = getHitNode(mouseX(), mouseY(), 0);
//        if (hit != null) {
//            this.nodeSetter.setNode(hit);
//            this.targetEdge = null;
//        }

        Node target = this.nodeSetter.getNode();
        if (target == null) {
            Node parent = null;
            if (graph.nodes.size() == 0 || (parent = getHitNode(mouseX(), mouseY(), 0)) != null) {
//                 Node candidate = new Node(5);
                Node candidate = nodeSupplier.get();
                this.nodeSetter.setNode(candidate);
                if (parent != null) {
                    targetEdge = new Edge();
                    targetEdge.setStart(parent);
                    targetEdge.setEnd(candidate);
                }
            }
        } else {
            if(getHitNode(target.getPosition(), target.getRadius()) == null
                    && getCrossEdge(target.getPosition(), target.getRadius()) == null) {
                if (targetEdge == null || (
                        getCrossEdge(targetEdge) == null &&
                                getHitNode(targetEdge) == null)) {
                    this.nodeSetter.register(graph, targetEdge);
//                    if (targetEdge != null) {
//                        target.setParent(targetEdge);
//                        graph.edges.add(targetEdge);
//                    }

//                    // --- del ---
//                    units.get((graph.edges.size() - 1) % units.size()).addPlan(target);
//                    // --- del ---
                }
            }
        }
    }

    public int mouseX() {
        return (int)(mouseX / displayScale - displayVec.x);
    }

    public int mouseY() {
        return (int)(mouseY / displayScale - displayVec.y);
    }

//    @Override
//    public void mouseReleased() {
//     -- ノードの移動 --
//        this.nodeSetter.clear();
//        this.targetEdge = null;
//    }

    private Edge targetEdge = null;

    @Override
    public void keyPressed() {
        keymap.keyPressed(key, this);
    }
}
