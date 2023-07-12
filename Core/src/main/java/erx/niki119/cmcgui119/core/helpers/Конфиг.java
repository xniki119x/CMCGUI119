package erx.niki119.cmcgui119.core.helpers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import erx.niki119.cmcgui119.core.Ядро;
import erx.niki119.cmcgui119.core.utils.References;
import erx.niki119.cmcgui119.core.utils.json.JsonConfig;
import erx.niki119.cmcgui119.core.utils.json.components.JsonButton;
import erx.niki119.cmcgui119.core.utils.json.components.JsonImage;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;

import java.io.*;

public class Конфиг {
    private Ядро ядро;
    public JsonConfig config;
    private final File config_file;
    private final Gson gson;
    public boolean isReloadable = true;

    public Конфиг(Ядро ядро){
        this.ядро = ядро;
        config_file = new File(String.format("%s/%s/%s.json", ядро.папка_игры, References.MOD_ID, References.MOD_ID));
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
        File f = new File(config_file.getParentFile().getPath()+"/меню"); // насрал
        if(!f.exists()) {
            f.mkdirs();
            if(config.главное_меню){
                createDefaultScreen();
            }
        }
    }
    private void createDefaultConfig(){
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
        создать_стандартное_главное_меню();
        создать_стандартное_меню_паузы();
    }
    public void создать_стандартное_меню_паузы(){
        String путь_к_меню = config_file.getParentFile().getPath() + "/меню";
        try {
            new File(путь_к_меню+"/меню_паузы").mkdirs();
        File f1 = new File(путь_к_меню + "/меню_паузы.json");
        JsonScreen меню_паузы = new JsonScreen("screen", "меню_паузы");
        JsonButton вернуться_в_игру = new JsonButton(-102, 8,204,20,"DEFAULT", "Вернуться в игру", "cmcgui119:textures/button2.png", "OPEN_GUI", "NULL");
        JsonButton достижения = new JsonButton(-102, 32,98,20,"DEFAULT", "Достижения", "cmcgui119:textures/button2.png", "OPEN_GUI", "ADVANCEMENTS");
        JsonButton статистика = new JsonButton(4, 32,98,20,"DEFAULT", "Статистика", "cmcgui119:textures/button2.png",
                "OPEN_GUI", "STATS");
        JsonButton настройки = new JsonButton(-102, 80,98,20,"DEFAULT", "Настройки", "cmcgui119:textures/button2.png"
                , "OPEN_GUI", "OPTIONS");
        JsonButton выход = new JsonButton(-102, 104,204,20,"DEFAULT", "Выход", "cmcgui119:textures/button2.png", "DISCONNECT");
        JsonButton перезагрузка = new JsonButton(-30, 10,20,20,"TOP_RIGHT", "", "cmcgui119:textures/button_reload.png", "RELOAD_MOD_CONFIG");
        File f2 = new File(путь_к_меню+"/меню_паузы/вернуться_в_игру.json");
        File f3 = new File(путь_к_меню+"/меню_паузы/достижения.json");
        File f4 = new File(путь_к_меню+"/меню_паузы/статистика.json");
        File f5 = new File(путь_к_меню+"/меню_паузы/настройки.json");
        File f6 = new File(путь_к_меню+"/меню_паузы/выход.json");
        File f7 = new File(путь_к_меню+"/меню_паузы/перезагрузка.json");
        f1.createNewFile();
        f2.createNewFile();
        f3.createNewFile();
        f4.createNewFile();
        f5.createNewFile();
        f6.createNewFile();
        f7.createNewFile();
            Файл.writeStringInFile(f1, gson.toJson(меню_паузы));
            Файл.writeStringInFile(f2, gson.toJson(вернуться_в_игру));
            Файл.writeStringInFile(f3, gson.toJson(достижения));
            Файл.writeStringInFile(f4, gson.toJson(статистика));
            Файл.writeStringInFile(f5, gson.toJson(настройки));
            Файл.writeStringInFile(f6, gson.toJson(выход));
            Файл.writeStringInFile(f7, gson.toJson(перезагрузка));
        }catch(Exception e){
            ядро.ЛОГГЕР.debug("Не удалось создать файлы для стандартного меню паузы");
        }
//        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, new TranslationTextComponent("gui.advancements"), (p_213065_1_) -> {
//            this.minecraft.setScreen(new AdvancementsScreen(this.minecraft.player.connection.getAdvancements()));
//        }));
//        this.addButton(new Button(this.width / 2 + 4, this.height / 4 + 48 + -16, 98, 20, new TranslationTextComponent("gui.stats"), (p_213066_1_) -> {
//            this.minecraft.setScreen(new StatsScreen(this, this.minecraft.player.getStats()));
//        }));
//        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 96 + -16, 98, 20, new TranslationTextComponent("menu.options"), (p_213071_1_) -> {
//            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
//        }));
//        button.active = this.minecraft.hasSingleplayerServer() && !this.minecraft.getSingleplayerServer().isPublished();
//        Button button1 = this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, new TranslationTextComponent("menu.returnToMenu"), (p_213067_1_) -> {
//            boolean flag = this.minecraft.isLocalServer();
//            boolean flag1 = this.minecraft.isConnectedToRealms();
//            p_213067_1_.active = false;
//            this.minecraft.level.disconnect();
//            if (flag) {
//                this.minecraft.clearLevel(new DirtMessageScreen(new TranslationTextComponent("menu.savingLevel")));
//            } else {
//                this.minecraft.clearLevel();
//            }
//
//            if (flag) {
//                this.minecraft.setScreen(new MainMenuScreen());
//            } else if (flag1) {
//                RealmsBridgeScreen realmsbridgescreen = new RealmsBridgeScreen();
//                realmsbridgescreen.switchToRealms(new MainMenuScreen());
//            } else {
//                this.minecraft.setScreen(new MultiplayerScreen(new MainMenuScreen()));
//            }
//
//        }));
//        if (!this.minecraft.isLocalServer()) {
//            button1.setMessage(new TranslationTextComponent("menu.disconnect"));
//        }
    }

    public void создать_стандартное_главное_меню(){
        String путь_к_меню = config_file.getParentFile().getPath() + "/меню";
        try {
            JsonScreen главное_меню = new JsonScreen("screen", "главное_меню", "cmcgui119:textures/background.png");
            JsonButton одиночная_игра = new JsonButton(20, -160,100,20,"BOTTOM_LEFT", "Одиночная игра", "cmcgui119:textures/button2.png", "OPEN_GUI", "SINGLEPLAYER");
            JsonButton сетевая_игра = new JsonButton(20, -130,100,20,"BOTTOM_LEFT", "Сетевая игра", "cmcgui119:textures/button2.png", "OPEN_GUI","MULTIPLAYER");
            JsonButton настройки = new JsonButton(20, -100,100,20,"BOTTOM_LEFT", "Настройки", "cmcgui119:textures/button2.png", "OPEN_GUI","OPTIONS");
            JsonButton моды = new JsonButton(20, -70,100,20,"BOTTOM_LEFT", "Моды", "cmcgui119:textures/button2.png", "OPEN_GUI","MODS");
            JsonButton выход = new JsonButton(20, -40,100,20,"BOTTOM_LEFT", "Выход", "cmcgui119:textures/button2.png", "QUIT");
            JsonButton перезагрузка = new JsonButton(-30, 10,20,20,"TOP_RIGHT", "", "cmcgui119:textures/button_reload.png", "RELOAD_MOD_CONFIG");
            JsonImage лого = new JsonImage("image", 10,-17,200,100,"TOP_LEFT","cmcgui119:textures/logo2.png");
            File f1 = new File(путь_к_меню + "/главное_меню.json");
            new File(путь_к_меню+"/главное_меню").mkdirs();
            File f2 = new File(путь_к_меню+"/главное_меню/одиночная_игра.json");
            File f3 = new File(путь_к_меню+"/главное_меню/сетевая_игра.json");
            File f4 = new File(путь_к_меню+"/главное_меню/настройки.json");
            File f5 = new File(путь_к_меню+"/главное_меню/моды.json");
            File f6 = new File(путь_к_меню+"/главное_меню/выход.json");
            File f7 = new File(путь_к_меню+"/главное_меню/перезагрузка.json");
            File f8 = new File(путь_к_меню+"/главное_меню/лого.json");
            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
            f5.createNewFile();
            f6.createNewFile();
            f7.createNewFile();
            f8.createNewFile();
            Файл.writeStringInFile(f1, gson.toJson(главное_меню));
            Файл.writeStringInFile(f2, gson.toJson(одиночная_игра));
            Файл.writeStringInFile(f3, gson.toJson(сетевая_игра));
            Файл.writeStringInFile(f4, gson.toJson(настройки));
            Файл.writeStringInFile(f5, gson.toJson(моды));
            Файл.writeStringInFile(f6, gson.toJson(выход));
            Файл.writeStringInFile(f7, gson.toJson(перезагрузка));
            Файл.writeStringInFile(f8, gson.toJson(лого));
        }catch(Exception e){
            ядро.ЛОГГЕР.debug("Не удалось создать файлы для стандартного меню");
        }
    }
}
