package main.java.sample;

public class Matrix {
    private int rows;
    private int cols;
    private double[] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows * cols];
        if (rows == cols)
            for (int i = 0; i < rows; i++) {
                matrix[i * rows + i] = 1f;
            }
    }

    public Matrix buildMoveMatrix(double dx, double dy, double dz) {
        set(0, 3, dx);
        set(1, 3, dy);
        set(2, 3, dz);
        return this;
    }

    public Matrix buildRotationMatrix(double dx, double dy, double dz) {
        set(0, 3, dx);
        set(1, 3, dy);
        set(2, 3, dz);
        return this;
    }

    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix(rows, matrix.cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < matrix.cols; c++) {
                float s = 0;
                for (int k = 0; k < cols; k++) {
                    s += get(r, k) * matrix.get(k, c);
                }
                result.set(r, c, s);
            }
        }

        return result;


    }


    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result.append(matrix[row * rows + col]).append(" ");
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void set(int row, int column, double x) {
        matrix[row * rows + column] = x;
    }

    public double get(int row, int col){
        return matrix[row * rows + col];
    }

    public double[] multiplyByVector(double[] v) {

        double[] result = new double[v.length];
        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int col = 0; col < cols; col++) {
                sum += matrix[row * rows + col] * v[col];
            }
            result[row] = sum;
        }
        return result;
    }

}
