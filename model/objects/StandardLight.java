/*
 * Created on 12 jul 2009
 */
package model.objects;

import java.awt.Color;

import model.Light;

public class StandardLight implements Light {
    private double x,y,z;
    private double[] pos;
    private Color color;
    
    public StandardLight(double x, double y, double z, Color c) {
        this.x = x;
        this.y = y;
        this.z = z;
        pos = new double[] { this.x, this.y, this.z };
        this.color = c;
    }

    public double[] getPos() {
        return pos;
    }
    
    public Color getColor() {
        return color;
    }
}
