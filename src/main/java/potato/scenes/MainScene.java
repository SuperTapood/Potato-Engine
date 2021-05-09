package potato.scenes;

import potato.AssetPool;
import potato.Scene;
import potato.Window;
import potato.render.Image;
import potato.text.Text;

public class MainScene extends Scene {

    public MainScene(Window window) {
        super(window);
        init();
    }

    public void init() {
        loadResources();
        //Camera camera = new Camera(new Vector2f(-250, 0));
        this.append(new Text(500, 500, 50, "HelloWorlds"));
        //this.append(new Image(500, 100, 201, 113, "sprites/buy_bg.png"));
    }

    private void loadResources() {
        AssetPool.getShader("src/main/java/potato/shaders/default.glsl");
    }
}
