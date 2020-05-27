package ctare.core;

import ctare.Main;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by ctare on 2020/05/27.
 */
public abstract class RoundObject implements Drawable {
    private PVector position;
    private boolean active = false;

    protected final Layer layer;

    public RoundObject(Layer layer) {
        this.layer = layer;
    }

    public RoundObject() {
        this(Main.instance().baseLayer);
    }

    public abstract int getRadius();
    public abstract Color getColor();

    public void setPosition(float x, float y) {
        this.position = new PVector(x, y);
    }

    public final void setPosition(PVector vector) {
        this.setPosition(vector.x, vector.y);
    }

    public PVector getPosition() {
        return position;
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

    public void design(PApplet app) {
        app.ellipse(this.position.x, this.position.y, this.getRadius() * 2, this.getRadius() * 2);
    }

    protected abstract void update();

    @Override
    public final void draw() {
        if (this.getPosition() != null) {
            if (this.isActive()) {
                this.update();
                this.layer.paint(main -> {
                    main.noStroke();
                    main.fill(this.getColor().r, this.getColor().g, this.getColor().b);
                    this.design(main);
                });
            }
        }
    }
}
