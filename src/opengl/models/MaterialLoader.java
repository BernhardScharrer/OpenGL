package opengl.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

import opengl.util.Color;

public class MaterialLoader {
	
	private static BufferedReader reader;
	private static Vector3f vertex,normal;
	private static List<Vector3f> vertices = new ArrayList<>();
	private static List<Vector3f> normals = new ArrayList<>();
	private static List<Integer> indices = new ArrayList<>();
	private static float[] vertices_array = null;
	private static float[] normals_array = null;
	private static int[] material = null;
	private static int[] indices_array = null;
	private static int cvp;
	private static String line;
	private static String[] current,vertex1,vertex2,vertex3;
	
	private static List<Material> materials;
	private static Material[] material_array;
	private static Material mat;
	
	private int id;
	
	public static VAO load(String file) {
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
					
					processVertex(vertex1, indices, material_id, normals, material, normals_array);
					processVertex(vertex2, indices, material_id, normals, material, normals_array);
					processVertex(vertex3, indices, material_id, normals, material, normals_array);
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
	
	private static void processVertex(String[] vertexdata, List<Integer> indices, int material_id, List<Vector3f> normals, int[] material, float[] normals_array) {
		
		cvp = Integer.parseInt(vertexdata[0])-1;
		indices.add(cvp);
		material[cvp] = material_id;
		normal = normals.get(Integer.parseInt(vertexdata[1])-1);
		normals_array[cvp*3] = normal.x;
		normals_array[cvp*3+1] = normal.y;
		normals_array[cvp*3+2] = normal.z;
		
	}
	
	public Material[] loadMaterials(String file) {
		
		try {
			
			materials = new ArrayList<>();
			reader = new BufferedReader(new FileReader(new File("res/"+file+".mtl")));
			
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("newmtl ")) {
					mat = new Material(id++, line.split(" ")[1], null);
					materials.add(mat);
				}
				
				if (line.startsWith("Kd ")) {
					if (mat != null) {
						mat.setColor(new Color(
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
