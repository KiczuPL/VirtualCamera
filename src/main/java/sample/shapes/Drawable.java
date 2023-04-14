package main.java.sample.shapes;

import java.util.List;

public interface Drawable {

    List<Edge> getEdgeList();
    List<Vector3f> getPointList();
    List<Polygon> getPolygonList();
    List<Triangle> getTriangleList();
}
