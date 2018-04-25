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

        bostonData();

        //uniformDist(50,50,50);

        // normal



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
        XYChart chart = new XYChartBuilder().title("Boston Strangler - Outlier Removed & Center of Mass").xAxisTitle("Longitude").yAxisTitle("Latitude").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);


        chart.addSeries("Data", xPoints, yPoints);
        double [] c_of_m_x = {-71.07449180833332};
        double [] c_of_m_y = {42.40020720833334};
        chart.addSeries("C of M", c_of_m_x , c_of_m_y  );
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
