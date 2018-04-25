import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MarkovChain {
    public static void main(String [] args){

        uniformDist(5, 50, 50);

    }
    public static void uniformDist(int n, double maxX, double maxY){

        double [] xPoints = new double [n];
        double [] yPoints = new double [n];

        for(int i = 0; i < n; i++){
            xPoints[i] = randomWithRange( 0, maxX );
            yPoints[i] = randomWithRange( 0, maxY );
        }

        // Write to file
        //writeToFile("uniform_distribution_number.txt", xPoints, yPoints);

        // Generate Chart
        makeChart(xPoints, yPoints);
    }

    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }

    public static void makeChart(double [] xPoints, double [] yPoints){
        XYChart chart = new XYChartBuilder().title("Markov Chain Original Points").xAxisTitle("X").yAxisTitle("Y").width(50).height(50).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setChartTitleVisible(true);


        chart.addSeries("Data", xPoints, yPoints);
        //double [] c_of_m_x = {-71.07449180833332};
        //double [] c_of_m_y = {42.40020720833334};
        double [] x_1 = {15,35};
        double [] y_1 = {30,16};
        chart.addSeries("Points", x_1 , y_1  );

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
