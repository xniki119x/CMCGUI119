package erx.niki119.cmcgui119.utils;

import erx.niki119.cmcgui119.CMCGUI119;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
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
        CMCGUI119.CONFIG_HELPER.reload();
        Minecraft.getInstance().setScreen(CMCGUI119.JSON_HELPER.getScreen("MainMenuScreen"));
    }),
    NONE(a -> {
    });

    private Consumer<ActionType> action;

    ActionType(Consumer<ActionType> action) {
        this.action = action;
    }

    public void start(ActionType a){
        action.accept(a);
    }
}
