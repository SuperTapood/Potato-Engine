package potato.fonts;

import potato.GlobalData;
import potato.render.Shader;

public class Text extends Batch {
    private CFont font;
    private Shader fontShader;

    // --- [Text] ---

    /**
     * Create a new instance of a text manager
     *
     * @param fontPath the path for the selected font
     * @param size     the size of the font
     */

    public Text(String fontPath, int size) {
        super();
        font = generateFont(fontPath, size);
        fontShader = new Shader(GlobalData.FONT_SHADER);
        super.shader = fontShader;
        super.font = font;
        super.initBatch();
        super.fontSize = size;
    }

    private CFont generateFont(String font, int size) {
        // todo: enable choosing font using just the name (without directory)
        return new CFont(font, size);
    }

    @Override
    public void addText(String text, int x, int y, float size, int rgb) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            potato.fonts.CharInfo charInfo = font.getCharacter(c);
            if (charInfo.width == 0) {
                System.out.println("Unknown character " + c);
                continue;
            }

            float xPos = x;
            float yPos = y;
            addCharacter(xPos, yPos, size, charInfo, rgb);
            x += charInfo.width * (size / super.fontSize);
        }
    }

    @Override
    public void addText(String text, int x, int y, float size, int red, int green, int blue) {
        String hex = String.format("%02x%02x%02x", red, green, blue);
        addText(text, x, y, size, Integer.parseInt(hex, 16));
    }

    @Override
    public void addText(String text, int x, int y, float size, int[] rgb) {
        addText(text, x, y, size, rgb[0], rgb[1], rgb[2]);
    }
}
