package testfunctions;

import alg.PSOVector;

public class Rastrigin extends ObjectiveFunction {

    public Rastrigin(double minBound, double maxBound) {
        super(minBound, maxBound);
    }

    @Override
    public double calculate(PSOVector x) {
        double result = 0;
        for ( int i = 0; i < x.getDimension(); i++ ) {
            double currX = x.getCoordinate(i);
            result += Math.pow(currX, 2) - 10.0 * Math.cos(2 * Math.PI * currX);
        }
        return result + 10.0 * x.getDimension();
    }
}
