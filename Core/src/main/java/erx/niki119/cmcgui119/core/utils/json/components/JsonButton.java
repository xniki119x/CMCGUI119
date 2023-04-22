package erx.niki119.cmcgui119.core.utils.json.components;

public class JsonButton extends JsonComponent {
    public String texture;
    public String text;
    public String action;
    public String data;
    public String fontColor;
    public String hoveredFontColor;

    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action, String data) {
        this(xPos, yPos, width, height, anchor, text, texture, action, data, "000", "000");
    }

    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action, String data, String fontColor, String hoveredFontColor) {
        super("button", xPos, yPos, width, height, anchor);
        this.text = text;
        this.texture = texture;
        this.action = action;
        this.data = data;
        this.fontColor = fontColor;
        this.hoveredFontColor = hoveredFontColor;
    }

    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action) {
        this(xPos, yPos, width, height, anchor,text,texture,action,"", "FFF", "FFF");
    }
}
