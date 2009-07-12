/*
 * Created on 12 jul 2009
 */
package model;

public class Eye {
    int [] pos;
    public Eye(int x, int y, int z) {
        pos = new int [] {x,y,z};
    }
    
    public int [] getPos() {
        return pos;
    }
    
    public int getX(){ return pos[0]; }
    public int getY(){ return pos[1]; }
    public int getZ(){ return pos[2]; }
}
