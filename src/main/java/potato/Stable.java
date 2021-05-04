package potato;

import potato.scenes.MainScene;

public class Stable {
    public static void run() {
        Window window = new Window(1280, 720, "Engine Test");
        MainScene main = new MainScene(window);
        window.setCurrentScene(main);
        window.run();
    }
}
