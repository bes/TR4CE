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
    
    public BufferedImage render(Graphics g, ImageObserver o) {
        for (int x = 0; x < raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                Point3D rasterPos = raster.getPoint(x, y);
                Ray r = new Ray(eye.getPos(), rasterPos.minus(eye.getPos()).normalized());
                
                //g.setColor(Color.RED);
                //g.fillRect((int)worldX(rasterPos.getX()), (int)worldY(rasterPos.getY()), 3, 3);
                

                Color c = trace(r, 10, 1, null, g);
                if (c != null) {
	                int xT = (width-x);
	                int yT = (height-y);
	                bG.setColor(c);
	                bG.fillRect(xT, yT, 1, 1);
	                g.setColor(c);
	                g.fillRect(xT, yT, 1, 1);
                }
            }
        }
        g.drawImage(bimg, 0, 0, o);
        return bimg;
    }
    
    private int k = 0;
    private Color trace(Ray r, int cutoff, double nju1, Shape ins, Graphics g){
    	if (cutoff == 0)
    		return roomColor;
    	
        Object[] hit = closestIntersection(r);

        Shape s = (Shape) hit[0];
        Point3D point = (Point3D) hit[1];
        

        if (s != null){
            //g.setColor(Color.black);
            //g.fillRect((int)worldX(point.getX()), (int)worldY(point.getY()), 3,3);

        	MColor mc = new MColor();

        	Point3D N = null;
        	boolean hasInvertedNormal = s.hasInvertNormal(r, point);
    		if (hasInvertedNormal){
    			N = s.getInvertedNormal(point);
    		}
    		else {
    			N = s.getNormal(point);
    		}

            //Point3D R = r.getVector().minus(N.multiply( r.getVector().dot(N) * 2 )).normalized();
        	Point3D L = r.getPoint().minus(point).normalized();
        	Point3D R = N.multiply(N.dot(L)*2).minus(L);

            double red, green, blue;
            red = green = blue = 0;
            for (Light l: lights) {
                Point3D ls = l.getPos();
                Ray rs = new Ray(point, ls.minus(point).normalized());
                
                
                if (!hasIntersection(rs)) {
	            	Point3D La = rs.getVector().normalized();
	                double LN = La.dot(N);
	                
	                Point3D V = eye.getPos().minus(point).normalized();
	                
	                double RValphaPow = alphaPow(Math.max(0,R.dot(V)), 8);
	
	                red   +=  (s.diffuse() * LN * l.getColor().getRed() * l.getIntensity() + s.diffuse() * LN * s.getColor().getRed() + s.specular() * RValphaPow * l.getColor().getRed() * l.getIntensity());
	                green +=  (s.diffuse() * LN * l.getColor().getGreen() * l.getIntensity() + s.diffuse() * LN * s.getColor().getGreen() + s.specular() * RValphaPow * l.getColor().getGreen() * l.getIntensity());
	                blue  +=  (s.diffuse() * LN * l.getColor().getBlue() * l.getIntensity() + s.diffuse() * LN * s.getColor().getBlue() + s.specular() * RValphaPow * l.getColor().getBlue() * l.getIntensity());
                }
            }
            //Reflective
            if(s.reflection() > 0) {
                Color c = trace(new Ray(point, R), cutoff-1, 1, s, g);
                red += s.reflection() * c.getRed();
                green += s.reflection() * c.getGreen();
                blue += s.reflection() * c.getBlue();
            }
            
            if(s.refraction() > 0) {
            	double nju2 = s.refraction();
            	if (hasInvertedNormal){
            		nju2 = 1;
            		nju1 = s.refraction();
            	}
            	
                double nju = nju1 / nju2;
                double c1 = Point3D.zero.minus(N).dot(r.getVector());
                double c2 = Math.sqrt(1 - nju*nju*(1 - c1*c1));
                //Point3D ipar = N.multiply(-c1);
                //Point3D iperp = r.getVector().plus(N.multiply(c1));
                Point3D t = r.getVector().multiply(nju).plus(N.multiply(nju*c1 - c2));
                //Refractive
                Color c = trace(new Ray(point.plus(t.multiply(1)), t), cutoff - 1, nju2, s, g);

                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
            
            mc.addColor( 
            		s.ambient() * s.getColor().getRed() + red, 
            		s.ambient() * s.getColor().getGreen() + green, 
            		s.ambient() * s.getColor().getBlue() + blue);
            
            return mc.getColor();
    	}
        
        return roomColor;
    }
    
    private boolean hasIntersection(Ray r){
        for (Shape s: shapes) {
            if(s.intersects(r, this) != null)
            	return true;
        }
        
        return false;
    }
    
    private Object[] closestIntersection(Ray r){
    	Shape hit = null;
    	Point3D hitPoint = null;
        double zd = Double.MAX_VALUE;
        for (Shape s: shapes) {
            Point3D point = s.intersects(r, this);
            if (point != null){
                double tzd = point.minus(r.getPoint()).abs();
            	if (zd > tzd) {
            		zd = tzd;
            		hit = s;
            		Point3D normal = null;
            		if (s.hasInvertNormal(r, point)){
            			normal = s.getInvertedNormal(point);
            		}
            		else {
            			normal = s.getNormal(point);
            		}
            		hitPoint = point.plus(
            				normal
            				.multiply(0.00005));
            	}
            }
        }
    	return new Object[]{hit, hitPoint};
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
