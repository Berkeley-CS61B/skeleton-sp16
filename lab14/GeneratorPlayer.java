import edu.princeton.cs.introcs.StdAudio;

public class GeneratorPlayer {
	private Generator generator;

	public GeneratorPlayer(Generator generator) {
		this.generator = generator;
	}

	public void play(int numSamples) {
		for (int ii = 0; ii < numSamples; ii += 1) {
			StdAudio.play(generator.next());		
		}
	}

	public static void main(String[] args) {
		//MultiplicativeRandomGenerator rg = new MultiplicativeRandomGenerator(987123981723L, 7);
		//Generator generator = new StrangeBitwiseGenerator(1024);
		//Generator generator = new AcceleratingSawToothGenerator(1024, 0.98);
		//Generator generator = new SawToothGenerator(512);
		Generator generator = new SineWaveGenerator(61);
		GeneratorPlayer gp = new GeneratorPlayer(generator);
		gp.play(1000000);
	}
}