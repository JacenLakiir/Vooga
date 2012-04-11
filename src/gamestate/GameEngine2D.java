package gamestate;


import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

import demo.DemoGame1;
import demo.DemoGame2;
import demo.DemoGame3;
import demo.DemoGame4;

public class GameEngine2D extends GameEngine{
    private static Map<Integer, GameObject> map = new HashMap<Integer, GameObject>();
    public static final int GAME = 2;
    public static final int PAUSE = 3;
    public static final int MENU = 1;
    public static final int EXIT = 4;
    public static final int GAME2 = 5;
    public static final int GAME3 = 6;
    public static final int GAME4 = 7;
    private int previousGameID;
    public GameEngine2D(){
        nextGameID = MENU;
    }
    
    public void initResources(){
        map.put(MENU, new Menu(this));
        map.put(GAME, new DemoGame1(this));
        map.put(GAME2, new DemoGame2(this));
        map.put(GAME3, new DemoGame3(this));
        map.put(GAME4, new DemoGame4(this));
        map.put(PAUSE, new Pause(this));
    }
    
    public void continueGame(){
        nextGameID = previousGameID;
    }
    
    public void setPreivousGameID(int id){
        previousGameID = id;
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