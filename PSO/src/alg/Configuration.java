package alg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is configuration class witch get the configuration parameter from file and passed as parameter to {@link PSO}.
 * 
 * @author kostadin
 */
public class Configuration {

    private int dimension;
    private int paricles;
    private int initialize;
    private double minBound;
    private double maxBound;
    private double velocityBound;
    private double omega, alpha, betha, gamma;

    private Configuration() {

    }

    /**
     * Reads the parameters from config file and create instance of {@link Configuration}
     * 
     * @return
     */
    public static Configuration make() {
        Configuration config = new Configuration();
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value
            config.setInitialize(prop.getProperty("initialization").charAt(0));
            config.setDimension(Integer.valueOf(prop.getProperty("dimension")));
            config.setMaxBound(Double.valueOf(prop.getProperty("maxbound")));
            config.setMinBound(Double.valueOf(prop.getProperty("minbound")));
            config.setVelocityBound(Double.valueOf(prop.getProperty("velocitybound")));
            config.setParicles(Integer.valueOf(prop.getProperty("particles")));
            config.setOmega(Double.valueOf(prop.getProperty("omega")));
            config.setAlpha(Double.valueOf(prop.getProperty("alpha")));
            config.setBetha(Double.valueOf(prop.getProperty("betha")));
            config.setGamma(Double.valueOf(prop.getProperty("gamma")));

        } catch ( IOException ex ) {
            ex.printStackTrace();
        } finally {
            if ( input != null ) {
                try {
                    input.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }

        return config;
    }

    private void setDimension(int dimension) {
        this.dimension = dimension;
    }

    private void setParicles(int paricles) {
        this.paricles = paricles;
    }

    private void setMinBound(double minBound) {
        this.minBound = minBound;
    }

    private void setMaxBound(double maxBound) {
        this.maxBound = maxBound;
    }

    private void setVelocityBound(double velocityBound) {
        this.velocityBound = velocityBound;
    }

    private void setOmega(double omega) {
        this.omega = omega;
    }

    private void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    private void setBetha(double betha) {
        this.betha = betha;
    }

    private void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getParicles() {
        return this.paricles;
    }

    public double getMinBound() {
        return this.minBound;
    }

    public double getMaxBound() {
        return this.maxBound;
    }

    public double getVelocityBound() {
        return this.velocityBound;
    }

    public double getOmega() {
        return this.omega;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public double getBetha() {
        return this.betha;
    }

    public double getGamma() {
        return this.gamma;
    }

    public int getInitialize() {
        return this.initialize;
    }

    private void setInitialize(int initialize) {
        this.initialize = initialize;
    }
}
