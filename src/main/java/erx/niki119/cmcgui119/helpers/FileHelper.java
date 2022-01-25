package erx.niki119.cmcgui119.helpers;

import java.io.*;

public class FileHelper {
    public static String readFileString(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String s;
        StringBuilder ss = new StringBuilder();
        while((s = br.readLine()) != null){
            ss.append(s);
        }
        return ss.toString();
    }
    public static String getExtension(File file) {
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if(i > 0) {
            extension = file.getName().substring(i + 1);
        }
        return extension;
    }
    public static void writeStringInFile(File f, String s) throws IOException{
        FileWriter fw = new FileWriter(f);
        fw.write(s);
        fw.flush();
        fw.close();
    }
}
