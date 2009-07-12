/*
 * Created on 12 jul 2009
 */
package model;

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
    
    private int maxColor(int val){
        return val > 255 ? 255 : val;
    }
    
    public void render(Graphics g, ImageObserver o) {
        bG.setColor(roomColor);
        bG.fillRect(0, 0, width, height);
        
        double ka = 1f;
        double kd = 1f;
        double ks = 0.1f;
        
        int loops = 1;
        int numLights = lights.size();
        for (int x = -raster.getWidth() / 2; x < raster.getWidth() / 2; x++) {
            for (int y = -raster.getHeight() / 2; y < raster.getHeight() / 2; y++) {
                MColor mc = new MColor();
                double pX = x;
                double pY = y;
//                for (double pX = x - 0.5; pX <= x + 0.5; pX += 0.1) {
//                    for (double pY = y - 0.5; pY <= y + 0.5; pY += 0.1) {
                        Ray r = new Ray(eye.getX() , eye.getY() , eye.getZ() ,pX - eye.getX(), pY - eye.getY(), 2000 - eye.getZ());
//                        boolean pixelSet = false;
                        for (Shape s: shapes) {
                            double [] point = s.intersects(r, this);
                            
                            if (point != null){
                                double [] normal = s.getNormal(point);
                                double red, green, blue;
                                red = green = blue = 0;
                                float phong = 0; // http://en.wikipedia.org/wiki/Phong_shading
                                for (Light l: lights) {
                                    double[] spoint = null;
                                    boolean blocked = false;
                                    double[] ls = l.getPos();
                                    Ray rs = new Ray(point[0], point[1], point[2], ls[0] - point[0], ls[1] - point[1], ls[2] - point[2]);
 /*                                   for (Shape ss: shapes) {
                                        spoint = ss.intersects(rs, this);
                                        if (spoint != null) {
                                            blocked = true;
                                            break;
                                        }
                                    }
                                    if (!blocked) {*/
                                        double LN = (rs.getNorm()[0]*normal[0] + rs.getNorm()[1]*normal[1] + rs.getNorm()[2]*normal[2]);
                                        double inV[] = new double[]{ls[0] - point[0], ls[1] - point[1], ls[2] - point[2] };
                                        double ndot_inV = normal[0] * inV[0] + normal[1] * inV[1] + normal[2] * inV[2];
                                        double inVpar[] = { normal[0] * ndot_inV, normal[1] * ndot_inV, normal[2] * ndot_inV };
                                        double R[] = { inV[0] - 2*inVpar[0], inV[1] - 2*inVpar[1], inV[2] - 2*inVpar[2] }; //Phong R
                                        double Rabs = Math.sqrt(R[0]*R[0] + R[1]*R[1] + R[2]*R[2]);
                                        R[0] = R[0] / Rabs;
                                        R[1] = R[1] / Rabs;
                                        R[2] = R[2] / Rabs;
                                        double Vabs = Math.sqrt(point[0]*point[0] + point[1]*point[1] + point[2]*point[2]);
                                        double V[] = { -point[0]/Vabs, -point[1]/Vabs, -point[2]/Vabs }; // Phong V
                                        double RValphaPow = alphaPow(R[0]*V[0] + R[1]*V[1] + R[2]*V[2], 1);

                                        red   +=  (kd * LN * l.getColor().getRed() + ks * RValphaPow *s.getColor().getRed()) / loops;
                                        green +=  (kd * LN * l.getColor().getGreen() + ks * RValphaPow * s.getColor().getGreen()) / loops;
                                        blue  +=  (kd * LN * l.getColor().getBlue() + ks * RValphaPow * s.getColor().getBlue()) / loops;
                                        
                                        
                                        //float red = s.getColor().getRed()/(loops) + l.getColor().getRed()/(loops+numLights);
                                        //float green = s.getColor().getGreen()/(loops) + l.getColor().getGreen()/(loops+numLights);
                                        //float blue = s.getColor().getBlue()/(loops) + l.getColor().getBlue()/(loops+numLights);
                                    //}
                                }
                                mc.addColor(ka * red, ka * green, ka * blue);
                            }else{
                                mc.addColor(roomColor.getRed()/loops, roomColor.getGreen()/loops, roomColor.getBlue()/loops);
                            }
                        }
                //    }
                //}
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
