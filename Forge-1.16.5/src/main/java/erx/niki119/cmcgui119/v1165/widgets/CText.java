package erx.niki119.cmcgui119.v1165.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import erx.niki119.cmcgui119.core.utils.AnchorType;
import erx.niki119.cmcgui119.core.utils.json.components.JsonText;
import erx.niki119.cmcgui119.v1165.EmpyrealInterface;
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
        fontColor = getColorFromString(json.fontColor);
        hoveredFontColor = getColorFromString(json.hoveredFontColor);
    }
    public CText init(Screen screen){
        this.x = anchor.getX(new int[]{screen.width, screen.height}) + this.xOffset;
        this.y = anchor.getY(new int[]{screen.width, screen.height}) + this.yOffset;
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

    public static int getColorFromString(String colorString){
        int R = 0;
        int G = 0;
        int B = 0;
        int color = 0;
        try {
            if(colorString.length() == 6) {
                R = getIntFromColorString(colorString.substring(0, 2));
                G = getIntFromColorString(colorString.substring(2,4));
                B = getIntFromColorString(colorString.substring(4,6));
                color = R * 16*16*16*16+G*16*16+B;
            } else if(colorString.length() == 3) {
                R = getIntFromColorString(colorString.substring(0, 1));
                G = getIntFromColorString(colorString.substring(1,2));
                B = getIntFromColorString(colorString.substring(2,3));
                color =R * 16*16*16*16+G*16*16+B;
            }
        }catch(Exception e){
            EmpyrealInterface.LOGGER.error("ERROR PARSE COLOR");
        }
        return color;
    }
    public static int getIntFromColorString(String color){
        int i = 0;
        if(color.length()==2){
            i = get10From16(color.substring(0,1))*16+get10From16(color.substring(1,2));
        }else if(color.length()==1) {
            i = get10From16(color)*17;
        }
        return i;
    }
    public static int get10From16(String s16){
        int i10 = 0;
        s16 = s16.toLowerCase();
        switch(s16){
            case "1":i10=1;break;
            case "2":i10=2;break;
            case "3":i10=3;break;
            case "4":i10=4;break;
            case "5":i10=5;break;
            case "6":i10=6;break;
            case "7":i10=7;break;
            case "8":i10=8;break;
            case "9":i10=9;break;
            case "a":i10=10;break;
            case "b":i10=11;break;
            case "c":i10=12;break;
            case "d":i10=13;break;
            case "e":i10=14;break;
            case "f":i10=15;break;
        }
        return i10;
    }

}
