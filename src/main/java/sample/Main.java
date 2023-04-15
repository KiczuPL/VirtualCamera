package main.java.sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.sample.world.VirtualCamera;
import main.java.sample.world.World;

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
        scene.addEventFilter(KeyEvent.KEY_PRESSED, camera.getKeyPressedHandler());
        scene.addEventFilter(KeyEvent.KEY_RELEASED, camera.getKeyReleasedHandler());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
