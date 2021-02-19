package potato.nightly;

import potato.nightly.scenes.MainScene;

public class Nightly {
    public static void run() {
        Window window = new Window(1280, 720, "Engine Test");
        MainScene main = new MainScene(window);
        window.setCurrentScene(main);
        window.run();
    }
}
