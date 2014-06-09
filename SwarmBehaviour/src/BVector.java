/**
 * This class defines vector for easy calculations.
 * 
 * @author kostadin
 */
public class BVector {
    private double x;
    private double y;

    public BVector(double a, double b) {
        x = a;
        y = b;
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

    public void addition(BVector a) {
        x += a.getX();
        y += a.getY();
    }

    public void divide(double i) {
        x /= i;
        y /= i;
    }

    public void substract(BVector a) {
        x -= a.getX();
        y -= a.getY();
    }

    public double norm() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void multiply(double arg) {
        x *= arg;
        y *= arg;
    }
}
