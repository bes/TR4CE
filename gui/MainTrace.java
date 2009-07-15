/*
 * Created on 12 jul 2009
 */
package gui;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

    int width = 400;
    int height = 400;
    int depth = 100;

    public MainTrace(){
        super("tr4ce");
        
        addWindowListener(this);

        setSize(width, height);
        setVisible(true);

        w = new World(width, height, depth);
        //w.addShape(new Sphere(0,0,16,10));
        //w.addShape(new Sphere(-10,-10,30,10, Color.RED));
        //w.addShape(new Plane(new Point3D(1,0,1), new Point3D(0,1,0), 1));
        Sphere sp = new Sphere(new Point3D(0,0,60),
        		0,0.2,0.3,
        		7, 
        		1.33,
        		0.7,
        		Color.BLUE);
        sp.setName("trans");
        w.addShape(sp);

        w.addShape(new Sphere(new Point3D(8,0,90),
        		0.1,0.8,0.2,
        		6, 
        		0,
        		0.6,
        		Color.RED));

        w.addShape(new Sphere(new Point3D(-5,7,50),
        		0.1,0.8,0.2,
        		2, 
        		0,
        		2,
        		Color.YELLOW));


        
        //w.addLight(new StandardLight(-100,-100,-10, Color.WHITE));
        //w.addLight(new StandardLight(0,0,0, Color.red));
        w.addLight(new StandardLight(0,0,0, Color.WHITE));
        w.addLight(new StandardLight(0,10,75, Color.WHITE));
        
        
        w.setRaster(new Raster(width, height, width/2, 180));
        w.setEye(new Eye(0,0,0));

        new Thread(this).start();
    }
    
    public static void main(String[] args) {
        MainTrace t = new MainTrace();
        t.setVisible(true);
    }

    public void run() {
        w.render(this.getGraphics(), this);
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
