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

    public static final String UNIFORM_NAME_1 = "uniform_1.txt";
    public static final String UNIFORM_NAME_2 = "uniform_2.txt";
    public static final String UNIFORM_NAME_3 = "uniform_3.txt";


    public static final int NUM_POINTS_1 = 50; // used in data generation
    public static final int NUM_POINTS_2 = 50;
    public static final int NUM_POINTS_3 = 50;

    /* Graph Titles */
    public static final String UNIFORM_GRAPH_NAME_1 = "Uniform Graph 1";
    public static final String UNIFORM_GRAPH_NAME_2 = "Uniform Graph 2";
    public static final String UNIFORM_GRAPH_NAME_3 = "Uniform Graph 3";

    /* Graph Axes Titles */
    public static final String X_AXIS_TITLE_X = "X";
    public static final String Y_AXIS_TITLE_Y = "Y";
    public static final String X_AXIS_TITLE_X_LONGITUDE = "Longitude";
    public static final String Y_AXIS_TITLE_Y_LATITUDE = "Latitude";





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
        uniformDist(UNIFORM_NAME_1, NUM_POINTS_1, 0, 10, 0, 10);
        uniformDist(UNIFORM_NAME_2, NUM_POINTS_2, 0, 50, 0, 50);
        uniformDist(UNIFORM_NAME_3, NUM_POINTS_3, 100, 200, 100, 200);

        // Normal
        // Combined

        // Data Loading and Initial Graphing

        Object [] result1 = loadFromFile(UNIFORM_NAME_1);
        Object [] result2 = loadFromFile(UNIFORM_NAME_2);
        Object [] result3 = loadFromFile(UNIFORM_NAME_3);

        XYChart uniformChart1 = makeChart(UNIFORM_GRAPH_NAME_1, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
        XYChart uniformChart2 = makeChart(UNIFORM_GRAPH_NAME_2, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
        XYChart uniformChart3 = makeChart(UNIFORM_GRAPH_NAME_3, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );

        uniformChart1.addSeries(result1);


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




    /* randomWithRange
     * args: minimum range value, maximum range value
     * output: returns random number x in range min <= x <= max
     * */
    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }


    /* FILE FUNCTIONS */

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

            pw.println(numPoints); // so future file readers know how many lines to expect

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


    /* loadFromFile
     * args: a file name
     * output: returns array containing the x coordinate array and the y coordinate array
     * */
    public static Object [] loadFromFile(String filename){
        File file = new File (filename);
        FileInputStream fis;
        Scanner scan;

        double [] xPoints; // will hold loaded points
        double [] yPoints;
        int numPoints; // first line of file contains an int so we can create appropriately sized arrays

        try{
            fis = new FileInputStream(file);
            scan = new Scanner(fis);

            if(scan.hasNext()){
                // non empty file
                numPoints = scan.nextInt();
            }
            else{
                System.out.println("No num points value, improper file, returning");
                return null;
            }

            // create arrays
            xPoints = new double [numPoints];
            yPoints = new double [numPoints];

            // load arrays
            for(int i = 0; i < numPoints; i++){
                xPoints[i] = scan.nextDouble();
                yPoints[i] = scan.nextDouble();
            }

            scan.close();
            fis.close();

            // return arrays
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

    /* addSeries ( separate arrays )
     * args: chart, name of series, array of x coordinates, array of y coordinates
     * output: adds series to chart object reference
     * */
    public static void addSeries(XYChart chart, String seriesName, double [] xPoints, double [] yPoints ){
        chart.addSeries(seriesName, xPoints, yPoints );
    }

    /* addSeries ( Object array )
     * args: chart, name of series, array of array of x coordinates and array of y coordinates
     * output: adds series to chart object reference
     * */
    public static void addSeries(XYChart chart, String seriesName, Object [] arrays ){
        double [] xPoints = (double []) arrays[0];
        double [] yPoints = (double []) arrays[0];
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
     * args: array of x coordinates, array of y coordinates
     * output: center of mass ordered pair
     * */
    public static double [] modelTwo(double [] xPoints, double [] yPoints){

        int numPoints = xPoints.length;
        double xAvg = 0; double yAvg = 0;
        for(int i = 0; i < numPoints; i ++ ){
            xAvg += xPoints[i];
            yAvg += yPoints[i];
        }
        xAvg /= numPoints;
        yAvg /= numPoints;

        double [] c_of_m = {xAvg, yAvg};
        return  c_of_m;
    }

    /* modelThree (Markov Chain)
     * args:
     * output:
     * */
    public static void modelThree(){

    }


}
