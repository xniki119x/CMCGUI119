package erx.niki119.cmcgui119.v119.event;

import erx.niki119.cmcgui119.v119.CMCGUI119;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void screenOpenEvent(ScreenEvent.Opening event){
        if(event.getScreen() != null) {
            CMCGUI119.LOGGER.info(event.getScreen().getClass());
            if(event.getScreen().getClass() == TitleScreen.class) {
                if(CMCGUI119.CORE.getChelper().config.enableCustomMainMenuScreen) {
                    try {
                        Screen s = CMCGUI119.INSTANCE.getScreen("MainMenuScreen");
                        if(s != null) {
                            event.setNewScreen(s);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
