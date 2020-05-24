package ctare.mod.worksystem;

import ctare.nodes.CentralNode;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.UnitNode;
import ctare.mod.worksystem.resource.Resource;

/**
 * Created by ctare on 2020/05/20.
 */
public class Report extends Purpose<CentralNode> {
    public final Resource resource;

    public Report(Resource resource) {
        this.resource = resource;
    }

    @Override
    public State getState(UnitNode unit, CentralNode where) {
        return new Delivery(unit, where, this);
    }

    public Resource getResource() {
        return resource;
    }
}
