package keyConfiguration;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyConfig {
    HashMap<Integer, String> map = new HashMap<Integer, String>();
    
    public void initialization(){
        map.put(KeyEvent.VK_LEFT,"left");
        map.put(KeyEvent.VK_RIGHT, "right");
        map.put(KeyEvent.VK_UP, "jump");
    }
    
    public HashMap<Integer, String> getKeyMap(){
        return map;
    }
    
    public void parseKeyConfig(String fileName){
         
    }
        
    
    public void setCustomKey(String fileName, Key customKey, String action){
        
    }
}  
