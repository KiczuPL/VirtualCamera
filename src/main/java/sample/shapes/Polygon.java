package main.java.sample.shapes;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Polygon {
    private Vector3f p1;
    private Vector3f p2;
    private Vector3f p3;
    private Vector3f p4;
    private Color color;

    public Polygon(Vector3f p1, Vector3f p2, Vector3f p3, Vector3f p4, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.color = color;
    }
    public Polygon(Vector3f p1, Vector3f p2, Vector3f p3, Vector3f p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        Random rand = new Random(2137L + (long)(p1.getX()*p2.getZ()) + (long)(p3.getY()*p4.getZ()) * (long)(p4.getZ()*p2.getY()));
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        this.color = Color.rgb(red,green,blue);
    }

    public Polygon projectTo2D(double screenWidth, double screenHeight, double distanceToProjectionPlane) {
        return new Polygon(
                p1.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                p2.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                p3.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                p4.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                getColor()
        );
    }
    
    public List<Triangle> triangularize(int depth){
        List<Triangle> result = new ArrayList<>();
        triangularize(p1,p2,p3,p4,depth,result);
        return result;
    }

    private void triangularize(Vector3f p1,Vector3f p2,Vector3f p3,Vector3f p4, int depth, List<Triangle> result){
        Vector3f center = p1.add(p2).add(p3).add(p4).multiply(0.25d);
        if(depth > 0){
            Vector3f p1p2 = p1.add(p2).multiply(0.5d);
            Vector3f p2p3 = p2.add(p3).multiply(0.5d);
            Vector3f p3p4 = p3.add(p4).multiply(0.5d);
            Vector3f p4p1 = p4.add(p1).multiply(0.5d);
            triangularize(p1,p1p2,center,p4p1,depth-1,result);
            triangularize(p2,p2p3,center,p1p2,depth-1,result);
            triangularize(p3,p3p4,center,p2p3,depth-1,result);
            triangularize(p4,p4p1,center, p3p4,depth-1,result);

//            triangularize(p1,p2,p2p3,p4p1,depth-1,result);
//            triangularize(p2p3,p3,p4,p4p1,depth-1,result);
        }else{
            result.add(new Triangle(p1.multiply(1d),p2.multiply(1d),p3.multiply(1d),color));
            result.add(new Triangle(p1.multiply(1d),p3.multiply(1d),p4.multiply(1d),color));
        }

    }
    

    public Vector3f getP1() {
        return p1;
    }

    public void setP1(Vector3f p1) {
        this.p1 = p1;
    }

    public Vector3f getP2() {
        return p2;
    }

    public void setP2(Vector3f p2) {
        this.p2 = p2;
    }

    public Vector3f getP3() {
        return p3;
    }

    public void setP3(Vector3f p3) {
        this.p3 = p3;
    }

    public Vector3f getP4() {
        return p4;
    }

    public void setP4(Vector3f p4) {
        this.p4 = p4;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double[] getXPoints() {
        return new double[]{p1.getX(), p2.getX(), p3.getX(), p4.getX()};
    }

    public double[] getYPoints() {
        return new double[]{p1.getY(), p2.getY(), p3.getY(), p4.getY()};
    }

    public double getBiggestPointDistance() {
        double max = p1.getDistanceFromCenter();
        double tmp = p2.getDistanceFromCenter();
        if (tmp > max)
            max = tmp;
        tmp = tmp = p3.getDistanceFromCenter();
        if (tmp > max)
            max = tmp;
        tmp = tmp = p4.getDistanceFromCenter();
        if (tmp > max)
            max = tmp;
        return max;

    }

    public double getAveragePointDistance() {
        return Math.sqrt(
                p1.getDistanceFromCenter()*p1.getDistanceFromCenter() +
                p2.getDistanceFromCenter()*p2.getDistanceFromCenter()+
                p3.getDistanceFromCenter()*p3.getDistanceFromCenter() +
                p4.getDistanceFromCenter()*p4.getDistanceFromCenter());
    }

    public double getDistanceFromWeightCenter() {
        return Math.sqrt(
                p1.add(p2).add(p3).add(p4).multiply(0.25d).getDistanceFromCenter());
    }



    public double getLowestPointDistance() {
        double min = p1.getDistanceFromCenter();
        double tmp = p2.getDistanceFromCenter();
        if (tmp < min)
            min = tmp;
        tmp = tmp = p3.getDistanceFromCenter();
        if (tmp < min)
            min = tmp;
        tmp = tmp = p4.getDistanceFromCenter();
        if (tmp < min)
            min = tmp;
        return min;
    }

    public boolean isVisible() {
        return p1.getZ() > 0 && p2.getZ() > 0 && p3.getZ() > 0 && p4.getZ() > 0;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ", color=" + color +
                '}';
    }
}
