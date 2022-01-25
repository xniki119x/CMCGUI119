package erx.niki119.cmcgui119.utils.json.components;

public class JsonButton extends JsonComponent {
    public String texture;
    public String text;
    public String action;
    public JsonButton(int xPos, int yPos, int width, int height, String anchor, String text, String texture, String action) {
        super("button", xPos, yPos, width, height, anchor);
        this.text = text;
        this.texture = texture;
        this.action = action;
    }
}
