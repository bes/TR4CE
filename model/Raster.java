/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

public class Raster {
	private int width,height,dist;
    private double fov;
    
    public Raster(int width, int height, int dist, double fov) {
    	this.width = width;
    	this.height = height;
    	this.dist = dist;
        this.fov = fov;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public Point3D getPoint(int x, int y){
    	//X
    	int hw = width/2;
    	int hh = height/2;
    	
    	double xv,yv;
    	
    		double angleStep = fov/width;
    		double angle = angleStep * (x);
    		xv = hw * Math.sin(Math.toRadians(angle));
    		
    		angleStep = fov/height;
    		angle = angleStep * (y);
    		yv = hh * Math.sin(Math.toRadians(angle));
    		
    		
    	return new Point3D(xv, yv, dist);
    }
}
