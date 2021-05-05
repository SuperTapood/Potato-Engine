package potato.text;

import java.io.IOException;

public class Generate {
    public Generate(String font) {
        try {
            Runtime.getRuntime().exec("ffpython convert.py " + font);
            Runtime.getRuntime().exec("python post.py");
        } catch (IOException e) {
        }
    }
}
