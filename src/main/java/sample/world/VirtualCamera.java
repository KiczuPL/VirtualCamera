package main.java.sample.world;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import main.java.sample.input.VirtualCameraListener;
import main.java.sample.shapes.Box;

public class VirtualCamera extends Canvas {

    private GraphicsContext graphics;
    private VirtualCameraListener listener;
    private World world;
    private int screenWidth, screenHeight;
    private double distanceToProjectionPlane;

    public VirtualCamera(World world, int screenWidth,int screenHeight, double distanceToProjectionPlane){
        super(screenWidth, screenHeight);
        this.distanceToProjectionPlane=distanceToProjectionPlane;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        graphics = getGraphicsContext2D();
        this.world=world;
        graphics.setImageSmoothing(false);

        world.addDrawable(new Box(0,0,500,300,300,300, Color.RED));
        world.addDrawable(new Box(400,0,500,300,300,300, Color.YELLOW));
        world.addDrawable(new Box(0,0,900,300,300,300, Color.GREEN));
        world.addDrawable(new Box(400,0,900,300,300,300, Color.BLUE));

        world.addDrawable(new Box(0,400,500,300,300,300, Color.WHITE));
        world.addDrawable(new Box(400,400,500,300,300,300, Color.DEEPPINK));
        world.addDrawable(new Box(0,400,900,300,300,300, Color.SANDYBROWN));
        world.addDrawable(new Box(400,400,900,300,300,300, Color.CYAN));


        listener = new VirtualCameraListener(this,world);
        world.draw(graphics, screenWidth,screenHeight,distanceToProjectionPlane);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                world.transform(listener.buildTransformationMatrix());
                draw();
            }
        }.start();
    }

    public void draw(){
        world.draw(graphics, screenWidth,screenHeight,distanceToProjectionPlane);
    }


    public double getDistanceToProjectionPlane() {
        return distanceToProjectionPlane;
    }

    public void setDistanceToProjectionPlane(double distanceToProjectionPlane) {
        this.distanceToProjectionPlane = distanceToProjectionPlane;
    }

    public EventHandler<KeyEvent> getKeyPressedHandler() {
        return listener.getKeyPressedHandler();
    }

    public EventHandler<KeyEvent> getKeyReleasedHandler() {
        return listener.getKeyReleasedHandler();
    }
}
