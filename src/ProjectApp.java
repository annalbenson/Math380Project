import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProjectApp {

    public static final String uniform_name_1 = "uniform_1.txt";
    public static final String uniform_name_2 = "uniform_2.txt";
    public static final String uniform_name_3 = "uniform_3.txt";

    public static final int numPoints_1 = 50;
    public static final int numPoints_2 = 50;
    public static final int numPoints_3 = 50;

    public static void main(String [] args){

        /*
        * Step 1) Generate Data (Uniform, Normal. Mixed)
        *   Save to File
        * Step 2) Load Data From File and Graph
        * Step 3) Apply Model to Data and Graph
        *
        *
        * */




        // 11 graphs

        // Boston Strangler


        // Data Generation
        uniformDist(uniform_name_1, numPoints_1, 0, 10, 0, 10);
        uniformDist(uniform_name_2, numPoints_2, 0, 50, 0, 50);
        uniformDist(uniform_name_3, numPoints_3, 100, 200, 100, 200);

        // Normal
        // Combined

        // Data Loading and Initial Graphing

        loadFromFile(uniform_name_1, numPoints_1);
        loadFromFile(uniform_name_2, numPoints_2);
        loadFromFile(uniform_name_3, numPoints_3);



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

    /* writeToFile
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



    /* LOADING FUNCTIONS */


    /* loadFromFile
     * args: a file name, number of points in the file
     * output: returns array containing array of x coordinates and array of y coordinates
     * */
    public static Object [] loadFromFile(String filename, int numPoints){
        File file = new File (filename);
        FileInputStream fis;
        Scanner scan;

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];

        try{
            fis = new FileInputStream(file);
            scan = new Scanner(fis);

            for(int i = 0; i < numPoints; i++){
                xPoints[i] = scan.nextDouble();
                yPoints[i] = scan.nextDouble();
            }

            scan.close();
            fis.close();

            // return arrays of x and y values
            Object [] objs = {xPoints, yPoints};
            return objs;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    /* CHART AND GRAPHING FUNCTIONS */

    /* makeChart
     * args: Chart title, x axis title, y axis title
     * output: returns chart object with Scatter style
     * */
    public static XYChart makeChart(String title, String xAxisTitle, String yAxisTitle, int width, int height) {
        XYChart chart = new XYChartBuilder().title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).width(width).height(height).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);

        return chart;
    }

    /* displayChart
     * args: chart
     * output: displays chart in own window
     * */
    public static void displayChart(XYChart chart){
        new SwingWrapper<>(chart).displayChart();
    }

    /* addSeries
     * args: chart, name of series, array of x coordinates, array of y coordinates
     * output: displays chart in own window
     * */
    public static void addSeries(XYChart chart, String seriesName, double [] xPoints, double [] yPoints ){
        chart.addSeries(seriesName, xPoints, yPoints );
    }

    /* Model Application Functions */


    /* modelOne (Farthest Points)
     * args:
     * output:
     * */
    public static void modelOne(){

    }

    /* modelTwo (Center of Mass)
     * args:
     * output:
     * */
    public static void modelTwo(){

    }

    /* modelThree (Markov Chain)
     * args:
     * output:
     * */
    public static void modelThree(){

    }


}
