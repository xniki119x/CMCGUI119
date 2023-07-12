package erx.niki119.cmcgui119.v1201.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;

public class CComponent implements Renderable
{
    public int x, y;
    public boolean isHovered = false;


    public CComponent init(Screen screen)
    {
        return this;
    }

    @Override
    public void render(GuiGraphics p_281245_, int p_253973_, int p_254325_, float p_254004_)
    {

    }

}
