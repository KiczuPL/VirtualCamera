package main.java.sample.world;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.java.sample.input.VirtualCameraListener;
import main.java.sample.shapes.Box;
import main.java.sample.shapes.Edge;
import main.java.sample.shapes.Polygon;
import main.java.sample.shapes.Triangle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VirtualCamera extends Canvas {

    private GraphicsContext graphics;
    private VirtualCameraListener listener;
    private World world;
    private int screenWidth, screenHeight;
    private double distanceToProjectionPlane;

    private DisplayMode displayMode;

    public VirtualCamera(World world, int screenWidth,int screenHeight, double distanceToProjectionPlane){
        super(screenWidth, screenHeight);
        this.distanceToProjectionPlane=distanceToProjectionPlane;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        graphics = getGraphicsContext2D();
        this.world=world;
        graphics.setImageSmoothing(false);
        displayMode=DisplayMode.WALLS;

        double boxDepth = 300;
        double boxWidth = 700;
        double boxWidthSpacing=100;
        world.addDrawable(new Box(0,0,500,boxWidth,300,boxDepth, Color.RED));
        world.addDrawable(new Box(boxWidth+boxWidthSpacing,0,500,boxWidth,300,boxDepth, Color.YELLOW));
        world.addDrawable(new Box(0,0,900,boxWidth,300,boxDepth, Color.GREEN));
        world.addDrawable(new Box(boxWidth+boxWidthSpacing,0,900,boxWidth,300,boxDepth, Color.BLUE));

        world.addDrawable(new Box(0,400,500,boxWidth,300,boxDepth, Color.WHITE));
        world.addDrawable(new Box(boxWidth+boxWidthSpacing,400,500,boxWidth,300,boxDepth, Color.DEEPPINK));
        world.addDrawable(new Box(0,400,900,boxWidth,300,boxDepth, Color.SANDYBROWN));
        world.addDrawable(new Box(boxWidth+boxWidthSpacing,400,900,boxWidth,300,300, Color.CYAN));


        listener = new VirtualCameraListener(this,world);
        draw();
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double start = System.nanoTime();
                world.transform(listener.buildTransformationMatrix());
                double transformingTime = (System.nanoTime() - start)/ 1000000000000.0;
                System.out.println("Transforming: "+transformingTime);
                draw();
                double drawingTime = (System.nanoTime() - transformingTime)/ 1000000000000.0;
                System.out.println("Drawing: "+drawingTime);
            }
        }.start();
    }

    public void draw(){
        switch (displayMode){
            case WALLS -> drawWalls();
            case WIREFRAME -> drawWireframes();
        }
    }

    public void drawWireframes(){
        graphics.setFill(Paint.valueOf("BLACK"));
        graphics.fillRect(0, 0, screenWidth, screenHeight);

        List<Edge> projectedTriangles = world.getEdges().stream()
                .map((edge) -> edge.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane))
                .filter(Edge::isVisible)
                .sorted(Comparator.comparingDouble(e -> -(e.getStart().getZ() + e.getEnd().getZ()) / 2))
                .collect(Collectors.toList());


        projectedTriangles.forEach(triangle -> {
            graphics.setStroke(triangle.getColor());
            graphics.strokeLine(triangle.getStart().getX(), triangle.getStart().getY() , triangle.getEnd().getX() , triangle.getEnd().getY());
        });
    }

    public void drawWalls(){
        double start = System.nanoTime();
        graphics.setFill(Paint.valueOf("BLACK"));
        graphics.fillRect(0, 0, screenWidth, screenHeight);

        List<Triangle> projectedTriangles = world.getTriangles().parallelStream()
                .filter(Triangle::isVisible)
                .sorted(Comparator.comparingDouble(Triangle::getCenterPointDistance).reversed())
                .map((triangle)->triangle.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane))
                .collect(Collectors.toList());
        //System.out.println(projectedTriangles.toString());
        System.out.println("PROCESSED: "+(System.nanoTime()-start)/ 1000000000000.0);
        start = System.nanoTime();
        projectedTriangles.forEach((triangle) -> {
            graphics.setFill(triangle.getColor());
            graphics.setStroke(triangle.getColor());
            graphics.fillPolygon(triangle.getXPoints(), triangle.getYPoints(), 3);
            graphics.strokePolygon(triangle.getXPoints(), triangle.getYPoints(), 3);
        });
        System.out.println("DRAWED: "+(System.nanoTime()-start)/ 1000000000000.0);

//        List<Polygon> projectedPolygons = world.getPolygons().stream()
//                .filter(Polygon::isVisible)
//                .sorted(Comparator.comparingDouble(Polygon::getDistanceFromWeightCenter).reversed())
//                .map((polygon)->polygon.projectTo2D(screenWidth, screenHeight, distanceToProjectionPlane))
//                .collect(Collectors.toList());
//        //System.out.println(projectedPolygons.toString());
//        projectedPolygons.forEach(polygon -> {
//            graphics.setFill(polygon.getColor());
//            graphics.fillPolygon(polygon.getXPoints(), polygon.getYPoints(), 4);
//        });
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

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }
}
