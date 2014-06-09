package alg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import testfunctions.ObjectiveFunction;

/**
 * This is the main class of the algorithm.
 * 
 * @author kostadin
 */
public class PSO {
    private final Configuration config;
    private ArrayList<Particle> particles;

    private final PSOVector gbPosition;
    private double gbResult;

    private final ObjectiveFunction of;

    public PSO(Configuration conf, ObjectiveFunction of) {
        super();
        this.config = conf;
        this.particles = new ArrayList<Particle>(conf.getParicles());
        this.gbPosition = new PSOVector(conf.getDimension());
        this.gbResult = Double.MAX_VALUE;
        this.of = of;

        initParticles(conf.getDimension(), conf.getParicles(), of);
    }

    /**
     * Initialize all the particles.
     * 
     * @param dimension
     * @param particles
     * @param of
     */
    private void initParticles(int dimension, int particles, ObjectiveFunction of) {
        for ( int i = 0; i < particles; i++ ) {
            this.particles.add(new Particle(dimension, of.getMinBound(), of.getMaxBound(), this.config.getInitialize()));
        }
    }

    public ArrayList<Particle> getParticles() {
        return this.particles;
    }

    public void setParticles(ArrayList<Particle> paricles) {
        this.particles = paricles;
    }

    public PSOVector getBestPosition() {
        return this.gbPosition;
    }

    public double getBestResult() {
        return this.gbResult;
    }

    public void run() throws IOException {
        double delta = 0;
        int count = 0;
        int countDelta = 0;
        File file = new File("outut.dat");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        do {
            for ( Particle particle : this.particles ) {
                particle.calcVelocity(this.gbPosition, this.config.getOmega(), this.config.getAlpha(), this.config.getBetha(), this.config.getGamma());
                particle.boundVelocity(this.config.getVelocityBound());
                particle.calcPosition(this.of.getMinBound(), this.of.getMaxBound());
                particle.calcPerformance(this.of);
                delta = updateGlobalPerformance(particle);
                for ( int i = 0; i < this.config.getDimension(); i++ ) {
                    output.write(getBestPosition().getCoordinate(i) + "  ");
                }
            }
            output.write("\n");
            count++;
            //number of iteration in witch we do not have improvement of the best result of the objective function 
            if ( delta == 0 ) {
                countDelta++;
            }
        } while ( count < 500 && countDelta < 20 );
        output.close();

        System.out.println("BEST GLOBAL POSITION");
        for ( int i = 0; i < this.config.getDimension(); i++ ) {
            System.out.print(getBestPosition().getCoordinate(i) + " ");
        }
        System.out.println("\nValue: " + getBestResult());
        System.out.println("Iterations: " + count);
    }

    /**
     * Checks and updates the global performance of the algorithm.
     * 
     * @param particle
     * @return
     */
    private double updateGlobalPerformance(Particle particle) {
        double delta = 0;
        if ( this.gbResult > particle.getBestResult() ) {
            delta = this.gbResult - particle.getBestResult();
            this.gbPosition.setCoordinates(particle.getBestPosition().getCoordinates());
            this.gbResult = particle.getBestResult();
        }
        return Math.abs(delta);
    }

}
