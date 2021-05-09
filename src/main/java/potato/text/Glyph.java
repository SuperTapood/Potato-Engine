package potato.text;

import potato.AssetPool;
import potato.render.Image;
import potato.render.Potato;
import potato.render.Texture;

public class Glyph {
    private Image img;
    private int width, height;

    public Glyph(int x, int y, int size, char c) {
        String path = "src/main/java/potato/text/" + charToName(c) + ".png";
        System.out.println(path);
        Texture text = AssetPool.getTexture(path);
        width = text.getWidth();
        height = text.getHeight();
        img = new Image(x, y, width, size, path);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static String charToName(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(c));
        if ((int)c > 64 && (int)c < 91) {
            sb.append("_upper");
        } else if ((int)c > 96 && (int)c < 123) {
            sb.append("_lower");
        }
        return sb.toString();
    }

    public void update(float dt) {
        img.update(dt);
    }
}
