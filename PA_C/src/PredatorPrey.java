import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class PredatorPrey {

	private double a, b, c, d, e, f, g, h;
	private double x, y;
	
	public PredatorPrey(double x0, double y0, double a, double b, double c, double d, double e, double f) {
		this(x0, y0, a, b, c, d, e, f, 0., 0.);
	}
	
	public PredatorPrey(double x0, double y0, double a, double b, double c, double d, double e, double f, double g, double h) {
		this.x = x0;
		this.y = y0;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
	}
	
	/**
	 * Calculate new points on based on the previous calculate x and y
	 */
	public void newGeneration() {
		double newX, newY;
		
		newX = x + a*x + b*y + e*x*x + g*x*y;
		newY = y + c*x + d*y + f*y*y + h*x*y;
		
		x = newX;
		y = newY;
	}
	
	/**
	 * Calculate for every step new points and calculate the mean population size
	 * @param steps
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void iterate(int steps) throws FileNotFoundException, UnsupportedEncodingException {
		StringBuffer sbx = new StringBuffer(), sby = new StringBuffer();
		double meanX = x;
		double meanY = y;
		
		sbx.append("0 " + x + "\n");
		sby.append("0 " + y + "\n");
		
		for (int i = 1; i <= steps; ++i) {
			newGeneration();
			sbx.append(i + " " + x + "\n");
			sby.append(i + " " + y + "\n");
			meanX += x;
			meanY += y;
		}
		
		saveToFile("plotx.txt", sbx.toString());
		saveToFile("ploty.txt", sby.toString());
		
		meanX /= steps + 1;
		meanY /= steps + 1;
		
		System.out.println("Mean x: " + meanX);
		System.out.println("Mean y: " + meanY);
	}
	
	private void saveToFile(String filePath, String content) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filePath, "ASCII");
		
		writer.print(content);
		
		writer.close();
	}
	
	/**
	 * First set of parameters that yield almost stable oscillations.	
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void stable1() throws FileNotFoundException, UnsupportedEncodingException {
		double x0 = 5.;
		double y0 = 2.;
		double a = 0.01; // 0.1
		double b = 0.;
		double c = 0.;
		double d = -.005;
		double e = 0.;
		double f = 0.;
		double g = -.002;
		double h = .0004;
		PredatorPrey pp = new PredatorPrey(x0, y0, a, b, c, d, e, f, g, h);
		
		pp.iterate(5000);
	}
	
	/**
	 * Second set of parameters that yield almost stable oscillations
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void stable2() throws FileNotFoundException, UnsupportedEncodingException {
		double x0 = 5.;
		double y0 = 2.;
		double a = 0.004807;
		double b = 0.;
		double c = 0.;
		double d = -.009272;
		double e = 0.;
		double f = 0.;
		double g = -.0002482;
		double h = .0002756;
		PredatorPrey pp = new PredatorPrey(x0, y0, a, b, c, d, e, f, g, h);
		
		pp.iterate(5000);
	}
		
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PredatorPrey.stable2();
	}

}
