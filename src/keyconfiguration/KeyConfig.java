package keyconfiguration;
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

import com.golden.gamedev.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * @author Hui Dong
 *
 */
public class KeyConfig {
    private HashMap<String, String> keyMap = new HashMap<String, String>();
    private List<Key> keyList = new ArrayList<Key>();
    private Player player;
    private Game myGame;
    public KeyConfig(Player player, Game game){
        myGame = game;
        this.player = player;
    }
    public void initialization(){
        
    }
    

    
    public HashMap<String, String> getKeyMap(){
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
            Type collectionType = new TypeToken<HashMap<String,String>>(){}.getType();
            keyMap = gson.fromJson(wholeFile, collectionType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         for(String action : keyMap.keySet()){
             keyList.add(new Key(keyMap.get(action), action, player, myGame));
         }
    }
        
    
    public void setCustomKey(String fileName, String customKey, String action) throws FileNotFoundException {
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
