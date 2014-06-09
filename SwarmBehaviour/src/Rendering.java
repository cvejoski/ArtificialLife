import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rendering extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JFrame frame;
    private final ArrayList<Boid> boids;

    public Rendering(ArrayList<Boid> boids, int width, int height) {
        frame = new JFrame("Swarm Behavior");

        this.boids = boids;
        initFrame(width, height);
    }

    private void initFrame(int width, int height) {
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void run() {
        try {
            Thread.sleep(10);
        } catch ( InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        repaint();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 192, 0));

        for ( Boid boid : boids ) {
            g.fillPolygon(boid.getX(), boid.getY(), 3);
            //g.drawPolygon(boid.getX(), boid.getY(), 3);
        }
    }

}
