package core.gamestate;

public abstract class Game2D extends GameObject2D{
    Class<? extends GameObject2D> mclass;
    public Game2D(GameEngine2D engine) {
        super(engine);
    }
    
    public abstract boolean isWin();

    @Override
    public void update(long milliSec) {
        if(isWin()){
            switchToGameObject(mclass);
        }
        super.update(milliSec);
    }
    
    public abstract void switchToNextLevel();
    
    

}
