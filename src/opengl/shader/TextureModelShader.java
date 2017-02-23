package opengl.shader;

public class TextureModelShader extends Shader {

	public TextureModelShader() {
		super("TextureModel.vsh", "TextureModel.fsh");
	}

	@Override
	protected void getAllUniformLocations() {
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertices");
		super.bindAttribute(1, "textures");
		super.bindAttribute(2, "normals");
	}

}
