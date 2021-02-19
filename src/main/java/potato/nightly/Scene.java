package potato.nightly;

import potato.nightly.render.Renderer;
import potato.nightly.render.SpriteRenderer;

import java.util.ArrayList;

public class Scene {
    public int length = 0;
    protected Renderer renderer = new Renderer();
    protected Window window;
    private ArrayList<SpriteRenderer> objects = new ArrayList<>();

    public Scene(Window window) {
        this.window = window;
    }

    public void start(Window window) {
        for (SpriteRenderer sr : objects) {
            sr.start();
            this.renderer.add(sr, window);
        }
    }

    public void append(SpriteRenderer obj) {
        objects.add(obj);
        length++;
        this.renderer.add(obj, window);
    }

    public void extend(SpriteRenderer[] objects) {
        for (SpriteRenderer o : objects) {
            append(o);
        }
    }

    public void extend(ArrayList<SpriteRenderer> objects) {
        for (SpriteRenderer o : objects) {
            append(o);
        }
    }

    public void extend(Scene scene) {
        this.extend(scene.objects);
    }

    public void appstart(SpriteRenderer object) {
        Scene newPotatoes = new Scene(window);
        newPotatoes.append(object);
        newPotatoes.extend(this);
        this.objects = newPotatoes.objects;
    }

    public Object get(int index) {
        if (index < 0) {
            index = this.length + index;
        }
        return this.objects.get(index);
    }

    public int indexOf(Object object) {
        return this.objects.indexOf(object);
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
