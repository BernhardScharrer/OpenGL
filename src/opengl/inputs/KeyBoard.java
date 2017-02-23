package opengl.inputs;

import org.lwjgl.input.Keyboard;

public class KeyBoard {
	
	public static final int KEY_A = 0;
	public static final int KEY_B = 1;
	public static final int KEY_C = 2;
	public static final int KEY_D = 3;
	public static final int KEY_E = 4;
	public static final int KEY_F = 5;
	public static final int KEY_G = 6;
	public static final int KEY_H = 7;
	public static final int KEY_I = 8;
	public static final int KEY_J = 9;
	public static final int KEY_K = 10;
	public static final int KEY_L = 11;
	public static final int KEY_M = 12;
	public static final int KEY_N = 13;
	public static final int KEY_O = 14;
	public static final int KEY_P = 15;
	public static final int KEY_Q = 16;
	public static final int KEY_R = 17;
	public static final int KEY_S = 18;
	public static final int KEY_T = 19;
	public static final int KEY_U = 20;
	public static final int KEY_V = 21;
	public static final int KEY_W = 22;
	public static final int KEY_X = 23;
	public static final int KEY_Y = 24;
	public static final int KEY_Z = 25;
	public static final int KEY_0 = 26;
	public static final int KEY_1 = 27;
	public static final int KEY_2 = 28;
	public static final int KEY_3 = 29;
	public static final int KEY_4 = 30;
	public static final int KEY_5 = 31;
	public static final int KEY_6 = 32;
	public static final int KEY_7 = 33;
	public static final int KEY_8 = 34;
	public static final int KEY_9 = 35;
	public static final int KEY_UNDERLINE = 36;
	public static final int KEY_SPACE = 37;
	public static final int KEY_TAB = 38;
	public static final int KEY_ESCAPE = 39;
	public static final int KEY_BACK = 40;
	public static final int KEY_SLASH = 41;
	public static final int KEY_BACKSLASH = 42;
	public static final int KEY_RCONTROL = 43;
	public static final int KEY_LCONTROL = 44;
	public static final int KEY_RSHIFT = 45;
	public static final int KEY_LSHIFT = 46;
	public static final int KEY_COMMA = 47;
	public static final int KEY_CAPITAL = 48;
	public static final int KEY_SEMICOLON = 49;
	public static final int KEY_UP = 50;
	public static final int KEY_DOWN = 51;
	public static final int KEY_LEFT = 52;
	public static final int KEY_RIGHT = 53;
	
	private static boolean[] pressed = new boolean[100];
	private static boolean[] released = new boolean[100];
	private static boolean[] typed = new boolean[100];
	private static int n = 0;

	public static void init() {
		for (n=0;n<100;n++) released[n] = true;
	}
	
	public static void update() {
		for (n=0;n<50;n++) typed[n] = false;
		pressed[KEY_A] = Keyboard.isKeyDown(Keyboard.KEY_A);
		pressed[KEY_B] = Keyboard.isKeyDown(Keyboard.KEY_B);
		pressed[KEY_C] = Keyboard.isKeyDown(Keyboard.KEY_C);
		pressed[KEY_D] = Keyboard.isKeyDown(Keyboard.KEY_D);
		pressed[KEY_E] = Keyboard.isKeyDown(Keyboard.KEY_E);
		pressed[KEY_F] = Keyboard.isKeyDown(Keyboard.KEY_F);
		pressed[KEY_G] = Keyboard.isKeyDown(Keyboard.KEY_G);
		pressed[KEY_H] = Keyboard.isKeyDown(Keyboard.KEY_H);
		pressed[KEY_I] = Keyboard.isKeyDown(Keyboard.KEY_I);
		pressed[KEY_J] = Keyboard.isKeyDown(Keyboard.KEY_J);
		pressed[KEY_K] = Keyboard.isKeyDown(Keyboard.KEY_K);
		pressed[KEY_L] = Keyboard.isKeyDown(Keyboard.KEY_L);
		pressed[KEY_M] = Keyboard.isKeyDown(Keyboard.KEY_M);
		pressed[KEY_N] = Keyboard.isKeyDown(Keyboard.KEY_N);
		pressed[KEY_O] = Keyboard.isKeyDown(Keyboard.KEY_O);
		pressed[KEY_P] = Keyboard.isKeyDown(Keyboard.KEY_P);
		pressed[KEY_Q] = Keyboard.isKeyDown(Keyboard.KEY_Q);
		pressed[KEY_R] = Keyboard.isKeyDown(Keyboard.KEY_R);
		pressed[KEY_S] = Keyboard.isKeyDown(Keyboard.KEY_S);
		pressed[KEY_T] = Keyboard.isKeyDown(Keyboard.KEY_T);
		pressed[KEY_U] = Keyboard.isKeyDown(Keyboard.KEY_U);
		pressed[KEY_V] = Keyboard.isKeyDown(Keyboard.KEY_V);
		pressed[KEY_W] = Keyboard.isKeyDown(Keyboard.KEY_W);
		pressed[KEY_X] = Keyboard.isKeyDown(Keyboard.KEY_X);
		pressed[KEY_Y] = Keyboard.isKeyDown(Keyboard.KEY_Y);
		pressed[KEY_Z] = Keyboard.isKeyDown(Keyboard.KEY_Z);
		pressed[KEY_0] = Keyboard.isKeyDown(Keyboard.KEY_0);
		pressed[KEY_1] = Keyboard.isKeyDown(Keyboard.KEY_1);
		pressed[KEY_2] = Keyboard.isKeyDown(Keyboard.KEY_2);
		pressed[KEY_3] = Keyboard.isKeyDown(Keyboard.KEY_3);
		pressed[KEY_4] = Keyboard.isKeyDown(Keyboard.KEY_4);
		pressed[KEY_5] = Keyboard.isKeyDown(Keyboard.KEY_5);
		pressed[KEY_6] = Keyboard.isKeyDown(Keyboard.KEY_6);
		pressed[KEY_7] = Keyboard.isKeyDown(Keyboard.KEY_7);
		pressed[KEY_8] = Keyboard.isKeyDown(Keyboard.KEY_8);
		pressed[KEY_9] = Keyboard.isKeyDown(Keyboard.KEY_9);
		pressed[KEY_UNDERLINE] = Keyboard.isKeyDown(Keyboard.KEY_UNDERLINE);
		pressed[KEY_SPACE] = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		pressed[KEY_TAB] = Keyboard.isKeyDown(Keyboard.KEY_TAB);
		pressed[KEY_ESCAPE] = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
		pressed[KEY_BACK] = Keyboard.isKeyDown(Keyboard.KEY_BACK);
		pressed[KEY_SLASH] = Keyboard.isKeyDown(Keyboard.KEY_SLASH);
		pressed[KEY_BACKSLASH] = Keyboard.isKeyDown(Keyboard.KEY_BACKSLASH);
		pressed[KEY_RCONTROL] = Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
		pressed[KEY_LCONTROL] = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
		pressed[KEY_RSHIFT] = Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		pressed[KEY_LSHIFT] = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		pressed[KEY_COMMA] = Keyboard.isKeyDown(Keyboard.KEY_COMMA);
		pressed[KEY_CAPITAL] = Keyboard.isKeyDown(Keyboard.KEY_CAPITAL);
		pressed[KEY_SEMICOLON] = Keyboard.isKeyDown(Keyboard.KEY_SEMICOLON);
		pressed[KEY_UP] = Keyboard.isKeyDown(Keyboard.KEY_UP);
		pressed[KEY_DOWN] = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		pressed[KEY_LEFT] = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		pressed[KEY_RIGHT] = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		
		for (n=0;n<50;n++) {
			if (pressed[n] && released[n]) {
				released[n] = false;
				typed[n] = true;
			} else if (!pressed[n]) released[n] = true;
		}
		
	}
	
	public static boolean isKeyPressed(int keycode) {
		return pressed[keycode];
	}
	
	public static boolean isKeyTyped(int keycode) {
		return typed[keycode];
	}
	
	public static String getCharByKeycode(int keycode) {
		boolean shift = isKeyPressed(KEY_RSHIFT) || isKeyPressed(KEY_LSHIFT);
		switch (keycode) {
		case KEY_A: return (shift ? "A" : "a");
		case KEY_B: return (shift ? "B" : "b");
		case KEY_C: return (shift ? "C" : "c");
		case KEY_D: return (shift ? "D" : "d");
		case KEY_E: return (shift ? "E" : "e");
		case KEY_F: return (shift ? "F" : "f");
		case KEY_G: return (shift ? "G" : "g");
		case KEY_H: return (shift ? "H" : "h");
		case KEY_I: return (shift ? "I" : "i");
		case KEY_J: return (shift ? "J" : "j");
		case KEY_K: return (shift ? "K" : "k");
		case KEY_L: return (shift ? "L" : "l");
		case KEY_M: return (shift ? "M" : "m");
		case KEY_N: return (shift ? "N" : "n");
		case KEY_O: return (shift ? "O" : "o");
		case KEY_P: return (shift ? "P" : "p");
		case KEY_Q: return (shift ? "Q" : "q");
		case KEY_R: return (shift ? "R" : "r");
		case KEY_S: return (shift ? "S" : "s");
		case KEY_T: return (shift ? "T" : "t");
		case KEY_U: return (shift ? "U" : "u");
		case KEY_V: return (shift ? "V" : "v");
		case KEY_W: return (shift ? "W" : "w");
		case KEY_X: return (shift ? "X" : "x");
		case KEY_Y: return (shift ? "Y" : "y");
		case KEY_Z: return (shift ? "Z" : "z");
		case KEY_0: return (shift ? "0" : "0");
		case KEY_1: return (shift ? "!" : "1");
		case KEY_2: return (shift ? "\"" : "2");
		case KEY_3: return (shift ? "§" : "3");
		case KEY_4: return (shift ? "$" : "4");
		case KEY_5: return (shift ? "%" : "5");
		case KEY_6: return (shift ? "&" : "6");
		case KEY_7: return (shift ? "/" : "7");
		case KEY_8: return (shift ? "(" : "8");
		case KEY_9: return (shift ? ")" : "9");
		case KEY_UP: return (shift ? "UP" : "UP");
		case KEY_DOWN: return (shift ? "DOWN" : "DOWN");
		case KEY_LEFT: return (shift ? "LEFT" : "LEFT");
		case KEY_RIGHT: return (shift ? "RIGHT" : "RIGHT");
		}
		return "-";
	}
	
}
