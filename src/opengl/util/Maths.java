package opengl.util;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import opengl.models.Camera;

public class Maths {

	private static Matrix4f matrix;
	private static final Vector3f x = new Vector3f(1, 0, 0), y = new Vector3f(0, 1, 0), z = new Vector3f(0, 0, 1), scale = new Vector3f();

	public static Matrix4f createTM(Vector3f translation, Vector3f rotation, float scale) {
		matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), x, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), y, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), z, matrix, matrix);
		Maths.scale.x = scale;
		Maths.scale.y = scale;
		Maths.scale.z = scale;
		Matrix4f.scale(Maths.scale, matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTM(int x, int y) {
		matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(new Vector3f(), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(0), Maths.x, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(0), Maths.y, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(0), Maths.z, matrix, matrix);
		Maths.scale.x = 1;
		Maths.scale.y = 1;
		Maths.scale.z = 1;
		Matrix4f.scale(Maths.scale, matrix, matrix);
		return matrix;
	}
	
    public static Matrix4f createVM() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(Camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(Camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(Camera.getRoll()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f cameraPos = Camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
    }

}
