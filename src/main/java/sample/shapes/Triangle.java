package main.java.sample.shapes;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Triangle {

    private Vector3f p1;
    private Vector3f p2;
    private Vector3f p3;
    private Color color;

    private List<Edge> edges;

    public Triangle(Vector3f p1, Vector3f p2, Vector3f p3, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
        edges = new ArrayList<>();

    }

    public Triangle(Vector3f p1, Vector3f p2, Vector3f p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        Random rand = new Random(2137L + (long)(p1.getX()*p2.getZ()) + (long)(p3.getY()*p1.getZ()) * (long)(p3.getZ()*p2.getY()));
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        this.color = Color.rgb(red,green,blue);
    }

    public double getBiggestPointDistance() {
        double max = p1.getDistanceFromCenter();
        double tmp = p2.getDistanceFromCenter();
        if (tmp > max)
            max = tmp;
        tmp = tmp = p3.getDistanceFromCenter();
        if (tmp > max)
            max = tmp;
        return max;

    }

    public double getAveragePointDistance() {
        return Math.sqrt(p1.getDistanceFromCenter()*p1.getDistanceFromCenter() +
                p2.getDistanceFromCenter()*p2.getDistanceFromCenter()+
                p3.getDistanceFromCenter()*p3.getDistanceFromCenter());

    }

    public double getCenterPointDistance() {
        //return Math.sqrt(p1.add(p2).add(p3).multiply(0.3333333d).getDistanceFromCenter());
        return p1.add(p2).add(p3).multiply(1d/3d).getDistanceFromCenter();
    }

    public Triangle projectTo2D(double screenWidth, double screenHeight, double distanceToProjectionPlane) {
        return new Triangle(
                p1.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                p2.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                p3.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane),
                getColor()
        );
    }

    public boolean isVisible() {
        return p1.getZ() > 0 && p2.getZ() > 0 && p3.getZ() > 0;
    }

    public double[] getXPoints() {
        return new double[]{p1.getX(), p2.getX(), p3.getX()};
    }

    public double[] getYPoints() {
        return new double[]{p1.getY(), p2.getY(), p3.getY()};
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


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
