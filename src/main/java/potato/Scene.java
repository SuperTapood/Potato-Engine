package potato;

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
    }

    public void add(Text obj) {
        objects.add(obj);
    }

    public void render() {
        for (Object object : objects) {
            try {
                Method func = object.getClass().getMethod("render", Object[].class);
                func.invoke(object, (Object) null);
            }
            catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {}
        }
        renderer.render(GlobalData.windowPtr);
    }

    public void update(float dt) {
        for (Object object : objects) {
            try {
                Method func = object.getClass().getMethod("update", Object.class);
                func.invoke(object, dt);
            }
            catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
