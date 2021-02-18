package potato.nightly.scenes;

import org.joml.Vector2f;
import potato.nightly.AssetPool;
import potato.nightly.Scene;
import potato.nightly.Transform;
import potato.nightly.Window;
import potato.nightly.render.Camera;
import potato.nightly.render.Sprite;
import potato.nightly.render.SpriteRenderer;

public class MainScene extends Scene {
    private final Window window;
    private Camera camera;
    private SpriteRenderer obj;

    public MainScene(Window window) {
        super(window);
        this.window = window;
    }

    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));

        obj = new SpriteRenderer("Object 1",
                new Transform(new Vector2f(500, 100),
                        new Vector2f(201, 113)), 0);

        Sprite sprite = new Sprite();
        sprite.setTexture(AssetPool.getTexture("assets/sprites/buy_bg.png"));
        obj.setSprite(sprite);
        this.append(obj);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
    }
}
