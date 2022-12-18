package am.s_mukhamedzhanov.sd;

import am.s_mukhamedzhanov.sd.drawing.AWTDrawer;
import am.s_mukhamedzhanov.sd.drawing.DrawingApi;
import am.s_mukhamedzhanov.sd.drawing.JavaFXDrawer;
import am.s_mukhamedzhanov.sd.graph.EdgeListGraph;
import am.s_mukhamedzhanov.sd.graph.Graph;
import am.s_mukhamedzhanov.sd.graph.MatrixGraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph;
        DrawingApi drawingApi;
        if ("awt".equals(args[0])) {
            drawingApi = new AWTDrawer(600, 400);
        } else {
            drawingApi = new JavaFXDrawer(600, 400);
        }
        if ("matrix".equals(args[1])) {
            graph = readMatrixGraph(drawingApi);
        } else {
            graph = readListEdgesGraph(drawingApi);
        }
        graph.drawGraph();
    }

    private static Graph readListEdgesGraph(DrawingApi drawingApi) throws IOException {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("edgesList.txt")) {
            assert is != null;
            String content = new String(is.readAllBytes());
            List<Integer> integers = Arrays.stream(content.split("[ \r\n]+"))
                    .map(Integer::valueOf)
                    .toList();
            return new EdgeListGraph(drawingApi, integers);
        }
    }

    private static Graph readMatrixGraph(DrawingApi drawingApi) throws IOException {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("matrix.txt")) {
            assert is != null;
            String content = new String(is.readAllBytes());
            List<List<Integer>> matrix = Arrays.stream(content.split("\r\n"))
                    .map(line -> Arrays.stream(line.split(" +")).map(Integer::valueOf).toList())
                    .toList();
            return new MatrixGraph(drawingApi, matrix);
        }
    }
}