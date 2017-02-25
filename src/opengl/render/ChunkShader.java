package opengl.render;

import org.lwjgl.util.vector.Matrix4f;

import opengl.shader.Shader;

public class ChunkShader extends Shader {

	private int loc_TM;
	private int loc_VM;
	private int loc_PM;

	public ChunkShader() {
		super("Chunk.vsh", "Chunk.fsh");
	}

	@Override
	protected void getAllUniformLocations() {
		loc_TM = super.getUniformLocation("TM");
		loc_VM = super.getUniformLocation("VM");
		loc_PM = super.getUniformLocation("PM");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertices");
		super.bindAttribute(1, "color");
		super.bindAttribute(2, "normals");
	}
	
	public void loadTM(Matrix4f tm) {
		super.loadMatrix(loc_TM, tm);
	}
	
	public void loadVM(Matrix4f vm) {
		super.loadMatrix(loc_VM, vm);
	}

	public void loadPM(Matrix4f PM) {
		super.loadMatrix(loc_PM, PM);
	}

}
