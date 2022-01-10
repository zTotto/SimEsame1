package esesame2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphImpl<X> implements Graph<X> {

    private final Map<X,Set<X>> nodes;

    protected GraphImpl(Map<X,Set<X>> nodeMap) {
        this.nodes = nodeMap;
    }

    @Override
    public Set<X> getNodes() {
        var temp = new HashSet<X>();
        nodes.values().forEach(e -> e.forEach(i -> temp.add(i)));
        return ((Stream<X>) Stream.concat(nodes.keySet().stream(), temp.stream())).collect(Collectors.toSet());
    }

    @Override
    public boolean edgePresent(X start, X end) {
        if (nodes.containsKey(start) && nodes.values().stream().anyMatch(set -> set.contains(end))) {
            if (nodes.get(start).contains(end)) {
                return true;
            } else {
                for (X node : nodes.get(start)) {
                    if (edgePresent(node, end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getEdgesCount() {
        var temp = new LinkedList<X>();
        nodes.values().forEach(c -> c.forEach(i -> temp.add(i)));
        System.out.println("Temp = " + temp);
        return (int) temp.stream().count();
    }

    @Override
    public Stream<Pair<X, X>> getEdgesStream() {
        return null;
    }

}
