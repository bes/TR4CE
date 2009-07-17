/*
 * Created on 12 jul 2009
 */
package model.objects;

import gui.Point3D;

import java.awt.Color;

import model.Light;

public class StandardLight implements Light {
    private Point3D pos;
    private Color color;
    private double intensity;
    
    public StandardLight(double x, double y, double z, Color c, double i) {
        pos = new Point3D(x, y, z);
        this.color = c;
        this.intensity = i;
    }

    public Point3D getPos() {
        return pos;
    }
    
    public Color getColor() {
        return color;
    }
    
    public double getIntensity(){
      return intensity;
    }
}
