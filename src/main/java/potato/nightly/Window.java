package potato.nightly;

import org.joml.Vector2f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import potato.nightly.fonts.Batch;
import potato.nightly.fonts.CFont;
import potato.nightly.fonts.CharInfo;
import potato.nightly.fonts.Sdf;
import potato.nightly.listeners.KeyListener;
import potato.nightly.listeners.MouseListener;
import potato.nightly.render.Camera;
import potato.nightly.render.Shader;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClear;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    private final int width, height;
    private final String title;
    private final MouseListener mouseListener;
    private final KeyListener keyListener;
    private final Map<String, Scene> scenes = new HashMap<>();
    private final Camera camera;
    private long glfwWindow;
    private Scene currentScene;
    private CFont font;

    private float[] vertices = {
            // x, y,        r, g, b              ux, uy
            0.5f, 0.5f,     1.0f, 0.2f, 0.11f,   1.0f, 0.0f,
            0.5f, -0.5f,    1.0f, 0.2f, 0.11f,   1.0f, 1.0f,
            -0.5f, -0.5f,   1.0f, 0.2f, 0.11f,   0.0f, 1.0f,
            -0.5f, 0.5f,    1.0f, 0.2f, 0.11f,   0.0f, 0.0f
    };

    private int[] indices = {
            0, 1, 3,
            1, 2, 3
    };


    public Window() {
        this.width = 1280;
        this.height = 720;
        this.title = "Window";
        this.mouseListener = new MouseListener();
        this.keyListener = new KeyListener();
        this.currentScene = new Scene(this);
        this.camera = new Camera(new Vector2f(-250, 0));
    }

    private void configWindowHints() {
        // Configure GLFW
        glfwDefaultWindowHints();
        // this will make the window invisible until we are done setting it
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // don't make thy window resizable
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        // do not maximize the window. This is done bc i want to implement
        // developer and actual dims check out the data resize class for more info.
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
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
        // Enable v-sync
        // setting this to 1 will make the fps flop 55 - 65 fps
        // setting this to 0 will run this at about 5K to 7K fps
        glfwSwapInterval(0);


        // Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    public void run() {
        System.out.println("LWJGL Version " + Version.getVersion());

        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void setCallbacks() {
        glfwSetCursorPosCallback(glfwWindow, mouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, mouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, mouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, keyListener::keyCallback);
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
        //glClearColor(0.1f, 0.09f, 0.1f, 1.0f);
        setCallbacks();
    }

    private void loop() {
        Sdf.generateCodepointBitmap('G', "C:/Windows/Fonts/arial.ttf", 32);
        //this.font = new CFont("C:/Windows/Fonts/Arial.ttf", 64);
        
        //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        //glEnable(GL_TEXTURE_2D);

        Shader fontShader = new Shader("src/main/java/potato/nightly/shaders/fontShader.glsl");
        Shader sdfShader = new Shader("src/main/java/potato/nightly/shaders/sdfShader.glsl");
        Batch batch = new Batch();
        batch.shader = fontShader;
        batch.sdfShader = sdfShader;
        batch.font = font;
        batch.initBatch();
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        CharInfo oneQuad = new CharInfo(0, 0, 1, 1);
        oneQuad.calculateTextureCoordinates(1, 1);

        Random random = new Random();

//        float beginTime = (float) glfwGetTime();
//        float endTime;
//        float dt;

        while (!glfwWindowShouldClose(glfwWindow)) {
            glClear(GL_COLOR_BUFFER_BIT);
            glClearColor(0.1f, 0.09f, 0.1f, 1);

//            batch.addText("Hello world!", 200, 200, 1, 0xFF00AB0);
//            batch.addText("My name is SuperTapood!", 100, 300, 1.1f, 0xAA01BB);
//
//            StringBuilder message = new StringBuilder();
//            for (int i = 0; i < 10; i++){
//                message.append((char)(random.nextInt('z' - 'a') + 'a'));
//            }

//            batch.addText(message.toString(), 200,400, 1.1f, 0xAA01BB);

            batch.addCharacter(0, 0, 640.0f, oneQuad, 0xFF4500);
            batch.flushBatch();

            glfwSwapBuffers(glfwWindow);
            glfwPollEvents();

//            endTime = (float) glfwGetTime();
//            dt = endTime - beginTime;
//            System.out.println(MessageFormat.format("{0}ms, {1} FPS", dt * 1000, 1 / dt));
//            beginTime = endTime;
        }

//        // this will show about 60.1 fps
//        float fps = 60.15f;
//        float perFrame = 1 / fps;
//        float beginTime = (float) glfwGetTime();
//        float endTime;
//        float frameTime = 0;
//        float dt;
//        while (!glfwWindowShouldClose(glfwWindow)) {
//            glClear(GL_COLOR_BUFFER_BIT);
//            if (frameTime >= perFrame){
//                System.out.println(1 / frameTime);
//                frameTime = 0;
//                //currentScene.update(this);
//                glfwSwapBuffers(glfwWindow);
//            }
//            glfwPollEvents();
//            endTime = (float) glfwGetTime();
//            dt = endTime - beginTime;
//            frameTime += dt;
//            //System.out.println(MessageFormat.format("{0}ms, {1} FPS", dt * 1000, 1 / dt));
//            beginTime = endTime;
//        }

    }

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
        currentScene.start(this);
    }

    public void setCurrentScene(String name) {
        Scene scene = getScene(name);
        setCurrentScene(scene);
    }

    public Camera getCamera() {
        return this.camera;
    }

}
