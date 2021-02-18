import scenes.MainScene;

import potato.stable.Window;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        window.init();
        window.run();
//        Window window = new Window();
//        window.init();
//        addMain(window);
//        window.setCurrentScene("Main");
//        window.run();
    }

    public static void addMain(Window window) {
        MainScene main = new MainScene(window);
        main.init();
        window.addScene("Main", main);
    }
}
