/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

import java.awt.Color;

public interface Shape {
    public boolean test(double x, double y, double z);
    public Point3D intersects(Ray r, World w);
    public Color getColor();
    public Point3D getNormal(Point3D point);
    
}
