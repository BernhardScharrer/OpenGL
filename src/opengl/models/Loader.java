package opengl.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import opengl.util.Color;

public class Loader {
	
	protected static int loadTexture(String file) {
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+file+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int id = tex.getTextureID();
		Models.texs.add(id);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		return id;
	}
	
	protected static Material[] loadMaterials(String file) {
		return new MaterialLoader().loadMaterials(file);
	}
	
	protected static VAO loadTexturedModel(String file) {
		return new ModelLoader().loadTexturedModel(file);
	}
	
	protected static VAO loadMaterialModel(String file) {
		return new ModelLoader().loadMaterialModel(file);
	}
	
	private static class MaterialLoader {
		
		private BufferedReader reader;
		private String line;
		
		private List<Material> materials;
		private Material[] material_array;
		private Material current;
		
		private int id;
		
		public Material[] loadMaterials(String file) {
			
			try {
				
				materials = new ArrayList<>();
				reader = new BufferedReader(new FileReader(new File("res/"+file+".mtl")));
				
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("newmtl ")) {
						current = new Material(id++, line.split(" ")[1], null);
						materials.add(current);
					}
					
					if (line.startsWith("Kd ")) {
						if (current != null) {
							current.setColor(new Color(
									Float.parseFloat(line.split(" ")[1]), 
									Float.parseFloat(line.split(" ")[2]), 
									Float.parseFloat(line.split(" ")[3]))
								);
						}
					}
					
				}
				
				material_array = new Material[materials.size()];
				for (int n = 0;n < materials.size();n++) {
					material_array[n] = materials.get(n);
				}
				
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return material_array;
		}
		
	}
	
	private static class ModelLoader {
		
		private BufferedReader reader;
		private Vector3f vertex,normal;
		private Vector2f texture;
		private List<Vector3f> vertices = new ArrayList<>();
		private List<Vector2f> textures = new ArrayList<>();
		private List<Vector3f> normals = new ArrayList<>();
		private List<Integer> indices = new ArrayList<>();
		private float[] vertices_array = null;
		private float[] textures_array = null;
		private float[] normals_array = null;
		private int[] material = null;
		private int[] indices_array = null;
		private int cvp;
		private String line;
		private String[] current,vertex1,vertex2,vertex3;
		
		/*
		 * load model with texture
		 */
		private VAO loadTexturedModel(String file) {
			try {
				reader = new BufferedReader(new FileReader(new File("res/"+file+".obj")));
				
				while(true) {
					line = reader.readLine();
					current = line.split(" ");
					if (line.startsWith("v ")) {
						vertex = new Vector3f(
							Float.parseFloat(current[1]),
							Float.parseFloat(current[2]),
							Float.parseFloat(current[3])	
						);
						vertices.add(vertex);
					} else if (line.startsWith("vt ")) {
						texture = new Vector2f(
								Float.parseFloat(current[1]),
								Float.parseFloat(current[2])
							);
						textures.add(texture);
					} else if (line.startsWith("vn ")) {
						normal = new Vector3f(
								Float.parseFloat(current[1]),
								Float.parseFloat(current[2]),
								Float.parseFloat(current[3])	
							);
						normals.add(normal);
					} else if (line.startsWith("f ")) {
						textures_array = new float[vertices.size()*2];
						normals_array = new float[vertices.size()*3];
						break;
					}
				}
				
				while (line != null) {
					if (!line.startsWith("f ")) {
						line = reader.readLine();
						continue;
					}
					current = line.split(" ");
					vertex1 = current[1].split("/");
					vertex2 = current[2].split("/");
					vertex3 = current[3].split("/");
					
					processVertex(vertex1, indices, textures, normals, textures_array, normals_array);
					processVertex(vertex2, indices, textures, normals, textures_array, normals_array);
					processVertex(vertex3, indices, textures, normals, textures_array, normals_array);
					line = reader.readLine();
				}
				
				reader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			vertices_array = new float[vertices.size()*3];
			indices_array = new int[indices.size()];
			
			cvp = 0;
			
			for (Vector3f vertex : vertices) {
				vertices_array[cvp++] = vertex.x;
				vertices_array[cvp++] = vertex.y;
				vertices_array[cvp++] = vertex.z;
			}
			
			for (cvp = 0;cvp<indices.size();cvp++) indices_array[cvp] = indices.get(cvp);
			
			int id = GL30.glGenVertexArrays();
			Models.vaos.add(id);
			GL30.glBindVertexArray(id);
			
			VBO indices = new VBO(indices_array);
			VBO vertices = new VBO(0, 3, vertices_array);
			VBO textures = new VBO(1, 2, textures_array);
			VBO normals = new VBO(2, 3, normals_array);
			
			GL30.glBindVertexArray(0);
			
			VAO vao = new VAO(id, indices.getCount(), indices, vertices, textures, normals);
			
			return vao;
		}
		
		/*
		 * load model with materials
		 */
		public VAO loadMaterialModel(String file) {
			try {
				reader = new BufferedReader(new FileReader(new File("res/"+file+".obj")));
				int material_id = -1;
				
				while(true) {
					line = reader.readLine();
					if (line!=null) {
						current = line.split(" ");
						if (line.startsWith("v ")) {
							vertex = new Vector3f(
								Float.parseFloat(current[1]),
								Float.parseFloat(current[2]),
								Float.parseFloat(current[3])	
							);
							vertices.add(vertex);
						} else if (line.startsWith("vn ")) {
							normal = new Vector3f(
									Float.parseFloat(current[1]),
									Float.parseFloat(current[2]),
									Float.parseFloat(current[3])	
								);
							normals.add(normal);
						} else if (line.startsWith("f ")) {
							material = new int[vertices.size()];
							normals_array = new float[vertices.size()*3];
							break;
						}
					}
				}
				
				do {
					if (line.startsWith("usemtl ")) {
						material_id++;
					}
					if (line.startsWith("f ")) {
						current = line.split(" ");
						vertex1 = current[1].split("//");
						vertex2 = current[2].split("//");
						vertex3 = current[3].split("//");
						
						processVertexWithoutTexture(vertex1, indices, material_id, normals, material, normals_array);
						processVertexWithoutTexture(vertex2, indices, material_id, normals, material, normals_array);
						processVertexWithoutTexture(vertex3, indices, material_id, normals, material, normals_array);
					}
				} while ((line=reader.readLine()) != null);
				
				reader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			vertices_array = new float[vertices.size()*3];
			indices_array = new int[indices.size()];
			
			cvp = 0;
			
			for (Vector3f vertex : vertices) {
				vertices_array[cvp++] = vertex.x;
				vertices_array[cvp++] = vertex.y;
				vertices_array[cvp++] = vertex.z;
			}
			
			for (cvp = 0;cvp<indices.size();cvp++) indices_array[cvp] = indices.get(cvp);
			
			int id = GL30.glGenVertexArrays();
			Models.vaos.add(id);
			GL30.glBindVertexArray(id);
			
			VBO indices = new VBO(indices_array);
			VBO vertices = new VBO(0, 3, vertices_array);
			VBO materials = new VBO(1, 1, material);
			VBO normals = new VBO(2, 3, normals_array);
			
			GL30.glBindVertexArray(0);
			
			VAO vao = new VAO(id, vertices.getCount(), indices, vertices, materials, normals);
			
			return vao;
		}

		private void processVertex(String[] vertexdata, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textures_array, float[] normals_array) {
			
			cvp = Integer.parseInt(vertexdata[0])-1;
			indices.add(cvp);
			texture = textures.get(Integer.parseInt(vertexdata[1])-1);
			textures_array[cvp*2] = texture.x;
			textures_array[cvp*2+1] = 1-texture.y;
			normal = normals.get(Integer.parseInt(vertexdata[2])-1);
			normals_array[cvp*3] = normal.x;
			normals_array[cvp*3+1] = normal.y;
			normals_array[cvp*3+2] = normal.z;
			
		}
		
		private void processVertexWithoutTexture(String[] vertexdata, List<Integer> indices, int material_id, List<Vector3f> normals, int[] material, float[] normals_array) {
			
			cvp = Integer.parseInt(vertexdata[0])-1;
			indices.add(cvp);
			material[cvp] = material_id;
			normal = normals.get(Integer.parseInt(vertexdata[1])-1);
			normals_array[cvp*3] = normal.x;
			normals_array[cvp*3+1] = normal.y;
			normals_array[cvp*3+2] = normal.z;
			
		}
		
	}
	
}
