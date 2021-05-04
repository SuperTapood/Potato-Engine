package potato.stable;

import potato.stable.render.Potato;
import potato.stable.render.Renderer;

import java.util.ArrayList;

public class Scene {
    public int length = 0;
    protected Renderer renderer = new Renderer();
    protected Window window;
    private ArrayList<Potato> objects = new ArrayList<>();

    public Scene(Window window) {
        this.window = window;
    }

    public void start(Window window) {
        for (Potato sr : objects) {
            sr.start();
            this.renderer.add(sr, window);
        }
    }

    public void append(Potato obj) {
        objects.add(obj);
        length++;
        this.renderer.add(obj, window);
    }

    public void extend(Potato[] objects) {
        for (Potato o : objects) {
            append(o);
        }
    }

    public void extend(ArrayList<Potato> objects) {
        for (Potato o : objects) {
            append(o);
        }
    }

    public void extend(Scene scene) {
        this.extend(scene.objects);
    }

    public void appstart(Potato object) {
        Scene newPotatoes = new Scene(window);
        newPotatoes.append(object);
        newPotatoes.extend(this);
        this.objects = newPotatoes.objects;
    }

    public Potato get(int index) {
        if (index < 0) {
            index = this.length + index;
        }
        return this.objects.get(index);
    }

    public int indexOf(Potato sr) {
        return this.objects.indexOf(sr);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (Object p : this.objects) {
            builder.append(p.toString()).append("\n");
        }
        return builder.toString();
    }

    public void print() {
        System.out.println(this.toString());
    }

    public void update(Window window) {
        this.renderer.render(window);
    }
}
