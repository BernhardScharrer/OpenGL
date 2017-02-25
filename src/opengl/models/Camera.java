package opengl.models;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import opengl.inputs.KeyBoard;
import opengl.util.Maths;

public class Camera {
	
	public static Vector3f location = new Vector3f(0,1,0);
	private static float pitch=90, yaw=0, roll;
	
	private static final float FOV = 10;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	private static Matrix4f PM;
	
	public static void generatePM() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		PM = new Matrix4f();
		PM.m00 = x_scale;
		PM.m11 = y_scale;
		PM.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		PM.m23 = -1;
		PM.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		PM.m33 = 0;
	}
	
	public static void update() {
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_W)) {
			location.x += 0.02f * Math.cos(Math.toRadians(yaw-90));
			location.z += 0.02f * Math.sin(Math.toRadians(yaw-90));
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_S)) {
			location.x -= 0.02f * Math.cos(Math.toRadians(yaw-90));
			location.z -= 0.02f * Math.sin(Math.toRadians(yaw-90));
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_SPACE)) {
			location.y+=0.001f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_LSHIFT)) {
			location.y-=0.001f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_DOWN)) {
			pitch += 0.1f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_UP)) {
			pitch -= 0.1f;
		}
		
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_RIGHT)) {
			yaw += 0.1f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_LEFT)) {
			yaw -= 0.1f;
		}
		
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_RIGHT)) {
			yaw += 0.02f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_LEFT)) {
			yaw -= 0.02f;
		}
		
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_R)) {
			roll += 0.02f;
		}
		if (KeyBoard.isKeyPressed(KeyBoard.KEY_L)) {
			roll -= 0.02f;
		}
		
	}

	public static Vector3f getPosition() {
		return location;
	}

	public static float getPitch() {
		return pitch;
	}

	public static float getYaw() {
		return yaw;
	}

	public static float getRoll() {
		return roll;
	}
	
	public static Matrix4f generateVM() {
		return Maths.createVM();
	}
	
	public static Matrix4f getPM() {
		return PM;
	}
	
}
