package GameState;


import com.golden.gamedev.GameObject;

public class PlayingState extends State{


    public PlayingState(GameEngine2D engine) {
        super(engine);
    }

    @Override
    public void nextState(GameEngine2D engine, int nextGameID) {
        this.engine = engine;
        engine.setState(new PauseState(engine));
    }

    @Override
    public GameObject getGameObject() {
        return new SimpleGameToTestLevelEditor(engine);
    }



}
