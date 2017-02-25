package opengl.chunk;

public class World {
	
	private final int SIZE = 32;
	private Chunk[][] chunks = new Chunk[SIZE][SIZE];
	private Generator generator;
	
	public World() {
		generator = new Generator();
	}
	
	public void generate(int x, int y) {
		chunks[x][y] = generator.generate(x,y);
	}
	
	public Chunk getChunk(int x, int y) {
		return chunks[x][y];
	}
	
	
}
