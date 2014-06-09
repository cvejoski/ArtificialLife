package testfunctions;

import alg.PSOVector;

public class TestFunction21 extends ObjectiveFunction {

    public TestFunction21(double minBound, double maxBound) {
        super(minBound, maxBound);

    }

    @Override
    public double calculate(PSOVector x) {
        double result = 0;
        for ( int i = 0; i < x.getDimension(); i++ ) {
            double currCoord = x.getCoordinate(i);
            result += currCoord * Math.sin(currCoord) + currCoord * Math.cos(2 * currCoord);
        }
        return result;
    }
}
