package GameState;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class MenuState extends State{
    public MenuState(GameEngine2D engine) {
        super(engine);
    }

    @Override
    public void nextState(GameEngine2D manage, int nextGameID) {
        engine = manage;
        if(nextGameID == 0)
            manage.setState(new PlayingState(engine));

    }

    @Override
    public GameObject getGameObject() {
        return new Menu(engine);
    }
    
    

}
