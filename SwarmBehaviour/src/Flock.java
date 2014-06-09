import java.util.ArrayList;

/**
 * This is the basic class for creating Flocks
 * All the operations of movement of boids are controlled by this class
 * 
 * @author kostadin
 */
public class Flock {

    private final int neighborhoodRadius;
    private final int cohesionFactor;
    private final int separationRadius;
    private final int alignmentFactor;
    private final double speedLimit;

    private final ArrayList<Boid> boids = new ArrayList<Boid>();

    private final Rendering rendering = new Rendering(boids, 1000, 1000);

    public Flock(int nBoids, int cohesionFactor, int separationRadius, int allignmentFactor, int neighborhoodRadius, double speedLimit) {
        this.cohesionFactor = cohesionFactor;
        this.separationRadius = separationRadius;
        alignmentFactor = allignmentFactor;
        this.speedLimit = speedLimit;
        this.neighborhoodRadius = neighborhoodRadius;

        initialize(nBoids);
    }

    /**
     * Creating nBoid in 150 x 150 grid
     * 
     * @param nBoids number of boids
     */
    private void initialize(int nBoids) {
        for ( int i = 0; i < nBoids; i++ ) {

            try {
                boids.add(new Boid(i, new BVector(0, 0), new BVector(150 + Math.random() * 150 + 1, 150 + Math.random() * 150 + 1), 1000, 1000));
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculating every step and rendering the output
     */
    public void run() {
        while ( true ) {
            rendering.run();
            step();
        }
    }

    /**
     * Calculating the new positions of the boids
     */
    private void step() {
        BVector r1, r2, r3;
        for ( Boid boid : boids ) {
            r1 = cohesion(boid);
            r2 = separation(boid);
            r3 = alignment(boid);
            update(r1, r2, r3, boid);
        }
    }

    /**
     * update old parameters of boid with new
     * 
     * @param r1 rule 1
     * @param r2 rule 2
     * @param r3 rule 3
     * @param boid
     */
    private void update(BVector r1, BVector r2, BVector r3, Boid boid) {
        boid.getVelocity().addition(r1);
        boid.getVelocity().addition(r2);
        boid.getVelocity().addition(r3);
        boid.getPosition().addition(boid.getVelocity());
        boid.speedTune(speedLimit);
    }

    /**
     * Cohesion rule for boid
     * 
     * @param b
     * @return
     */
    private BVector cohesion(Boid b) {
        BVector result = new BVector(0, 0);
        int count = 0;
        for ( Boid boid : boids ) {
            if ( boid.getId() != b.getId() && b.distance(boid.getPosition()) < neighborhoodRadius ) {
                result.addition(boid.getPosition());
                count++;
            }
        }
        if ( count > 0 ) {
            result.divide(count);
        }
        result.substract(b.getPosition());
        result.divide(cohesionFactor);

        return result;
    }

    /**
     * Separation rule for boid
     * 
     * @param b
     * @return
     */
    private BVector separation(Boid b) {
        BVector result = new BVector(0, 0);
        int count = 0;
        for ( Boid boid : boids ) {
            if ( boid.getId() != b.getId() ) {
                if ( b.distance(boid.getPosition()) < separationRadius ) {
                    result.substract(boid.getPosition());
                    result.addition(b.getPosition());
                    result.divide(b.distance(boid.getPosition()));
                    count++;
                }
            }
        }

        if ( count > 0 ) {
            result.divide(count);
        }

        return result;

    }

    /**
     * Alignment rule for boid
     * 
     * @param b
     * @return
     */
    private BVector alignment(Boid b) {
        BVector result = new BVector(0, 0);
        int count = 0;
        for ( Boid boid : boids ) {
            if ( boid.getId() != b.getId() && boid.distance(b.getPosition()) < 70 ) {
                result.addition(boid.getVelocity());
                count++;
            }
        }
        if ( count > 0 ) {
            result.divide(count);
        }
        result.substract(b.getVelocity());
        result.divide(alignmentFactor);

        return result;
    }
}
