package core.keyconfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.characters.GameElement;
import com.golden.gamedev.GameObject;

/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyConfig {
    private KeyConfigModel keyModel;
    protected GameObject myGame;
    protected GameElement element;
    public KeyConfig(GameElement player, GameObject game) {
        element = player;
        myGame = game;
        keyModel = parseKeyConfig("configurations/KeyConfig.json");
    }

    

    private List<Key> constructInputKeyList(HashMap<String, String> keyMap){
        List<Key> keys = new ArrayList<Key>();
        for(String action : keyMap.keySet()){
            int length = keyMap.get(action).split(",").length;
            if(length == 1)
                keys.add(new SingleInputKey(keyMap.get(action), action, element, myGame));
            if(length > 1)
                keys.add(new SequentialInputKey(keyMap.get(action), action, element, myGame));                
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
    
    public List<Key> getKeyList(){
        List<Key> keys = new ArrayList<Key> ();
        if(element !=null)
            keys.addAll(constructInputKeyList(keyModel.getInputKeyMap()));
        keys.addAll(constructSystemKeyList(keyModel.getSystemKeyMap()));
        return keys;
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
