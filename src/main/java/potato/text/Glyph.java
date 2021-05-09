package potato.text;

import potato.AssetPool;
import potato.Transform;
import potato.render.Image;
import potato.render.Potato;
import potato.render.Sprite;
import potato.render.Texture;

public class Glyph {
    private int width, height;
    private final Potato potato;
    public boolean isDirty = true;

    public Glyph(int x, int y, int size, char c) {
        String path = "src/main/java/potato/text/" + charToName(c) + ".png";
        Texture text = AssetPool.getTexture(path);
        width = text.getWidth();
        height = text.getHeight();
        potato = new Potato(new Transform(x, y, width, size), 0);
        Sprite sprite = new Sprite();
        sprite.setTexture(text);
        potato.setSprite(sprite);
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
        if (!potato.lastTransform.equals(potato.transform)) {
            potato.transform.copy(potato.lastTransform);
            isDirty = true;
        }
    }
}
