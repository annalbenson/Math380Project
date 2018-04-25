public class Point {

    private double x;
    private double y;

    public Point(){
        setX(0);
        setY(0);
    }

    public Point(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point this, Point that){
        return Math.sqrt(  Math.pow(this.getX() - that.getX(),2) + Math.pow(this.getY() - that.getY(),2));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
