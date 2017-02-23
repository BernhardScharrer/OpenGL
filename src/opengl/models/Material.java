package opengl.models;

import opengl.util.Color;

public class Material {
	
	private int id;
	private String name;
	private Color color;
	
	public Material(int id, String name, Color color) {
		this.id = id;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
	protected void setColor(Color color) {
		this.color = color;
	}
	
}
