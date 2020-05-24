package ctare.utils;

import ctare.core.Node;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;
import java.util.Random;

/**
 * Created by ctare on 2020/05/19.
 */
public final class Calc {
    private Calc() {}

    public static final Random random = new Random();

    public static boolean bernoulli(float p) {
        return random.nextDouble() < p;
    }

    public static <E> E choice(List<E> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static final float dist(Node node1, Node node2) {
        return PApplet.dist(node1.getPosition().x, node1.getPosition().y, node2.getPosition().x, node2.getPosition().y);
    }
    public static final float dist(PVector vec1, PVector vec2) {
        return PApplet.dist(vec1.x, vec1.y, vec2.x, vec2.y);
    }

    public static PVector getRandomPosition(Node node, float padding) {
        float theta = PApplet.radians(Calc.random.nextFloat() * 360);
        float rad = Calc.random.nextFloat() * (node.getRadius() - padding);
        return node.getPosition().copy().add(PApplet.cos(theta) * rad, PApplet.sin(theta) * rad);
    }

    public static <E> E getNode(List<E> nodes) {
        return Calc.choice(nodes);
    }
}
