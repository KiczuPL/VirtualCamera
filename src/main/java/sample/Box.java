package main.java.sample;

import java.util.ArrayList;
import java.util.List;

public class Box implements Drawable{
    private List<Triangle> triangleList;

    public Box(double posX, double posY, double posZ, double width, double height, double depth){
        triangleList = new ArrayList<Triangle>();
        Vector3f p1 = new Vector3f(posX, posY, posZ);
        Vector3f p2 = new Vector3f(posX, posY, posZ+depth);
        Vector3f p3 = new Vector3f(posX+width, posY, posZ);
        Vector3f p4 = new Vector3f(posX+width, posY, posZ+depth);

        Vector3f p5 = new Vector3f(posX, posY+height, posZ);
        Vector3f p6 = new Vector3f(posX, posY+height, posZ+depth);
        Vector3f p7 = new Vector3f(posX+width, posY+height, posZ);
        Vector3f p8 = new Vector3f(posX+width, posY+height, posZ+depth);


        //BOTTOM
        triangleList.add(new Triangle(p1,p4,p3));
        triangleList.add(new Triangle(p2,p4,p2));

        //TOP
        //triangleList.add(new Triangle(p5,p8,p6));
        //triangleList.add(new Triangle(p5,p8,p7));

//        //LEFT
//        triangleList.add(new Triangle(p1,p6,p5));
//        triangleList.add(new Triangle(p1,p6,p2));
//
//        //RIGHT
//        triangleList.add(new Triangle(p3,p8,p4));
//        triangleList.add(new Triangle(p3,p8,p7));
//
//        //FRONT
//        triangleList.add(new Triangle(p1,p7,p3));
//        triangleList.add(new Triangle(p1,p7,p5));
//
//        //BACK
//        triangleList.add(new Triangle(p2,p8,p6));
//        triangleList.add(new Triangle(p2,p8,p4));

    }

    @Override
    public List<Triangle> getTriangleList() {
        return triangleList;
    }
}
