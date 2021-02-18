import stable.*;
import scenes.MainScene;

public class Main {

    public static void main(String[] args) {
        potato.Window window = new Window();
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
