package erx.niki119.cmcgui119.v1165.utils;

import net.minecraft.client.gui.screen.Screen;
import java.util.function.Function;

public enum AnchorType {
    DEFAULT(s -> s.width / 2, s -> s.height / 4 + 48),
    TOP_LEFT(s -> 0, s -> 0),
    TOP_CENTER(s -> s.width / 2, s -> 0),
    TOP_RIGHT(s -> s.width, s -> 0),
    MIDDLE_LEFT(s -> 0, s -> s.height / 2),
    MIDDLE_CENTER(s -> s.width / 2, s -> s.height / 2),
    MIDDLE_RIGHT(s -> s.width, s -> s.height / 2),
    BOTTOM_LEFT(s -> 0, s -> s.height),
    BOTTOM_CENTER(s -> s.width / 2, s -> s.height),
    BOTTOM_RIGHT(s -> s.width, s -> s.height);

    private Function<Screen, Integer> xFunc;
    private Function<Screen, Integer> yFunc;

    private AnchorType(Function<Screen, Integer> xFunc, Function<Screen, Integer> yFunc) {
        this.xFunc = xFunc;
        this.yFunc = yFunc;
    }

    public int getX(Screen scn) {
        return this.xFunc.apply(scn);
    }

    public int getY(Screen scn) {
        return this.yFunc.apply(scn);
    }
}
