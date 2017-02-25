package opengl.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import opengl.chunk.Chunk;
import opengl.chunk.World;
import opengl.models.Camera;

public class ChunkRender extends Render<ChunkShader> {

	private Chunk chunk;

	public ChunkRender() {
		super(new ChunkShader());
		World world = new World();
		world.generate(0, 0);
		chunk = world.getChunk(0, 0);
	}

	@Override
	protected void render() {

		GL30.glBindVertexArray(chunk.getModel().getVAO().getID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);

		shader.loadTM(chunk.generateTM());
		shader.loadVM(Camera.generateVM());
		shader.loadPM(Camera.getPM());
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		GL11.glDrawElements(GL11.GL_TRIANGLES, chunk.getModel().getVAO().getVC(), GL11.GL_UNSIGNED_INT, 0);

		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
	}

}
