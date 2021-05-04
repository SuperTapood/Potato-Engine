package potato.render;

import org.joml.Vector2f;

public class Sprite {

    private final Vector2f[] texCoords = {
            new Vector2f(1, 1),
            new Vector2f(1, 0),
            new Vector2f(0, 0),
            new Vector2f(0, 1)
    };
    private Texture texture = null;

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture tex) {
        this.texture = tex;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }
}
