package potato;

import java.util.function.Consumer;
import java.util.function.Function;

public class Button {
    private int x, y, w, h;
    private Consumer<Void> f;

    public Button(int xPos, int yPos, int width, int height, Consumer<Void> resp) {
        x = xPos;
        y = yPos;
        w = width;
        h = height;
        f = resp;
    }

    public boolean doesHover() {
        float mouseX = GlobalData.mouseListener.getX();
        float mouseY = GlobalData.mouseListener.getY();
        return x + w > mouseX && mouseX > x &&
                y + h > mouseY && mouseY > y;
    }

    public boolean doesClick() {
        return GlobalData.mouseListener.mouseButtonDown(0);
    }

    public boolean doesPressButton() {
        return doesHover() && doesClick();
    }

    public void update() {
        if (doesPressButton()) {
            f.accept(null);
        }
    }
}
