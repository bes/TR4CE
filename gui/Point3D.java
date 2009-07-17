/*
 * Created on 12 jul 2009
 */
package gui;

public class Point3D {
    public static final Point3D zero = new Point3D(0,0,0);
    
    protected double [] point;
        
    public Point3D(double point[]) {
        this.point = point.clone();
    }
    
    public Point3D(double x, double y, double z){
        point = new double[] { x , y , z };
    }
    
    public Point3D(Point3D p) {
    	point = p.point;
    }
    
    public double dot(Point3D b){
        return point[0] * b.point[0] + point[1] * b.point[1] + point[2] * b.point[2];
    }
        
    public Point3D minus(Point3D b){
        return new Point3D(point[0] - b.point[0], point[1] - b.point[1], point[2] - b.point[2]);
    }
    
    public Point3D plus(Point3D b){
    	return new Point3D(point[0] + b.point[0], point[1] + b.point[1], point[2] + b.point[2]);
    }
    
    public Point3D multiply(double m){
    	return new Point3D(point[0]*m, point[1]*m, point[2]*m);
    }
    
    public double abs(){
        return Math.sqrt(point[0] * point[0] + point[1] * point[1] + point[2] * point[2]); 
    }
    
    public double abs_squared(){
    	return point[0] * point[0] + point[1] * point[1] + point[2] * point[2];
    }
    
    public Point3D normalized() {
    	double abs = abs();
    	return new Point3D(point[0]/abs,point[1]/abs,point[2]/abs);
    }
    
    public Point3D cross(Point3D c){
    	return new Point3D(
    			point[1]*c.point[2] - point[2]*c.point[1],
    			point[2]*c.point[0] - point[0]*c.point[2],
    			point[0]*c.point[1] - point[1]*c.point[0]);
    }
    
    public double distance(Point3D b){
    	return this.minus(b).abs();
    }
    
    public double getX(){
    	return point[0];
    }
    
    public double getY(){
    	return point[1];
    }
    
    public double getZ(){
    	return point[2];
    }
    
    public String toString(){
    	return "{ " + getX() + ", " + getY() + ", " + getZ() + " }";
    }
    
    public Point3D rotateY90CCW(){
    	return new Point3D(point[2],point[1],-point[0]);
    }
    
    public Point3D rotateX90CCW(){
    	return new Point3D(point[0],-point[2],point[1]);
    }
 }
