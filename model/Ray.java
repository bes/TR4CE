/*
 * Created on 12 jul 2009
 */
package model;

public class Ray {
    private double[] norm;
    private double[] pos;
    
    public Ray(double x, double y, double z, double xV, double yV, double zV) {
        double abs = Math.sqrt(xV*xV + yV*yV + zV*zV);
        norm = new double[] { xV / abs, yV / abs, zV / abs };
        pos = new double[] { x,y,z };
    }
    
    public double[] getNorm() {
        return norm;
    }
    
    public double[] getPos() {
        return pos;
    }
}
