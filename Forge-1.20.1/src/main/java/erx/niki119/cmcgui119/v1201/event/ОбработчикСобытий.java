package erx.niki119.cmcgui119.v1201.event;

import erx.niki119.cmcgui119.v1201.EmpyrealInterface;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ОбработчикСобытий {

    @SubscribeEvent
    public void событие_открытие_меню(ScreenEvent.Opening событие){
        if(событие.getScreen() != null) {
            Class<?> класс = событие.getScreen().getClass();
            System.out.println(класс);
            if(класс == TitleScreen.class) {
                if(EmpyrealInterface.Ядро.получить_конфиг().config.главное_меню) {
                    try {
                        Screen s = EmpyrealInterface.INSTANCE.getScreen("главное_меню");
                        if(s != null)
                            событие.setNewScreen(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(класс == PauseScreen.class){
                if(EmpyrealInterface.Ядро.получить_конфиг().config.меню_паузы) {
                    try {
                        Screen s = EmpyrealInterface.INSTANCE.getScreen("меню_паузы");
                        if(s != null)
                            событие.setNewScreen(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
