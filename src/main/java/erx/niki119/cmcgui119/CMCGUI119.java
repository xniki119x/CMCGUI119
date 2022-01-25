package erx.niki119.cmcgui119;

import erx.niki119.cmcgui119.helpers.JsonHelper;
import erx.niki119.cmcgui119.helpers.EventHelper;
import erx.niki119.cmcgui119.helpers.ConfigHelper;
import erx.niki119.cmcgui119.utils.References;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("cmcgui119")
public class CMCGUI119 {
    public static final Logger LOGGER = LogManager.getLogger(References.MOD_NAME);
    public static final EventHelper guiEvenHandler = new EventHelper();
    public static final JsonHelper JSON_HELPER = new JsonHelper();
    public static final ConfigHelper CONFIG_HELPER = new ConfigHelper(FMLPaths.GAMEDIR.get().toFile().getPath()+"/"+ References.MOD_ID+"/"+References.MOD_ID+".json");
    public CMCGUI119() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(guiEvenHandler);
        LOGGER.info("Запустился");
    }
}
