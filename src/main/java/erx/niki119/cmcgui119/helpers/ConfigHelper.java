package erx.niki119.cmcgui119.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import erx.niki119.cmcgui119.CMCGUI119;
import erx.niki119.cmcgui119.utils.json.JsonConfig;
import erx.niki119.cmcgui119.utils.json.components.JsonButton;
import erx.niki119.cmcgui119.utils.json.components.JsonImage;
import erx.niki119.cmcgui119.utils.json.screens.JsonScreen;
import erx.niki119.cmcgui119.widgets.CButton;
import erx.niki119.cmcgui119.widgets.CComponent;
import net.minecraft.client.gui.screen.MainMenuScreen;

import java.io.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ConfigHelper {
    public JsonConfig config;
    private final File config_file;
    private final Gson gson;
    public boolean isReloadable = true;

    public ConfigHelper(String configPath){
        config_file = new File(configPath);
        gson = new GsonBuilder().setPrettyPrinting().create();
        loadСonfig();
    }
    public void reload(){
        if(isReloadable){
            loadСonfig();
        }
    }
    public void loadСonfig(){
        if(!config_file.getParentFile().exists())
            config_file.getParentFile().mkdirs();
        if(!config_file.exists())
            createDefaultConfig();
        config = readJsonConfig();
        File f = new File(config_file.getParentFile().getPath()+"/screens");
        if(!f.exists()) {
            f.mkdirs();
            if(config.enableCustomMainMenuScreen){
                createDefaultScreen();
            }
        }
        CMCGUI119.JSON_HELPER.loadComponents();
    }
    public void createDefaultConfig(){
        try {
            config_file.createNewFile();
            FileWriter fw = new FileWriter(config_file);
            String jsonstring = gson.toJson(new JsonConfig());
            fw.write(jsonstring);
            fw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public JsonConfig readJsonConfig(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(config_file)));
            String s;
            String ss = "";
            while((s = br.readLine()) != null){
                ss+=s;
            }
            return gson.fromJson(ss, JsonConfig.class);
        }catch(Exception e){
            e.printStackTrace();
            return new JsonConfig();
        }
    }

    public void createDefaultScreen(){
        String screensPath = config_file.getParentFile().getPath() + "/screens";
        try {
            JsonScreen mainMenuScreen = new JsonScreen("screen", "MainMenuScreen", "cmcgui119:textures/background.png");
            JsonButton singleplayer = new JsonButton(20, -160,100,20,"BOTTOM_LEFT", "Singleplayer", "cmcgui119:textures/button.png", "OPEN_SINGLEPLAYER");
            JsonButton multiplayer = new JsonButton(20, -130,100,20,"BOTTOM_LEFT", "Multiplayer", "cmcgui119:textures/button.png", "OPEN_MULTIPLAYER");
            JsonButton settings = new JsonButton(20, -100,100,20,"BOTTOM_LEFT", "Options", "cmcgui119:textures/button.png", "OPEN_OPTIONS");
            JsonButton mods = new JsonButton(20, -70,100,20,"BOTTOM_LEFT", "Mods", "cmcgui119:textures/button.png", "OPEN_MODS");
            JsonButton quit = new JsonButton(20, -40,100,20,"BOTTOM_LEFT", "Quit", "cmcgui119:textures/button.png", "QUIT");
            JsonButton reload = new JsonButton(-30, 10,20,20,"TOP_RIGHT", "", "cmcgui119:textures/button_reload.png", "RELOAD_MOD_CONFIG");
            JsonImage logo = new JsonImage("image", 10,-17,200,100,"TOP_LEFT","cmcgui119:textures/logo.png");
            File f1 = new File(screensPath + "/MainMenuScreen.json");
            new File(screensPath+"/MainMenuScreen").mkdirs();
            File f2 = new File(screensPath+"/MainMenuScreen/singleplayer.json");
            File f3 = new File(screensPath+"/MainMenuScreen/multiplayer.json");
            File f4 = new File(screensPath+"/MainMenuScreen/options.json");
            File f5 = new File(screensPath+"/MainMenuScreen/mods.json");
            File f6 = new File(screensPath+"/MainMenuScreen/quit.json");
            File f7 = new File(screensPath+"/MainMenuScreen/reload.json");
            File f8 = new File(screensPath+"/MainMenuScreen/logo.json");
            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
            f5.createNewFile();
            f6.createNewFile();
            f7.createNewFile();
            f8.createNewFile();
            FileHelper.writeStringInFile(f1, JsonHelper.gson.toJson(mainMenuScreen));
            FileHelper.writeStringInFile(f2, JsonHelper.gson.toJson(singleplayer));
            FileHelper.writeStringInFile(f3, JsonHelper.gson.toJson(multiplayer));
            FileHelper.writeStringInFile(f4, JsonHelper.gson.toJson(settings));
            FileHelper.writeStringInFile(f5, JsonHelper.gson.toJson(mods));
            FileHelper.writeStringInFile(f6, JsonHelper.gson.toJson(quit));
            FileHelper.writeStringInFile(f7, JsonHelper.gson.toJson(reload));
            FileHelper.writeStringInFile(f8, JsonHelper.gson.toJson(logo));
        }catch(Exception e){
            DebugHelper.debug("Не удалось создать файлы для стандартного скрина");
        }
    }
}
