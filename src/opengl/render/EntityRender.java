package opengl.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

import opengl.models.Camera;
import opengl.models.Entity;
import opengl.models.MaterialModel;
import opengl.shader.EntityShader;

public class EntityRender extends Render<EntityShader> {

	private Entity entity;

	public EntityRender() {
		super(new EntityShader());
		entity = new Entity(new MaterialModel("cube", "cube"), new Vector3f(), new Vector3f(0,0,0), 1);
	}

	@Override
	protected void render() {

//		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

		GL30.glBindVertexArray(entity.getModel().getVAO().getID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);

		shader.loadMaterials(entity.getModel().getMaterials());
		shader.loadTM(entity.generateTM());
		shader.loadVM(Camera.generateVM());
		shader.loadPM(Camera.getPM());
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVAO().getVC(), GL11.GL_UNSIGNED_INT, 0);

		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);

	}

}
