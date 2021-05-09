package potato.text;

import potato.Transform;
import potato.render.Potato;

public class Text extends Potato {
    Glyph[] text;

    public Text(int x, int y, int size, String text) {
        super(new Transform(0, 0, 0, 0),0);
        System.out.println(text);
        this.text = new Glyph[text.length()];
        int lastX = x;
        for (int i = 0; i < text.length(); i++) {
            var ch = new Glyph(lastX, y, size, text.charAt(i));
            lastX += ch.getWidth();
            this.text[i] = ch;
        }
        super.setBlank();
    }

    @Override
    public void update(float dt) {
        System.out.println("update");
        isDirty = true;
    }


}
