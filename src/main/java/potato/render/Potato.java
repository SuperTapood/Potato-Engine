package potato.render;

import org.joml.Vector2f;
import org.joml.Vector4f;
import potato.Transform;

public class Potato {

    public final Vector4f color = new Vector4f(1, 1, 1, 1);
    public Transform transform;
    public Sprite sprite = new Sprite();
    public transient Transform lastTransform;
    public boolean blank = false;

    public Potato(Transform transform) {
        this.transform = transform;
    }

    public void start() {
        this.lastTransform = transform.copy();
    }

    public Vector4f getColor() {
        return this.color;
    }

    public void setColor(Vector4f color) {
        if (!this.color.equals(color)) {
            this.color.set(color);
        }
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setBlank() {
        blank = true;
    }
}
