package main.java.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private List<Drawable> drawables;
    List<Edge> edges;
    List<Vector3f> points;

    public World() {
        drawables = new ArrayList<>();
        edges = new ArrayList<>();
        points = new ArrayList<>();
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        edges.addAll(drawable.getEdgeList());
        points.addAll(drawable.getPointList());
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void transform(Matrix matrix) {
//        for (Edge edge : edges) {
//            edge.transform(matrix);
//        }
        for( Vector3f point : points){
            point.transform(matrix);
        }
    }


    public void draw(final GraphicsContext graphics, final double screenWidth, final double screenHeight, final double fieldOfView) {
        graphics.setStroke(Paint.valueOf("BLACK"));
        graphics.fillRect(0, 0, screenWidth, screenHeight);

        List<Edge> projectedTriangles = edges.stream().map((edge) -> edge.projectTo2D(screenWidth, screenHeight, fieldOfView)).collect(Collectors.toList());
        projectedTriangles.forEach(triangle -> {
            graphics.setStroke(triangle.getColor());
            graphics.strokeLine(triangle.getStart().getX(), triangle.getStart().getY() , triangle.getEnd().getX() , triangle.getEnd().getY());
        });
    }
}
