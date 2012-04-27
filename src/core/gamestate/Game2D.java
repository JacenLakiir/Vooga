package core.gamestate;

@SuppressWarnings("serial")
public abstract class Game2D extends GameObject2D{
    private Class<? extends GameObject2D> mclass;
    private Class<? extends GameObject2D> over;
    public Game2D(GameEngine2D engine) {
        super(engine);
        registerNextLevel();
        registerGameOverEvent();
    }
    
    
    
    public abstract boolean isWin();
    public abstract boolean isFail();
    

    @Override
    public void update(long milliSec) {
        if(isWin()){
            getEngine().storeCurrentGameID(mclass);
            switchToGameObject(mclass);
        }
        if(isFail()){
            getEngine().storeCurrentGameID(over);
            switchToGameObject(over);
        }
        super.update(milliSec);
    }
    
    public void setNextLevel(Class<? extends GameObject2D> mclass){
        this.mclass = mclass;
    }
    
    public void setGameOverEvent(Class<? extends GameObject2D> over){
        this.over = over;
    }
    
    public abstract void registerNextLevel();
    
    public abstract void registerGameOverEvent();

}
