/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

public class Eye {
    Point3D pos;
    public Eye(double x, double y, double z) {
        pos = new Point3D(x,y,z);
    }
    
    public Point3D getPos() {
        return pos;
    }
}
