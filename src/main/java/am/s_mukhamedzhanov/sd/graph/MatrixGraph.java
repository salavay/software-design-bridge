package am.s_mukhamedzhanov.sd.graph;

import am.s_mukhamedzhanov.sd.drawing.DrawingApi;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph extends Graph {

    int size;

    List<Edge> edges;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    public MatrixGraph(DrawingApi drawingApi, List<List<Integer>> matrix) {
        super(drawingApi);
        size = matrix.size();
        edges = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i + 1; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    edges.add(new Edge(i, j));
                }
            }
        }
    }
}
