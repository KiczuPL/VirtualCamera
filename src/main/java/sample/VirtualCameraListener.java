package main.java.sample;

import com.sun.webkit.ThemeClient;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

    private boolean stateChanged;

    Scene attachedScene;
    VirtualCamera camera;
    World world;

    public VirtualCameraListener(Scene scene, VirtualCamera camera, World world) {
        attachedScene = scene;
        this.camera = camera;
        this.world = world;
        scene.addEventFilter(KeyEvent.KEY_PRESSED, buildKeyPressedHandler());
        scene.addEventFilter(KeyEvent.KEY_RELEASED, buildKeyReleasedHandler());

        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                world.transform(buildTransformationMatrix());
                camera.draw();
            }
        }.start();

    }

    public Matrix buildTransformationMatrix() {
        Matrix transformation = new Matrix(4, 4);
        if (moveForward)
            transformation = transformation.multiply(TransformationMatrix.MOVE_FORWARD);
        if (moveBackward)
            transformation = transformation.multiply(TransformationMatrix.MOVE_BACKWARD);
        if (moveLeft)
            transformation = transformation.multiply(TransformationMatrix.MOVE_LEFT);
        if (moveRight)
            transformation = transformation.multiply(TransformationMatrix.MOVE_RIGHT);
        if (moveDown)
            transformation = transformation.multiply(TransformationMatrix.MOVE_DOWN);
        if (moveUp)
            transformation = transformation.multiply(TransformationMatrix.MOVE_UP);
        if (rotateUp)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_UP);
        if (rotateDown)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_DOWN);
        if (rotateLeft)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_LEFT);
        if (rotateRight)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_RIGHT);
        if (rotateClockwise)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_COUNTERCLOCKWISE);
        if (rotateCounterclockwise)
            transformation = transformation.multiply(TransformationMatrix.ROTATE_CLOCKWISE);
        if (increaseFocal)
            camera.setFieldOfView(camera.getFieldOfView() + 20d);
        if (decreaseFocal)
            camera.setFieldOfView(camera.getFieldOfView() - 20d);
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
            if (keyEvent.getCode() == KeyCode.Z)
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
            if (keyEvent.getCode() == KeyCode.Z)
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
}
