/*
 * Created on 12 jul 2009
 */
package model;

import java.awt.Color;

public class MColor {
    double r,g,b;
    
    public MColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public MColor(){
        r = 0;
        g = 0;
        b = 0;
    }
    
    public void addColor(double r, double g, double b) {
        this.r += r;
        this.g += g;
        this.b += b;
    }
    
    public int maxOut(double val) {
        return (int) (val > 255 ? 255 : val);
    }
    
    public Color getColor() {
        return new Color(maxOut(r), maxOut(g), maxOut(b));
    }

}
