/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;

public class Eye {
    Point3D pos, direction;
    
    public Eye(Point3D pos, Point3D direction) {
        this.pos = pos;
        this.direction = direction;
    }
    
    public Point3D getPos() {
        return pos;
    }
    
    public Point3D getDirection() {
    	return direction;
    }
}
