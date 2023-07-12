package erx.niki119.cmcgui119.v1201;


import erx.niki119.cmcgui119.core.Ядро;

import erx.niki119.cmcgui119.core.IMod;
import erx.niki119.cmcgui119.core.helpers.Жсон;
import erx.niki119.cmcgui119.core.utils.References;
import erx.niki119.cmcgui119.core.utils.json.components.*;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.v1201.event.ОбработчикСобытий;
import erx.niki119.cmcgui119.v1201.gui.CScreen;
import erx.niki119.cmcgui119.v1201.widgets.CButton;
import erx.niki119.cmcgui119.v1201.widgets.CComponent;
import erx.niki119.cmcgui119.v1201.widgets.CImage;
import erx.niki119.cmcgui119.v1201.widgets.CText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(References.MOD_ID)
public class EmpyrealInterface implements IMod {
    public static Logger LOGGER = LogManager.getLogger(References.MOD_NAME);
    public static Ядро Ядро;
    public static EmpyrealInterface INSTANCE;
    public ОбработчикСобытий обработчикСобытий = new ОбработчикСобытий();
    private List<CScreen> screens = new ArrayList<>();
    public EmpyrealInterface() {
        Ядро = new Ядро(this);
        INSTANCE = this;
        loadScreens();
        MinecraftForge.EVENT_BUS.register(обработчикСобытий);
        LOGGER.info("Запустился");
    }

    @Override
    public void reload() {
        loadScreens();
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public String getGameDir(){
        return FMLPaths.GAMEDIR.get().toFile().getPath();
    }

    public CScreen getScreen(String s){
        for(CScreen screen : screens){
            if(screen.name.equals(s))
                return screen;
        }
        return null;
    }

    public void loadScreens(){
        List<JsonScreen> jsons = Ядро.получить_жсон().loadScreens();
        Жсон jh = Ядро.получить_жсон();
        screens.clear();
        for(JsonScreen j : jsons){
            screens.add(new CScreen(j, jsonsToCComponents(jh.readComponentsForScreen(j))));
        }
    }

    public static List<CComponent> jsonsToCComponents(List<JsonComponent> jsons){
        List<CComponent> components = new ArrayList<>();
        for(JsonBase jb : jsons){
            if(jb instanceof JsonButton){
                components.add(new CButton(((JsonButton) jb)));
            }else if(jb instanceof JsonImage){
                components.add(new CImage(((JsonImage) jb)));
            }else if(jb instanceof JsonText){
                components.add(new CText(((JsonText) jb)));
            }
        }
        return components;
    }
}
