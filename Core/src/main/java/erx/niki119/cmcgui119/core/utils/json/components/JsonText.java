package erx.niki119.cmcgui119.core.utils.json.components;

public class JsonText extends JsonComponent {

    public String fontColor;
    public String hoveredFontColor;
    public String text;

    public JsonText(String id, int xPos, int yPos, int width, int height, String anchor, String text, String fontColor, String hoveredFontColor) {
        super(id,xPos,yPos, width, height, anchor);
        this.text = text;
        this.fontColor = fontColor;
        this.hoveredFontColor = hoveredFontColor;
    }
}
