package opengl.models;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import opengl.util.Maths;

public class Entity {
	
	private MaterialModel model;
	private Vector3f location;
	private Vector3f rotation;
	private float scale;
	
	public Entity(MaterialModel model, Vector3f location, Vector3f rotation, float scale) {
		this.model = model;
		this.location = location;
		this.rotation = rotation;
		this.scale = scale;
	}

	public MaterialModel getModel() {
		return model;
	}

	public Matrix4f generateTM() {
		return Maths.createTM(location, rotation, scale);
	}
	
}
