package am.s_mukhamedzhanov.sd.drawing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class AWTDrawer implements DrawingApi {

    int w, h;
    List<Shape> circles = new ArrayList<>();
    List<Shape> lines = new ArrayList<>();
    List<Text> texts = new ArrayList<>();
    private static class Text {
        double x, y;
        String text;

        public Text(String text, double x, double y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }

    public AWTDrawer(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public void drawCircle(double x, double y, double r) {
        circles.add(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        lines.add(new Line2D.Double(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)));
    }

    @Override
    public void drawText(String text, double x, double y) {
        Text e = new Text(text, x, y);
        texts.add(e);
    }

    @Override
    public void showResult() {
        new CustomFrame();
    }

    private class CustomFrame extends Frame {

        public CustomFrame() throws HeadlessException {
            super();
            addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosing(final WindowEvent e) {
                            System.exit(0);
                        }
                    }
            );

            setSize(w, h);
            setVisible(true);
        }

        @Override
        public void paint(final Graphics g) {
            final Graphics2D graphics2D = (Graphics2D) g;

            final Font font = getFont().deriveFont(Font.BOLD, 20);
            final FontRenderContext frc = graphics2D.getFontRenderContext();
            texts.forEach(textShape -> new TextLayout(textShape.text, font, frc)
                    .draw(graphics2D, (float) textShape.x, (float) textShape.y));
            circles.forEach(graphics2D::fill);
            lines.forEach(graphics2D::draw);

        }
    }

    @Override
    public long getDrawingAreaWidth() {
        return w;
    }

    @Override
    public long getDrawingAreaHeight() {
        return h;
    }
}
