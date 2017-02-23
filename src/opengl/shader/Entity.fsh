#version 400 core

in vec3 material_color;
in vec3 surface;
in vec3 light;

layout (location = 0) out vec4 color;

void main(void) {
	
	
	vec3 n_surface = normalize(surface);
	vec3 n_light = normalize(light);

	float nDotl = dot(n_surface, n_light);
	float bright = max(nDotl, 0.0);
	vec3 diffuse = vec3(1,1,1) * bright;

	color = vec4(surface,1.0);
	
}