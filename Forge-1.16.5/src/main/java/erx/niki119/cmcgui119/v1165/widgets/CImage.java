package erx.niki119.cmcgui119.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import erx.niki119.cmcgui119.utils.AnchorType;
import erx.niki119.cmcgui119.utils.json.components.JsonImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

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
        this.x = xOffset+anchor.getX(screen);
        this.y = yOffset+anchor.getY(screen);
        return this;
    }

    @Override
    public void render(MatrixStack stack, int p_93658_, int p_93659_, float p_93660_) {
        Minecraft mc = Minecraft.getInstance();
        isHovered = p_93658_ >= this.x && p_93659_ >= this.y && p_93658_ < this.x + this.width && p_93659_ < this.y + this.height;
        mc.getTextureManager().bind(texture);
        blit(stack, x , y , 0, 0, width, height, width, height);
        }
}
