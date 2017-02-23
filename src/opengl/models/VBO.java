package opengl.models;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VBO {
	
	private int id;
	private int slot;
	private int count;
	private int[] idata;
	private float[] fdata;
	
	public VBO(int slot, int coordinatesize, float[] data) {
		
		this.slot = slot;
		this.count = data.length;
		this.id = GL15.glGenBuffers();
		this.fdata = data;
		Models.vbos.add(id);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		
		FloatBuffer fbuffer = BufferUtils.createFloatBuffer(data.length);
		fbuffer.put(data);
		fbuffer.flip();
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, fbuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(slot, coordinatesize, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	public VBO(int[] data) {
		
		this.count = data.length;
		this.slot = -1;
		this.id = GL15.glGenBuffers();
		this.idata = data;
		Models.vbos.add(id);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		
		IntBuffer ibuffer = BufferUtils.createIntBuffer(data.length);
		ibuffer.put(data);
		ibuffer.flip();
		
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, ibuffer, GL15.GL_STATIC_DRAW);
		
	}
	
	public VBO(int slot, int coordinatesize, int[] data) {
		
		this.slot = slot;
		this.count = data.length;
		this.id = GL15.glGenBuffers();
		this.idata = data;
		Models.vbos.add(id);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		
		IntBuffer ibuffer = BufferUtils.createIntBuffer(data.length);
		ibuffer.put(data);
		ibuffer.flip();
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, ibuffer, GL15.GL_STATIC_DRAW);
		GL30.glVertexAttribIPointer(slot, 1, GL11.GL_INT, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	public int getID() {
		return id;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public int getCount() {
		return count;
	}

	public int[] getIntData() {
		return idata;
	}

	public float[] getFloatData() {
		return fdata;
	}

	
	
}
