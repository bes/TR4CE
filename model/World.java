/*
 * Created on 12 jul 2009
 */
package model;

import gui.Point3D;
import gui.PrintUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class World {
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
    private LinkedList<Light> lights = new LinkedList<Light>();
    
    private Raster raster;
    private Eye eye;
    
    private BufferedImage bimg;
    private Graphics bG;
    
    private Color roomColor = Color.BLACK;
    
    private int width, height, depth;
    
    public World(int width, int height, int depth){
        this.width = width;
        this.height = height;
        this.depth = depth;
        
        bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bG = bimg.getGraphics();
    }
    
    public int getDepth() {
        return depth;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void addShape(Shape s) {
        shapes.add(s);
    }
    
    public void addLight(Light l) {
        lights.add(l);
    }
    
    public void setRaster(Raster c) {
        this.raster = c;
    }
    
    public void setEye(Eye e){
        this.eye = e;
    }
    
    public void render(Graphics g, ImageObserver o) {
        bG.setColor(roomColor);
        bG.fillRect(0, 0, width, height);
        
        double ka = 0.2f;
        double kd = 0.5f;
        double ks = 0.3f;
        
        for (int x = -raster.getWidth() / 2; x < raster.getWidth() / 2; x++) {
            for (int y = -raster.getHeight() / 2; y < raster.getHeight() / 2; y++) {
                MColor mc = new MColor();
                Point3D rasterPos = new Point3D(x,y,2000);
                Ray r = new Ray(eye.getPos(), rasterPos.minus(eye.getPos()));
                double zd = Double.MAX_VALUE;
                for (Shape s: shapes) {
                    Point3D point = s.intersects(r, this);
                    
                    if (point != null){
                        double tzd = point.minus(eye.getPos()).abs();
                    	if (zd > tzd) {
                    		zd = tzd;
	                        double red, green, blue;
	                        red = green = blue = 0;
	                        for (Light l: lights) {
	                            Point3D ls = l.getPos();
	                            Ray rs = new Ray(point, ls.minus(point));
	                            
	                        	Point3D L = rs.getVector().normalized();
	                        	Point3D N = s.getNormal(point);
	                            double LN = L.dot(N);
	                            
	                            Point3D R = r.getVector().minus(N.multiply( r.getVector().dot(N) * 2 )).normalized();
	                            Point3D V = eye.getPos().minus(point).normalized();
	                            
	                            double RValphaPow = Math.max(0,R.dot(V));
	
	                            red   +=  (kd * LN * s.getColor().getRed() + ks * RValphaPow * l.getColor().getRed());
	                            green +=  (kd * LN * s.getColor().getGreen() + ks * RValphaPow * l.getColor().getGreen());
	                            blue  +=  (kd * LN * s.getColor().getBlue() + ks * RValphaPow * l.getColor().getBlue());
	                        }
	                        mc.addColor( 
	                        		ka * s.getColor().getRed() + red, 
	                        		ka * s.getColor().getGreen() + green, 
	                        		ka * s.getColor().getBlue() + blue);
                    	}
                    }
                }

                int xT = (int)worldX(x);
                int yT = (int)worldY(y);
                bG.setColor(mc.getColor());
                bG.fillRect(xT, yT, 1, 1);
            }
        }
        g.drawImage(bimg, 0, 0, o);
    }
    
    private double alphaPow(double val, int pow) {
    	double temp = val;
    	for (int i = 1; i < pow; i++){
    		temp *= val;
    	}
    	return temp;
    }
    
    private double worldX(double x){
        return x + raster.getWidth() / 2;
    }
    
    private double worldY(double y){
        return (y + raster.getHeight() / 2);
    }
}
