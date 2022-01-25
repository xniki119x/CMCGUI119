package erx.niki119.cmcgui119.helpers;

import erx.niki119.cmcgui119.CMCGUI119;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHelper {
    @SubscribeEvent
    public void screenOpenEvent(GuiOpenEvent event){
        if(event.getGui() != null) {
            if(event.getGui().getClass() == MainMenuScreen.class) {
                if(CMCGUI119.CONFIG_HELPER.config.enableCustomMainMenuScreen) {
                    try {
                        Screen s = CMCGUI119.JSON_HELPER.getScreen("MainMenuScreen");
                        if(s != null)
                            event.setGui(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
