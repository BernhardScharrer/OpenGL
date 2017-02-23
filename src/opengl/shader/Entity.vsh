#version 400 core

layout (location = 0) in vec3 vertices;
layout (location = 1) in int material;
layout (location = 2) in vec3 normals;

out vec3 material_color;
out vec3 surface;
out vec3 light;

uniform vec3[] materials;
uniform mat4 TM;
uniform mat4 VM;
uniform mat4 PM;

vec4 world_pos;
vec4 rel_cam;

void main(void) {
	
	material_color = materials[2];
	
	world_pos = TM*vec4(vertices*0.03,1.0);
	rel_cam = VM*world_pos;
	gl_Position = PM*rel_cam;

	surface = vertices;//(TM*vec4(normals,0.0)).xyz;
	light = vec3(0,0,100) - world_pos.xyz;
	
}