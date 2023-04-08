package main.java.sample;


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

    public void transform(Matrix matrix){
        vector=matrix.multiplyByVector(vector);
    }

    public Vector3f projectTo2D(double screenWidth, double screenHeight, double fov) {
        Vector3f v = new Vector3f(
                screenWidth / 2d + (getX() * fov) / (getZ() > 0 ? getZ() : -getZ()),
                screenHeight / 2d - (getY() * fov) / (getZ()  > 0 ? getZ() : -getZ()),
                0);
        //System.out.println(v.toString());
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

    public void setWAndScale(double w){
        vector[0]= vector[0] * (w/vector[3]);
        vector[1]= vector[1] * (w/vector[3]);
        vector[2]= vector[2] * (w/vector[3]);
        setW(w);
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
