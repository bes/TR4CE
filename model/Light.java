/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

import java.awt.Color;

public interface Light {
    public Point3D getPos();
    public Color getColor();
    public double getIntensity();
}
