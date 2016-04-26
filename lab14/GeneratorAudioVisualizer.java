import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

public class GeneratorAudioVisualizer {
	private Generator generator;

	public GeneratorAudioVisualizer(Generator generator) {
		this.generator = generator;
	}

	public void drawAndPlay(int numSamplesDraw, int numSamplesPlay) {
		double[] xValues = new double[numSamplesDraw];
		double[] samples = new double[numSamplesDraw];
		for (int ii = 0; ii < numSamplesDraw; ii += 1) {
			xValues[ii] = ii;
			samples[ii] = generator.next();
		}

		// Create Chart
	    Chart chart = QuickChart.getChart("Generator Output", "X", "Y", "y(x)", xValues, samples);
	 
	    // Show it
	    new SwingWrapper(chart).displayChart();	

	    for (int jj = 0; jj < numSamplesDraw; jj += 1) {
	    	StdAudio.play(samples[jj]);
	    }	

	    int remainingSamples = numSamplesPlay - numSamplesDraw;
	    for (int jj = 0; jj < remainingSamples; jj += 1) {
	    	StdAudio.play(generator.next());
	    }
		
	}
} 