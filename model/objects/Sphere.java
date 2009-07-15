/*
 * Created on 12 jul 2009
 */
package model.objects;

import gui.Point3D;

import java.awt.Color;

import model.Ray;
import model.Shape;
import model.World;

public class Sphere implements Shape{
    double r;
    Point3D pos;
    Color c;
    
    String name = null;
    
    double a,d,s,refract,reflect;
    
    public Sphere(Point3D p, double a, double d, double s, double r, double refract, double reflect, Color c) {
    	pos = p;
        this.r = r;
        this.c = c;
        this.a = a;
        this.d = d;
        this.s = s;
        
        this.refract = refract;
        this.reflect = reflect;
    }

    public Color getColor() {
        return c;
    }
    
    public Point3D intersects(Ray ray, World w) {
        Point3D unit = ray.getVector().normalized();
        Point3D p    = ray.getPoint();
        
        double alpha = -p.minus(pos).dot(unit);
        Point3D q    = p.plus(unit.multiply(alpha));
        double bSq   = q.minus(pos).abs_squared();
        
        if (bSq > r*r)
            return null;
        
        double a = Math.sqrt(r*r - bSq);
        if (alpha >= a) {
        	Point3D q1 = q.minus(unit.multiply(a));
            return q1;
        }
        
        if (alpha + a > 0) {
        	Point3D q2 = q.plus(unit.multiply(a));
            return q2;
        }
        
        return null;
    }

    public boolean test(double x, double y, double z) {
        // Move the point to sphere coordinates
/*        double sX = x - this.x;
        double sY = y - this.y;
        double sZ = z - this.z;

        //System.out.println("" + sX + "," + sY + "," + sZ + ": " + Math.sqrt(sX*sX + sY*sY + sZ*sZ) + " r:" + r);

        // If a point is contained within the absolute distance |d| of the sphere
        if (Math.sqrt(sX*sX + sY*sY + sZ*sZ) < r ) {
            return true;
        }*/
        return false;
    }
    
    public Point3D getNormal(Point3D point){
    	return point.minus(pos).normalized();
    }

	@Override
	public double ambient() {
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public double diffuse() {
		// TODO Auto-generated method stub
		return d;
	}

	@Override
	public double specular() {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public double refraction() {
		// TODO Auto-generated method stub
		return refract;
	}

	@Override
	public double reflection() {
		// TODO Auto-generated method stub
		return reflect;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		name = s;
	}
}
