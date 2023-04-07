package main.java.sample;

public abstract class TransformationMatrix {

    public static final double STEP = 10f;
    public static final Matrix MOVE_FORWARD = new Matrix(4,4).buildMoveMatrix(0,0,-STEP);
    public static final Matrix MOVE_BACKWARD = new Matrix(4,4).buildMoveMatrix(0,0,STEP);
    public static final Matrix MOVE_LEFT = new Matrix(4,4).buildMoveMatrix(STEP,0,0);
    public static final Matrix MOVE_RIGHT = new Matrix(4,4).buildMoveMatrix(-STEP,0,0);
    public static final Matrix MOVE_UP = new Matrix(4,4).buildMoveMatrix(-0,STEP,0);
    public static final Matrix MOVE_DOWN = new Matrix(4,4).buildMoveMatrix(0,-STEP,0);

}
