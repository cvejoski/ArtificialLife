package alg;

import java.io.IOException;

import testfunctions.Hosaki;
import testfunctions.Rastrigin;
import testfunctions.TestFunction21;

public class Main {

    public static void main(String[] args) {
        Configuration config = Configuration.make();

        TestFunction21 test21 = new TestFunction21(config.getMinBound(), config.getMaxBound());
        Rastrigin rastrigin = new Rastrigin(config.getMinBound(), config.getMaxBound());
        Hosaki hosaki = new Hosaki(config.getMinBound(), config.getMaxBound());

        PSO pso = new PSO(config, hosaki);
        System.out.println("START");
        try {
            pso.run();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
