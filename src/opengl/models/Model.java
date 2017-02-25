package opengl.models;

public abstract class Model {
	
	private VAO vao;
	
	public Model(String... files) {
		vao = loadModel(files);
	}
	
	public Model(float[]... data) {
		vao = createModel(data);
	}
	
	public VAO getVAO() {
		return vao;
	}

	protected abstract VAO loadModel(String... files);
	protected abstract VAO createModel(float[]... files);
	
}
