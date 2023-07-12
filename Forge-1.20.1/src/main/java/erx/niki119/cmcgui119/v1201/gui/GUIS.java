package erx.niki119.cmcgui119.v1201.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraftforge.client.gui.ModListScreen;

import java.util.function.Function;

public enum GUIS implements Function<Screen, Screen> {
    TITLESCREEN(m -> new TitleScreen()),
    SINGLEPLAYER(SelectWorldScreen::new),
    MULTIPLAYER(JoinMultiplayerScreen::new),
    ADVANCEMENTS(m -> new AdvancementsScreen(Minecraft.getInstance().player.connection.getAdvancements())),
    STATS(m -> new StatsScreen(Minecraft.getInstance().screen, Minecraft.getInstance().player.getStats())),
    OPTIONS(m -> new OptionsScreen(m, Minecraft.getInstance().options)),
    NULL(m -> null),
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
