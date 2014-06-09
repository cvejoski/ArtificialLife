package alg;

import testfunctions.ObjectiveFunction;

/**
 * This is class describing particles in PSO.
 * 
 * @author kostadin
 */
public class Particle {
    private final PSOVector position;
    private PSOVector velocity;
    private PSOVector bestPosition;
    private double bestResult;

    public Particle(int dimension, double minBound, double maxBound, int initType) {
        this.position = new PSOVector(dimension);
        this.velocity = new PSOVector(dimension);
        this.bestPosition = new PSOVector(dimension);
        this.bestResult = Double.MAX_VALUE;
        init(dimension, minBound, maxBound, initType);
    }

    /**
     * Type of initialization of coordinates and velocity of the particle.
     * 
     * @param dimension
     * @param minBound
     * @param maxBound
     * @param initType
     */
    private void init(int dimension, double minBound, double maxBound, int initType) {
        switch ( initType ) {
        //initialization type a
            case 97:
                initType_a(dimension, minBound, maxBound);
                break;
            //initialization type b
            case 98:
                initType_a(dimension, 0.05 * minBound, 0.05 * maxBound);
                break;
            //initialization type c
            case 99:
                initType_c(dimension, minBound, maxBound);
                break;
            default:
                throw new RuntimeException("Initialization method does not exists!");
        }

    }

    /**
     * Initialization type c of particle.
     * Only work with 3 dimensional case.
     * 
     * @param dimension
     * @param minBound
     * @param maxBound
     */
    private void initType_c(int dimension, double minBound, double maxBound) {
        double radius = 0.05 * Math.sqrt(Math.pow(minBound, 2) + Math.pow(maxBound, 2));
        double middle = (minBound + maxBound) / 2;
        double angle = randomBetween(0, 2 * Math.PI);

        this.position.setCoordinate(2, randomBetween(middle - radius, middle + radius));
        double tmp = Math.sqrt(Math.pow(radius, 2) - Math.pow(this.position.getCoordinate(2), 2));
        this.position.setCoordinate(0, tmp * Math.cos(angle));
        this.position.setCoordinate(1, tmp * Math.sin(angle));

        for ( int i = 0; i < dimension; i++ ) {
            this.velocity.setCoordinate(i, randomBetween(0.05 * minBound, 0.05 * maxBound));
        }
    }

    /**
     * Initialization type a of particle.
     * 
     * @param dimension
     * @param minBound
     * @param maxBound
     */
    private void initType_a(int dimension, double minBound, double maxBound) {
        for ( int i = 0; i < dimension; i++ ) {
            this.position.setCoordinate(i, randomBetween(minBound, maxBound));
            this.velocity.setCoordinate(i, randomBetween(minBound, maxBound));
        }
        this.bestPosition.setCoordinates(this.position.getCoordinates());
    }

    public PSOVector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(PSOVector velocity) {
        this.velocity.setCoordinates(velocity.getCoordinates());
    }

    public PSOVector getPosition() {
        return this.position;
    }

    public void setPosition(PSOVector position) {
        this.position.setCoordinates(position.getCoordinates());
    }

    public PSOVector getBestPosition() {
        return this.bestPosition;
    }

    public void setBestPosition(PSOVector bestPosition) {
        this.bestPosition.setCoordinates(bestPosition.getCoordinates());
    }

    public double getBestResult() {
        return this.bestResult;
    }

    public void setBestResult(double bestResult) {
        this.bestResult = bestResult;
    }

    public void calcVelocity(PSOVector gbPosition, double omega, double alpha, double betha, double gamma) {
        double R = Math.random();
        PSOVector result = new PSOVector(this.bestPosition.getDimension());
        result.add(this.velocity.multiply(omega));
        result.add(calcDirection(this.bestPosition, alpha, R));
        result.add(calcDirection(gbPosition, betha, R));
        result.add(calcDirection(gbPosition, gamma, R));
        this.velocity = result;
    }

    public void calcPosition(double minBound, double maxBound) {
        this.position.add(this.velocity);
        boundPosition(minBound, maxBound);
    }

    /**
     * Calculate part of the steering direction.
     * 
     * @param gbPosition
     * @param param
     * @param R
     * @return
     */
    private PSOVector calcDirection(PSOVector gbPosition, double param, double R) {
        PSOVector result = new PSOVector(this.bestPosition.getDimension());
        PSOVector tmp = new PSOVector(gbPosition.getDimension());
        tmp.setCoordinates(gbPosition.getCoordinates());
        result.add(tmp.substract(this.position));
        result.multiply(param * R);
        return result;
    }

    /**
     * Calculates new performance of the particle and check the bestResul.
     * 
     * @param of
     */
    public void calcPerformance(ObjectiveFunction of) {
        double result = of.calculate(this.position);
        if ( result < this.bestResult ) {
            this.bestResult = result;
            this.bestPosition = this.position;
        }
    }

    /**
     * Taking care of the position of the particle to be in the bounds.
     * 
     * @param minBound
     * @param maxBound
     */
    private void boundPosition(double minBound, double maxBound) {
        for ( int i = 0; i < this.position.getDimension(); i++ ) {
            if ( this.position.getCoordinate(i) < minBound || this.position.getCoordinate(i) > maxBound ) {
                this.position.setCoordinate(i, randomBetween(minBound, maxBound));
            }
        }
    }

    public void boundVelocity(double bound) {
        for ( int i = 0; i < this.velocity.getDimension(); i++ ) {
            if ( this.velocity.getCoordinate(i) > bound ) {
                normalizeVelocity();
            }
        }
    }

    private void normalizeVelocity() {
        double max = Double.MIN_VALUE;
        for ( int i = 0; i < this.velocity.getDimension(); i++ ) {
            if ( max < this.velocity.getCoordinate(i) ) {
                max = this.velocity.getCoordinate(i);
            }
        }
        this.velocity.divide(max);
    }

    private double randomBetween(double min, double max) {
        return min + Math.random() * (max - min);
    }
}
