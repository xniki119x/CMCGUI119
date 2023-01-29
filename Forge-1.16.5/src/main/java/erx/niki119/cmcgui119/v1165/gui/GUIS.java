package erx.niki119.cmcgui119.v1165.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraft.client.gui.screen.*;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;

import java.util.function.Function;

public enum GUIS implements Function<Screen, Screen> {
    SINGLEPLAYER(WorldSelectionScreen::new),
    MULTIPLAYER(MultiplayerScreen::new),
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
