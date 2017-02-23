package opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {
	
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	private static final int FPS = 60;
	
	/*
	 * Create Display + Settings
	 */
	public static void open() {
		try {
			DisplayMode FULL_HD = new DisplayMode(WIDTH, HEIGHT);
			Display.setDisplayMode(FULL_HD);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	/*
	 * Update Display
	 */
	public static void update() {
		Display.sync(FPS);
		Display.update();
	}
	
	/*
	 * Close Display
	 */
	public static void close() {
		Display.destroy();
	}
	
}
