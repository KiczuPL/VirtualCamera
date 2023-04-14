package main.java.sample.world;

import main.java.sample.matrix.Matrix;
import main.java.sample.shapes.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Drawable> drawables;
    private List<Edge> edges;
    private List<Vector3f> points;
    private List<Polygon> polygons;
    private List<Triangle> triangles;

    public World() {
        drawables = new ArrayList<>();
        edges = new ArrayList<>();
        points = new ArrayList<>();
        polygons = new ArrayList<>();
        triangles = new ArrayList<>();
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        edges.addAll(drawable.getEdgeList());
        points.addAll(drawable.getPointList());
        polygons.addAll(drawable.getPolygonList());
        triangles.addAll(drawable.getTriangleList());
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

    public List<Triangle> getTriangles(){return triangles;}
}
