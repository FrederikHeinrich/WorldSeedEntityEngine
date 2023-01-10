#version 150

#moj_import <fog.glsl>

in vec3 Position;
in vec4 Color;
in ivec2 UV2;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat3 IViewRotMat;
uniform vec4 ColorModulator;
uniform int FogShape;

out float vertexDistance;
flat out vec4 vertexColor;

void main() {
    gl_Position = vec4(0, 0, 0, 0);
}