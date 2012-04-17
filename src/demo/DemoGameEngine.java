package demo;


import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import core.gamestate.GameEngine2D;
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
public class DemoGameEngine extends GameEngine2D{
    private boolean initial = true;
    private int previousGameID;

    public void constructGameList(){
        addGameObject(new Menu(this));
        addGameObject(new DemoAI(this));
        addGameObject(new DemoPlayfield(this));
        addGameObject(new DemoHUD(this));
        addGameObject(new Pause(this));
    }
    
    public void initResources(){
        super.initResources();
        initializeGameList();
        if(initial){
            nextGameID = idMap.get(Menu.class.getName());
            initial = false;
        }
    }
    
    public void continueGame(){
        nextGameID = previousGameID;
    }
    
    public void setPreviousGameID(int id){
        previousGameID = id;
    }
    
    
    public static void main(String[] args){
        GameLoader game = new GameLoader();
        game.setup(new DemoGameEngine(), new Dimension(640,480), false); 
        game.start();
    }  
}