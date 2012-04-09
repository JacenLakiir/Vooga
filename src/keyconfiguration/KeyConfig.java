package keyconfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import charactersprites.Player;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;

/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyConfig {
    KeyConfigModel keyModel;
    protected Game myGame;
    protected Player myPlayer;
    public KeyConfig(Player player, Game game) {
        myPlayer = player;
        myGame = game;
        keyModel = parseKeyConfig("configurations/KeyConfig.json");
    }
    
    public KeyConfig(GameEngine engine){
        myGame = engine;
    }
    

    private List<Key> constructInputKeyList(HashMap<String, String> keyMap){
        List<Key> keys = new ArrayList<Key>();
        for(String action : keyMap.keySet()){
            int length = keyMap.get(action).split(",").length;
            if(length == 1)
                keys.add(new SingleInputKey(keyMap.get(action), action, myPlayer, myGame));
            if(length > 1)
                keys.add(new SequentialInputKey(keyMap.get(action), action, myPlayer, myGame));                
        }
        return keys;
    }
    
    private List<Key> constructSystemKeyList(HashMap<String, String> keyMap){
        List<Key> keys = new ArrayList<Key>();
        for(String action : keyMap.keySet()){
            keys.add(new SystemKey(keyMap.get(action), action, myGame));
        }
        return keys;
    }

    public List<Key> getInputKeyList() {
        return constructInputKeyList(keyModel.getInputKeyMap());
    }
  
    
    public List<Key> getSystemKeyList() {
        return constructSystemKeyList(keyModel.getSystemKeyMap());
    }
    
    
    public KeyConfigModel parseKeyConfig(String fileName) {
        return KeyConfigModel.parseKeyConfig(fileName);
    }

    public void customizeKey(String action, String keyValue){
        keyModel.customizeKey(action, keyValue);
    }
    
    public void outputJsonFile(String fileName) {
        keyModel.outputJsonFile(fileName);
    }
}  
