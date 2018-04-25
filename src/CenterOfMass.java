import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class CenterOfMass {
    public static void main(String [] args){

        // Load File

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

        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        // Calculate C of M of the points
        double xAvg = 0; double yAvg = 0;
        for(int i = 0; i < numPoints; i ++ ){
            xAvg += xPoints[i];
            yAvg += yPoints[i];
        }
        xAvg /= numPoints;
        yAvg /= numPoints;

        System.out.println("Center of Mass is (" + xAvg + "," + yAvg + ")"); // (Long, Lat)

    }
}
