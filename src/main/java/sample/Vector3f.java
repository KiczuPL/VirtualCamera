package main.java.sample;

import lombok.Data;

import java.util.Arrays;

@Data
public class Vector3f {
    private double[] vector;

    public Vector3f() {
        this.vector = new double[]{0f, 0f, 0f, 1.f};
    }

    public Vector3f(double x, double y, double z) {
        this.vector = new double[]{x, y, z, 1.f};
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

    public void transform(Matrix matrix){
        //System.out.println("matrix: "+matrix.toString());

        //System.out.println("Przed "+Arrays.toString(vector));
        vector=matrix.multiplyByVector(vector);
        //System.out.println("Po "+Arrays.toString(vector));
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
