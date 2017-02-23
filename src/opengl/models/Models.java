package opengl.models;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Models {
	
	protected static List<Integer> vaos = new ArrayList<>();
	protected static List<Integer> vbos = new ArrayList<>();
	protected static List<Integer> texs = new ArrayList<>();
	
	public static void clean() {
		for (int vao : vaos) GL30.glDeleteVertexArrays(vao);
		for (int vbo : vbos) GL15.glDeleteBuffers(vbo);
		for (int tex : texs) GL11.glDeleteTextures(tex);
		vaos.clear();
		vbos.clear();
		texs.clear();
	}
	
}