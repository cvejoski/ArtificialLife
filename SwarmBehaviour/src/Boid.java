/**
 * @author kostadin
 *         This class is used to describe single boid
 */

public class Boid {
    private final int id;

    private BVector velocity;
    private BVector position;

    private final int boundX;
    private final int boundY;

    public Boid(int id, BVector velocity, BVector position, int boundX, int boundY) {
        this.id = id;
        this.velocity = velocity;
        this.position = position;
        this.boundX = boundX;
        this.boundY = boundY;
    }

    public int getId() {
        return id;
    }

    public BVector getVelocity() {
        return velocity;
    }

    public void setVelocity(BVector velocity) {
        this.velocity = velocity;
    }

    public BVector getPosition() {
        return position;
    }

    public void setPosition(BVector position) {
        this.position = position;
    }

    /**
     * Calculates Euclidian distance.
     * 
     * @param a is the location to where we want to measure distance
     * @return distance between this boid and a
     */
    public double distance(BVector a) {
        double result = 0;
        result = Math.sqrt(Math.pow(position.getX() - a.getX(), 2) + Math.pow(position.getY() - a.getY(), 2));
        return result;
    }

    /**
     * Get speed from velocity
     * 
     * @return speed
     */
    private double getSpeed() {
        return velocity.norm();
    }

    /**
     * limit the speed of the boid to the speedLimit
     * 
     * @param speedLimit
     */
    public void speedTune(double speedLimit) {
        if ( getSpeed() > speedLimit ) {
            velocity.divide(getSpeed());
            velocity.multiply(speedLimit);
        }
    }

    /**
     * Position X of the boid for plotting in torus like grid
     * 
     * @return
     */
    public int[] getX() {
        int[] result = new int[3];

        int tmpX = modulo(position.getX(), boundX);

        if ( tmpX < 10 ) {
            tmpX += 10;
        }

        result[0] = tmpX - 10;
        result[1] = tmpX - 10;
        result[2] = tmpX;

        return result;
    }

    /**
     * Position Y of the boid for drawing a triangle in torus like grid
     * 
     * @return
     */
    public int[] getY() {
        int[] result = new int[3];

        int tmpY = modulo(position.getY(), boundY);

        if ( tmpY < 10 ) {
            tmpY += 10;
        }

        result[0] = tmpY - 5;
        result[1] = tmpY + 5;
        result[2] = tmpY;
        return result;
    }

    /**
     * Modulo operation for the torus topology to be able to manage the boundaries of the grid
     */
    int modulo(double d, int n) {
        return (int) (d >= 0 ? d % n : (n - Math.abs(d % n)) % n);
    }

}
