package main.java.sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private List<Drawable> drawables;
    List<Triangle> triangles;

    public World(){
        drawables = new ArrayList<>();
        triangles=new ArrayList<>();
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        triangles.addAll(drawable.getTriangleList());
    }

    public void addTriangle(Triangle triangle){
        triangles.add(triangle);
    }

    public void transform(Matrix matrix){
        for(Triangle triangle : triangles){
            triangle.transform(matrix);
        }
    }


    public void draw(final GraphicsContext graphics, final double screenWidth, final double screenHeight, final double fieldOfView){
        graphics.clearRect(0,0,screenWidth,screenHeight);
        List<Triangle> projectedTriangles = triangles.stream().map((triangle)->triangle.projectTo2D(screenWidth,screenHeight,fieldOfView)).collect(Collectors.toList());
        projectedTriangles.forEach(triangle -> {
            graphics.strokeLine(triangle.getP1().getX() + screenWidth/2d, triangle.getP1().getY()+ screenHeight/2d,triangle.getP2().getX()+ screenWidth/2d, triangle.getP2().getY()+ screenHeight/2d);
            graphics.strokeLine(triangle.getP2().getX()+ screenWidth/2d, triangle.getP2().getY()+ screenHeight/2d,triangle.getP3().getX()+ screenWidth/2d, triangle.getP3().getY()+ screenHeight/2d);
            graphics.strokeLine(triangle.getP3().getX()+ screenWidth/2d, triangle.getP3().getY()+ screenHeight/2d,triangle.getP1().getX()+ screenWidth/2d, triangle.getP1().getY()+ screenHeight/2d);
        });
    }
}
