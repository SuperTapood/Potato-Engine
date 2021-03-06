package potato;


import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import potato.fonts.DynamicText;
import potato.fonts.Query;
import potato.fonts.StaticText;
import potato.fonts.Text;
import potato.listeners.KeyListener;
import potato.listeners.MouseListener;
import potato.render.Camera;
import potato.render.Image;
import potato.render.Renderer;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    public final int width, height;
    public final String title;
    public final MouseListener mouseListener;
    public final KeyListener keyListener;
    //    public final Map<String, Scene> scenes = new HashMap<>();
    public final Camera camera;
    public final float[] vertices = {
            // x, y,        r, g, b              ux, uy
            0.5f, 0.5f, 1.0f, 0.2f, 0.11f, 1.0f, 0.0f,
            0.5f, -0.5f, 1.0f, 0.2f, 0.11f, 1.0f, 1.0f,
            -0.5f, -0.5f, 1.0f, 0.2f, 0.11f, 0.0f, 1.0f,
            -0.5f, 0.5f, 1.0f, 0.2f, 0.11f, 0.0f, 0.0f
    };
    public final int[] indices = {
            0, 1, 3,
            1, 2, 3
    };
    public boolean stop = false;
    public long glfwWindow;
    public HashMap<String, Scene> scenes = new HashMap<>();
    public Scene currentScene = new Scene();

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
        // set pointers for listeners
        GlobalData.keyListener = keyListener;
        GlobalData.mouseListener = mouseListener;
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

    public void configWindowHints() {
        // Configure GLFW
        glfwDefaultWindowHints();
        // this will make the window invisible until we are done setting it
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // make thy window resizablen't
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        // do not maximize the window.
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
        // assert some version stuff
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    }

    public void createWindow() {
        // Create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current, whatever that means.
        // a lot of stuff breaks if this isn't done
        glfwMakeContextCurrent(glfwWindow);

        // Enable v-sync
        // setting this to 1 will make the fps match the monitor's refresh rate
        // setting this to 0 will run this at maximum effort
        glfwSwapInterval(0);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    public void setCallbacks() {
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

    public void stop() {
        stop = true;
    }

    public void loop() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        float fps = GlobalData.FPS;
        float perFrame = 1 / fps;
        float beginTime = (float) glfwGetTime();
        float endTime;
        float frameTime = 0;
        float dt = 0f;

        Text text = new Text(64);
        StaticText test = new StaticText(64);
        test.setLabel("Test", 200, 200, 64, 60, 60, 60);
        DynamicText test2 = new DynamicText(64);
        test2.setLabel(50, 50, 64, 0xFF00AB0);
        Consumer<Void> func = (a) -> stop();
        Button button = new Button(50, 50, 50, 50, func);
        Renderer renderer = new Renderer();
        Image buttonImage = new Image(50, 50, 100, 100, "sprites/money_bg.png");
        renderer.add(buttonImage);
        //glClearColor(0.1f, 0.09f, 0.1f, 1);
        glClearColor(1, 1, 1, 1);
        Random rnd = new Random();
        while (!glfwWindowShouldClose(glfwWindow) && !stop) {
            if (frameTime >= perFrame) {
                GlobalData.frameTime = frameTime;
                GlobalData.setFps(1 / frameTime);
                frameTime = 0;
                glClear(GL_COLOR_BUFFER_BIT);
                text.addText("Hello world!", 200, 200, 64, 0xFF00AB0);
                text.addText("My name is SuperTapood!", 100, 300, 70, 0xAA01BB);
                text.addText("Test text", 300, 400, 64, 170, 1, 187);

                StringBuilder message = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    message.append((char) (rnd.nextInt('z' - 'a') + 'a'));
                }
                text.addText(message.toString(), 200, 400, 64, 0xAA01BB);
                test.render();
                text.flushBatch();
                renderer.renderAll();
                glfwSwapBuffers(glfwWindow);
            }
            glfwPollEvents();

            if (GlobalData.FPS != fps) {
                fps = GlobalData.FPS;
                perFrame = 1 / fps;
            }
            endTime = (float) glfwGetTime();
            dt = endTime - beginTime;
            frameTime += dt;
            //System.out.println(MessageFormat.format("{0}ms, {1} FPS", dt, 1 / dt));
            beginTime = endTime;
        }
    }

    public void terminate() {
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public void setScene(String name) {
        currentScene = scenes.get(name);
    }

}
