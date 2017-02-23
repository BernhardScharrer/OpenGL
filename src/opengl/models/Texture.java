package opengl.models;

public class Texture {
	
	private int id;
	
	public Texture(String file) {
		this.id = Loader.loadTexture(file);
	}
	
	public Texture(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
