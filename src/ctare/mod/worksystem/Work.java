package ctare.mod.worksystem;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.UnitNode;
import ctare.utils.Calc;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by ctare on 2020/05/20.
 */
public class Work extends Purpose<WorkplaceNode> {
    public static ArrayList<Predicate<UnitNode>> policy = new ArrayList<Predicate<UnitNode>>(){{
        this.add(unitNode -> Calc.bernoulli(0.01f));
    }};

    @Override
    public State getState(UnitNode unit, WorkplaceNode where) {
        return new Working(unit, (ResourceNode)where);
    }
}
