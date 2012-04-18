package core.gamestate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public abstract class GameEngine2D extends GameEngine{

    private List<GameObject> list = new ArrayList<GameObject>();
    protected void addGameObject(GameObject object){
        list.add(object);
    }
    public abstract void constructGameList();
    
    protected HashMap<String, GameObject> map = new HashMap<String, GameObject>();  
    protected HashMap<String, Integer> idMap = new HashMap<String, Integer>();
    @Override
    public void initResources() {
        super.initResources();
        constructGameList();
        int id = 0;
        for(GameObject object : list){
            String name = object.getClass().getName();
            map.put(name, object);
            idMap.put(name, id);
            id++;
        }
    }
    
    public void initializeGameList(){
        list = new  ArrayList<GameObject>();
    }

    public Integer getGameID(String className){
        return idMap.get(className);
    }

    public List<GameObject> getGameObjects(){
        return list;
    }

    @Override
    public GameObject getGame(int arg0) {
        String className = "";
        for(String name : idMap.keySet()){
            if(nextGameID == idMap.get(name))
                className = name;
        }
        return  map.get(className);
    }
    
    
}
