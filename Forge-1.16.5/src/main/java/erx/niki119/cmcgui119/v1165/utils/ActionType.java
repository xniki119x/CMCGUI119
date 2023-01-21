package erx.niki119.cmcgui119.v1165.utils;

import erx.niki119.cmcgui119.v1165.CMCGUI119;
import erx.niki119.cmcgui119.v1165.gui.GUIS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;

import java.util.Locale;
import java.util.function.Consumer;

public enum ActionType {
    OPEN_SINGLEPLAYER(a -> {
        if(Minecraft.getInstance().screen != null)
            Minecraft.getInstance().setScreen(new WorldSelectionScreen(Minecraft.getInstance().screen));
    }),
    OPEN_OPTIONS(a -> {
        if(Minecraft.getInstance().screen != null)
            Minecraft.getInstance().setScreen(new OptionsScreen(Minecraft.getInstance().screen, Minecraft.getInstance().options));
    }),
    OPEN_MODS(a -> {
        Minecraft.getInstance().setScreen(new ModListScreen(Minecraft.getInstance().screen));
    }),
    OPEN_MULTIPLAYER(a -> {
        Minecraft.getInstance().setScreen(new MultiplayerScreen(Minecraft.getInstance().screen));
    }),
    QUIT(a -> {
        Minecraft.getInstance().stop();
    }),
    RELOAD_MOD_CONFIG(a -> {
        CMCGUI119.INSTANCE.reload();
        Minecraft.getInstance().setScreen(CMCGUI119.INSTANCE.getScreen("MainMenuScreen"));
    }),
    OPEN_GUI(a ->{
        try {
            Minecraft.getInstance().setScreen(GUIS.valueOf(a.data.toUpperCase(Locale.ROOT)).apply(Minecraft.getInstance().screen));
        }catch (Exception e){e.printStackTrace();}
    }),
    NONE(a -> {
    });

    private Consumer<ActionType> action;
    public String data;

    ActionType(Consumer<ActionType> action) {
        this.action = action;
    }

    public void start(ActionType a){
        action.accept(a);
    }
}
