package erx.niki119.cmcgui119.v119.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraftforge.client.gui.ModListScreen;

import java.util.function.Function;

public enum GUIS implements Function<Screen, Screen> {
    SINGLEPLAYER(SelectWorldScreen::new),
    MULTIPLAYER(JoinMultiplayerScreen::new),
    OPTIONS(m -> new OptionsScreen(m, Minecraft.getInstance().options)),
    MODS(ModListScreen::new);

    public Function<Screen, Screen> s;

    GUIS(Function<Screen, Screen> s){
        this.s=s;
    }

    @Override
    public Screen apply(Screen screen) {
        return s.apply(screen);
    }
}
