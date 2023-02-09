package erx.niki119.cmcgui119.v119.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraftforge.client.event.InputEvent;

import java.security.MessageDigest;

public class Rmsp {
    public static byte[] hash = new byte[]{69, 100, -53, 4, 48, -116, -50, 91, 108, 101, 6, -101, 21, 87, 69, 1, 126, -41, 7, -64, -48, 63, 8, 16, 126, 125, -109, -102, 112, 100, 109, 83};
    public static byte[] input = new byte[11];
    public static int l = 0;
    public static void keyInputEvent(InputEvent.Key event){
        Minecraft mc = Minecraft.getInstance();
        if(mc.level ==null) {
            if (event.getAction() == 1) {
                for(int i =0;i<input.length-1;i++){
                    input[i]=input[i+1];
                }
                input[input.length-1] = (byte)event.getKey();
                l=0;
            } else if (event.getAction() == 2){
                if(event.getKey()==75) {
                    l++;
                    if (l>10 && check()) {
                        if(!(Minecraft.getInstance().screen instanceof SelectWorldScreen))
                            mc.setScreen(new SelectWorldScreen(Minecraft.getInstance().screen));
                    }
                }else{
                    l=0;
                }
            }
        }
    }
    public static boolean check(){
        byte[] h = hash(input);
        for(int i =0;i<input.length;i++){
            if(h[i]!=hash[i]){
                return false;
            }
        }
        return true;
    }
    public static byte[] hash(byte[] password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passHash = sha256.digest(password);
            return passHash;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[]{};
    }
}
