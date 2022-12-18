package am.s_mukhamedzhanov.sd.graph;

import am.s_mukhamedzhanov.sd.drawing.DrawingApi;

import java.util.List;

public abstract class Graph {

    public abstract int getSize();

    public abstract List<Edge> getEdges();

    private final DrawingApi drawingApi;

    protected Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    private static class JavaHasNoPair {
        public double x;
        public double y;

        public JavaHasNoPair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private JavaHasNoPair getCoordForVert(int id) {
        double ang = Math.PI * 2 * id / (double) getSize();
        double centerX = drawingApi.getDrawingAreaWidth() / 2d;
        double centerY = drawingApi.getDrawingAreaHeight() / 2d;
        double r = drawingApi.getDrawingAreaWidth() / 4d;


        return new JavaHasNoPair(
                centerX + r * Math.cos(ang),
                centerY + r * Math.sin(ang));
    }

    public void drawGraph() {
        for (int i = 0; i < getSize(); i++) {
            JavaHasNoPair coordForVert = getCoordForVert(i);
            drawingApi.drawCircle(coordForVert.x, coordForVert.y,  5);
            drawingApi.drawText(Integer.toString(i), coordForVert.x, coordForVert.y + 10);
        }
        for (Edge edge : getEdges()) {
            JavaHasNoPair v = getCoordForVert(edge.from);
            JavaHasNoPair u = getCoordForVert(edge.to);
            drawingApi.drawLine(v.x, v.y, u.x, u.y);
        }

        drawingApi.showResult();
    }
}
