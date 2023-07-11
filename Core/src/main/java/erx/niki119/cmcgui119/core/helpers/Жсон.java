package erx.niki119.cmcgui119.core.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import erx.niki119.cmcgui119.core.Ядро;
import erx.niki119.cmcgui119.core.utils.References;
import erx.niki119.cmcgui119.core.utils.json.components.*;
import erx.niki119.cmcgui119.core.utils.json.screens.JsonScreen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Жсон {
    private Ядро ядро;
    private String screensDir;
    public Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Жсон(Ядро ядро) {
        this.ядро = ядро;
        screensDir = String.format("%s/%s/меню", ядро.папка_игры,References.MOD_ID);
    }

    public List<JsonScreen> loadScreens(){
        List<JsonScreen> list = new ArrayList<>();
        try {
            File screens = new File(screensDir);
            for(File f : screens.listFiles()){
                if(f.isFile() && Файл.getExtension(f).equals("json")) {
                    ядро.ЛОГГЕР.debug("Parse json file with name  "+ f.getName());
                    list.add(gson.fromJson(Файл.readFileString(f), JsonScreen.class));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<JsonComponent> readComponentsForScreen(JsonScreen json){
        List<JsonComponent> screenComponents = new ArrayList<>();
        try {
            File screenComponentsFolder = new File(String.format("%s/%s",screensDir,json.name));
            if(screenComponentsFolder.exists()){
                List<File> dirs = new ArrayList<>();
                List<File> jsonFiles = new ArrayList<>();
                sort(screenComponentsFolder, dirs, jsonFiles);
                if(!jsonFiles.isEmpty()){
                    filesToJsons(jsonFiles,screenComponents);
                }
                //screenComponents = jsonsToCComponents(jsonBases);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return screenComponents;
    }

    public static void sort(File screens, List<File> dirs, List<File> jsons) {
        try {
            for(File f : screens.listFiles()) {
                if(f.isFile() && Файл.getExtension(f).equals("json")) {
                    jsons.add(f);
                } else {
                    if(f.isDirectory()) {
                        sort(f, dirs, jsons);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void filesToJsons(List<File> jsonFiles, List<JsonComponent> jsonBases){
        try {
            for(File f : jsonFiles) {
                String id = gson.fromJson(Файл.readFileString(f), JsonBase.class).id;
                switch(id){
                    case "button":jsonBases.add(gson.fromJson(Файл.readFileString(f), JsonButton.class));break;
                    case "image":jsonBases.add(gson.fromJson(Файл.readFileString(f), JsonImage.class));break;
                    case "text":jsonBases.add(gson.fromJson(Файл.readFileString(f), JsonText.class));break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    public List<CScreen> screens;
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void loadComponents(){
        DebugHelper.debug("Load screens");
        screens = jsonsToCScreens(JsonHelper.readAllJsonScreens());
    }
    public CScreen getScreen(String name){
        for(CScreen screen : screens){
            if(screen.name.equals(name)){
                return screen;
            }
        }
        DebugHelper.debug("Not exists screen with name "+name);
        return null;
    }


    public static List<JsonBase> readAllJsonComponents() {
        List<JsonBase> list = new ArrayList<>();
        try {
            List<File> dirs = new ArrayList<>();
            List<File> jsons = new ArrayList<>();
            File screens = new File(FMLPaths.GAMEDIR.get().toFile().getPath() + "/" + References.MOD_ID + "/screens");
            //System.out.println(screens.getPath());
            if(screens.exists()) {
                sort(screens, dirs, jsons);
            }
            if(!jsons.isEmpty()){
                filesToJsons(jsons,list);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<JsonScreen> readAllJsonScreens(){
        List<JsonScreen> list = new ArrayList<>();
        try {
            File screens = new File(FMLPaths.GAMEDIR.get().toFile().getPath() + "/" + References.MOD_ID + "/screens");
            for(File f : screens.listFiles()){
                if(f.isFile() && FileHelper.getExtension(f).equals("json")) {
                    DebugHelper.debug("Parse json file with name  "+ f.getName());
                    list.add(gson.fromJson(FileHelper.readFileString(f), JsonScreen.class));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<CComponent> jsonsToCComponents(List<JsonBase> jsons){
        List<CComponent> components = new ArrayList<>();
        for(JsonBase jb : jsons){
            if(jb instanceof JsonButton){
                components.add(new CButton(((JsonButton) jb)));
            }else if(jb instanceof JsonImage){
                components.add(new CImage(((JsonImage) jb)));
            }else if(jb instanceof JsonText){
                components.add(new CText(((JsonText) jb)));
            }
        }
        return components;
    }
    public static List<CScreen> jsonsToCScreens(List<JsonScreen> jsons){
        List<CScreen> screens = new ArrayList<>();
        for(JsonScreen j : jsons){
                DebugHelper.debug("Detected screen with name "+ j.name);
                screens.add(new CScreen(j,readComponentsForScreen(j)));
        }
        return screens;
    }


    public static int getColorFromString(String colorString){
        int R = 0;
        int G = 0;
        int B = 0;
        int color = 0;
        try {
            if(colorString.length() == 6) {
                R = getIntFromColorString(colorString.substring(0, 2));
                G = getIntFromColorString(colorString.substring(2,4));
                B = getIntFromColorString(colorString.substring(4,6));
                color = R * 16*16*16*16+G*16*16+B;
            } else if(colorString.length() == 3) {
                R = getIntFromColorString(colorString.substring(0, 1));
                G = getIntFromColorString(colorString.substring(1,2));
                B = getIntFromColorString(colorString.substring(2,3));
                color =R * 16*16*16*16+G*16*16+B;
            }
        }catch(Exception e){
            CMCGUI119.LOGGER.error("ERROR PARSE COLOR");
        }
        return color;
    }
    public static int getIntFromColorString(String color){
        int i = 0;
        if(color.length()==2){
            i = get10From16(color.substring(0,1))*16+get10From16(color.substring(1,2));
        }else if(color.length()==1) {
            i = get10From16(color)*17;
        }
        return i;
    }
    public static int get10From16(String s16){
        int i10 = 0;
        s16 = s16.toLowerCase();
        switch(s16){
            case "1":i10=1;break;
            case "2":i10=2;break;
            case "3":i10=3;break;
            case "4":i10=4;break;
            case "5":i10=5;break;
            case "6":i10=6;break;
            case "7":i10=7;break;
            case "8":i10=8;break;
            case "9":i10=9;break;
            case "a":i10=10;break;
            case "b":i10=11;break;
            case "c":i10=12;break;
            case "d":i10=13;break;
            case "e":i10=14;break;
            case "f":i10=15;break;
        }
        return i10;
    }


     */
}
