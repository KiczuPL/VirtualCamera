package main.java.sample.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.sample.matrix.Matrix;
import main.java.sample.matrix.TransformationMatrix;
import main.java.sample.world.DisplayMode;
import main.java.sample.world.VirtualCamera;
import main.java.sample.world.World;

public class VirtualCameraListener {
    private boolean moveForward;
    private boolean moveBackward;
    private boolean moveLeft;
    private boolean moveRight;

    private boolean moveDown;
    private boolean moveUp;

    private boolean rotateUp;
    private boolean rotateDown;
    private boolean rotateLeft;
    private boolean rotateRight;

    private boolean rotateClockwise;
    private boolean rotateCounterclockwise;
    private boolean increaseFocal;
    private boolean decreaseFocal;

    VirtualCamera camera;
    World world;

    private EventHandler<KeyEvent> keyPressedHandler;
    private EventHandler<KeyEvent> keyReleasedHandler;
    TransformationMatrix transformationMatrix;

    public VirtualCameraListener( VirtualCamera camera, World world) {
        this.camera = camera;
        this.world = world;
        transformationMatrix = new TransformationMatrix();
        keyPressedHandler=buildKeyPressedHandler();
        keyReleasedHandler=buildKeyReleasedHandler();
    }

    public Matrix buildTransformationMatrix() {
        Matrix transformation = new Matrix(4, 4);
        if (moveForward)
            transformation = transformation.multiply(transformationMatrix.MOVE_FORWARD);
        if (moveBackward)
            transformation = transformation.multiply(transformationMatrix.MOVE_BACKWARD);
        if (moveLeft)
            transformation = transformation.multiply(transformationMatrix.MOVE_LEFT);
        if (moveRight)
            transformation = transformation.multiply(transformationMatrix.MOVE_RIGHT);
        if (moveDown)
            transformation = transformation.multiply(transformationMatrix.MOVE_DOWN);
        if (moveUp)
            transformation = transformation.multiply(transformationMatrix.MOVE_UP);
        if (rotateUp)
            transformation = transformation.multiply(transformationMatrix.ROTATE_UP);
        if (rotateDown)
            transformation = transformation.multiply(transformationMatrix.ROTATE_DOWN);
        if (rotateLeft)
            transformation = transformation.multiply(transformationMatrix.ROTATE_LEFT);
        if (rotateRight)
            transformation = transformation.multiply(transformationMatrix.ROTATE_RIGHT);
        if (rotateClockwise)
            transformation = transformation.multiply(transformationMatrix.ROTATE_COUNTERCLOCKWISE);
        if (rotateCounterclockwise)
            transformation = transformation.multiply(transformationMatrix.ROTATE_CLOCKWISE);
        if (increaseFocal){
            double newFocal = camera.getDistanceToProjectionPlane() * 1.05d;
            camera.setDistanceToProjectionPlane(newFocal);
            //transformationMatrix.rescaleSteps(newFocal);
        }

        if (decreaseFocal){
            double newFocal = camera.getDistanceToProjectionPlane() / 1.05d;
            camera.setDistanceToProjectionPlane(newFocal);
            //transformationMatrix.rescaleSteps(newFocal);
        }
        return transformation;
    }

    private EventHandler<KeyEvent> buildKeyPressedHandler() {
        return (keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.W)
                moveForward = true;
            if (keyEvent.getCode() == KeyCode.S)
                moveBackward = true;
            if (keyEvent.getCode() == KeyCode.A)
                moveLeft = true;
            if (keyEvent.getCode() == KeyCode.D)
                moveRight = true;
            if (keyEvent.getCode() == KeyCode.SHIFT)
                moveDown = true;
            if (keyEvent.getCode() == KeyCode.SPACE)
                moveUp = true;
            if (keyEvent.getCode() == KeyCode.UP)
                rotateUp = true;
            if (keyEvent.getCode() == KeyCode.DOWN)
                rotateDown = true;
            if (keyEvent.getCode() == KeyCode.LEFT)
                rotateLeft = true;
            if (keyEvent.getCode() == KeyCode.RIGHT)
                rotateRight = true;
            if (keyEvent.getCode() == KeyCode.E)
                rotateClockwise = true;
            if (keyEvent.getCode() == KeyCode.Q)
                rotateCounterclockwise = true;
            if (keyEvent.getCode() == KeyCode.EQUALS)
                increaseFocal = true;
            if (keyEvent.getCode() == KeyCode.MINUS)
                decreaseFocal = true;
            if(keyEvent.getCode()==KeyCode.OPEN_BRACKET)
                camera.setDisplayMode(DisplayMode.WALLS);
            if(keyEvent.getCode()==KeyCode.CLOSE_BRACKET)
                camera.setDisplayMode(DisplayMode.WIREFRAME);
        };
    }

    private EventHandler<KeyEvent> buildKeyReleasedHandler() {
        return (keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.W)
                moveForward = false;
            if (keyEvent.getCode() == KeyCode.S)
                moveBackward = false;
            if (keyEvent.getCode() == KeyCode.A)
                moveLeft = false;
            if (keyEvent.getCode() == KeyCode.D)
                moveRight = false;
            if (keyEvent.getCode() == KeyCode.SHIFT)
                moveDown = false;
            if (keyEvent.getCode() == KeyCode.SPACE)
                moveUp = false;
            if (keyEvent.getCode() == KeyCode.UP)
                rotateUp = false;
            if (keyEvent.getCode() == KeyCode.DOWN)
                rotateDown = false;
            if (keyEvent.getCode() == KeyCode.LEFT)
                rotateLeft = false;
            if (keyEvent.getCode() == KeyCode.RIGHT)
                rotateRight = false;
            if (keyEvent.getCode() == KeyCode.E)
                rotateClockwise = false;
            if (keyEvent.getCode() == KeyCode.Q)
                rotateCounterclockwise = false;
            if (keyEvent.getCode() == KeyCode.EQUALS)
                increaseFocal = false;
            if (keyEvent.getCode() == KeyCode.MINUS)
                decreaseFocal = false;
        };
    }

    public EventHandler<KeyEvent> getKeyPressedHandler() {
        return keyPressedHandler;
    }

    public EventHandler<KeyEvent> getKeyReleasedHandler() {
        return keyReleasedHandler;
    }
}
