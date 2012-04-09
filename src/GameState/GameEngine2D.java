package GameState;


import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class GameEngine2D extends GameEngine{
    private static Map<Integer, GameObject> map = new HashMap<Integer, GameObject>();
    public static final int GAME = 2;
    public static final int PAUSE = 3;
    public static final int MENU = 1;
    public static final int EXIT = 4;
    public void initResources(){
        map.put(MENU, new Menu(this));
        map.put(GAME, new SimpleGameToTestLevelEditor(this));
        map.put(PAUSE, new Pause(this));
        nextGameID = MENU;
    }
    
    public void setGameID(int id){
        nextGameID = id;
    }

    
    @Override
    public GameObject getGame(int gameID) {
        return map.get(nextGameID);
    }
    
    public static void main(String[] args){
        GameLoader game = new GameLoader();
        game.setup(new GameEngine2D(), new Dimension(640,480), false); 
        game.start();
    }
    
}