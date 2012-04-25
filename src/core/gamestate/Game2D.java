package core.gamestate;

public abstract class Game2D extends GameObject2D{
    Class<? extends GameObject2D> mclass;
    public Game2D(GameEngine2D engine) {
        super(engine);
        setNextLevel();
    }
    
    public abstract boolean isWin();
    
    

    @Override
    public void update(long milliSec) {
        if(isWin()){
            getEngine().storeCurrentGameID(mclass);
            switchToGameObject(mclass);
        }
        super.update(milliSec);
    }
    
    public void registerNextLevel(Class<? extends GameObject2D> mclass){
        this.mclass = mclass;
    }
    
    public abstract void setNextLevel();
    
    

}
