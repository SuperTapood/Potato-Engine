package potato.fonts;

import potato.GlobalData;

public class DynamicText extends StaticText{

    public DynamicText(String fontPath, int fontSize) {
        super(fontPath, fontSize);
    }

    public DynamicText(int fontSize) {
        super(GlobalData.defaultFont, fontSize);
    }

    public void setLabel(int x, int y, float size, int rgb) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = rgb;
    }

    public void setLabel(int x, int y, float size, int red, int green, int blue) {
        String hex = String.format("%02x%02x%02x", red, green, blue);
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = Integer.parseInt(hex, 16);
    }

    public void setLabel(int x, int y, float size, int[] rgb) {
        String hex = String.format("%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
        this.x = x;
        this.y = y;
        this.size = size;
        this.rgb = Integer.parseInt(hex, 16);
    }

    public void setText(String text) {
        super.text = text;
    }
}
