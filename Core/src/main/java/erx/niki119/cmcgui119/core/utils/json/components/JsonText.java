package erx.niki119.cmcgui119.v1165.utils.json.components;

import net.minecraft.client.Minecraft;

public class JsonText extends JsonComponent {

    public String fontColor;
    public String hoveredFontColor;
    public String text;

    public JsonText(String id, int xPos, int yPos, String anchor, String text, String fontColor, String hoveredFontColor) {
        super(id,xPos,yPos, Minecraft.getInstance().font.width(text),Minecraft.getInstance().font.lineHeight, anchor);
        this.text = text;
        this.fontColor = fontColor;
        this.hoveredFontColor = hoveredFontColor;
    }
}
