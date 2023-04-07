package main.java.sample;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
        world.addDrawable(new Box(0,0,100,100,100,100));
        world.draw(graphics, screenWidth,screenHeight,100f);

    }

    public void draw(){
        world.draw(graphics, screenWidth,screenHeight,fieldOfView);
    }
}
