package potato;

public class GlobalData {
    // default engine values
    public final static String SHADER_DIR = "src/main/java/potato/shaders/";
    public final static String DEFAULT_SHADER_PATH = SHADER_DIR + "default.glsl";
    public final static String FONT_SHADER = SHADER_DIR + "fontShader.glsl";
    // this may cause NullPointerException if invoked before a window is created
    public static Window windowPtr = null;
    public static String defaultFont;
}
