package GameState;


import com.golden.gamedev.GameObject;

public class PauseState extends State{

    public PauseState(GameEngine2D engine) {
        super(engine);
    }

    @Override
    public void nextState(GameEngine2D engine, int nextGameID) {
        this.engine = engine;
        if(nextGameID == 0 || nextGameID ==1)
            engine.setState(new PlayingState(engine));
        if(nextGameID == 2){
            engine.setState(new MenuState(engine));
        }
    }

    @Override
    public GameObject getGameObject() {
        return null;
    }



}
