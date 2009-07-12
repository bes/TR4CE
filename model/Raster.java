/*
 * Created on 12 jul 2009
 */
package model;

public class Raster {
    private double [] pos;
    private int width, height;
    
    public Raster(double x, double y, double z, int width, int height) {
        pos = new double[] {x,y,z};
        this.width = width;
        this.height = height;
    }
    
    public double[] getPos() {
        return pos;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
