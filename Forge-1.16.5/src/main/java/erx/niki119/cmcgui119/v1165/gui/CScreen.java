package erx.niki119.cmcgui119.v1165.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.v1165.widgets.CButton;
import erx.niki119.cmcgui119.v1165.widgets.CComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import java.util.List;

public class CScreen extends Screen {
    public List<CComponent> components;
    public ResourceLocation background;
    public String name;

    public CScreen(JsonScreen json, List<CComponent> components) {
        super(new TranslationTextComponent(json.name));
        name = json.name;
        if(json.background!=null) {
            background = new ResourceLocation(json.background);
        }else {
            background = new ResourceLocation("textures/gui/presets/isles.png");
        }
        this.components = components;
    }

    @Override
    public void init(Minecraft p_231158_1_, int p_231158_2_, int p_231158_3_) {
        super.init(p_231158_1_, p_231158_2_, p_231158_3_);
        for(CComponent c : components){
            c.init(this);
        }
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        fill(p_230430_1_, 0, 0, this.width, this.height, -1);
        minecraft.getTextureManager().bind(background);
        blit(p_230430_1_, 0, 0, this.width, this.height, 0.0F, 0.0F, 1, 1, 1, 1);
        for(CComponent c : components){
            c.render(p_230430_1_,p_230430_2_,p_230430_3_,p_230430_4_);
        }
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        for(CComponent c : components){
            if(c instanceof CButton) {
                if(c.isHovered) {
                    return ((CButton)c).press();
                }
            }
        }
        return false;
    }
}
