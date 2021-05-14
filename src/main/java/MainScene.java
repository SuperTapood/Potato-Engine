import potato.Scene;
import potato.fonts.DynamicText;
import potato.fonts.Query;

public class MainScene extends Scene {
    public MainScene() {
        super();
        DynamicText frameCounter = new DynamicText(64);
        frameCounter.setQuery(new Query("# FPS", new String[]{"fps"}), 690, 580, 25, 0xFF00AB0);
        super.add(frameCounter);
    }
}
