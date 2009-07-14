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
import model.objects.Sphere;
import model.objects.StandardLight;

public class MainTrace extends JFrame implements Runnable, WindowListener{
    
    private static final long serialVersionUID = 1L;
    World w;

    int width = 300;
    int height = 300;
    int depth = 100;

    public MainTrace(){
        super("tr4ce");
        
        addWindowListener(this);

        setSize(width, height);
        setVisible(true);

        w = new World(width, height, depth);
        //w.addShape(new Sphere(0,0,16,10));
        w.addShape(new Sphere(-30,-30,110,10, Color.RED));
        w.addShape(new Sphere(30,30,80,3, Color.YELLOW));
        //w.addLight(new StandardLight(-100,-100,-10, Color.WHITE));
        //w.addLight(new StandardLight(0,0,0, Color.red));
        w.addLight(new StandardLight(100,200,-50, Color.WHITE));
        w.setRaster(new Raster(0,0,0, width, height));
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
