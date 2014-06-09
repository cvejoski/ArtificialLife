package testfunctions;

import alg.PSOVector;

public class Hosaki extends ObjectiveFunction {

    public Hosaki(double minBound, double maxBound) {
        super(minBound, maxBound);
    }

    @Override
    public double calculate(PSOVector x) {
        double result = 0;
        double x1 = x.getCoordinate(0);
        double x2 = x.getCoordinate(1);

        result = (1 - 8 * x1 + 7 * Math.pow(x1, 2) - 7.0 / 3.0 * Math.pow(x1, 3) + 0.25 * Math.pow(x1, 4)) * Math.pow(x2, 2) * Math.pow(Math.E, -1 * x2);
        return result;
    }
}
