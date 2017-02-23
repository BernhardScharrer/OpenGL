package opengl.models;

public class MaterialModel extends Model {
	
	private Material[] materials;
	
	public MaterialModel(String obj, String mtl) {
		super(obj, mtl);
	}

	@Override
	protected VAO loadModel(String... files) {
		this.materials = Loader.loadMaterials(files[1]);
		return Loader.loadMaterialModel(files[0]);
	}
	
	public Material getMaterial(int id) {
		return materials[id];
	}

	public Material[] getMaterials() {
		return materials;
	}
	
}
