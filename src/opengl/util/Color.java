package opengl.util;

import org.lwjgl.util.vector.Vector3f;

public class Color {
	
	private float r,g,b;
	private Vector3f color;
	
	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.color = new Vector3f(r, g, b);
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}
	
	public Vector3f getVColor() {
		return color;
	}
	
}
