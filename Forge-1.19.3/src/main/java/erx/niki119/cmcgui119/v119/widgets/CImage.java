package erx.niki119.cmcgui119.v119.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erx.niki119.cmcgui119.core.utils.AnchorType;
import erx.niki119.cmcgui119.core.utils.json.components.JsonImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class CImage extends CComponent {
    protected ResourceLocation texture;
    public AnchorType anchor;
    public int width, height;
    protected final int xOffset, yOffset;
    public CImage(JsonImage json){
        this.xOffset = json.xPos;
        this.yOffset = json.yPos;
        this.width = json.width;
        this.height = json.height;
        this.anchor = AnchorType.valueOf(json.anchor);
        this.texture = json.texture != null ? new ResourceLocation(json.texture) : new ResourceLocation("");
    }

    @Override
    public CImage init(Screen screen){
        this.x = xOffset+anchor.getX(new int[]{screen.width, screen.height});
        this.y = yOffset+anchor.getY(new int[]{screen.width, screen.height});
        return this;
    }

    @Override
    public void render(PoseStack stack, int p_93658_, int p_93659_, float p_93660_) {
        Minecraft mc = Minecraft.getInstance();
        isHovered = p_93658_ >= this.x && p_93659_ >= this.y && p_93658_ < this.x + this.width && p_93659_ < this.y + this.height;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        blit(stack, x , y , 0, 0, width, height, width, height);
        }
}
