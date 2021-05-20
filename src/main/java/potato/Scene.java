package potato;

import potato.fonts.StaticText;
import potato.fonts.Text;
import potato.render.Potato;
import potato.render.Renderer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Scene {
    public ArrayList<Object> objects;
    public Renderer renderer;

    public Scene() {
        renderer = new Renderer();
        objects = new ArrayList<>();
    }

    public void add(Potato obj) {
        objects.add(obj);
        renderer.add(obj);
    }

    public void add(Text obj) {
        objects.add(obj);
    }

    public void add(Button obj) {
        objects.add(obj);
    }

    public void add(StaticText obj) {
        objects.add(obj);
    }

    public void render(float dt) {
        int rendererIndex = 0;
        for (Object object : objects) {
            try {
                Method func = object.getClass().getMethod("render", float.class);
                func.invoke(object, dt);
            } catch (Exception e) {
//                try {
//                    renderer.render(rendererIndex);
//                    rendererIndex++;
//                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(69);
                }
            }
        }
    }
