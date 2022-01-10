package esesame2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphFactoryImpl implements GraphFactory {

    private <X> Map<X, Set<X>> graphMapper(List<X> startNodes, List<X> endNodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        Iterator<X> start = startNodes.iterator();
        Iterator<X> end = endNodes.iterator();        
        while (start.hasNext() && end.hasNext()) {
            graphMap.put(start.next(), Set.of(end.next()));
        }
        return graphMap;
    }

    @Override
    public <X> Graph<X> createDirectedChain(List<X> nodes) {
        return new GraphImpl<X>(graphMapper(nodes.subList(0, nodes.size() - 1), nodes.subList(1, nodes.size())));
    }

    @Override
    public <X> Graph<X> createBidirectionalChain(List<X> nodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        graphMap.put(nodes.get(0), Set.of(nodes.get(1)));
        for (int i = 1; i < nodes.size() - 1; i++) {
            graphMap.put(nodes.get(i), Set.of(nodes.get(i - 1), nodes.get(i + 1)));
        }
        graphMap.put(nodes.get(nodes.size() - 1), Set.of(nodes.get(nodes.size() - 2)));
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> createDirectedCircle(List<X> nodes) {
        Map<X, Set<X>> graphMap = graphMapper(nodes.subList(0, nodes.size() - 1), nodes.subList(1, nodes.size()));
        graphMap.put(nodes.get(nodes.size() - 1), Set.of(nodes.get(0)));
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> createBidirectionalCircle(List<X> nodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        graphMap.put(nodes.get(0), Set.of(nodes.get(1), nodes.get(nodes.size() - 1)));
        for (int i = 1; i < nodes.size() - 1; i++) {
            graphMap.put(nodes.get(i), Set.of(nodes.get(i - 1), nodes.get(i + 1)));
        }
        graphMap.put(nodes.get(nodes.size() - 1), Set.of(nodes.get(nodes.size() - 2), nodes.get(0)));
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> createDirectedStar(X center, Set<X> nodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        graphMap.put(center, nodes);
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> createBidirectionalStar(X center, Set<X> nodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        graphMap.put(center, nodes);
        for (X node : nodes) {
            graphMap.put(node, Set.of(center));
        }
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> createFull(Set<X> nodes) {
        Map<X, Set<X>> graphMap = new HashMap<>();
        for (X node : nodes) {
            Set<X> temp = new HashSet<X>(nodes);
            temp.remove(node);
            graphMap.put(node, temp);
        }
        return new GraphImpl<X>(graphMap);
    }

    @Override
    public <X> Graph<X> combine(Graph<X> g1, Graph<X> g2) {
        // TODO Auto-generated method stub
        return null;
    }

}
