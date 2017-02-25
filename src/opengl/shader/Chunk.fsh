#version 400 core

in vec3 chunk_color;

out vec4 color;

void main(void) {
	
	color = vec4(chunk_color, 1.0);
	
}