package erx.niki119.cmcgui119.v1165;


import erx.niki119.cmcgui119.core.Core;

import erx.niki119.cmcgui119.core.IMod;
import erx.niki119.cmcgui119.core.helpers.JsonHelper;
import erx.niki119.cmcgui119.core.utils.References;
import erx.niki119.cmcgui119.core.utils.json.components.*;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.v1165.event.EventHandler;
import erx.niki119.cmcgui119.v1165.gui.CScreen;
import erx.niki119.cmcgui119.v1165.utils.Rmsp;
import erx.niki119.cmcgui119.v1165.widgets.CButton;
import erx.niki119.cmcgui119.v1165.widgets.CComponent;
import erx.niki119.cmcgui119.v1165.widgets.CImage;
import erx.niki119.cmcgui119.v1165.widgets.CText;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod("empyrealinterface")
public class CMCGUI119 implements IMod {
    public static Logger LOGGER = LogManager.getLogger(References.MOD_NAME);
    public static Core CORE;
    public static CMCGUI119 INSTANCE;
    public EventHandler eventHandler = new EventHandler();
    private List<CScreen> screens = new ArrayList<>();
    public CMCGUI119() {
        CORE = new Core(this);
        INSTANCE = this;
        loadScreens();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        MinecraftForge.EVENT_BUS.addListener(Rmsp::keyInputEvent);
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
        List<JsonScreen> jsons = CORE.getJhelper().loadScreens();
        JsonHelper jh = CORE.getJhelper();
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
