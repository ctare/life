package ctare.mod.worksystem;

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
        this.where.states.get(StorageStates.class).storage.save(this.report.getResource());
        State.Manager.call(Delivery.class, this);
        this.unit.readyFor(where, new Nothing());
    }
}
