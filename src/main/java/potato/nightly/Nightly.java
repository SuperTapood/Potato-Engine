package potato.nightly;

import potato.nightly.scenes.MainScene;

public class Nightly {
    public static void run() {
        Window window = new Window(1280, 720, "Title");
        window.init();
        MainScene main = new MainScene(window);
        main.init();
        window.setCurrentScene(main);
        window.run();
    }
}
