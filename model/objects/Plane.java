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
	
	double a,diff,s,reflection,refracion;
	
	public Plane(Point3D pos, double d, double a, double diff, double s, double reflection, double refraction, Color c){
		this.consts = pos;
		this.d = d;
		this.c = c;
		
		this.a = a;
		this.diff = diff;
		this.s = s;
		this.reflection = reflection;
		this.refracion = refraction;
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
	public double ambient() {
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public double diffuse() {
		// TODO Auto-generated method stub
		return diff;
	}

	@Override
	public double specular() {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public double refraction() {
		// TODO Auto-generated method stub
		return refracion;
	}

	@Override
	public double reflection() {
		// TODO Auto-generated method stub
		return refracion;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Plane";
	}

	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasInvertNormal(Ray r, Point3D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point3D getInvertedNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

}
