#version 120
#include "lighting.glh"

varying vec4 color0;
varying vec3 normal0;
varying vec3 worldPos0;

uniform DirectionalLight R_directionalLight;

void main()
{
	gl_FragColor = color0 * CalcDirectionalLight(R_directionalLight, normalize(normal0), worldPos0);
}