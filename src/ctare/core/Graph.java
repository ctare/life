package ctare.core;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/17.
 */
public class Graph {
    public ArrayList<Node> nodes = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();

    public void register(Node node, Edge edge) {
        if (edge != null) {
            node.setParent(edge);
            edges.add(edge);

            Node parent = edge.getStart();
            for (Node n : nodes) {
                node.distances.put(n, parent.distances.get(n) + 1);
                n.distances.put(node, n.distances.get(parent) + 1);
            }
        }
        nodes.add(node);
        node.distances.put(node, 0);
        node.activate();
    }

    public static ArrayList<Node> getRoute(Node startNode) {
        Edge parent = startNode.getParent();
        ArrayList<Node> route = new ArrayList<>();
        route.add(startNode);
        while (parent != null) {
            Node parentNode = parent.getStart();
            route.add(parentNode);
            parent = parentNode.getParent();
        }

        return route;
    }

    public static ArrayList<Node> getRoute(Node startNode, Node endNode) {
        ArrayList<Node> route1 = Graph.getRoute(startNode);
        ArrayList<Node> route2 = Graph.getRoute(endNode);

        int loop = Math.min(route1.size(), route2.size());
        int branchPoint = loop;
        for (int i = 0; i < loop; i++) {
            if(route1.get(route1.size() - i - 1) != route2.get(route2.size() - i - 1)) {
                branchPoint = i;
                break;
            }
        }

        ArrayList<Node> route = new ArrayList<>();
        for (int i = 1; i <= route1.size() - branchPoint; i++) {
            route.add(route1.get(i));
        }
        for (int i = route2.size() - branchPoint - 1; i >= 0; i--) {
            route.add(route2.get(i));
        }
        return route;
    }
}
