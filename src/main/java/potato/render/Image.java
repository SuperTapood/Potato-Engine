package potato.render;

import potato.AssetPool;
import potato.Transform;

public class Image extends Potato {
    Sprite sprite = new Sprite();

    public Image(int x, int y, int w, int h, int zIndex, String path) {
        super(new Transform(x, y, w, h), zIndex);
        sprite.setTexture(AssetPool.getTexture(path));
        super.setSprite(sprite);
    }

    public Image(int x, int y, int w, int h, String path) {
        super(new Transform(x, y, w, h), 0);
        sprite.setTexture(AssetPool.getTexture(path));
        super.setSprite(sprite);
    }
}
