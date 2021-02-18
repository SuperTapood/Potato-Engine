package potato.stable.listeners;

import java.text.MessageFormat;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private final boolean[] mouseButtonPressed = new boolean[3];
    private boolean isDragging;
    private String prev = null;

    public MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public void mousePosCallback(long window, double xpos, double ypos) {
        lastX = xPos;
        lastY = yPos;
        xPos = xpos;
        yPos = ypos;
        isDragging = mouseButtonPressed[0] || mouseButtonPressed[1] || mouseButtonPressed[2];
    }

    public void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < mouseButtonPressed.length) {
                mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < mouseButtonPressed.length) {
                mouseButtonPressed[button] = false;
                isDragging = false;
            }
        }
    }

    public void mouseScrollCallback(long window, double xOffset, double yOffset) {
        scrollX = xOffset;
        scrollY = yOffset;
    }

    public void endFrame() {
        scrollX = 0;
        scrollY = 0;
        lastX = xPos;
        lastY = yPos;
    }

    public float getX() {
        return (float) xPos;
    }

    public float getY() {
        return (float) yPos;
    }

    public float getDx() {
        return (float) (lastX - xPos);
    }

    public float getDy() {
        return (float) (lastY - yPos);
    }

    public float getScrollX() {
        return (float) scrollX;
    }

    public float getScrollY() {
        return (float) scrollY;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public boolean mouseButtonDown(int button) {
        if (button < mouseButtonPressed.length) {
            return mouseButtonPressed[button];
        } else {
            return false;
        }
    }

    public String getLoc() {
        float locX = getX();
        float locY = getY();
        return MessageFormat.format("Location: ({0}, {1})", locX, locY);
    }

    public String getPressed() {
        boolean leftButton = mouseButtonDown(0);
        boolean middleButton = mouseButtonDown(1);
        boolean rightButton = mouseButtonDown(2);
        return MessageFormat.format("Buttons: {0}, {1}, {2}", leftButton, middleButton, rightButton);
    }

    public void printStatus() {
        String out = MessageFormat.format("Mouse status: Location {0}, {1}", getLoc(), getPressed());
        if (!out.equals(prev)) {
            prev = out;
            System.out.println(out);
        }
    }
}
