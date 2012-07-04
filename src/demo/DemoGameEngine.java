package demo;


import io.SimpleGameToTestLevelEditor;
import java.awt.Dimension;
import com.golden.gamedev.GameLoader;
import core.gamestate.GameEngine2D;


/**
 * Run the game engine and then select DemoAI or DemoPlayField to run the demo game
 * @author Hui Dong
 *
 */
public class DemoGameEngine extends GameEngine2D{
    public void initResources(){
        super.initResources();
        initializeGameList();
        if(isInitial()){
            setInitialGameObject(Menu.class);
            setInitial(false);
        }
    }
    
    public static void main(String[] args){
        GameLoader game = new GameLoader();
        game.setup(new DemoGameEngine(), new Dimension(640,480), false); 
        game.start();
    }

    @Override
    public void registerGameObjects() {
        addGameObject(new Menu(this));
        addGameObject(new DemoAI(this));
        addGameObject(new DemoPlayfield(this));
        addGameObject(new DemoHUD(this));
        addGameObject(new SimpleGameToTestLevelEditor(this));
        addGameObject(new DemoAntigravity(this));
        addGameObject(new Pause(this));        
    }  
}