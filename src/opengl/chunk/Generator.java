package opengl.chunk;

import java.util.Random;

public class Generator {
	
	private static final Random RANDOM = new Random();
	private int seed;
	
	public Generator() {
		this.seed = RANDOM.nextInt(1000000000);
	}
	
	public Generator(int seed) {
		this.seed = seed;
	}
	
}
