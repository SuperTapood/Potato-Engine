package test;

import org.lwjgl.opengl.GL;
import potato.fonts.Text;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClear;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long window;

    public Window() {
        init();
    }

    private void init() {
        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(1920, 1080, "Font Rendering", NULL, NULL);
        if (window == NULL) {
            System.out.println("Could not create window.");
            glfwTerminate();
            return;
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        // Initialize gl functions for windows using GLAD
        GL.createCapabilities();
    }

    public void run() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        Text text = new Text("C:/Windows/Fonts/Arial.ttf", 64);

        Random random = new Random();
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);
            glClearColor(1, 1, 1, 1);

            text.addText("Hello world!", 200, 200, 1f, 0xFF00AB0);
            text.addText("My name is Gabe!", 100, 300, 1.1f, 0xAA01BB);

            StringBuilder message = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                message.append((char) (random.nextInt('z' - 'a') + 'a'));
            }
            text.addText(message.toString(), 200, 400, 1.1f, 0xAA01BB);

            text.flushBatch();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}
