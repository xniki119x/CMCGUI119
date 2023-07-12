package erx.niki119.cmcgui119.v1201.gui;

import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.v1201.widgets.CButton;
import erx.niki119.cmcgui119.v1201.widgets.CComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
    public List<CComponent> components;
    public ResourceLocation background;
    public boolean backgroundb = true;
    public String name;
    public boolean p = false;

    public CScreen(JsonScreen json, List<CComponent> components) {
        super(Component.translatable(json.name));
        name = json.name;
        if(json.background.equals("")) backgroundb = false;
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
        super.init();
        for(CComponent c : components){
            c.init(this);
        }
    }

    @Override
    public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        if(p) {
            this.panorama.render(p_230430_4_, Mth.clamp(1, 0, 1));
        }else if(backgroundb){
            //fill(p_230430_1_, 0, 0, this.width, this.height, -1);
            p_230430_1_.blit(background, 0, 0, this.width, this.height, 0.0F, 0.0F, 1, 1, 1, 1);
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
