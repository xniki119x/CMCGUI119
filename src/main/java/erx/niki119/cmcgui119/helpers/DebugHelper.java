package erx.niki119.cmcgui119.helpers;

import erx.niki119.cmcgui119.CMCGUI119;

public class DebugHelper {
    public static void debug(Object o){
        if(CMCGUI119.CONFIG_HELPER == null || CMCGUI119.CONFIG_HELPER.config.enableDebug)
            CMCGUI119.LOGGER.debug(o);
    }
}
