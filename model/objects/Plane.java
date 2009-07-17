package model.objects;

import gui.Point3D;

import java.awt.Color;

import model.Ray;
import model.Shape;
import model.World;

public class Plane implements Shape {
	Point3D consts;
	double d;
	Color c;
	
	public Plane(Point3D pos, double d, Color c){
		this.consts = pos;
		this.d = d;
		this.c = c;
		
		/*
		Point3D p0 = new Point3D(0,-10,4);
		Point3D p1 = new Point3D(10,-10,1);
		Point3D p2 = new Point3D(4,-10,2);
		this.normal = p1.minus(p0).cross(p2.minus(p0));
		this.d = p0.dot(normal);
		System.out.println(this.d);
		System.out.println(this.normal.getX() + " - " + this.normal.getY() + " - " + this.normal.getZ());
		this.consts = this.normal;
		*/
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public Point3D getNormal(Point3D point) {
		return consts;
	}

	@Override
	public Point3D intersects(Ray r, World w) {
	  
	  /*
		Point3D lA = r.getPoint();
		Point3D lB = lA.plus(r.getVector().normalized().multiply(w.getDepth()));
		
		double ta = d - lA.dot(consts.normalized());
		double tb = lB.minus(lA).dot(consts.normalized());
		
		Point3D i = null;
		if (tb != 0){
			double t = ta/tb;
			if (t >= 0 && t <= 1){
				i = lA.plus(lB.minus(lA).multiply(t));
			}
			//System.out.println(t + "      " + i);
		}*/
	  
	  Point3D i = null;
	  double dp = consts.dot(r.getVector());
	  if ( dp != 0 ) {
	    double t  = -(consts.dot(r.getPoint()) + d) / dp;
	    if (t > 0) {
	      i = r.getPoint().plus(r.getVector().multiply(t));
	    }
	  }
		
		return i;
	}

	@Override
	public boolean test(double x, double y, double z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double ambient() {
		// TODO Auto-generated method stub
		return 0.1;
	}

	@Override
	public double diffuse() {
		// TODO Auto-generated method stub
		return 0.81;
	}

	@Override
	public double specular() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double refraction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double reflection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		
	}

}
