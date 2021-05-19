package potato.render;

import potato.AssetPool;
import potato.Transform;

public class Image extends Potato {
    Sprite sprite = new Sprite();

    public Image(int x, int y, int w, int h, String path) {
        super(new Transform(x, y, w, h));
        sprite.setTexture(AssetPool.getTexture(path));
        super.setSprite(sprite);
    }
}
