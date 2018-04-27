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
    public static final String NORMAL_NAME_1 = "normal_1.txt";
    public static final String NORMAL_NAME_2 = "normal_2.txt";
    public static final String NORMAL_NAME_3 = "normal_3.txt";


    public static final int NUM_POINTS_1 = 25; // used in data generation
    public static final int NUM_POINTS_2 = 50;
    public static final int NUM_POINTS_3 = 50;

    /* Graph Titles */
    public static final String UNIFORM_GRAPH_NAME_1 = "Uniform Graph 1";
    public static final String UNIFORM_GRAPH_NAME_2 = "Uniform Graph 2";
    public static final String UNIFORM_GRAPH_NAME_3 = "Uniform Graph 3";

    public static final String NORMAL_GRAPH_NAME_1 = "Normal Graph 1";
    public static final String NORMAL_GRAPH_NAME_2 = "Normal Graph 2";
    public static final String NORMAL_GRAPH_NAME_3 = "Normal Graph 3";

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


        /*
        // Data Generation
        uniformDist(UNIFORM_NAME_1, NUM_POINTS_1, 0, 10, 0, 10);
        //uniformDist(UNIFORM_NAME_2, NUM_POINTS_2, 0, 50, 0, 50);
        //uniformDist(UNIFORM_NAME_3, NUM_POINTS_3, 100, 200, 100, 200);

        normalDist(NORMAL_NAME_1, NUM_POINTS_1, 0, 100, 0, 100);

        // Combined

        // Data Loading and Initial Graphing

        Object [] result1 = loadFromFile(UNIFORM_NAME_1);
        //Object [] result2 = loadFromFile(UNIFORM_NAME_2);
        //Object [] result3 = loadFromFile(UNIFORM_NAME_3);

        XYChart uniformChart1 = makeChart(UNIFORM_GRAPH_NAME_1, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
        //XYChart uniformChart2 = makeChart(UNIFORM_GRAPH_NAME_2, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
        //XYChart uniformChart3 = makeChart(UNIFORM_GRAPH_NAME_3, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );

        addSeries(uniformChart1, "UniformTest", result1);

        XYChart normalChart1 = makeChart(NORMAL_GRAPH_NAME_1, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
        Object [] resultN1 = loadFromFile(NORMAL_NAME_1);
        addSeries(normalChart1,"Normal Test", resultN1 );



        displayChart(normalChart1);


        Object [] c_of_m = modelTwo(resultN1);
        //double [] xPoints = c_of_m[0];
        //double [] yPoints = c_of_m[1];

        addSeries(normalChart1, "c of m", c_of_m );

        displayChart(normalChart1);

        //displayChart(uniformChart1);
        */

        // (0,0) (0,1) (1,0) (5,5)
        double [] xPoints = {0,0,1,5};
        double [] yPoints = {0,1,0,5};
        Object [] resultModelOne = modelOne(xPoints, yPoints);

        XYChart testChart = makeChart("Test Chart", X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
        addSeries(testChart, "Points", xPoints, yPoints);
        addSeries(testChart, "Farthest Points", resultModelOne );
        displayChart(testChart);

    }

    /* Data Generation Functions */


    /* Generation function: uniformDist
     * args: a file name, number of data points to be generated, min and max x values, min and max y values
     * output: produces n ordered pairs with a uniform distribution with ranges:
     *          minX <= produced x values <= maxX & minY <= produced y values <= maxY.
     *          This data is then written to a file with name "filename"
     * */
    public static void uniformDist(String filename, int numPoints, double minX, double maxX, double minY, double maxY){

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];

        // Generate points
        for(int i = 0; i < numPoints; i++){
            xPoints[i] = randomWithRange( 0, maxX );
            yPoints[i] = randomWithRange( 0, maxY );
        }

        System.out.println("first point generated : (x,y) = "  + xPoints[0] + "," + yPoints[0]);

        // Write to file
        writeToFile(filename, xPoints, yPoints);

    }



    public static void normalDist(String filename, int numPoints, double minX, double maxX, double minY, double maxY){

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];


        // assume total range is 100
        // let buffer zone be 40 to 60
        // let first level be 30 to 70
        // let second level be 20 to 80
        // let final level be 0 to 100

        int firstNumPoints = (int) Math.floor(0.68 * numPoints);
        System.out.println("first num points = " + firstNumPoints);
        double xGen; double yGen;
        for(int i = 0; i < firstNumPoints; i++){

            xGen = randomWithRange(30,70);
            xPoints[i] = xGen;
            if( 30 < xGen && xGen < 40 ){
                // in former range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }
            if( 40 < xGen && xGen < 60 ){
                // needs y below or above buffer zone
                yGen = randomWithMultipleRanges(30,40,60,70);
                yPoints[i] = yGen;
            }
            if( 60 < xGen && xGen < 70){
                // in later range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }


            //xPoints[i] = randomWithMultipleRanges(30, 40, 60, 70 );
            //xPoints[i] = randomWithRange(30,70 );
            //yPoints[i] = randomWithMultipleRanges(30, 40, 60, 70);
            //yPoints[i] = randomWithRange(30, 70);

        }

        int secondNumPoints = (int) Math.floor(0.27 * numPoints);
        System.out.println("second num points = " + secondNumPoints);
        for(int i = firstNumPoints; i < (firstNumPoints + secondNumPoints); i++){


            xGen = randomWithRange(20,80);
            xPoints[i] = xGen;
            if( 20 < xGen && xGen < 30 ){
                // in former range, y value can be anything
                yGen = randomWithRange(20,80);
                yPoints[i] = yGen;
            }
            if( 30 < xGen && xGen < 70 ){
                // needs y below or above buffer zone
                yGen = randomWithMultipleRanges(20,30,70,80);
                yPoints[i] = yGen;
            }
            if( 70 < xGen && xGen < 80){
                // in later range, y value can be anything
                yGen = randomWithRange(20,80);
                yPoints[i] = yGen;
            }



            //xPoints[i] = randomWithMultipleRanges(20, 30, 70, 80 );
            //xPoints[i] = randomWithRange(20,80);
            //yPoints[i] = randomWithMultipleRanges(20, 30, 70, 80);
            //yPoints[i] = randomWithRange(20, 80);
        }


        int finalNumPoints = numPoints - firstNumPoints - secondNumPoints;
        System.out.println("final num points = " + finalNumPoints);
        System.out.println("Left to generate = " + finalNumPoints);

        for(int i = (firstNumPoints + secondNumPoints); i < numPoints; i++){

            xGen = randomWithRange(minX,maxX);
            xPoints[i] = xGen;
            if( minX < xGen && xGen < 20 ){
                // in former range, y value can be anything
                yGen = randomWithRange(minY,maxY);
                yPoints[i] = yGen;
            }
            if( 20 < xGen && xGen < 80 ){
                // needs y below or above buffer zone
                yGen = randomWithMultipleRanges(20,30,70,80);
                yPoints[i] = yGen;
            }
            if( 80 < xGen && xGen < maxX){
                // in later range, y value can be anything
                yGen = randomWithRange(minY,maxY);
                yPoints[i] = yGen;
            }




            //xPoints[i] = randomWithMultipleRanges(minX, 20, 80, maxX );
            //xPoints[i] = randomWithRange(minX,maxX );
            //yPoints[i] = randomWithMultipleRanges(minY, 20, 80, maxY);
            //yPoints[i] = randomWithRange(minY, maxY);
        }



        // Write to file
        writeToFile(filename, xPoints, yPoints);

    }


    /* Generation function: combinedDist
     * args: a file name, number of data points to be generated, min and max x values, min and max y values
     * output: produces n ordered pairs with a uniform/normal distribution with ranges:
     *          minX <= produced x values <= maxX & minY <= produced y values <= maxY.
     *          This data is then written to a file with name "filename"
     * */

    public static void combinedDist(String filename, int n, double minX, double maxX, double minY, double maxY){



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

    /* randomWithMultipleRanges
     * args: minimum range value, maximum range value
     * output: picks a range and then returns a random number x in the selected range
     * */
    public static double randomWithMultipleRanges(double min1, double max1, double min2, double max2){

        double whichRange = Math.random();
        if(whichRange <= 0.5){
            // use first interval
            double range = (max1 - min1);
            double rand = Math.random() * range + min1;
            return rand;

        }else{
            // use second interval
            double range = (max2 - min2);
            double rand = Math.random() * range + min2;
            return rand;
        }

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

            System.out.println("first point loaded: (x,y) = "  + xPoints[0] + "," + yPoints[0]);

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
    public static void addSeries(XYChart chart, String seriesName, Object [] points ){
        double [] xPoints = (double []) points[0];
        double [] yPoints = (double []) points[1];
        chart.addSeries(seriesName, xPoints, yPoints );
    }

    /* generateCircle
     * args: array of array of x coordinates and array of y coordinates
     *
     *
     * */
    public static Object [] generateCircle(Object [] points){
        // calculate center point and radius by extension
        // generate points to be that circle
        // return points

        double [] xPoints = (double []) points[0];
        double [] yPoints = (double []) points[1];

        double x1 = xPoints[0]; double y1 = yPoints[0];
        double x2 = xPoints[1]; double y2 = yPoints[1];

        double diameter = Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        double radius = diameter / 2.0;
        double centerX = Math.abs(x2 - x1) / 2.0;
        double centerY = Math.abs(y2 - y1) / 2.0;

        // circle = radius^2 = x^2 + y^2


        double [] xCirclePoints = new double [100];
        double [] yCirclePoints = new double [100];
        int counter;
        for(int i = 0; i < 100; i ++){

        }

        Object [] result = null;
        return result;
    }


    /* Model Application Functions */


    /* modelOne (Farthest Points)
     * args: array of x coordinates, array of y coordinates
     * output: two farthest points
     * */
    public static Object [] modelOne( double [] xPoints, double [] yPoints  ){

        // find most extreme points --> convex hull problem

        int numPoints = xPoints.length;

        double maxDistance = 0; // want to maximize distance
        double distance;
        double x1; double y1; double x2; double y2;
        int idx1 = 0; int idx2 = 0;
        for(int i = 0; i < numPoints; i++){
            for(int j = i+1; j < numPoints; j++){
                x1 = xPoints[i]; x2 = xPoints[j];
                y1 = yPoints[i]; y2 = yPoints[j];
                distance = Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
                if( distance > maxDistance ){
                    maxDistance = distance;
                    idx1 = i; idx2 = j;
                }
            }
        }

        x1 = xPoints[idx1]; y1 = yPoints[idx1];
        x2 = xPoints[idx2]; y2 = yPoints[idx2];
        System.out.println("Farthest Points: " + "(" + x1 + "," + y1 + ")" + " and " + "(" + x2 + "," + y2 + ")" );

        double [] xResult = {x1, x2};
        double [] yResult = {y1, y2};
        Object [] result = {xResult, yResult};
        return result;

    }


    /* modelTwo (Center of Mass)
     * args: array of x coordinates, array of y coordinates
     * output: center of mass ordered pair
     * */
    public static Object [] modelTwo(double [] xPoints, double [] yPoints){

        int numPoints = xPoints.length;
        double xAvg = 0; double yAvg = 0;
        for(int i = 0; i < numPoints; i ++ ){
            xAvg += xPoints[i];
            yAvg += yPoints[i];
        }
        xAvg /= numPoints;
        yAvg /= numPoints;

        double [] x = {xAvg};
        double [] y = {yAvg};
        Object [] c_of_m = {xAvg, yAvg};
        return  c_of_m;
    }

    /* modelTwo (Center of Mass)
     * args: array of arrays
     * output: center of mass ordered pair
     * */
    public static double [] modelTwo(Object [] points){

        double [] xPoints = (double []) points[0];
        double [] yPoints = (double []) points[1];
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

        // plot current point, predict, plot next, check
        // or plot 10 buildings and start somewhere




    }


}
