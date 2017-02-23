package opengl.inputs;

public class Mouse {
	
	/*
	 * TODO CLASS
	 */
	
	private static final int M_LEFT = 0;
	private static final int M_RIGHT = 1;
	public static int X = 0;
	public static int Y = 0;
	private static int n;
	private static boolean[] pressed = new boolean[2];
	private static boolean[] typed = new boolean[2];
	private static boolean[] released = new boolean[2];
	
	public static void init() {
		for (n=0;n<2;n++) released[n] = true;
	}
	
	public static void update() {
		
		for (n=0;n<2;n++) typed[n] = false;
		
		X = org.lwjgl.input.Mouse.getX();
		Y = 1000-org.lwjgl.input.Mouse.getY();
		pressed[M_LEFT] = org.lwjgl.input.Mouse.isButtonDown(0);
		pressed[M_RIGHT] = org.lwjgl.input.Mouse.isButtonDown(1);
		
		for (n=0;n<2;n++) {
			if (pressed[n] && released[n]) {
				released[n] = false;
				typed[n] = true;
			} else if (!pressed[n]) released[n] = true;
		}
	}
	
	public static boolean isButtonPressed(int button) {
		return pressed[button];
	}
	
	public static boolean isButtonTyped(int button) {
		return typed[button];
	}
	
}
