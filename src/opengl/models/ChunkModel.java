package opengl.models;

import org.lwjgl.opengl.GL30;

public class ChunkModel {

	private VAO vao;
	
	
	public ChunkModel(int[] indices, float[] vertices, float[] colors, float[] normals) {
		
		int id = GL30.glGenVertexArrays();
		Models.vaos.add(id);
		GL30.glBindVertexArray(id);
		vao = new VAO(id, indices.length, new VBO(indices), new VBO(0, 3, vertices), new VBO(1, 3, colors), new VBO(2, 3, normals));
		GL30.glBindVertexArray(0);
		
	}
	
	public VAO getVAO() {
		return vao;
	}

}
