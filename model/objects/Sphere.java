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
    double x,y,z,r;
    
    public Sphere(double x, double y, double z, double r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }

    public Color getColor() {
        return Color.WHITE;
    }

    public void move(double x, double y, double z) {
        // TODO: implement
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double[] intersects(Ray ray, World w) {
        double[] unit = ray.getNorm();
        double[] p    = ray.getPos();
        double alpha  = - ((p[0]-x)*unit[0] + (p[1]-y)*unit[1] + (p[2]-z)*unit[2]);
        double[] q    = new double[] {p[0]+alpha * unit[0], p[1]+alpha*unit[1], p[2]+alpha*unit[2] };
        double bSq    = ((q[0] - x)*(q[0] - x) + (q[1] - y) * (q[1] - y) + (q[2] - z) * (q[2] - z));
        
        if (bSq > r*r)
            return null;
        
        double a = Math.sqrt(r*r - bSq);
        if (alpha >= a) {
            double[] q1 = new double[] { q[0] - a*unit[0] , q[1] - a*unit[1] , q[2] - a*unit[2] };
            return q1;
        }
        
        if (alpha + a > 0) {
            double[] q2 = new double[] { q[0] + a*unit[0] , q[1] + a*unit[1] , q[2] + a*unit[2] };
            return q2;
        }
        
        return null;
    }

    public boolean test(double x, double y, double z) {
        // Move the point to sphere coordinates
        double sX = x - this.x;
        double sY = y - this.y;
        double sZ = z - this.z;

        //System.out.println("" + sX + "," + sY + "," + sZ + ": " + Math.sqrt(sX*sX + sY*sY + sZ*sZ) + " r:" + r);

        // If a point is contained within the absolute distance |d| of the sphere
        if (Math.sqrt(sX*sX + sY*sY + sZ*sZ) < r ) {
            return true;
        }
        return false;
    }

    public double[] getNormal(double[] point) {
        double [] vals = new double[] { point[0] - x , point[1] - y, point[2] -z };
        double abs = Math.sqrt(vals[0] * vals[0] + vals[1] * vals[1] + vals[2] * vals[2]);
        return new double[] { vals[0] / abs, vals[1] / abs, vals[2] / abs };
    }
}
