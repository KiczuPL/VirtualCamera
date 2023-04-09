package main.java.sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private final int screenWidth = 1280;
    private final int screenHeight = 720;
    private double fieldOfView = 500d;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Virtual camera");
        World world = new World();
        VirtualCamera camera = new VirtualCamera(world, screenWidth, screenHeight, fieldOfView);
        Group group = new Group(camera);
        Scene scene = new Scene(group, screenWidth, screenHeight);
        //world.transform(new Matrix(4,4).buildMoveMatrix(screenWidth/2,screenHeight/2,0));
        primaryStage.setScene(scene);
        primaryStage.show();


        VirtualCameraListener listener = new VirtualCameraListener(scene,camera,world);

//        scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
//            //scene.addEventHandler(KeyEvent.KEY_PRESSED,(keyEvent -> {
//            //keyEvent.consume();
//            Matrix transformation = new Matrix(4, 4);
//            if (keyEvent.getCode() == KeyCode.W)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_FORWARD);
//            if (keyEvent.getCode() == KeyCode.S)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_BACKWARD);
//            if (keyEvent.getCode() == KeyCode.A)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_LEFT);
//            if (keyEvent.getCode() == KeyCode.D)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_RIGHT);
//            if (keyEvent.getCode() == KeyCode.Z)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_DOWN);
//            if (keyEvent.getCode() == KeyCode.SPACE)
//                transformation = transformation.multiply(TransformationMatrix.MOVE_UP);
//            if (keyEvent.getCode() == KeyCode.UP)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_UP);
//            if (keyEvent.getCode() == KeyCode.DOWN)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_DOWN);
//            if (keyEvent.getCode() == KeyCode.LEFT)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_LEFT);
//            if (keyEvent.getCode() == KeyCode.RIGHT)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_RIGHT);
//            if (keyEvent.getCode() == KeyCode.E)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_COUNTERCLOCKWISE);
//            if (keyEvent.getCode() == KeyCode.Q)
//                transformation = transformation.multiply(TransformationMatrix.ROTATE_CLOCKWISE);
//
//            if (keyEvent.getCode() == KeyCode.EQUALS)
//            {
//                camera.setFieldOfView(camera.getFieldOfView()+20d);
//            }
//            if (keyEvent.getCode() == KeyCode.MINUS)
//            {
//                camera.setFieldOfView(camera.getFieldOfView()-20d);
//            }
//
//
//            world.transform(transformation);
//            camera.draw();
//        }));


    }


    public static void main(String[] args) {
        launch(args);
    }
}
