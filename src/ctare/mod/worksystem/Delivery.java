package ctare.mod.worksystem;

import ctare.mod.bagsystem.BagStates;
import ctare.mod.bagsystem.Item;
import ctare.nodes.CentralNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Nothing;
import ctare.nodes.unit.state.State;

import java.util.ArrayList;
import java.util.List;

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
//        ResourceItem resource = this.report.getResource();
        BagStates bag = this.unit.states.get(BagStates.class);
        List<ResourceItem> savedItems = new ArrayList<>();
        for (Item item : bag.items()) {
            if (item instanceof ResourceItem) {
                ResourceItem resource = (ResourceItem) item;
                this.where.states.get(StorageStates.class).storage.save(resource.getResource());
                savedItems.add(resource);
            }
        }
        savedItems.forEach(bag::remove);
        
        State.Manager.call(Delivery.class, this);
        this.unit.forceReadyFor(where, new Nothing());
    }
}
