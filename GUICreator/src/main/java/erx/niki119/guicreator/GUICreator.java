package erx.niki119.guicreator;

import erx.niki119.cmcgui119.core.Core;
import erx.niki119.cmcgui119.core.IMod;
import org.apache.logging.log4j.Logger;

public class GUICreator {

    public static Core CORE;

    public static void main(String[] args) {
        CORE = new Core(new IMod() {
            @Override
            public Logger getLogger() {
                return null;
            }

            @Override
            public String getGameDir() {
                return null;
            }

            @Override
            public void reload() {

            }
        });
    }
}
