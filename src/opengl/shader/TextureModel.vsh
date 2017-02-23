#version 400 core

layout (location = 0) in vec3 vertices;
layout (location = 1) in vec2 textures;
layout (location = 2) in vec3 normals;

out vec2 tex_coords;

void main(void) {
	
	tex_coords = textures;
	gl_Position = vec4(vertices*0.2, 1.0);
	
}