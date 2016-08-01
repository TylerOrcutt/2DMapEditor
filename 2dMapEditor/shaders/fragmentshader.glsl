varying vec2 v_texCoord;
uniform sampler2D s_texture;
uniform int useTexture;
void main() {
if(useTexture==1){
gl_FragColor = texture2D( s_texture, v_texCoord ) ;

}else{
gl_FragColor=gl_Color;
}
//gl_FragColor = vec4(1,0,1,1);

}