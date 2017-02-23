package opengl.render;

import opengl.shader.Shader;

public abstract class Render<SHADER extends Shader> {
	
	protected SHADER shader;
	
	public Render(SHADER shader) {
		this.shader = shader;
	}
	
	protected SHADER getShader() {
		return shader;
	}
	
	public void clean() {
		shader.clean();
	}
	
	protected abstract void render();
	
}
