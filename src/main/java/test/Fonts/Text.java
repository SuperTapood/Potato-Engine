package test.Fonts;

import potato.GlobalData;
import potato.render.Shader;

public class Text extends Batch {
    private CFont font;
    private Shader fontShader;

    public Text(String fontPath, int size) {
        super();
        font = generateFont(fontPath, size);
        fontShader = new Shader(GlobalData.FONT_SHADER);
        super.shader = fontShader;
        super.font = font;
        super.initBatch();
    }

    private CFont generateFont(String font, int size) {
        // todo: enable choosing font using just the name (without directory)
        return new CFont(font, size);
    }
}
