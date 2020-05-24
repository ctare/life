package ctare.core;

import ctare.Main;

/**
 * Created by ctare on 2020/05/17.
 */
public class Edge implements Drawable {
    private Node start, end;

    @Override
    public void draw() {
        if (start != null && end != null) {
            Main.instance().baseLayer.paint(main -> {
                main.strokeWeight(3);
                main.stroke(135);
                main.line(start.getPosition().x, start.getPosition().y, end.getPosition().x, end.getPosition().y);
            });
        }
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
