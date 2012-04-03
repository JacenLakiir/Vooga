package keyconfiguration;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import charactersprites.Player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * @author Hui Dong
 *
 */
public class KeyConfig {
    HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
    List<Key> keyList = new ArrayList<Key>();
    Player player;
    public KeyConfig(Player player){
        this.player = player;
    }
    public void initialization(){
        
    }
    
    public HashMap<String, Integer> getKeyMap(){
        return keyMap;
    }
    
    public List<Key> getKeyList(){
        return keyList;
    }
    
    public void parseKeyConfig(String fileName){
         Gson gson = new Gson();
         Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            String wholeFile = scanner.useDelimiter("\\A").next();
            Type collectionType = new TypeToken<HashMap<String,Integer>>(){}.getType();
            keyMap = gson.fromJson(wholeFile, collectionType);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         for(String action : keyMap.keySet()){
             keyList.add(new Key(keyMap.get(action), action, player));
         }
    }
        
    
    public void setCustomKey(String fileName, Integer customKey, String action) throws FileNotFoundException {
        keyMap.put(action, customKey);
    }
    
    public void outputJsonFile(String fileName) throws IOException{
        Gson gson = new Gson();
        FileWriter out = new FileWriter(fileName);
        BufferedWriter bufferedOut = new BufferedWriter(out);
        bufferedOut.write(gson.toJson(keyMap));
        bufferedOut.close();
    }
}  
