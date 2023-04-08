package main.java.sample;

public abstract class TransformationMatrix {

    public static final double STEP = 10f;
    public static final double ROTATION_STEP = Math.PI/100d;

    public static final Matrix MOVE_FORWARD = new Matrix(4,4).buildMoveMatrix(0,0,-STEP);
    public static final Matrix MOVE_BACKWARD = new Matrix(4,4).buildMoveMatrix(0,0,STEP);

    public static final Matrix MOVE_LEFT = new Matrix(4,4).buildMoveMatrix(STEP,0,0);
    public static final Matrix MOVE_RIGHT = new Matrix(4,4).buildMoveMatrix(-STEP,0,0);

    public static final Matrix MOVE_UP = new Matrix(4,4).buildMoveMatrix(0,-STEP,0);
    public static final Matrix MOVE_DOWN = new Matrix(4,4).buildMoveMatrix(0,STEP,0);

    public static final Matrix ROTATE_UP = new Matrix(4,4).buildRotationMatrix(ROTATION_STEP,RotationType.X_AXIS);
    public static final Matrix ROTATE_DOWN = new Matrix(4,4).buildRotationMatrix(-ROTATION_STEP,RotationType.X_AXIS);

    public static final Matrix ROTATE_LEFT = new Matrix(4,4).buildRotationMatrix(ROTATION_STEP,RotationType.Y_AXIS);
    public static final Matrix ROTATE_RIGHT = new Matrix(4,4).buildRotationMatrix(-ROTATION_STEP,RotationType.Y_AXIS);

    public static final Matrix ROTATE_CLOCKWISE = new Matrix(4,4).buildRotationMatrix(-ROTATION_STEP,RotationType.Z_AXIS);
    public static final Matrix ROTATE_COUNTERCLOCKWISE = new Matrix(4,4).buildRotationMatrix(ROTATION_STEP,RotationType.Z_AXIS);



}
