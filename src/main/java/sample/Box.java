package main.java.sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Box implements Drawable{
    private List<Edge> edgeList;
    private List<Vector3f> pointList;

    public Box(double posX, double posY, double posZ, double width, double height, double depth, Color color){
      edgeList=new ArrayList<>();
      pointList = new ArrayList<>();
        Vector3f p1 = new Vector3f(posX,posY,posZ);
        Vector3f p2 = new Vector3f(posX+width,posY,posZ);
        Vector3f p3 = new Vector3f(posX,posY,posZ+depth);
        Vector3f p4 = new Vector3f(posX+width,posY,posZ+depth);

        Vector3f p5 = new Vector3f(posX,posY+height,posZ);
        Vector3f p6 = new Vector3f(posX+width,posY+height,posZ);
        Vector3f p7 = new Vector3f(posX,posY+height,posZ+depth);
        Vector3f p8 = new Vector3f(posX+width,posY+height,posZ+depth);

        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p5);
        pointList.add(p6);
        pointList.add(p7);
        pointList.add(p8);

        //bottom
        edgeList.add(new Edge(p1,p2,color));
        edgeList.add(new Edge(p2,p4,color));
        edgeList.add(new Edge(p4,p3,color));
        edgeList.add(new Edge(p3,p1,color));
        //top
        edgeList.add(new Edge(p5,p6,color));
        edgeList.add(new Edge(p6,p8,color));
        edgeList.add(new Edge(p8,p7,color));
        edgeList.add(new Edge(p7,p5,color));
        //back
        edgeList.add(new Edge(p3,p4,color));
        edgeList.add(new Edge(p4,p8,color));
        edgeList.add(new Edge(p8,p7,color));
        edgeList.add(new Edge(p7,p3,color));

        //right
        edgeList.add(new Edge(p2,p4,color));
        edgeList.add(new Edge(p4,p8,color));
        edgeList.add(new Edge(p8,p6,color));
        edgeList.add(new Edge(p6,p2,color));

        //left
        edgeList.add(new Edge(p1,p3,color));
        edgeList.add(new Edge(p3,p7,color));
        edgeList.add(new Edge(p7,p5,color));
        edgeList.add(new Edge(p5,p1,color));
    }


    @Override
    public List<Edge> getEdgeList() {
        return edgeList;
    }

    @Override
    public List<Vector3f> getPointList() {
        return pointList;
    }
}
