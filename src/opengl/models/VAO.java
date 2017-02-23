package opengl.models;

public class VAO {
	
	private int id;
	private int vc;
	private VBO indices;
	private VBO[] vbos;
	
	public VAO(int id, int vc, VBO indices, VBO... vbos) {
		this.id = id;
		this.vc = vc;
		this.indices = indices;
		this.vbos = vbos;
	}
	
	public int getID() {
		return id;
	}
	
	public int getVC() {
		return vc;
	}
	
	public VBO getVBO(int slot) {
		return vbos[slot];
	}
	
	public VBO getIndices() {
		return indices;
	}
	
}
