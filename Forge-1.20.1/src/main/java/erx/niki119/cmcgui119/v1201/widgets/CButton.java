package erx.niki119.cmcgui119.v1201.widgets;

import erx.niki119.cmcgui119.core.utils.json.Value;
import erx.niki119.cmcgui119.v1201.utils.ActionType;
import erx.niki119.cmcgui119.core.utils.AnchorType;
import erx.niki119.cmcgui119.core.utils.json.components.JsonButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class CButton extends CComponent {

    protected ResourceLocation texture;
    protected int width, height;
    protected final int xOffset, yOffset;
    protected String text;
    protected final int fontColor, hoverFontColor;
    protected AnchorType anchor;
    public ActionType action;
    public String data;
    public CButton(JsonButton json){
        xOffset = x = new Value<Integer>(json.xPos, 0).get();
        yOffset = y = new Value<Integer>(json.yPos, 0).get();
        width = new Value<Integer>(json.width, 10).get();
        height = new Value<Integer>(json.height, 10).get();
        text = new Value<String>(json.text, "").get();
        anchor = AnchorType.valueOf(new Value<String>(json.anchor, "DEFAULT").get());
        data = new Value<String>(json.data, "").get();
        action = ActionType.valueOf(new Value<String>(json.action, "NONE").get());
        texture = new ResourceLocation(new Value<String>(json.texture, "missing").get());
        fontColor = CText.getColorFromString(new Value<String>(json.fontColor, "FFF").get());
        hoverFontColor = CText.getColorFromString(new Value<String>(json.hoveredFontColor, "FFF").get());
    }

    public boolean press(){
        if(action!=null) {
            action.data = data;
            action.start(action);
        }
        return true;
    }

    public CButton init(Screen screen){
        x = xOffset + anchor.getX(new int[]{screen.width, screen.height});
        y = yOffset + anchor.getY(new int[]{screen.width, screen.height});
        return this;
    }

    @Override
    public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_){
        Minecraft mc = Minecraft.getInstance();
        isHovered = p_230430_2_ >= x && p_230430_3_ >= y && p_230430_2_ < x + this.width && p_230430_3_ < y + this.height;
        if(!isHovered) {
            p_230430_1_.blit(texture, x, y, 0, 0, width, height, width, height*2);
            if(text != null)
                p_230430_1_.drawString(mc.font, text, x + width/2 - mc.font.width(text) / 2,
                y + height/2 - mc.font.lineHeight/2,
                        fontColor);
        }else {
            p_230430_1_.blit(texture, x, y, 0, height, width, height , width, height*2);
            if(text != null)
                p_230430_1_.drawString(mc.font, text, x + width/2 - mc.font.width(text) / 2, y + height/2 - mc.font.lineHeight/2, hoverFontColor);
        }
    }
}
