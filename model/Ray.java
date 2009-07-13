/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

public class Ray {
    private Point3D point;
    private Point3D vector;
    
    public Ray(double x, double y, double z, double xV, double yV, double zV) {
        this.point = new Point3D(xV,yV,zV);
        this.vector = new Point3D(x,y,z);
    }
    
    public Ray(Point3D point, Point3D vector) {
    	this.point = point;
    	this.vector = vector;
    }
    
    public Point3D getVector() {
    	return vector;
    }
    
    public Point3D getPoint() {
    	return point;
    }
}
