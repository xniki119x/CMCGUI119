package erx.niki119.cmcgui119.core.utils.json.components;

public class JsonButton extends JsonComponent {
    public String texture;
    public String text;
    public String action;
    public String data;
    public int fontColor;
    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action, String data) {
        this(xPos, yPos, width, height, anchor, text, texture, action, data, 16777215);
    }

    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action, String data, int fontColor) {
        super("button", xPos, yPos, width, height, anchor);
        this.text = text;
        this.texture = texture;
        this.action = action;
        this.data = data;
        this.fontColor = fontColor;
    }

    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action) {
        this(xPos, yPos, width, height, anchor,text,texture,action,"", 16777215);
    }
}
