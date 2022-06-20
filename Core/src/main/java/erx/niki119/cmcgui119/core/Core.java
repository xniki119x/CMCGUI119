package erx.niki119.cmcgui119.core;

import erx.niki119.cmcgui119.core.helpers.ConfigHelper;
import erx.niki119.cmcgui119.core.helpers.JsonHelper;
import org.apache.logging.log4j.Logger;

public class Core {

    private ConfigHelper chelper;
    private JsonHelper jhelper;
    public Logger LOGGER;
    public String gameDir;

    public Core(IMod mod) {
        LOGGER = mod.getLogger();
        gameDir = mod.getGameDir();
        chelper = new ConfigHelper(this);
        jhelper = new JsonHelper(this);
    }

    public ConfigHelper getChelper() {
        return chelper;
    }

    public JsonHelper getJhelper() {
        return jhelper;
    }
}
