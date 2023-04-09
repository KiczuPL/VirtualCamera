package main.java.sample;


import javafx.scene.paint.Color;

public class Edge {
    public Vector3f getStart() {
        return start;
    }

    public void setStart(Vector3f start) {
        this.start = start;
    }

    public Vector3f getEnd() {
        return end;
    }

    public void setEnd(Vector3f end) {
        this.end = end;
    }

    Vector3f start;
    Vector3f end;
    Color color;

    public Edge(Vector3f start, Vector3f end) {
        this.start = start;
        this.end = end;
        color = Color.RED;
    }

    public Edge(Vector3f start, Vector3f end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public void transform(Matrix matrix) {
        start.transform(matrix);
        end.transform(matrix);
    }


    public Edge projectTo2D(double screenWidth, double screenHeight, double fieldOfView) {
        return new Edge(
                start.projectTo2D(screenWidth,screenHeight,fieldOfView),
                end.projectTo2D(screenWidth,screenHeight,fieldOfView),
                getColor()
        );
    }

    public boolean isVisible(){
        return start.getZ()>0 || end.getZ() > 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
