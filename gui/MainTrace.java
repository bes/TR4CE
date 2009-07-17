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

    int width = 500;
    int height = 500;
    int depth = 50;

    public MainTrace(){
        super("tr4ce");
        
        addWindowListener(this);

        setSize(width, height);
        setVisible(true);

        w = new World(width, height, depth);
        //w.addShape(new Sphere(0,0,16,10));
        //w.addShape(new Sphere(-10,-10,30,10, Color.RED));
        //w.addShape(new Plane(new Point3D(1,0,1), new Point3D(0,1,0), 1));
        Sphere sp = new Sphere(new Point3D(0,0,0),
        		0,0.1,0.1,
        		40, 
        		1.2,
        		0,
        		Color.WHITE);
        sp.setName("trans");
        w.addShape(sp);

        w.addShape(new Sphere(new Point3D(20,0,-40),
        		0.1,0.8,0.4,
        		20, 
        		0,
        		2,
        		Color.GRAY));

        w.addShape(new Sphere(new Point3D(-60,50,40),
        		0.2,0.4,0.2,
        		30, 
        		0,
        		2,
        		Color.GREEN));
        w.addShape(new Sphere(new Point3D(-20,-20,-25),
        		0.1,0.8,0.2,
        		40, 
        		0,
        		2,
        		Color.RED));

        w.addShape(new Plane(new Point3D(0.5,1,-0.5), -80, Color.RED));
        w.addShape(new Plane(new Point3D(-0.4,1,-0.5), -180, Color.BLUE));
        //w.addShape(new Plane(new Point3D(-1,0,0), -100, Color.YELLOW));
System.out.println(new Point3D(0,1,0).rotateX90CCW());
        
        //w.addLight(new StandardLight(-100,-100,-10, Color.WHITE));
        //w.addLight(new StandardLight(0,0,0, Color.red));
        w.addLight(new StandardLight(0,90,-40, Color.WHITE));
        w.addLight(new StandardLight(-60,20,0, Color.WHITE));
        w.addLight(new StandardLight(0,0,0, Color.WHITE));
        
        Eye e = new Eye(new Point3D(0,0,-1000), new Point3D(0,0,1));
        w.setEye(e);
        w.setRaster(new Raster(width, height, e, 30));

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
