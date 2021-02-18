package scenes;

import org.joml.Vector2f;
import potato.stable.AssetPool;
import potato.stable.Scene;
import potato.stable.Transform;
import potato.stable.Window;
import potato.stable.render.Camera;
import potato.stable.render.Sprite;
import potato.stable.render.SpriteRenderer;

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
