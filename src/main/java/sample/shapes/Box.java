package main.java.sample.shapes;

import com.sun.javafx.scene.control.TabObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box implements Drawable {
    private List<Edge> edgeList;
    private List<Vector3f> pointList;
    private List<Polygon> polygonList;
    private List<Triangle> traingleList;

    public Color genColor(long seed){
        Random rand = new Random(seed);
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        return Color.rgb(red,green,blue);
    }

    public long getSeed(double posX, double posY, double posZ){
        return (long)(posX*3+posY*5+posZ*13);
    }


    public Box(double posX, double posY, double posZ, double width, double height, double depth, Color color) {
        edgeList = new ArrayList<>();
        pointList = new ArrayList<>();
        polygonList = new ArrayList<>();
        traingleList = new ArrayList<>();
        Vector3f p1 = new Vector3f(posX, posY, posZ);
        Vector3f p2 = new Vector3f(posX + width, posY, posZ);
        Vector3f p3 = new Vector3f(posX, posY, posZ + depth);
        Vector3f p4 = new Vector3f(posX + width, posY, posZ + depth);

        Vector3f p5 = new Vector3f(posX, posY + height, posZ);
        Vector3f p6 = new Vector3f(posX + width, posY + height, posZ);
        Vector3f p7 = new Vector3f(posX, posY + height, posZ + depth);
        Vector3f p8 = new Vector3f(posX + width, posY + height, posZ + depth);

        Color c;
        //bottom
        c = genColor((long)p1.getDistanceFromCenter());
        polygonList.add(new Polygon(p1,p2,p4,p3,c));
        //top
        c = genColor((long)p2.getDistanceFromCenter());
        polygonList.add(new Polygon(p5,p7,p8,p6,c));

        //back
        c = genColor((long)p3.getDistanceFromCenter());
        polygonList.add(new Polygon(p3,p4,p8,p7,c));


        //right
        c = genColor((long)p4.getDistanceFromCenter());
        polygonList.add(new Polygon(p2,p6,p8,p4,c));

        //left
        c = genColor((long)p5.getDistanceFromCenter());
        polygonList.add(new Polygon(p1,p3,p7,p5,c));

        //front
        c = genColor((long)p8.getDistanceFromCenter());
        polygonList.add(new Polygon(p1,p5,p6,p2,c));

        for(Polygon p : polygonList){
            traingleList.addAll(p.triangularize(1));
        }

        for(Triangle t : traingleList){
            pointList.add(t.getP1());
            pointList.add(t.getP2());
            pointList.add(t.getP3());
        }




//        //bottom
//        Color c = genColor(1L);
//        traingleList.add(new Triangle(p1,p2,p3,c));
//        traingleList.add(new Triangle(p2,p4,p3,c));
//        //top
//        c = genColor(2L);
//        traingleList.add(new Triangle(p5,p7,p6,c));
//        traingleList.add(new Triangle(p6,p7,p8,c));
//        //back
//        c = genColor(3L);
//        traingleList.add(new Triangle(p3,p4,p7,c));
//        traingleList.add(new Triangle(p4,p8,p7,c));
//        //front
//        c = genColor(4L);
//        traingleList.add(new Triangle(p1,p5,p2,c));
//        traingleList.add(new Triangle(p2,p5,p6,c));
//        //right
//        c = genColor(5L);
//        traingleList.add(new Triangle(p2,p8,p4,c));
//        traingleList.add(new Triangle(p2,p6,p8,c));
//        //left
//        c = genColor(6L);
//        traingleList.add(new Triangle(p1,p3,p7,c));
//        traingleList.add(new Triangle(p1,p7,p5,c));







        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p5);
        pointList.add(p6);
        pointList.add(p7);
        pointList.add(p8);

        //bottom
        edgeList.add(new Edge(p1, p2, color));
        edgeList.add(new Edge(p2, p4, color));
        edgeList.add(new Edge(p4, p3, color));
        edgeList.add(new Edge(p3, p1, color));
        //top
        edgeList.add(new Edge(p5, p6, color));
        edgeList.add(new Edge(p6, p8, color));
        edgeList.add(new Edge(p8, p7, color));
        edgeList.add(new Edge(p7, p5, color));
        //back
        edgeList.add(new Edge(p3, p4, color));
        edgeList.add(new Edge(p4, p8, color));
        edgeList.add(new Edge(p8, p7, color));
        edgeList.add(new Edge(p7, p3, color));

        //right
        edgeList.add(new Edge(p2, p4, color));
        edgeList.add(new Edge(p4, p8, color));
        edgeList.add(new Edge(p8, p6, color));
        edgeList.add(new Edge(p6, p2, color));

        //left
        edgeList.add(new Edge(p1, p3, color));
        edgeList.add(new Edge(p3, p7, color));
        edgeList.add(new Edge(p7, p5, color));
        edgeList.add(new Edge(p5, p1, color));




    }


    @Override
    public List<Edge> getEdgeList() {
        return edgeList;
    }

    @Override
    public List<Vector3f> getPointList() {
        return pointList;
    }

    @Override
    public List<Polygon> getPolygonList() {
        return polygonList;
    }

    @Override
    public List<Triangle> getTriangleList() {
        return traingleList;
    }
}
