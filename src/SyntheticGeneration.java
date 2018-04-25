public class SyntheticGeneration {
    public static void main(String [] args){

        // print



        // uniform

        uniformDist(10, 100, 100);



        // normal



    }
    public static void uniformDist(int n, double maxX, double maxY){

        Point [] points = new Point [n];
        double x; double y;
        for(int i = 0; i < n; i++){
            x = randomWithRange( 0, maxX );
            y = randomWithRange( 0, maxY );
            System.out.println("(" + x + "," + y + ")");
            points[i] = new Point (x,y);
        }

    }

    public static double randomWithRange(double min, double max){
        double range = (max - min);
        double rand = Math.random() * range + min;
        return rand;
    }
}
