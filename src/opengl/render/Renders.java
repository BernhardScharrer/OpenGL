package opengl.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import opengl.models.Camera;

public class Renders {
	
	private static List<Render<?>> renders = new ArrayList<>();
	
	public static void init() {
		
		Camera.generatePM();
		
//		renders.add(new TextureModelRender());
		renders.add(new EntityRender());
	}
	
	public static void update() {
		clear();
		Camera.update();
		for (Render<?> render : renders) {
			render.getShader().start();
			render.render();
			render.getShader().stop();
		}
	}

	public static void clean() {
		for (Render<?> render : renders) {
			render.getShader().clean();
		}
	}
	
	private static void clear() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 1, 1, 1);
	}
	
}
