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
    
    public Sphere(double x, double y, double z, double r, Color c) {
    	pos = new Point3D(x,y,z);
        this.r = r;
        this.c = c;
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
}
