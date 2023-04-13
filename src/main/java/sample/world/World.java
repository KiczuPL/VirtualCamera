package main.java.sample.world;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import main.java.sample.matrix.Matrix;
import main.java.sample.shapes.Drawable;
import main.java.sample.shapes.Edge;
import main.java.sample.shapes.Polygon;
import main.java.sample.shapes.Vector3f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private List<Drawable> drawables;
    private List<Edge> edges;
    private List<Vector3f> points;
    private List<Polygon> polygons;

    public World() {
        drawables = new ArrayList<>();
        edges = new ArrayList<>();
        points = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        edges.addAll(drawable.getEdgeList());
        points.addAll(drawable.getPointList());
        polygons.addAll(drawable.getPolygonList());
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void transform(Matrix matrix) {
        for( Vector3f point : points){
            point.transform(matrix);
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
