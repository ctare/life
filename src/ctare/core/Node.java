package ctare.core;

import ctare.Main;
import ctare.nodes.unit.UnitNode;
import ctare.utils.MappedArray;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ctare on 2020/05/17.
 */
public class Node implements Drawable {
    public final ArrayList<UnitNode> member = new ArrayList<>();
    public final MappedArray<Node, Integer> distances = new MappedArray<>(Comparator.comparingInt(MappedArray.KeyValue::getValue));

    private PVector position;
    private int amount;
    private Edge parent;
    private boolean active = false;
    protected Layer layer = Main.instance().baseLayer;

    protected final int SIZE = 5;
    protected int getSize() {
        return SIZE;
    }

    private static final Color COLOR = new Color(255, 255, 255);
    public Color getColor() {
        return Node.COLOR;
    }

    public Node(int amount) {
        this.amount = amount;
    }

    public void setPosition(float x, float y) {
        this.position = new PVector(x, y);
    }

    public PVector getPosition() {
        return position;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getRadius() {
        return this.amount * this.getSize();
    }

    @Override
    public void draw() {
        if (this.position != null) {
            if (this.active) {
                this.layer.paint(main -> {
                    main.noStroke();
                    main.fill(this.getColor().r, this.getColor().g, this.getColor().b);
                    this.design(main);
                });
            }
        }
    }

    public void design(PApplet app) {
        app.ellipse(this.position.x, this.position.y, this.getRadius() * 2, this.getRadius() * 2);
    }

    public Edge getParent() {
        return parent;
    }

    public void setParent(Edge parent) {
        this.parent = parent;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public final boolean isActive() {
        return this.active;
    }

    public void register(UnitNode unit) {
        this.member.add(unit);
    }

    public void unregister(UnitNode unit) {
        this.member.remove(unit);
    }
}
