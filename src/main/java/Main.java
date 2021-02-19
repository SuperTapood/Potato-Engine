import potato.nightly.Window;
import potato.nightly.scenes.MainScene;

public class Main {

    public static void main(String[] args) {
        Window window = new Window(1280, 720, "Title");
        window.init();
        MainScene main = new MainScene(window);
        main.init();
        window.setCurrentScene(main);
        window.run();
    }
}
