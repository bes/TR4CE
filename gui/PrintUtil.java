/*
 * Created on 12 jul 2009
 */
package gui;

public class PrintUtil {
    public static final void printDoublePoint(double[] point){
        if (point != null && point.length == 3)
            System.out.println("( " + point[0] + " , " + point[1] + " , " + point[2] + " ) ");
    }
}
