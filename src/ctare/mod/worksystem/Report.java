package ctare.mod.worksystem;

import ctare.nodes.CentralNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/20.
 */
public class Report extends Purpose<CentralNode> {
    public final ResourceItem resource;

    public Report(ResourceItem resource) {
        super(10);
        this.resource = resource;
    }

    @Override
    public State getState(UnitNode unit, CentralNode where) {
        return new Delivery(unit, where, this);
    }

    public ResourceItem getResource() {
        return resource;
    }
}
