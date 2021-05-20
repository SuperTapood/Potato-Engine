package potato.fonts;

import potato.GlobalData;

public class StaticText {
    public int x, y;
    public float size;
    public int rgb;
    public Text txt;
    public String text;

    public StaticText(String fontPath, int fontSize) {
        txt = new Text(fontPath, fontSize);
    }

    public StaticText(int fontSize) {
        txt = new Text(GlobalData.defaultFont, fontSize);
    }

    public void setLabel(String text, int x, int y, float size, int rgb) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = rgb;
    }

    public void setLabel(String text, int x, int y, float size, int red, int green, int blue) {
        String hex = String.format("%02x%02x%02x", red, green, blue);
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = Integer.parseInt(hex, 16);
    }

    public void setLabel(String text, int x, int y, float size, int[] rgb) {
        String hex = String.format("%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = Integer.parseInt(hex, 16);
    }

    public void render() {
        txt.addText(text, x, y, size, rgb);
        txt.flushBatch();
    }
}