package ctare.core;

import ctare.nodes.VacantNode;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.states.NodeInfoStates;
import ctare.utils.SortedArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ctare on 2020/05/23.
 */
public final class NodesManager {
    private NodesManager() {}

    private static HashMap<Class<? extends WorkplaceNode>, List<? extends WorkplaceNode>> workplaces = new HashMap<>();

    static {
        NodesManager.register(VacantNode.class);
    }

    public static void register(Class<? extends WorkplaceNode> cls) {
        workplaces.put(cls, new SortedArrayList<>(Comparator.comparingInt(WorkplaceNode::getDistance)));
    }

    public static <E> List<E> get(Class<E> cls) {
        return (List<E>)workplaces.get(cls);
    }

    public static <E> List<E> getVacancy(Class<E> cls) {
        return (List<E>)workplaces.get(cls).stream()
                .filter(node -> node.member.size() < node.states.get(NodeInfoStates.class).capacity.value)
                .collect(Collectors.toList());
    }
}
