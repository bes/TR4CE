package model.objects;

import gui.Point3D;

import java.awt.Color;

import model.Ray;
import model.Shape;
import model.World;

public class Plane implements Shape {
	Point3D consts, normal;
	double d;
	
	public Plane(Point3D pos, Point3D normal, double d){
		this.consts = pos;
		this.normal = normal;
		this.d = d;
		
		Point3D p0 = new Point3D(0,-10,0);
		Point3D p1 = new Point3D(1,-10,1);
		Point3D p2 = new Point3D(3,-10,2);
		this.normal = p1.minus(p0).cross(p2.minus(p0));
		this.d = p0.dot(normal);
		System.out.println(this.d);
		System.out.println(this.normal.getX() + " - " + this.normal.getY() + " - " + this.normal.getZ());
		this.consts = this.normal;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.CYAN;
	}

	@Override
	public Point3D getNormal(Point3D point) {
		return normal;
	}

	@Override
	public Point3D intersects(Ray r, World w) {
		Point3D beginP = r.getPoint();
		Point3D maxP = beginP.plus(r.getVector().multiply(w.getDepth()));
		
		double ta = d - Point3D.zero.minus(consts).dot(beginP);
		double tb = maxP.minus(beginP).dot(consts);
		
		Point3D i = null;
		if(ta != 0 && tb != 0){
			double t = ta/tb;
			i =  beginP.plus(maxP.minus(beginP).multiply(t));
		}
		
		if (i != null && i.getZ() > 0 && i.getZ() < 150 && i.getX() > 0){
			return i;
		}
		
		return null;
	}

	@Override
	public boolean test(double x, double y, double z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double ambient() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double diffuse() {
		// TODO Auto-generated method stub
		return 0;
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
