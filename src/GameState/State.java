package GameState;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public abstract class State  {
    protected GameEngine2D engine;
    public State(GameEngine2D engine){
        this.engine = engine;
    }

    public abstract void nextState(GameEngine2D manage, int nextGameID);
    
    public abstract GameObject getGameObject();
}
