package opengl.shader;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import opengl.models.Material;

public class EntityShader extends Shader {
	
	private static final int MAX_MATERIALS = 5;
	
	private int[] loc_materials;
	private int loc_TM;
	private int loc_VM;
	private int loc_PM;
	
	public EntityShader() {
		super("Entity.vsh", "Entity.fsh");
	}

	@Override
	protected void getAllUniformLocations() {
		loc_materials = new int[MAX_MATERIALS];
		for (int n = 0;n < MAX_MATERIALS;n++) {
			loc_materials[n] = super.getUniformLocation("materials["+n+"]");
		}
		loc_TM = super.getUniformLocation("TM");
		loc_VM = super.getUniformLocation("VM");
		loc_PM = super.getUniformLocation("PM");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "verticies");
		super.bindAttribute(1, "material");
		super.bindAttribute(2, "normals");
	}
	
	public void loadMaterials(Material[] materials) {
		for (int n = 0;n < MAX_MATERIALS;n++) {
			if (n < materials.length) {
				super.loadVector3f(loc_materials[n], materials[n].getColor().getVColor());
			} else {
				super.loadVector3f(loc_materials[n], new Vector3f(0,0,0));
			}
		}
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
