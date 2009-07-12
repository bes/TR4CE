/*
 * Created on 12 jul 2009
 */
package model.objects;

import java.awt.Color;

import model.Ray;
import model.Shape;
import model.World;

public class Cube implements Shape{
    int x, y, r;
    
    public Cube(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Color getColor() {
        return Color.orange;
    }

    public double[] intersects(Ray r, World w) {
        
        
        return null;
    }

    public void move(double x, double y, double z) {
    }

    public boolean test(double x, double y, double z) {
        return false;
    }

    public double[] getNormal(double[] point) {
        return null;
    }

}
