package potato.stable.listeners;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private final boolean[] keyPressed = new boolean[350];
    private String prev = "";

    public void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keyPressed[key] = false;
        }
    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    public String getKeys() {
        StringBuilder output = new StringBuilder("[ ");
        for (boolean b : keyPressed) {
            output.append(b).append(" ");
        }
        output.append("]");
        return output.toString();
    }

    private boolean areKeysEqual(String as, String bs) {
        char[] a = as.toCharArray();
        char[] b = bs.toCharArray();
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public void printStatus() {
        String keys = getKeys();
        if (!areKeysEqual(keys, prev)) {
            prev = keys;
            System.out.println("Keys: " + keys);
        }
    }
}