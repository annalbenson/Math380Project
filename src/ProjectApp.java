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

    /* File Names*/
    public static final String BOSTON_STRANGLER_NAME = "boston_strangler_data.txt";
    public static final String BOSTON_STRANGLER_NAME_NO_OUTLIER = "boston_strangler_data_no_outlier.txt";
    public static final String UNIFORM_NAME_1 = "uniform_1.txt";
    public static final String UNIFORM_NAME_2 = "uniform_2.txt";
    public static final String UNIFORM_NAME_3 = "uniform_3.txt";
    public static final String NORMAL_NAME_1 = "normal_1.txt";
    public static final String NORMAL_NAME_2 = "normal_2.txt";
    public static final String NORMAL_NAME_3 = "normal_3.txt";
    public static final String COMBINED_NAME_1 = "combined_1.txt";
    public static final String COMBINED_NAME_2 = "combined_2.txt";
    public static final String COMBINED_NAME_3 = "combined_3.txt";


    public static final int NUM_POINTS_1 = 5; // used in data generation
    public static final int NUM_POINTS_2 = 50;
    public static final int NUM_POINTS_3 = 50;

    /* Graph Titles */
    public static final String BOSTON_GRAPH_NAME_1 = "Boston Strangler Graph - All Data";
    public static final String BOSTON_GRAPH_NAME_2 = "Boston Strangler Graph - Outlier Removed";
    public static final String UNIFORM_GRAPH_NAME_1 = "Uniform Graph 1";
    public static final String UNIFORM_GRAPH_NAME_2 = "Uniform Graph 2";
    public static final String UNIFORM_GRAPH_NAME_3 = "Uniform Graph 3";
    public static final String NORMAL_GRAPH_NAME_1 = "Normal Graph 1";
    public static final String NORMAL_GRAPH_NAME_2 = "Normal Graph 2";
    public static final String NORMAL_GRAPH_NAME_3 = "Normal Graph 3";
    public static final String COMBINED_GRAPH_NAME_1 = "Combined Graph 1";
    public static final String COMBINED_GRAPH_NAME_2 = "Combined Graph 2";
    public static final String COMBINED_GRAPH_NAME_3 = "Combined Graph 3";

    /* Graph Axes Titles */
    public static final String X_AXIS_TITLE_X = "X";
    public static final String Y_AXIS_TITLE_Y = "Y";
    public static final String X_AXIS_TITLE_X_LONGITUDE = "Longitude";
    public static final String Y_AXIS_TITLE_Y_LATITUDE = "Latitude";


    public static void main(String [] args){

        /*
        // Boston Strangler -- All Data
        XYChart bostonStranglerChart1 = makeChart(BOSTON_GRAPH_NAME_1, X_AXIS_TITLE_X_LONGITUDE, Y_AXIS_TITLE_Y_LATITUDE, 100, 100);
        Object [] resultBS1 = loadFromFile(BOSTON_STRANGLER_NAME);
        addSeries(bostonStranglerChart1, "Data", resultBS1);
        // Model 1 - Circle
        double [] xPointsBS1 = (double []) resultBS1[0];
        double [] yPointsBS1 = (double []) resultBS1[1];
        Object [] resultModelOneBS1 = modelOne(xPointsBS1, yPointsBS1);
        addSeries(bostonStranglerChart1,"Farthest Points", resultModelOneBS1);
        // Model 2 - Center of Mass
        Object [] resultModelTwoBS1 = modelTwo(xPointsBS1,yPointsBS1);
        addSeries(bostonStranglerChart1,"Center of Mass", resultModelTwoBS1);
        // Model 3 - Markov
        // No Model 3 for this data
        // Display Graph
        displayChart(bostonStranglerChart1);
        */

        /*
        // Boston Strangler -- Outlier Removed
        XYChart bostonStranglerChart2 = makeChart(BOSTON_GRAPH_NAME_2, X_AXIS_TITLE_X_LONGITUDE, Y_AXIS_TITLE_Y_LATITUDE, 100, 100);
        Object [] resultBS2 = loadFromFile(BOSTON_STRANGLER_NAME_NO_OUTLIER);
        addSeries(bostonStranglerChart2, "Data", resultBS2);
        // Model 1 - Circle
        double [] xPointsBS2 = (double []) resultBS2[0];
        double [] yPointsBS2 = (double []) resultBS2[1];
        Object [] resultModelOneBS2 = modelOne(xPointsBS2, yPointsBS2);
        addSeries(bostonStranglerChart2,"Farthest Points", resultModelOneBS2);
        // Model 2 - Center of Mass
        Object [] resultModelTwoBS2 = modelTwo(xPointsBS2,yPointsBS2);
        addSeries(bostonStranglerChart2,"Center of Mass", resultModelTwoBS2);
        // Model 3 - Markov
        // Display Graph
        displayChart(bostonStranglerChart2);
        */

        // Uniform
        //uniformDist(UNIFORM_NAME_3, NUM_POINTS_1, 0, 100, 0, 100);
        //XYChart uniformChart1 = makeChart(UNIFORM_GRAPH_NAME_3, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
        //Object [] resultU1 = loadFromFile(UNIFORM_NAME_3);
        //addSeries(uniformChart1, "Data", resultU1);
        // Model 1 - Circle
        //double [] xPointsU1 = (double []) resultU1[0];
        //double [] yPointsU1 = (double []) resultU1[1];
        //Object [] resultModelOneU1 = modelOne(xPointsU1, yPointsU1);
        //addSeries(uniformChart1,"Farthest Points", resultModelOneU1);
        // Model 2 - Center of Mass
        //Object [] resultModelTwoU1 = modelTwo(xPointsU1,yPointsU1);
        //addSeries(uniformChart1,"Center of Mass", resultModelTwoU1);

        // Model 3 - Markov
        for(int i = 0; i < NUM_POINTS_1 - 1; i++ ){
            XYChart uniformChart1 = makeChart(UNIFORM_GRAPH_NAME_3 + " - points " + (i+1) + " and " + (i+2) , X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100 );
            Object [] resultU1 = loadFromFile(UNIFORM_NAME_3);
            modelThree(uniformChart1, resultU1, i, i + 1);
            displayChart(uniformChart1);
        }
        // Display Graph
        //displayChart(uniformChart1);


        // Normal
        //normalDist(NORMAL_NAME_3, NUM_POINTS_1, 0, 100, 0, 100);
        //XYChart normalChart1 = makeChart(NORMAL_GRAPH_NAME_3, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
        //Object [] resultN1 = loadFromFile(NORMAL_NAME_3);
        //addSeries(normalChart1,"Data", resultN1 );
        // Model 1 - Circle
        //double [] xPointsN1 = (double []) resultN1[0];
        //double [] yPointsN1 = (double []) resultN1[1];
        //Object [] resultModelOneN1 = modelOne(xPointsN1, yPointsN1);
        //addSeries(normalChart1, "Farthest Points", resultModelOneN1);
        // Model 2 - Center of Mass
        //Object [] resultModelTwoN1 = modelTwo(xPointsN1,yPointsN1);
        //addSeries(normalChart1,"Center of Mass", resultModelTwoN1);

        // Model 3 - Markov
        for(int i = 0; i < NUM_POINTS_1 - 1; i++ ){
            XYChart normalChart1 = makeChart(NORMAL_GRAPH_NAME_3 + " - points " + (i+1) + " and " + (i+2), X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
            Object [] resultN1 = loadFromFile(NORMAL_NAME_3);
            modelThree(normalChart1, resultN1, i, i + 1);
            displayChart(normalChart1);
        }

        // Display Graph
        //displayChart(normalChart1);


        // Combined
        //combinedDist(COMBINED_NAME_3, NUM_POINTS_1, 0, 100, 0, 100);
        //XYChart combinedChart1 = makeChart(COMBINED_GRAPH_NAME_3, X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
        //Object [] resultC1 = loadFromFile(COMBINED_NAME_3);
        //addSeries(combinedChart1,"Data", resultC1 );
        // Model 1 - Circle
        //double [] xPointsC1 = (double []) resultC1[0];
        //double [] yPointsC1 = (double []) resultC1[1];
        //Object [] resultModelOneC1 = modelOne(xPointsC1, yPointsC1);
        //addSeries(combinedChart1, "Farthest Points", resultModelOneC1);
        // Model 2 - Center of Mass
        //Object [] resultModelTwoC1 = modelTwo(xPointsC1,yPointsC1);
        //addSeries(combinedChart1,"Center of Mass", resultModelTwoC1);

        // Model 3 - Markov
        for(int i = 0; i < NUM_POINTS_1 - 1; i++ ){
            XYChart combinedChart1 = makeChart(COMBINED_GRAPH_NAME_3 + " - points " + (i+1) + " and " + (i+2), X_AXIS_TITLE_X, Y_AXIS_TITLE_Y, 100, 100);
            Object [] resultC1 = loadFromFile(COMBINED_NAME_3);
            modelThree(combinedChart1, resultC1, i, i + 1);
            displayChart(combinedChart1);
        }
        // Display Graph
        //displayChart(combinedChart1);

    }

    /* Data Generation Functions */


    /* Generation function: uniformDist
     * args: a file name, number of data points to be generated, min and max x values, min and max y values
     * output: produces numPoints ordered pairs with a uniform distribution with ranges:
     *          minX <= produced x values <= maxX & minY <= produced y values <= maxY.
     *          This data is then written to a file with name "filename"
     * */
    public static void uniformDist(String filename, int numPoints, double minX, double maxX, double minY, double maxY){

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];

        // Generate points
        for(int i = 0; i < numPoints; i++){
            xPoints[i] = randomWithRange( minX, maxX );
            yPoints[i] = randomWithRange( minY, maxY );
        }

        //System.out.println("first point generated : (x,y) = "  + xPoints[0] + "," + yPoints[0]);

        // Write to file
        writeToFile(filename, xPoints, yPoints);

    }



    public static void normalDist(String filename, int numPoints, double minX, double maxX, double minY, double maxY){

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];

        // assume total range is 100
        // let buffer zone be 45 to 55
        // let first level be 30 to 70
        // let second level be 20 to 80
        // let final level be 0 to 100

        int firstNumPoints = (int) Math.floor(0.68 * numPoints);
        System.out.println("first num points = " + firstNumPoints);
        double xGen; double yGen;
        for(int i = 0; i < firstNumPoints; i++){

            xGen = randomWithRange(30,70);
            xPoints[i] = xGen;
            if( 30 < xGen && xGen < 45 ){
                // in former range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }
            if( 45 < xGen && xGen < 55 ){
                // needs y below or above buffer zone
                yGen = randomWithMultipleRanges(30,45,55,70);
                yPoints[i] = yGen;
            }
            if( 55 < xGen && xGen < 70){
                // in later range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }
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

    public static void combinedDist(String filename, int numPoints, double minX, double maxX, double minY, double maxY){

        // Note: still buffer zone; do half points general uniform, half points normal

        double [] xPoints = new double [numPoints];
        double [] yPoints = new double [numPoints];


        int numPointsNormal =  (int) Math.floor(numPoints / 2);
        //int numPointsUniform =  numPoints - numPointsNormal;

        // Normal Generation:

        int firstNumPoints = (int) Math.floor(0.68 * numPointsNormal);
        //System.out.println("first num points = " + firstNumPoints);
        double xGen; double yGen;
        for(int i = 0; i < firstNumPoints; i++){

            xGen = randomWithRange(30,70);
            xPoints[i] = xGen;
            if( 30 < xGen && xGen < 45 ){
                // in former range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }
            if( 45 < xGen && xGen < 55 ){
                // needs y below or above buffer zone
                yGen = randomWithMultipleRanges(30,45,55,70);
                yPoints[i] = yGen;
            }
            if( 55 < xGen && xGen < 70){
                // in later range, y value can be anything
                yGen = randomWithRange(30,70);
                yPoints[i] = yGen;
            }

        }

        int secondNumPoints = (int) Math.floor(0.27 * numPointsNormal);
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

        }


        int finalNumPoints = numPointsNormal - firstNumPoints - secondNumPoints;
        System.out.println("final num points = " + finalNumPoints);
        System.out.println("Left to generate = " + finalNumPoints);

        for(int i = (firstNumPoints + secondNumPoints); i < numPointsNormal; i++){

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

        }


        //Need to make buffer zone

        // Uniform

        for(int i = numPointsNormal; i < numPoints; i++){
            xGen = randomWithRange( minX, maxX );
            xPoints[i] = xGen;
            if( 45 < xGen && xGen < 55 ){
                // y needs to be above or below buffer zone
                yPoints[i] = randomWithMultipleRanges(minY, 45, 55, maxY);
            } else{
                // y can be anything
                yPoints[i] = randomWithRange(minY, maxY);
            }

        }

        // Write to file
        writeToFile(filename, xPoints, yPoints);

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

            //System.out.println("first point loaded: (x,y) = "  + xPoints[0] + "," + yPoints[0]);

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


    /* Model Application Functions */


    /* modelOne (Farthest Points)
     * args: array of x coordinates, array of y coordinates
     * output: two farthest points
     * */
    public static Object [] modelOne( double [] xPoints, double [] yPoints  ){

        // find most extreme points --> convex hull problem

        int numPoints = xPoints.length;

        double maxDistance = 0; // want to maximize distance
        double distance = 0;
        double x1; double y1; double x2; double y2;
        int idx1 = -1; int idx2 = -1;
        for(int i = 0; i < numPoints; i++){
            for(int j = i; j < numPoints; j++){
                if( i != j ){
                    x1 = xPoints[i]; x2 = xPoints[j];
                    y1 = yPoints[i]; y2 = yPoints[j];
                    distance = Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
                    //System.out.println("Distance =" + distance);
                    if( distance >= maxDistance ){
                        maxDistance = distance;
                        //idx1 = i; idx2 = 11  ;
                        idx1 = i; idx2 = j;


                    }
                }
            }
        }

        System.out.println("idx1 = " + idx1);
        System.out.println("idx2 = " + idx2);
        x1 = xPoints[idx1]; y1 = yPoints[idx1];
        x2 = xPoints[idx2]; y2 = yPoints[idx2];
        //System.out.println("Farthest Points: " + "(" + x1 + "," + y1 + ")" + " and " + "(" + x2 + "," + y2 + ")" );

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
        Object [] c_of_m = {x, y};
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
     * args: chart, array with array of x coordinates and array of y coordinates, index of previous point, index of current point
     * output: chart with previous point graphed, expected area of next point, and current point graphed
     * */
    public static void modelThree(XYChart chart, Object [] points, int previous, int current){

        // graph previous point, current point, and previous point's zone

        // get x array and y array from points argument
        double [] xPoints = (double []) points[0];
        double [] yPoints = (double []) points[1];

        // get previous point in array form
        double[] xP = {xPoints[previous]}; double[] yP = {yPoints[previous]};
        addSeries(chart, "Previous Point (#" + (previous + 1) + ")", xP, yP);
        // get region for previous point
        concentricSquares(chart, 3, xP[0], yP[0], previous);
        // get current point in array form
        double[] xC = {xPoints[current]}; double[] yC = {yPoints[current]};
        addSeries(chart, "Current Point (#" + (current + 1) + ")", xC, yC);

        /*
        for(int i = 0; i < 1; i++) {

            // get point
            double[] x = {xPoints[i]}; double[] y = {yPoints[i]};
            // graph center
            addSeries(chart, "Point #" + i, x, y);
            // get levels
            concentricSquares(chart, 3, x[0], y[0], i);
        }
        */
    }


    /* concentricSquares
    *  args: chart, number of levels of squares, center point x and y, ordinal point number
    *  output:  graph with concentric squares representing regions where crimes are likely to occur
    *           surrounding given center point
    * */
    public static void concentricSquares( XYChart chart, int numLevels, double centerX, double centerY, int pointNumber ){

        int numPoints = 5;

        double [] xPoints; double [] yPoints;

        // centered at (centerX, centerY)
        double levels = (numLevels * 10) + 10; // Scale up due to graph's 100 by 100 dimensions
        //System.out.println("levels = " + levels);
        double positiveX; double negativeX;
        double positiveY; double negativeY;
        int counter = 0;
        for(int i = 10; i < levels; i += 10){
            positiveX = i + centerX; negativeX = (-1*i) + centerX;
            positiveY = i + centerY; negativeY = (-1*i) + centerY;
            xPoints = new double [numPoints]; yPoints = new double [numPoints];
            xPoints[0] = negativeX; xPoints[1] = positiveX; xPoints[2] = positiveX; xPoints[3] = negativeX; xPoints[4] = negativeX;
            //System.out.println(xPoints[0] + " " + xPoints[1]);
            yPoints[0] = positiveY; yPoints[1] = positiveY; yPoints[2] = negativeY; yPoints[3] = negativeY; yPoints[4] = positiveY;
            //System.out.println(yPoints[0] + " " + yPoints[1]);
            if( counter == 0){
                XYSeries series = chart.addSeries("Buffer for Point #" + (pointNumber + 1), xPoints, yPoints);
                series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
                counter++;
            }
            else{
                XYSeries series = chart.addSeries("Lvl" + counter + " for Point #" + (pointNumber + 1), xPoints, yPoints);
                series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
                counter++;
            }
        }
    }
}