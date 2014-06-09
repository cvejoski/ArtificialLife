package alg;

/**
 * This is helper class for easy operation with vectors.
 * 
 * @author kostadin
 */
public class PSOVector {
    private final double[] coordinates;
    private final int dimension;

    public PSOVector(int dimension) {
        this.dimension = dimension;
        this.coordinates = new double[dimension];
        init();
    }

    /**
     * Initialize all cooridanates in vector to 0.
     */
    private void init() {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] = 0;
        }
    }

    /**
     * Get coordinates of vector.
     * 
     * @return array with coordinates
     */
    public double[] getCoordinates() {
        return this.coordinates;
    }

    /**
     * Set coordinates of a vector.
     * 
     * @param source
     */
    public void setCoordinates(double[] source) {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] = source[i];
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public double getCoordinate(int coordinate) {
        return this.coordinates[coordinate];
    }

    public void setCoordinate(int coordinate, double value) {
        this.coordinates[coordinate] = value;
    }

    /**
     * Add to the vector another vector
     * 
     * @param source vector
     * @return result.
     */
    public PSOVector add(PSOVector source) {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] += source.getCoordinate(i);
        }
        return this;
    }

    /**
     * Substract of the vector another vector
     * 
     * @param source vector
     * @return result.
     */
    public PSOVector substract(PSOVector source) {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] -= source.getCoordinate(i);
        }
        return this;
    }

    /**
     * Divide the vector another vector
     * 
     * @param source vector
     * @return result.
     */
    public PSOVector divide(double value) {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] /= value;
        }
        return this;
    }

    /**
     * Multiply the vector another vector
     * 
     * @param source vector
     * @return result.
     */
    public PSOVector multiply(double value) {
        for ( int i = 0; i < this.dimension; i++ ) {
            this.coordinates[i] *= value;
        }
        return this;
    }

    /**
     * Norm of the vector.
     * 
     * @return
     */
    public double norm() {
        double result = 0;
        for ( int i = 0; i < this.dimension; i++ ) {
            result += Math.pow(this.coordinates[i], 2);
        }
        return Math.sqrt(result);
    }
}