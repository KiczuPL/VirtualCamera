package main.java.sample.matrix;

public class TransformationMatrix {

    public double STEP = 30f;
    public double ROTATION_STEP = Math.PI / 100d;

    public Matrix MOVE_FORWARD = new Matrix(4, 4).buildMoveMatrix(0, 0, -STEP);
    public Matrix MOVE_BACKWARD = new Matrix(4, 4).buildMoveMatrix(0, 0, STEP);

    public Matrix MOVE_LEFT = new Matrix(4, 4).buildMoveMatrix(STEP, 0, 0);
    public Matrix MOVE_RIGHT = new Matrix(4, 4).buildMoveMatrix(-STEP, 0, 0);

    public Matrix MOVE_UP = new Matrix(4, 4).buildMoveMatrix(0, -STEP, 0);
    public Matrix MOVE_DOWN = new Matrix(4, 4).buildMoveMatrix(0, STEP, 0);

    public Matrix ROTATE_UP = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.X_AXIS);
    public Matrix ROTATE_DOWN = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.X_AXIS);

    public Matrix ROTATE_LEFT = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.Y_AXIS);
    public Matrix ROTATE_RIGHT = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.Y_AXIS);

    public Matrix ROTATE_CLOCKWISE = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.Z_AXIS);
    public Matrix ROTATE_COUNTERCLOCKWISE = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.Z_AXIS);

    public void rescaleSteps(double newFocal) {
        newFocal+=1d;
        STEP = (5000d/newFocal);
        ROTATION_STEP = Math.PI / 100d /newFocal*1500d;
        System.out.println(newFocal);
        System.out.println(STEP);


        MOVE_FORWARD = new Matrix(4, 4).buildMoveMatrix(0, 0, -STEP);
        MOVE_BACKWARD = new Matrix(4, 4).buildMoveMatrix(0, 0, STEP);
        MOVE_LEFT = new Matrix(4, 4).buildMoveMatrix(STEP, 0, 0);
        MOVE_RIGHT = new Matrix(4, 4).buildMoveMatrix(-STEP, 0, 0);
        MOVE_UP = new Matrix(4, 4).buildMoveMatrix(0, -STEP, 0);
        MOVE_DOWN = new Matrix(4, 4).buildMoveMatrix(0, STEP, 0);
        ROTATE_UP = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.X_AXIS);
        ROTATE_DOWN = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.X_AXIS);
        ROTATE_LEFT = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.Y_AXIS);
        ROTATE_RIGHT = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.Y_AXIS);
        ROTATE_CLOCKWISE = new Matrix(4, 4).buildRotationMatrix(-ROTATION_STEP, RotationType.Z_AXIS);
        ROTATE_COUNTERCLOCKWISE = new Matrix(4, 4).buildRotationMatrix(ROTATION_STEP, RotationType.Z_AXIS);

    }


}
