package core.gamestate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public abstract class GameEngine2D extends GameEngine {
    private boolean isInitial = true;

    protected boolean isInitial() {
        return isInitial;
    }

    protected void setInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }

    private List<GameObject> list = new ArrayList<GameObject>();

    protected void addGameObject(GameObject object) {
        list.add(object);
    }

    public abstract void registerGameObjects();

    private HashMap<String, GameObject> map = new HashMap<String, GameObject>();
    private HashMap<String, Integer> idMap = new HashMap<String, Integer>();

    protected HashMap<String, Integer> getIdMap() {
        return idMap;
    }

    private int previousGameID;

    protected int getPreviousGameID() {
        return previousGameID;
    }

    @Override
    public void initResources() {
        super.initResources();
        registerGameObjects();
        int id = 0;
        for (GameObject object : list) {
            String name = object.getClass().getName();
            map.put(name, object);
            idMap.put(name, id);
            id++;
        }
    }

    public void initializeGameList() {
        list = new ArrayList<GameObject>();
    }

    public Integer getGameID(String className) {
        return idMap.get(className);
    }

    protected void setInitialGameObject(Class<? extends GameObject2D> gameClass) {
        nextGameID = idMap.get(gameClass.getName());

    }

    @Override
    public GameObject getGame(int gameID) {
        String className = "";
        for (String name : idMap.keySet()) {
            if (nextGameID == idMap.get(name))
                className = name;
        }
        return map.get(className);
    }

    public void storeCurrentGameID(Class<? extends GameObject2D> mclass) {
        previousGameID = idMap.get(mclass.getName());
    }

}
