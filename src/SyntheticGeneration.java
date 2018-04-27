//import org.knowm.xchart.*;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/* Charts made with the Java Library: https://knowm.org/open-source/xchart/ */



public class SyntheticGeneration {
    public static void main(String [] args){

        // print



        // uniform

        //uniformDist(10, 10, 10);

        //bostonData();

        //uniformDist(50,50,50);

        // normal

        //concentricSquares();
        int [] levelsPerCenter = {5,5};
        double [] xCenters = {0,5};
        double [] yCenters = {5,5};
        XYChart chart = squares("Graph Title", 2, levelsPerCenter, xCenters, yCenters );
        new SwingWrapper<>( chart ).displayChart();


    }

    public static void concentricSquares(){
        // used to produce arrays of f(x) = something
        int numPoints = 5;
        int numLevels = 5;


        double [] xPoints; double [] yPoints;

        XYChart chart = new XYChartBuilder().title("Concentric Squares").xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        //chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);

        //double [] xCenter = {0}; double [] yCenter = {5};
        //double [] x_lvl1 = {};
        //chart.addSeries("Center Point", xCenter, yCenter  );


        for(int i = 0; i < numLevels; i++){
            xPoints = new double [numPoints]; // need new objects
            yPoints = new double [numPoints];
            xPoints[0] = -1 * i; xPoints[1] = i; xPoints[2] = i; xPoints[3] = -1 * i; xPoints[4] = -1 * i;
            System.out.println(xPoints[0] + " " + xPoints[1]);
            yPoints[0] = i; yPoints[1] = i; yPoints[2] = -1 * i; yPoints[3] = -1 * i; yPoints[4] = i;
            System.out.println(yPoints[0] + " " + yPoints[1]);
            chart.addSeries("Lvl" + i, xPoints, yPoints);
        }

        new SwingWrapper<>(chart).displayChart();
    }

    public static void test(){
        final int numPoints = 5; // 5 points make a closed square
        int numLevelsA = 5;
        int numLevelsB = 4;


        double [] xPoints; double [] yPoints;
        double centerX; double centerY;
        double positive; double negative;

        XYChart chart = new XYChartBuilder().title("Concentric Squares - Two Event Points").xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        //chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);

        // centered at (0,0)
        centerX = 0; centerY = 0;
        for(int i = 0; i < numLevelsA; i++){
            xPoints = new double [numPoints]; yPoints = new double [numPoints];
            xPoints[0] = -1 * i; xPoints[1] = i; xPoints[2] = i; xPoints[3] = -1 * i; xPoints[4] = -1 * i;
            System.out.println(xPoints[0] + " " + xPoints[1]);
            yPoints[0] = i; yPoints[1] = i; yPoints[2] = -1 * i; yPoints[3] = -1 * i; yPoints[4] = i;
            System.out.println(yPoints[0] + " " + yPoints[1]);
            chart.addSeries("A Lvl" + i, xPoints, yPoints);
        }

        // centered at (5.5,5.5)
        centerX = 5.5; centerY = 5.5;

        for(int i = 0; i < numLevelsB; i++){
            xPoints = new double [numPoints]; yPoints = new double [numPoints];
            positive = i + centerX; negative = (-1*i) + centerY;

            xPoints[0] = negative; xPoints[1] = positive; xPoints[2] = positive; xPoints[3] = negative; xPoints[4] = negative;
            System.out.println(xPoints[0] + " " + xPoints[1]);
            yPoints[0] = positive; yPoints[1] = positive; yPoints[2] = negative; yPoints[3] = negative; yPoints[4] = positive;
            System.out.println(yPoints[0] + " " + yPoints[1]);
            chart.addSeries("B Lvl" + i, xPoints, yPoints);
        }

        new SwingWrapper<>(chart).displayChart();

    }

    public static XYChart squares(String title , int numCenters, int [] levelsPerCenter, double xCenters [], double yCenters [] ){

        final int numPoints = 5; // 5 points make a closed square
        double [] xPoints; double [] yPoints;
        double centerX; double centerY;
        double positive; double negative;
        int levels;

        XYChart chart = new XYChartBuilder().title(title).xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        //chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);


        for(int i = 0; i < numCenters; i ++){
            centerX = xCenters[i]; centerY = yCenters[i];
            levels = levelsPerCenter[i];
            for(int j = 0; j < levels; j++){
                xPoints = new double [numPoints]; yPoints = new double [numPoints];
                positive = j + centerX; negative = (-1*j) + centerY;

                xPoints[0] = negative; xPoints[1] = positive; xPoints[2] = positive; xPoints[3] = negative; xPoints[4] = negative;
                //System.out.println(xPoints[0] + " " + xPoints[1]);
                yPoints[0] = positive; yPoints[1] = positive; yPoints[2] = negative; yPoints[3] = negative; yPoints[4] = positive;
                //System.out.println(yPoints[0] + " " + yPoints[1]);
                chart.addSeries("Lvl" + i + "" + j, xPoints, yPoints);
            }
        }


        return chart;
    }



    public static void bostonData(){

        /*
        File file = new File("boston_strangler_data.txt");
        int numPoints = 13;
*/

        File file = new File("boston_strangler_data_no_outlier.txt");
        int numPoints = 12;

        FileInputStream fis = null;
        Scanner scan;

        double [] xPoints = new double [numPoints]; // known
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


            makeChart(xPoints, yPoints);

        } catch (Exception e){
            e.printStackTrace();
        }


    }






    public static void uniformDist(int n, double maxX, double maxY){

        double [] xPoints = new double [n];
        double [] yPoints = new double [n];

        for(int i = 0; i < n; i++){
            xPoints[i] = randomWithRange( 0, maxX );
            yPoints[i] = randomWithRange( 0, maxY );
        }

        // Write to file
        writeToFile("uniform_distribution_number.txt", xPoints, yPoints);

        // Generate Chart
        makeChart(xPoints, yPoints);
    }

    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }

    public static void makeChart(double [] xPoints, double [] yPoints){
        XYChart chart = new XYChartBuilder().title("Concentric Squares").xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);


        chart.addSeries("Data", xPoints, yPoints);
        //double [] c_of_m_x = {-71.07449180833332};
        //double [] c_of_m_y = {42.40020720833334};
        //chart.addSeries("C of M", c_of_m_x , c_of_m_y  );

        double [] xCenter = {5};
        double [] yCenter = {5};
        chart.addSeries("Center Point", xCenter, yCenter  );

        new SwingWrapper<>(chart).displayChart();
    }

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


}
