/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

public class Raster {
	private int width,height;
    private double fov;
    private Eye eye;
    private Point3D pos;
    
    private Point3D xV;
    private Point3D yV;
    
    public Raster(int width, int height, Eye eye, double fov) {
    	this.width = width;
    	this.height = height;
        this.fov = fov;
        this.eye = eye;
        
        double dist = (width/2) / Math.tan(Math.toRadians(fov/2));
        
        pos = eye.getPos().plus(eye.getDirection().multiply(dist));
        
        xV = eye.getDirection().rotateY90CCW();
        yV = eye.getDirection().rotateX90CCW();

        System.out.println("dist " + dist + " - " + pos + " dir: " + eye.getDirection());
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public Point3D getPoint(int x, int y){
    	double xlen = (-width/2) + x;
    	double ylen = (-height/2) + y;
    	Point3D temp = pos.plus(xV.multiply(xlen)).plus(yV.multiply(ylen));
    	//System.out.println(x + "," + y + ", " + temp + "            -            " + eye.getPos());
    	return temp;
    }
}
