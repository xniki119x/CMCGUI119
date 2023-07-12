package erx.niki119.cmcgui119.v1201.utils;

import erx.niki119.cmcgui119.v1201.EmpyrealInterface;
import erx.niki119.cmcgui119.v1201.gui.GUIS;
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

import java.net.URI;
import java.util.Locale;
import java.util.function.Consumer;

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
    CONNECT_TO_SERVER(a -> {
        ServerData sd = new ServerData(a.data, a.data, false);
        //Minecraft.getInstance().setScreen(new ConnectingScreen(new TitleScreen(), Minecraft.getInstance(), sd));
        //Minecraft.getInstance().setScreen(new ConnectScreen());
        ConnectScreen.startConnecting(new TitleScreen(), Minecraft.getInstance(), ServerAddress.parseString(sd.ip),
                sd, false);
    }),
    DISCONNECT(a -> {
        Minecraft.getInstance().clearLevel();
        Minecraft.getInstance().setScreen(new TitleScreen());
    }),
    QUIT(a -> {
        Minecraft.getInstance().stop();
    }),
    RELOAD_MOD_CONFIG(a -> {
        EmpyrealInterface.INSTANCE.reload();
        Minecraft.getInstance().setScreen(EmpyrealInterface.INSTANCE.getScreen("MainMenuScreen"));
    }),
    OPEN_GUI(a ->{
        try {
            Minecraft.getInstance().setScreen(GUIS.valueOf(a.data.toUpperCase(Locale.ROOT)).apply(Minecraft.getInstance().screen));
        }catch (Exception e){e.printStackTrace();}
    }),
    OPEN_URL(a ->{
        Util.getPlatform().openUri(URI.create(a.data));}),
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
