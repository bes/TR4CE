/*
 * Created on 12 jul 2009
 */
package gui;

public class Point3D {
    public static final Point3D zero = new Point3D(0,0,0);
    
    double [] point;
    
    public Point3D(double point[]) {
        this.point = point;
    }
    
    public Point3D(double x, double y, double z){
        point = new double[] { x , y , z };
    }
    
    public Point3D dot(Point3D b){
        return new Point3D(point[0] * b.point[0], point[1] * b.point[1], point[2] * b.point[2]);
    }
    
    public Point3D minus(Point3D b){
        return new Point3D(point[0] - b.point[0], point[1] - b.point[1], point[2] - b.point[2]);
    }
    
    public double abs(){
        return point[0] * point[0] + point[1] * point[1] + point[2] * point[2]; 
    }
}
