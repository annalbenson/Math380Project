import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ProjectApp {
    public static void main(String [] args){

        /*
        * Step 1) Generate Data (Uniform, Normal. Mixed)
        *   Save to File
        * Step 2) Load Data From File and Graph
        * Step 3) Apply Model to Data and Graph
        *
        *
        * */


    }

    /* Data Generation Functions */


    /* Generation function: uniformDist
     * args: a file name, number of data points to be generated, min and max x values, min and max y values
     * output: produces n ordered pairs with a uniform distribution with ranges:
     *          minX <= produced x values <= maxX & minY <= produced y values <= maxY.
     *          This data is then written to a file with name "filename"
     * */
    public static void uniformDist(String filename, int n, double minX, double maxX, double minY, double maxY){

        double [] xPoints = new double [n];
        double [] yPoints = new double [n];

        // Generate points
        for(int i = 0; i < n; i++){
            xPoints[i] = randomWithRange( minX, maxX );
            yPoints[i] = randomWithRange( minY, maxY );
        }

        // Write to file
        writeToFile(filename, xPoints, yPoints);

    }


    /* Generation function: normalDist
     * args: a file name, number of data points to be generated, min and max x values, min and max y values
     * output: produces n ordered pairs with a normal distribution with ranges:
     *          minX <= produced x values <= maxX & minY <= produced y values <= maxY.
     *          This data is then written to a file with name "filename"
     * */
    public static void normalDist(String filename, int n, double minX, double maxX, double minY, double maxY){

    }




    /* Helper function: randomWithRange
     * args: minimum range value, maximum range value
     * output: returns random number x in range min <= x <= max
     * */
    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }

    /* Helper function: writeToFile
    * args: a file name, array of x coordinate, array of y coordinates
    * output: produces file with data set made up of x and y coordinates
    * */

    public static void writeToFile(String filename, double [] xPoints, double [] yPoints){
        File file = new File(filename);
        FileOutputStream fos;
        PrintWriter pw;

        int numPoints = xPoints.length;

        try{
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);

            for(int i = 0; i < numPoints; i++ ){
                pw.println(xPoints[i] + "\t" + yPoints[i]);
            }

            pw.close();
            fos.close();

        } catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /* Loading and Graphing Functions */



    /* Model Application Functions */


}
