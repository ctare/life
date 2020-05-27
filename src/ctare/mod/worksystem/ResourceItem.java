package ctare.mod.worksystem;

import ctare.core.Color;
import ctare.mod.bagsystem.Item;
import ctare.mod.worksystem.resource.Resource;

/**
 * Created by ctare on 2020/05/27.
 */
public class ResourceItem extends Item {
    private Resource resource;
    private Color color;
    public ResourceItem(Resource resource, Color color) {
        this.resource = resource;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public Resource getResource() {
        return resource;
    }
}
