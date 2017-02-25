package opengl.chunk;

import java.util.Random;

import opengl.models.ChunkModel;

public class Generator {
	
	private static final Random RANDOM = new Random();
	private static final float AMPLITUDE = 0.0001f;
	private static final int SIZE = 4;
	private int seed;
	
	public Generator() {
		this.seed = RANDOM.nextInt(1000000000);
	}
	
	public Generator(int seed) {
		this.seed = seed;
	}

	public Chunk generate(int x, int y) {
		
		float[] vertices,colors,normals;
		int[] indices;
		int count = 0;
		
		vertices = new float[SIZE*SIZE*3];
		colors = new float[(SIZE-1)*(SIZE-1)*18];
		normals = new float[(SIZE-1)*(SIZE-1)*18];
		indices = new int[(SIZE-1)*(SIZE-1)*6];
		
		for (int dy=0;dy<SIZE;dy++) {
			for (int dx=0;dx<SIZE;dx++) {
				vertices[3*(dx+(SIZE*dy))+0] = dx;
				vertices[3*(dx+(SIZE*dy))+1] = generateHeight(dx, dy);
				vertices[3*(dx+(SIZE*dy))+2] = dy;
				
				if (dy != SIZE-1 && dx != SIZE-1) {
					
					int xy = dx+(SIZE*dy);
					
					
					System.out.println(xy +"|" + (xy) + "|" + (xy+1) + "|" + (xy+1+SIZE));
					
					indices[6*count+0] = xy;
					indices[6*count+1] = xy+1;
					indices[6*count+2] = xy+SIZE;
					
					indices[6*count+3] = xy+1+SIZE;
					indices[6*count+4] = xy+SIZE;
					indices[6*count+5] = xy+1;
					count++;
				}
			}
		}
		
		for (int n = 0;n < indices.length/3;n++) {
			
			Random random = new Random();
			
			float color = random.nextFloat();
			int index = indices[n];
			
			System.out.println(n*9);
			
			colors[n*9+0] = 0;
			colors[n*9+1] = color;
			colors[n*9+2] = 0;
			
			colors[n*9+3] = 0;
			colors[n*9+4] = color;
			colors[n*9+5] = 0;
			
			colors[n*9+6] = 0;
			colors[n*9+7] = color;
			colors[n*9+8] = 0;
			
		}
		
		return new Chunk(x, y, new ChunkModel(indices, vertices, colors, normals));
	}
	
	private float generateHeight(int x, int z) {
		return getInterpolateNoise(x/4f, z/4f) * AMPLITUDE;
	}

	private float getInterpolateNoise(float x, float z) {
		int intX = (int) x;
		int intZ = (int) z;
		float fracX = x - intX;
		float fracZ = z - intZ;
		
		float v1 = getSmoothNoise(intX, intZ);
		float v2 = getSmoothNoise(intX+1, intZ);
		float v3 = getSmoothNoise(intX, intZ+1);
		float v4 = getSmoothNoise(intX+1, intZ+1);
		float i1 = interpolate(v1, v2, fracX);
		float i2 = interpolate(v3, v4, fracZ);
		return interpolate(i1, i2, fracZ);
	}
	
	private float interpolate(float a, float b, float blend) {
		double theta = blend * Math.PI;
		float f = (float) (1f-Math.cos(theta)) * 0.5f;
		return a * (1f-f) + b *f;
	}
	
	private float getSmoothNoise(int x, int z) {
		float corners = (getNoise(x-1, z-1)+getNoise(x+1, z-1)+getNoise(x-1, z+1)+getNoise(x+1, z+1))/16f;
		float sides = (getNoise(x-1, z)+getNoise(x+1, z)+getNoise(x, z-1)+getNoise(x, z+1))/8f;
		float center = getNoise(x,z) / 4f;
		return corners + sides + center;
	}
	
	private float getNoise(int x, int z) {
		RANDOM.setSeed(x*1245+z*43851+seed);
		return RANDOM.nextFloat()*2f-1f;
	}
	
}
