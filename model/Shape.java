/*
 * Created on 12 jul 2009
 */
package model;

import java.awt.Color;

public interface Shape {
    public boolean test(double x, double y, double z);
    public void move(double x, double y, double z);
    public double[] intersects(Ray r, World w);
    public Color getColor();
    public double[] getNormal(double[] point);
    
}
