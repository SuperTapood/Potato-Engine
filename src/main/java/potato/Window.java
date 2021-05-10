package potato;


import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import potato.fonts.CFont;
import potato.fonts.Text;
import potato.listeners.KeyListener;
import potato.listeners.MouseListener;
import potato.render.Camera;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Random;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    private final int width, height;
    private final String title;
    private final MouseListener mouseListener;
    private final KeyListener keyListener;
    //    private final Map<String, Scene> scenes = new HashMap<>();
    private final Camera camera;
    private final float[] vertices = {
            // x, y,        r, g, b              ux, uy
            0.5f, 0.5f, 1.0f, 0.2f, 0.11f, 1.0f, 0.0f,
            0.5f, -0.5f, 1.0f, 0.2f, 0.11f, 1.0f, 1.0f,
            -0.5f, -0.5f, 1.0f, 0.2f, 0.11f, 0.0f, 1.0f,
            -0.5f, 0.5f, 1.0f, 0.2f, 0.11f, 0.0f, 0.0f
    };
    private final int[] indices = {
            0, 1, 3,
            1, 2, 3
    };
    private long glfwWindow;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.mouseListener = new MouseListener();
        this.keyListener = new KeyListener();
        this.camera = new Camera(new Vector2f(0, 0));
        // store a pointer to this window in the GlobalData class
        // to enable neat access to it
        GlobalData.windowPtr = this;
        // set an engine default font
        GlobalData.defaultFont = "Arial";
        init();
    }

    public void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        configWindowHints();
        createWindow();
        setCallbacks();
    }

    private void configWindowHints() {
        // Configure GLFW
        glfwDefaultWindowHints();
        // this will make the window invisible until we are done setting it
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // don't make thy window resizable
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        // do not maximize the window.
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
        // assert some version stuff
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    }

    private void createWindow() {
        // Create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current, whatever that means.
        glfwMakeContextCurrent(glfwWindow);
        GL.createCapabilities();

        // Enable v-sync
        // setting this to 1 will make the fps flop 55 - 65 fps
        // setting this to 0 will run this at about 5K to 7K fps
        glfwSwapInterval(0);

        // Make the window visible
        glfwShowWindow(glfwWindow);


    }

    private void setCallbacks() {
        glfwSetCursorPosCallback(glfwWindow, mouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, mouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, mouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, keyListener::keyCallback);
    }

    public void run() {
        //System.out.println("LWJGL Version " + Version.getVersion());
        loop();
        terminate();
    }

    private void loop() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        float fps = 60f;
        float perFrame = 1 / fps;
        float beginTime = (float) glfwGetTime();
        float endTime;
        float frameTime = 0;
        float dt;

        //glClearColor(0.1f, 0.09f, 0.1f, 1);
        glClearColor(1, 1, 1, 1);

        Text text = new Text(64);
        Random random = new Random();
        while (!glfwWindowShouldClose(glfwWindow)) {
            if (frameTime >= perFrame) {
                //System.out.println(MessageFormat.format("{0}ms, {1} FPS", frameTime, 1 / frameTime));
                text.addText(MessageFormat.format("{0}ms, {1} FPS", frameTime, 1 / frameTime),
                        50, 50, 64, 0xFF00AB0);
                frameTime = 0;
                glClear(GL_COLOR_BUFFER_BIT);
                text.addText("Hello world!", 200, 200, 64, 0xFF00AB0);
                text.addText("My name is SuperTapood!", 100, 300, 70, 0xAA01BB);
                text.addText("Test text", 300, 400, 64, 170, 1, 187);
                text.addText("Test text[]", 300, 400, 64, new int[]{170, 1, 187});

                StringBuilder message = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    message.append((char) (random.nextInt('z' - 'a') + 'a'));
                }
                text.addText(message.toString(), 200, 400, 64, 0xAA01BB);
                text.flushBatch();
                glfwSwapBuffers(glfwWindow);
            }
            glfwPollEvents();
            endTime = (float) glfwGetTime();
            dt = endTime - beginTime;
            frameTime += dt;
            //System.out.println(MessageFormat.format("{0}ms, {1} FPS", dt, 1 / dt));
            beginTime = endTime;
        }
    }

    private void terminate() {
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

//    public void addScene(String name, Scene scene) {
//        scenes.put(name, scene);
//    }
//
//    public Scene getScene(String name) {
//        return scenes.get(name);
//    }
//
//    public void setCurrentScene(Scene scene) {
//        currentScene = scene;
//        currentScene.start(this);
//    }
//
//    public void setCurrentScene(String name) {
//        Scene scene = getScene(name);
//        setCurrentScene(scene);
//    }

    public Camera getCamera() {
        return this.camera;
    }

}
