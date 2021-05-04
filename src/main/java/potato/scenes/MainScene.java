package potato.scenes;

import org.joml.Vector2f;
import potato.AssetPool;
import potato.Scene;
import potato.Transform;
import potato.Window;
import potato.render.Camera;
import potato.render.Potato;
import potato.render.Sprite;

public class MainScene extends Scene {

    public MainScene(Window window) {
        super(window);
        init();
    }

    public void init() {
        loadResources();
        Camera camera = new Camera(new Vector2f(-250, 0));

        Potato obj = new Potato(new Transform(500, 100, 201, 113), 0);

        Sprite sprite = new Sprite();
        sprite.setTexture(AssetPool.getTexture("sprites/buy_bg.png"));
        obj.setSprite(sprite);
        this.append(obj);
    }

    private void loadResources() {
        AssetPool.getShader("src/main/java/potato/shaders/default.glsl");
    }
}
