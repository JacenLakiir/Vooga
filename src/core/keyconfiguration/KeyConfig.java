package core.keyconfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.golden.gamedev.GameObject;

/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyConfig {
    private KeyConfigModel keyModel;
    private GameObject myGame;
    private boolean isSystemOnly = false;
    public KeyConfig(GameObject game, boolean isSystemKeyOnly) {
        myGame = game;
        keyModel = parseKeyConfig("configurations/KeyConfig.json");
        isSystemOnly = isSystemKeyOnly;
    }

    

    private List<Key> constructInputKeyList(HashMap<String, String> keyMap){
        List<Key> keys = new ArrayList<Key>();
        for(String action : keyMap.keySet()){
            int length = keyMap.get(action).split(",").length;
            if(length == 1)
                keys.add(new InputKeyDown(keyMap.get(action), action, myGame));
            if(length > 1)
                keys.add(new InputKeySequential(keyMap.get(action), action, myGame));                
        }
        return keys;
    }
    
    private List<Key> constructSystemKeyList(HashMap<String, String> keyMap){
        List<Key> keys = new ArrayList<Key>();
        for(String action : keyMap.keySet()){
            keys.add(new InputKeyPress(keyMap.get(action), action, myGame));
        }
        return keys;
    }
    
    public List<Key> getKeyList(){
        List<Key> keys = new ArrayList<Key> ();
        if(!isSystemOnly)
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
