package ctare.mod.worksystem;

import ctare.core.Color;
import ctare.core.NodesManager;
import ctare.nodes.WorkplaceNode;
import ctare.mod.worksystem.resource.Resource;
import processing.core.PApplet;

import java.util.*;

/**
 * Created by ctare on 2020/05/18.
 */
public abstract class ResourceNode extends WorkplaceNode<ResourceNode> {
    public Color resourceColor;
    protected int score = 0;
    private boolean growthPhase = true;

//    public static final SortedArrayList<ResourceNode> resources = new SortedArrayList<>();

    private static final int VOLUME = 10;

    public ResourceNode(int amount, Color resourceColor) {
        super(amount);
        this.resourceColor = resourceColor;
    }

    public void growth(int amount) {
         this.score = Math.min(this.score + amount, this.gain());
         if (this.score == this.gain()) {
             this.growthPhase = false;
         }
    }

    public boolean isGrowthPhase() {
        return this.growthPhase;
    }

    public abstract Resource gather(int amount);

    protected Resource gather(Resource resource) {
        resource.amount = Math.min(resource.amount, this.score);
        this.score -= resource.amount;

        if (this.score == 0) {
            this.growthPhase = true;
        }

        return resource;
    }

    @Override
    public Color getColor() {
        return this.resourceColor;
    }

    @Override
    public void design(PApplet app) {
        float k = ((float)this.score / (this.gain()));
        int r = 135, g = 135, b = 135;
        app.fill(r - (r - resourceColor.r) * k, g - (b - resourceColor.g) * k, b - (b - resourceColor.b) * k);
        app.strokeWeight(1);
        app.stroke(this.getColor().r, this.getColor().g, this.getColor().b);

        super.design(app);
    }

    private int gain() {
        return this.getAmount() * VOLUME;
    }

    @Override
    public List<ResourceNode> getNodes() {
        return NodesManager.get(ResourceNode.class);
    }
}
