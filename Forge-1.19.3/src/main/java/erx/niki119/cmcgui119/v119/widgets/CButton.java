package erx.niki119.cmcgui119.v119.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erx.niki119.cmcgui119.v119.utils.ActionType;
import erx.niki119.cmcgui119.core.utils.AnchorType;
import erx.niki119.cmcgui119.core.utils.json.components.JsonButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
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
        xOffset = x = json.xPos;
        yOffset = y = json.yPos;
        width = json.width;
        height = json.height;
        if(json.text!=null && !json.text.equals("")) this.text = Component.translatable(json.text).getString();
        anchor = AnchorType.valueOf(json.anchor);
        try {
            if(json.action != null && !json.action.equals("")) {
                action = ActionType.valueOf(json.action);
                this.data = json.data;
            }else {
                action = ActionType.NONE;
            }
        }catch(Exception e){
            e.printStackTrace();
            action = ActionType.NONE;
        }
        this.texture = new ResourceLocation(json.texture);
        this.fontColor = 16777215;
        this.hoverFontColor = 0;
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
    public void render(PoseStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_){
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        isHovered = p_230430_2_ >= x && p_230430_3_ >= y && p_230430_2_ < x + this.width && p_230430_3_ < y + this.height;
        if(!isHovered) {
            blit(p_230430_1_, x, y, 0, 0, width, height, width, height*2);
            if(text != null)
                mc.font.draw(p_230430_1_, text, x + width/2 - mc.font.width(text) / 2, y + height/2 - mc.font.lineHeight/2, fontColor);
        }else {
            blit(p_230430_1_, x, y, 0, height, width, height , width, height*2);
            if(text != null)
                mc.font.draw(p_230430_1_, text, x + width/2 - mc.font.width(text) / 2, y + height/2 - mc.font.lineHeight/2, hoverFontColor);
        }
    }
}
