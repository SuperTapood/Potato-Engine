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
                Method func = object.getClass().getMethod("update", Object.class);
                func.invoke(object, dt);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException a) {
                try {
                    Method func = object.getClass().getMethod("render", Object.class);
                    func.invoke(object, dt);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException b) {
                    try {
                        this.renderer.render(rendererIndex);
                        rendererIndex++;
                    } catch (Exception c) {
                        c.printStackTrace();
                        System.exit(69);
                    }
                }
            }
        }
    }
}
