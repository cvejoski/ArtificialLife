package testfunctions;

import alg.PSOVector;

public abstract class ObjectiveFunction {

    private final double minBound;
    private final double maxBound;

    public ObjectiveFunction(double minBound, double maxBound) {
        this.minBound = minBound;
        this.maxBound = maxBound;
    }

    public double getMinBound() {
        return this.minBound;
    }

    public double getMaxBound() {
        return this.maxBound;
    }

    public abstract double calculate(PSOVector x);

}