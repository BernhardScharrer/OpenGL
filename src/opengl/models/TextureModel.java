package opengl.models;

public class TextureModel extends Model{
	
	private Texture tex;
	
	public TextureModel(String obj, String tex) {
		super(obj, tex);
	}
	
	public Texture getTex() {
		return tex;
	}

	@Override
	protected VAO loadModel(String... files) {
		this.tex = new Texture(files[1]);
		return Loader.loadTexturedModel(files[0]);
	}
	
}
