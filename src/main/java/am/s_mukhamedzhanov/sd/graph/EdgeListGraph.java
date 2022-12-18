package am.s_mukhamedzhanov.sd.graph;

import am.s_mukhamedzhanov.sd.drawing.DrawingApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EdgeListGraph extends Graph {

    int size;

    List<Edge> edges;

    public EdgeListGraph(DrawingApi drawingApi, List<Integer> graph) {
        super(drawingApi);
        edges = new ArrayList<>();
        Set<Integer> verts = new HashSet<>();
        for (int i = 0; i < graph.size(); i += 2) {
            int v = graph.get(i);
            int u = graph.get(i + 1);

            verts.addAll(List.of(v, u));
            edges.add(new Edge(v, u));
        }
        size = verts.size();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }
}
