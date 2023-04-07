package main.java.sample;

import lombok.Data;

@Data
public class Triangle {
    private Vector3f p1;

    public Vector3f getP1() {
        return p1;
    }

    public void setP1(Vector3f p1) {
        this.p1 = p1;
    }

    public Vector3f getP2() {
        return p2;
    }

    public void setP2(Vector3f p2) {
        this.p2 = p2;
    }

    public Vector3f getP3() {
        return p3;
    }

    public void setP3(Vector3f p3) {
        this.p3 = p3;
    }

    private Vector3f p2;
    private Vector3f p3;

    public Triangle() {
        p1 = new Vector3f(0f, 0f, 0f);
        p2 = new Vector3f(0f, 0f, 0f);
        p3 = new Vector3f(0f, 0f, 0f);
    }

    public Triangle(Vector3f p1,Vector3f p2,Vector3f p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public void transform(Matrix matrix){
        p1.transform(matrix);
        p2.transform(matrix);
        p3.transform(matrix);
    }


    public Triangle projectTo2D(double screenWidth, double screenHeight, double fieldOfView) {
        Triangle result = new Triangle(
                projectPointTo2D(p1,fieldOfView),
                projectPointTo2D(p2,fieldOfView),
                projectPointTo2D(p3,fieldOfView)
        );
        return result;
    }

    public Vector3f projectPointTo2D(Vector3f p, double fov){
        Vector3f v=new Vector3f(p.getX()/p.getZ()*fov,p.getY()/p.getZ()*fov, fov);
        //System.out.println(v.toString());
        return v;
    }
}
