#version 330 core

out vec4 outColour;

uniform sampler2D tex;
uniform float alpha;

void main() {
  outColour = vec4(1.0, 1.0, 1.0, alpha);
}
