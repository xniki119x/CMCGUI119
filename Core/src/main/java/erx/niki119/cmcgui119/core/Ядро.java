package erx.niki119.cmcgui119.core;

import erx.niki119.cmcgui119.core.helpers.Конфиг;
import erx.niki119.cmcgui119.core.helpers.Жсон;
import org.apache.logging.log4j.Logger;

public class Ядро {

    private Конфиг конфиг;
    private Жсон жсон;
    public Logger ЛОГГЕР;
    public String папка_игры;

    public Ядро(IMod mod) {
        ЛОГГЕР = mod.getLogger();
        папка_игры = mod.getGameDir();
        конфиг = new Конфиг(this);
        жсон = new Жсон(this);
    }

    public Конфиг получить_конфиг() {
        return конфиг;
    }

    public Жсон получить_жсон() {
        return жсон;
    }
}
