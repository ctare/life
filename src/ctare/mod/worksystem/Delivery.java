package ctare.mod.worksystem;

import ctare.mod.bagsystem.BagStates;
import ctare.mod.worksystem.resource.Resource;
import ctare.nodes.CentralNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Nothing;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/20.
 */
public class Delivery extends State<CentralNode> {
    private Report report;
    public Delivery(UnitNode unit, CentralNode where, Report report) {
        super(unit, where);
        this.report = report;
    }

    @Override
    public void update() {
        Resource resource = this.report.getResource();
        this.where.states.get(StorageStates.class).storage.save(resource);
        this.unit.states.get(BagStates.class).remove(resource);
        State.Manager.call(Delivery.class, this);
        this.unit.forceReadyFor(where, new Nothing());
    }
}
