package core.keyconfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.golden.gamedev.GameObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyParser {
    private GameObject myGame;
    private boolean isSystemOnly = false;
    private KeyAdapter adapter;
    public KeyParser(GameObject game, boolean isSystemKeyOnly, KeyAdapter adapter) {
        myGame = game;
        this.adapter = adapter;
        isSystemOnly = isSystemKeyOnly;
    }

    public List<Key> constructKeyList(){
        Scanner scanner;
       
            try {
                scanner = new Scanner(new File("configurations/test.json"));
                String wholeFile = scanner.useDelimiter("\\A").next();
                
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                gsonBuilder.registerTypeAdapter(Key.class, adapter);
                Gson gson = gsonBuilder.create();
                Key[] keys = gson.fromJson(wholeFile, Key[].class);
                List<Key> keyList = Arrays.asList(keys);
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
    }
}  
