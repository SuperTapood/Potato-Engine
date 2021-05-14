//import potato.Window;
//import potato.scenes.MainScene;

import potato.Scene;
import potato.Window;

public class Main {

    public static void main(String[] args) {
        Window win = new Window(500, 500, "Title");
        Scene mainScene = new MainScene();
        win.addScene("main", mainScene);
        win.setScene("main");
        win.run();
    }
}
