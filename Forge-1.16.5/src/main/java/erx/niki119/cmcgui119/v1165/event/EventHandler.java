package erx.niki119.cmcgui119.v1165.event;

import erx.niki119.cmcgui119.v1165.CMCGUI119;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public void screenOpenEvent(GuiOpenEvent event){
        if(event.getGui() != null) {
            Class<?> clazz = event.getGui().getClass();
            System.out.println(clazz);
            if(clazz == MainMenuScreen.class) {
                if(CMCGUI119.CORE.getChelper().config.enableCustomMainMenuScreen) {
                    try {
                        Screen s = CMCGUI119.INSTANCE.getScreen("MainMenuScreen");
                        if(s != null)
                            event.setGui(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(clazz == IngameMenuScreen.class){
                if(CMCGUI119.CORE.getChelper().config.enableCustomMainMenuScreen) {

                }
            }
        }
    }
}
