package erx.niki119.cmcgui119.v1165.event;

import erx.niki119.cmcgui119.v1165.EmpyrealInterface;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ОбработчикСобытий {

    @SubscribeEvent
    public void событие_открытие_меню(GuiOpenEvent событие){
        if(событие.getGui() != null) {
            Class<?> класс = событие.getGui().getClass();
            System.out.println(класс);
            if(класс == MainMenuScreen.class) {
                if(EmpyrealInterface.Ядро.получить_конфиг().config.главное_меню) {
                    try {
                        Screen s = EmpyrealInterface.INSTANCE.getScreen("главное_меню");
                        if(s != null)
                            событие.setGui(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(класс == IngameMenuScreen.class){
                if(EmpyrealInterface.Ядро.получить_конфиг().config.меню_паузы) {
                    try {
                        Screen s = EmpyrealInterface.INSTANCE.getScreen("меню_паузы");
                        if(s != null)
                            событие.setGui(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
