#version 400 core

layout (location=0) in vec3 vertices;
layout (location=1) in vec3 color;
layout (location=2) in vec3 normal;

out vec3 chunk_color;

uniform mat4 TM;
uniform mat4 VM;
uniform mat4 PM;


void main(void) {
	
	chunk_color = color;
	gl_Position = PM * VM * TM * vec4(0.01*vertices, 1.0);
	
}