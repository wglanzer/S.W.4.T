package de.swat.common.gui.assets;

/**
 * Shaders, die in den Assets verwendet werden
 *
 * @author W.Glanzer, 31.05.2014.
 */
public enum ShaderKey
{

  FONT("font.frag", "font.vert");




  public static final String SHADER_DIR = "shaders/";
  public final String fragShader;
  public final String vertShader;

  ShaderKey(String pFragmentShaderPath, String pVertexShaderPath)
  {
    fragShader = SHADER_DIR + pFragmentShaderPath;
    vertShader = SHADER_DIR + pVertexShaderPath;
  }

  @Override
  public String toString()
  {
    return "ShaderKey{" +
        "fragShader='" + fragShader + '\'' +
        ", vertShader='" + vertShader + '\'' +
        '}';
  }
}
