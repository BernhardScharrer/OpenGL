package opengl.chunk;

import org.lwjgl.util.vector.Matrix4f;

import opengl.models.ChunkModel;
import opengl.util.Maths;

public class Chunk {
	
	private int x,y;
	private ChunkModel model;
	
	public Chunk(int x, int y, ChunkModel model) {
		this.x = x;
		this.y = y;
		this.model = model;
	}
	
	public ChunkModel getModel() {
		return model;
	}

	public Matrix4f generateTM() {
		return Maths.createTM(x,y);
	}

}
