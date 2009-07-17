/*
 * Created on 12 jul 2009
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;

import model.Eye;
import model.Raster;
import model.World;
import model.objects.Plane;
import model.objects.Sphere;
import model.objects.StandardLight;

public class MainTrace extends JFrame implements Runnable, WindowListener{
    
    private static final long serialVersionUID = 1L;
    World w;

    int width = 500;
    int height = 500;
    int depth = 50;
    
    BufferedImage i = null;

    public MainTrace(){
        super("tr4ce");
        
        addWindowListener(this);

        setSize(width, height);
        setVisible(true);

        w = new World(width, height, depth);
        //w.addShape(new Sphere(0,0,16,10));
        //w.addShape(new Sphere(-10,-10,30,10, Color.RED));
        //w.addShape(new Plane(new Point3D(1,0,1), new Point3D(0,1,0), 1));
        Sphere sp = new Sphere(new Point3D(0,50,360),
        		0,0.3,0,
        		100, 
        		1.7,
        		0,
        		Color.WHITE);
        sp.setName("trans");
        w.addShape(sp);

        w.addShape(new Sphere(new Point3D(-110,15,160),
        		0.1,0.5,0.2,
        		30, 
        		0,
        		0.1,
        		Color.YELLOW));
        
        w.addShape(new Sphere(new Point3D(80,30,180),
        		0.1,0.4,0.2,
        		60, 
        		0,
        		0,
        		Color.WHITE));

        w.addShape(new Plane(
        		new Point3D(0,1,0), 50,
        		0, 0.9, 0.3,
        		0,
        		0,
        		Color.RED));

//        w.addShape(new Plane(
//        		new Point3D(0,0,1), 300,
//        		0, 0.4, 0,
//        		0,
//        		0,
//        		Color.WHITE));
/*
        w.addShape(new Plane(
        		new Point3D(-1,0,0), 350,
        		0.3, 0.4, 0.1,
        		0,
        		0.1,
        		Color.YELLOW));
*/
        
        /*w.addShape(new Plane(
        		new Point3D(0,-1,0),
        		400,
        		0.6, 0.4, 0,
        		0,
        		0,
        		Color.RED));
         */
        
        
        
        //w.addShape(new Plane(new Point3D(0,0,1), -50, Color.RED));
        //w.addShape(new Plane(new Point3D(-0.4,1,-0.5), -180, Color.BLUE));
        //w.addShape(new Plane(new Point3D(-1,0,0), -100, Color.YELLOW));
        
        //w.addLight(new StandardLight(-100,-100,-10, Color.WHITE));
        w.addLight(new StandardLight(-110,90,160, Color.BLUE, 0.2));
        w.addLight(new StandardLight(80,100,900, Color.WHITE, 0.1));
        w.addLight(new StandardLight(80,30,300, Color.RED, 0.2));
        w.addLight(new StandardLight(0,10,1000, Color.WHITE, 0.2));
        w.addLight(new StandardLight(-80,100,100, Color.WHITE, 0.5));
        //w.addLight(new StandardLight(0,0,0, Color.WHITE));
        
        Eye e = new Eye(new Point3D(0,300,2000), new Point3D(-0.2,-0.2,-1.8));
        w.setEye(e);
        w.setRaster(new Raster(width, height, e, 30));

        new Thread(this).start();
    }
    
    public static void main(String[] args) {
        MainTrace t = new MainTrace();
        t.setVisible(true);
    }

    public void run() {
        i = w.render(this.getGraphics(), this);
    }
    
    public void paint(Graphics g){
    	if (i != null) {
    		g.drawImage(i, 0, 0, this);
    	}
    }
    
    public void repaint(){
    	
    }

    public void windowActivated(WindowEvent arg0) {
    }

    public void windowClosed(WindowEvent arg0) {
        System.out.println("Window closed.");
    }

    public void windowClosing(WindowEvent arg0) {
        System.out.println("Window closing.");
        System.exit(0);
    }

    public void windowDeactivated(WindowEvent arg0) {
    }

    public void windowDeiconified(WindowEvent arg0) {
    }

    public void windowIconified(WindowEvent arg0) {
    }

    public void windowOpened(WindowEvent arg0) {
    }
}
