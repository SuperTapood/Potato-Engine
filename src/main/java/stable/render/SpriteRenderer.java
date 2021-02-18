package potato.render;

import org.joml.Vector2f;
import org.joml.Vector4f;
import potato.Transform;

public class SpriteRenderer {

    private final Vector4f color = new Vector4f(1, 1, 1, 1);
    public Transform transform;
    public int zIndex;
    private Sprite sprite = new Sprite();
    private transient Transform lastTransform;
    private transient boolean isDirty = true;
    private final String name;

    public SpriteRenderer(String name, Transform transform, int zIndex) {
        this.name = name;
        this.transform = transform;
        this.zIndex = zIndex;
    }

    public void start() {
        this.lastTransform = transform.copy();
    }

    public void update(float dt) {
        if (!this.lastTransform.equals(this.transform)) {
            this.transform.copy(this.lastTransform);
            isDirty = true;
        }
    }

    public Vector4f getColor() {
        return this.color;
    }

    public void setColor(Vector4f color) {
        if (!this.color.equals(color)) {
            this.isDirty = true;
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
        this.isDirty = true;
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public void setClean() {
        this.isDirty = false;
    }
}
