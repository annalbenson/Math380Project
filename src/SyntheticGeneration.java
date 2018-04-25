import org.knowm.xchart.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/* Charts made with the Java Library: https://knowm.org/open-source/xchart/ */



public class SyntheticGeneration {
    public static void main(String [] args){

        // print



        // uniform

        //uniformDist(10, 10, 10);

        bostonData();

        // normal



    }

    public static void bostonData(){

        File file = new File("boston_strangler_data.txt");
        int numPoints = 13;

        /*
        File file = new File("boston_strangler_data_no_outlier.txt");
        int numPoints = 12;
        */
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

        //Point [] points = new Point [n];
        double [] xPoints = new double [n];
        double [] yPoints = new double [n];
        double x; double y;
        for(int i = 0; i < n; i++){
            xPoints[i] = randomWithRange( 0, maxX );
            yPoints[i] = randomWithRange( 0, maxY );
            //System.out.println("(" + x + "," + y + ")");
            //points[i] = new Point (x,y);
        }
        //XYChart chart = QuickChart.getChart("Sample", "X", "Y", "y(x)", xPoints, yPoints );

        //new SwingWrapper<>(chart).displayChart();

        makeChart(xPoints, yPoints);

    }

    public static void makeChart(double [] xPoints, double [] yPoints){
        XYChart chart = new XYChartBuilder().title("Boston Strangler - All Crimes").xAxisTitle("Longitude").yAxisTitle("Latitude").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);


        chart.addSeries("Data", xPoints, yPoints);
        new SwingWrapper<>(chart).displayChart();
    }


    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }
}
