package GameState;


import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class GameEngine2D extends GameEngine{
    State current;
    
    public GameEngine2D(){
        current = new MenuState(this);
    }
    
    public void setState(State s){
        current = s;
    }
    
    public void nextState(int gameID){
        nextGameID = gameID;
        current.nextState(this, gameID);
    }
    
    @Override
    public GameObject getGame(int arg0) {
        return current.getGameObject();
    }
    
    public static void main(String[] args){
        GameLoader game = new GameLoader();
        game.setup(new GameEngine2D(), new Dimension(640,480), false); 
        game.start();
    }
    
}