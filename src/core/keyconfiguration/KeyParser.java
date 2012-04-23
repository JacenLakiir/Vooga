package core.keyconfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.golden.gamedev.GameObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demo.custom.DemoKeyAdapter;

/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyParser {
    private GameObject myGame;
    private boolean isSystemOnly = false;
    private KeyAdapter adapter;
    private List<Key> keyList;
    public KeyParser(GameObject game, boolean isSystemKeyOnly, KeyAdapter adapter) {
        myGame = game;
        this.adapter = adapter;
        isSystemOnly = isSystemKeyOnly;
    }
    
    public KeyParser(){
        this.adapter = new DemoKeyAdapter("key_type");
    }

    public List<Key> parseKeyConfig(){
        Scanner scanner;
       
            try {
                scanner = new Scanner(new File("configurations/test.json"));
                String wholeFile = scanner.useDelimiter("\\A").next();
                
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                gsonBuilder.registerTypeAdapter(Key.class, adapter);
                Gson gson = gsonBuilder.create();
                Key[] keys = gson.fromJson(wholeFile, Key[].class);
                keyList = Arrays.asList(keys);
                for(Key key : keyList){
                    key.initial(myGame, isSystemOnly);
                }
                
                return keyList;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return new ArrayList<Key>();
        }
    
    public void outputJsonFile(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Key.class, adapter);
        Gson gson = gsonBuilder.create();   
        try {
            FileWriter out = new FileWriter(fileName);
            Key[] keys = keyList.toArray(new Key[0]);
            String str = gson.toJson(keys, Key[].class);
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setKey(String keyValue, String action, int index){
        keyList.get(index).setKeyValue(keyValue, action);
    }
    
    public static void main(String[] args){
        KeyParser parser = new KeyParser();
        List<Key> list = parser.parseKeyConfig();
        parser.setKey("39", "right", 1);
        parser.outputJsonFile("configurations/test1.json");
    }
}  
