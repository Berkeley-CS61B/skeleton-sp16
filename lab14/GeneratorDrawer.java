import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

public class GeneratorDrawer {
	private Generator generator;

	public GeneratorDrawer(Generator generator) {
		this.generator = generator;
	}

	public void draw(int numSamples) {
		double[] xValues = new double[numSamples];
		double[] samples = new double[numSamples];
		for (int ii = 0; ii < numSamples; ii += 1) {
			xValues[ii] = ii;
			samples[ii] = generator.next();
		}

		// Create Chart
	    Chart chart = QuickChart.getChart("Generator Output", "X", "Y", "y(x)", xValues, samples);
	 
	    // Show it
	    new SwingWrapper(chart).displayChart();		
		
	}
	
	public static void main(String[] args) {
		Generator generator = new SineWaveGenerator(60);
		GeneratorDrawer gd = new GeneratorDrawer(generator);
		gd.draw(65536);
	}
}
