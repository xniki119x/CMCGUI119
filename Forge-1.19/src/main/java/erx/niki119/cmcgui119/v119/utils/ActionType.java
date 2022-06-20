package erx.niki119.cmcgui119.v119.utils;

import erx.niki119.cmcgui119.v119.CMCGUI119;
import erx.niki119.cmcgui119.v119.gui.GUIS;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraftforge.client.gui.ModListScreen;

import javax.json.JsonObject;
import java.net.URI;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

public enum ActionType {
    OPEN_SINGLEPLAYER(a -> {
        if(Minecraft.getInstance().screen != null)
            Minecraft.getInstance().setScreen(new SelectWorldScreen(Minecraft.getInstance().screen));
    }),
    OPEN_OPTIONS(a -> {
        if(Minecraft.getInstance().screen != null)
            Minecraft.getInstance().setScreen(new OptionsScreen(Minecraft.getInstance().screen, Minecraft.getInstance().options));
    }),
    OPEN_MODS(a -> {
        Minecraft.getInstance().setScreen(new ModListScreen(Minecraft.getInstance().screen));
    }),
    OPEN_MULTIPLAYER(a -> {
        Minecraft.getInstance().setScreen(new JoinMultiplayerScreen(Minecraft.getInstance().screen));
    }),
    QUIT(a -> {
        Minecraft.getInstance().stop();
    }),
    RELOAD_MOD_CONFIG(a -> {
        CMCGUI119.INSTANCE.reload();
        //Minecraft.getInstance().setScreen(CMCGUI119.JSON_HELPER.getScreen("MainMenuScreen"));
    }),
    CONNECT_TO_SERVER(a -> {
        ServerData sd = new ServerData("", "", false);
        ConnectScreen.startConnecting(new TitleScreen(), Minecraft.getInstance(), ServerAddress.parseString(sd.ip), sd);
    }),
    OPEN_GUI(a ->{
        Minecraft.getInstance().setScreen(GUIS.valueOf(a.data.toUpperCase(Locale.ROOT)).apply(Minecraft.getInstance().screen));
    }),
    OPEN_URL(a ->{
        Util.getPlatform().openUri(URI.create(a.data));}),
    NONE(a -> {});

    private Consumer<ActionType> action;
    public String data;

    ActionType(Consumer<ActionType> action) {
        this.action = action;
    }
    public void start(ActionType a){
        action.accept(a);
    }
}
