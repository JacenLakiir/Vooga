package core.keyconfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
/**
 * 
 * @author Hui Dong
 *
 */
public class KeyConfigModel {
    private HashMap<String, String> inputKeyMap = new HashMap<String, String>();
    private HashMap<String, String> systemKeyMap = new HashMap<String, String>();
    private KeyConfigModel keyModel;
    
    public HashMap<String, String> getInputKeyMap() {
        return inputKeyMap;
    }
    public HashMap<String, String> getSystemKeyMap() {
        return systemKeyMap;
    }
    public static KeyConfigModel parseKeyConfig(String fileName) {
        Gson gson = new Gson();
        Scanner scanner;
        KeyConfigModel keyModel = null;
        try {
            scanner = new Scanner(new File(fileName));
            String wholeFile = scanner.useDelimiter("\\A").next();
            keyModel = gson.fromJson(wholeFile, KeyConfigModel.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return keyModel;
    }
    public void outputJsonFile(String fileName) {

        Gson gson = new Gson();
        try {
            FileWriter out = new FileWriter(fileName);
            BufferedWriter bufferedOut = new BufferedWriter(out);
            String json = gson.toJson(this);
            out = new FileWriter(fileName);
            bufferedOut.write(json);
            bufferedOut.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void customizeKey(String action, String keyValue){
        inputKeyMap.put(action, keyValue);
    }
    
    public static void main(String[] args){
        KeyConfigModel keys = new KeyConfigModel();
        keys.customizeKey("left", "1");
        keys.outputJsonFile("configurations/test.json");
    }

}
