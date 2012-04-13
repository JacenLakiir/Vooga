package demo;


import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import core.gamestate.Menu;
import core.gamestate.Pause;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;


/**
 * Run the game engine and then select DemoAI or DemoPlayField to run the demo game
 * @author Hui Dong
 *
 */
public class GameEngine2D extends GameEngine{
    private static Map<Integer, GameObject> map = new HashMap<Integer, GameObject>();
    public static final int GAMEAI = 2;
    public static final int PAUSE = 3;
    public static final int MENU = 1;
    public static final int EXIT = 4;
    public static final int GAME_PLAYFIELD = 5;
    public static final int GAME_HUD = 6;

    private int previousGameID;
    public GameEngine2D(){
        nextGameID = MENU;
    }
    
    public void initResources(){
        map.put(MENU, new Menu(this));
        map.put(GAMEAI, new DemoAI(this));
        map.put(GAME_PLAYFIELD, new DemoPlayfield(this));
        map.put(GAME_HUD, new DemoHUD(this));
        map.put(PAUSE, new Pause(this));
    }
    
    public void continueGame(){
        nextGameID = previousGameID;
    }
    
    public void setPreviousGameID(int id){
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