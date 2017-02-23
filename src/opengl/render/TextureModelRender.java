package opengl.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import opengl.models.TextureModel;
import opengl.shader.TextureModelShader;

public class TextureModelRender extends Render<TextureModelShader> {
	
	
	private TextureModel model;

	public TextureModelRender() {
		super(new TextureModelShader());
		model = new TextureModel("cubewithtex", "img");
	}

	@Override
	protected void render() {
		
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		GL30.glBindVertexArray(model.getVAO().getID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVAO().getVC(), GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
	}

}
