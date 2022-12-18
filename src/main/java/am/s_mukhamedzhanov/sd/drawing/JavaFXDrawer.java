package am.s_mukhamedzhanov.sd.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JavaFXDrawer implements DrawingApi {

    private final List<Consumer<GraphicsContext>> tasks = new ArrayList<>();
    private static Canvas canvas;

    public JavaFXDrawer(int w, int h) {
        canvas = new Canvas(w, h);
    }

    @Override
    public void drawCircle(double x, double y, double r) {
        tasks.add(c -> c.fillOval(x - r, y - r, 2 * r, 2 * r));
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        tasks.add(c -> c.strokeLine(x1, y1, x2, y2));
    }

    @Override
    public void drawText(String text, double x, double y) {
        tasks.add(c -> c.strokeText(text, x, y));
    }

    @Override
    public void showResult() {
        tasks.forEach(t -> t.accept(canvas.getGraphicsContext2D()));
        JavaFXApp.launch(JavaFXApp.class);
    }

    public static class JavaFXApp extends Application {
        @Override
        public void start(Stage stage) {
            final Group group = new Group();
            group.getChildren().add(canvas);
            stage.setScene(new Scene(group, Color.WHITE));
            stage.show();
            stage.setOnCloseRequest(windowEvent -> System.exit(0));
        }
    }



    @Override
    public long getDrawingAreaWidth() {
        return (long) canvas.getWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return (long) canvas.getHeight();
    }
}
