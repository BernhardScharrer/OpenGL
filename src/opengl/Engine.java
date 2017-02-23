package opengl;

import org.lwjgl.opengl.Display;

import opengl.inputs.Inputs;
import opengl.models.Models;
import opengl.render.Renders;

public class Engine {
	
	public static void main(String[] args) {
		Window.open();
		Inputs.init();
		Renders.init();
		
		while (!Display.isCloseRequested()) {
			
			Inputs.update();
			Renders.update();
			
			Window.update();
			
		}
		
		Models.clean();
		Renders.clean();
		Window.close();
		
	}
	
}
