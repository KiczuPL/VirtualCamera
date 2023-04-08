package main.java.sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VirtualCamera extends Canvas {

    private GraphicsContext graphics;
    //private VirtualCameraController controller;
    private World world;
    private int screenWidth, screenHeight;
    private double fieldOfView;

    public VirtualCamera(World world, int screenWidth,int screenHeight, double fieldOfView){
        super(screenWidth, screenHeight);
        this.fieldOfView=fieldOfView;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        graphics = getGraphicsContext2D();
        this.world=world;
        //controller = new VirtualCameraController(world,this);
//        world.addTriangle(new Triangle(
//                new Vector3f(100f,100f,100f),
//                new Vector3f(200f,100f,100f),
//                new Vector3f(100f,200f,100f)
//        ));
        world.addDrawable(new Box(0,0,500,300,300,300, Color.RED));
        world.addDrawable(new Box(400,0,500,300,300,300, Color.YELLOW));
        world.addDrawable(new Box(0,0,900,300,300,300, Color.GREEN));
        world.addDrawable(new Box(400,0,900,300,300,300, Color.BLUE));

        world.addDrawable(new Box(0,400,500,300,300,300, Color.WHITE));
        world.addDrawable(new Box(400,400,500,300,300,300, Color.DEEPPINK));
        world.addDrawable(new Box(0,400,900,300,300,300, Color.SANDYBROWN));
        world.addDrawable(new Box(400,400,900,300,300,300, Color.CYAN));



        world.draw(graphics, screenWidth,screenHeight,fieldOfView);

    }

    public void draw(){
        world.draw(graphics, screenWidth,screenHeight,fieldOfView);
    }


    public double getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(double fieldOfView) {
        this.fieldOfView = fieldOfView;
    }
}
