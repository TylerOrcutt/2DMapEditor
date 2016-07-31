
attribute  vec4  vPosition; 
uniform float vposx;
uniform float vposy;
uniform float vScaleX;
uniform float vScaleY;
uniform float tposx;
uniform float tposy;
uniform float tScaleX;
uniform float tScaleY;
attribute vec2 a_texCoord;
varying vec2 v_texCoord;
  void main() {
    vec4 position = vPosition;
  position.x=position.x*vScaleX + vposx;
  position.y=position.y*vScaleY+ vposy;

     gl_Position =  gl_ModelViewProjectionMatrix*position ;
   vec2 txtpos = a_texCoord;
  //   txtpos = vec2(txtpos.x, txtpos.y);
   txtpos.x=txtpos.x*tScaleX+tposx ; 
    txtpos.y=txtpos.y*tScaleY+tposy ;
    v_texCoord = txtpos;
    }