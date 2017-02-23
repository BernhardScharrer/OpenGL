package opengl.inputs;

public class Inputs {
	
	public static void init() {
		Mouse.init();
		KeyBoard.init();
	}
	
	public static void update() {
		Mouse.update();
		KeyBoard.update();
	}
	
}
