package erx.niki119.cmcgui119.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import erx.niki119.cmcgui119.helpers.JsonHelper;
import erx.niki119.cmcgui119.utils.AnchorType;
import erx.niki119.cmcgui119.utils.json.components.JsonText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;

public class CText extends CComponent {
    public final int xOffset, yOffset;
    public String text;
    public FontRenderer font;
    public int l;
    public AnchorType anchor = AnchorType.TOP_LEFT;
    public int fontColor, hoveredFontColor;
    public CText(JsonText json){
        xOffset = json.xPos;
        yOffset = json.yPos;
        anchor = AnchorType.valueOf(json.anchor);
        text = json.text;
        fontColor = JsonHelper.getColorFromString(json.fontColor);
        hoveredFontColor = JsonHelper.getColorFromString(json.hoveredFontColor);
    }
    public CText init(Screen screen){
        this.x = anchor.getX(screen) + this.xOffset;
        this.y = anchor.getY(screen) + this.yOffset;
        return this;
    }
    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_){
        Minecraft mc = Minecraft.getInstance();
        isHovered = p_230430_2_ >= x && p_230430_3_ >= y && p_230430_2_ < x + mc.font.width(text) && p_230430_3_ < y + mc.font.lineHeight;
        if(!isHovered) {
            drawString(p_230430_1_, mc.font, text, x, y, fontColor);
        }else {
            drawString(p_230430_1_, mc.font, text, x, y, hoveredFontColor);
        }
    }

}
