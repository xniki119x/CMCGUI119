package erx.niki119.cmcgui119.core.utils;

import java.util.function.Function;

public enum AnchorType {
    DEFAULT(s -> s[0] / 2, s -> s[1] / 4 + 48),
    TOP_LEFT(s -> 0, s -> 0),
    TOP_CENTER(s -> s[0] / 2, s -> 0),
    TOP_RIGHT(s -> s[0], s -> 0),
    MIDDLE_LEFT(s -> 0, s -> s[1] / 2),
    MIDDLE_CENTER(s -> s[0] / 2, s -> s[1] / 2),
    MIDDLE_RIGHT(s -> s[0], s -> s[1] / 2),
    BOTTOM_LEFT(s -> 0, s -> s[1]),
    BOTTOM_CENTER(s -> s[0] / 2, s -> s[1]),
    BOTTOM_RIGHT(s -> s[0], s -> s[1]);

    private Function<int[], Integer> xFunc; // {width, height}
    private Function<int[], Integer> yFunc;

    private AnchorType(Function<int[], Integer> xFunc, Function<int[], Integer> yFunc) {
        this.xFunc = xFunc;
        this.yFunc = yFunc;
    }

    public int getX(int[] arr) {
        return this.xFunc.apply(arr);
    }

    public int getY(int[] arr) {
        return this.yFunc.apply(arr);
    }
}
