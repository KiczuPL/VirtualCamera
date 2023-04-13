package main.java.sample.shapes;


import main.java.sample.matrix.Matrix;

import java.util.Arrays;

public class Vector3f {
    private double[] vector;

    public Vector3f() {
        this.vector = new double[]{0f, 0f, 0f, 1.f};
    }

    public Vector3f(double x, double y, double z) {
        this.vector = new double[]{x, y, z, 1.f};
    }

    public Vector3f(double[] vector) {
        this.vector = vector;
    }

    public double getDistanceFromCenter(){
        return Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ());
    }

    public void transform(Matrix matrix) {
        vector = matrix.multiplyByVector(vector);
    }

    public Vector3f projectTo2D(double screenWidth, double screenHeight, double distanceToProjectionPlane) {
        Vector3f v = new Vector3f(
                screenWidth / 2d + (getX() * distanceToProjectionPlane) / getZ(),
                screenHeight / 2d - (getY() * distanceToProjectionPlane) / getZ(),
                getZ());
        return v;
    }

    public double getX() {
        return vector[0];
    }

    public double getY() {
        return vector[1];
    }

    public double getZ() {
        return vector[2];
    }

    public double getW() {
        return vector[3];
    }

    public void setX(double x) {
        vector[0] = x;
    }

    public void setY(double y) {
        vector[1] = y;
    }

    public void setZ(double z) {
        vector[2] = z;
    }

    public void setW(double w) {
        vector[3] = w;
    }

    public void setWAndScale(double w) {
        vector[0] = vector[0] * (w / vector[3]);
        vector[1] = vector[1] * (w / vector[3]);
        vector[2] = vector[2] * (w / vector[3]);
        setW(w);
    }

    public Vector3f normalise() {
        vector[0] /= vector[3];
        vector[1] /= vector[3];
        vector[2] /= vector[3];
        vector[3] = 1d;
        return this;
    }


    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
