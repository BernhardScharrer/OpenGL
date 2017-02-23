package opengl.models;

public abstract class Model {
	
	private VAO vao;
	
	public Model(String... files) {
		vao = loadModel(files);
	}
	
	public VAO getVAO() {
		return vao;
	}

	protected abstract VAO loadModel(String... files);
	
}
