package erx.niki119.cmcgui119.v119.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.v119.widgets.CButton;
import erx.niki119.cmcgui119.v119.widgets.CComponent;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.List;

public class CScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    public boolean p = false;
    public List<CComponent> components;
    public ResourceLocation background;
    public String name;

    public CScreen(JsonScreen json, List<CComponent> components) {
        super(Component.translatable(json.name));
        name = json.name;
        if(json.background!=null) {
            background = new ResourceLocation(json.background);
        }else {
            p = true;
            background = new ResourceLocation("textures/gui/presets/isles.png");
        }
        this.components = components;
    }

    @Override
    public void init() {
        for(CComponent c : components){
            c.init(this);
        }
    }

    @Override
    public void render(PoseStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        if(p) {
            this.panorama.render(p_230430_4_, Mth.clamp(1, 0.0F, 1.0F));
        }else {
            RenderSystem.setShaderTexture(0, background);
            blit(p_230430_1_, 0, 0, this.width, this.height, 0.0F, 0.0F, 1, 1, 1, 1);
        }
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
