package core.gamestate;

import java.awt.Graphics2D;
import java.util.List;
import com.golden.gamedev.GameObject;

import core.keyconfiguration.Key;

public abstract class GameObject2D extends GameObject{
    protected List<Key>           keyList;
    private GameEngine2D engine;

    public GameObject2D(GameEngine2D engine) {
        super(engine);
        this.engine = engine;
    }
    
    @Override
    public abstract void initResources();

    @Override
    public abstract void render(Graphics2D graphic);

    @Override
    public void update(long arg0) {
        checkKeyboardInput(arg0);
    }
    
    protected GameEngine2D getEngine(){
        return engine;
    }
    
    public void addKeyListeners(Object object){
        for(Key key: keyList){
            key.addKeyListenenr(object);
        }
    }

    
    private void checkKeyboardInput (long milliSec)
    {
        for (Key key : keyList)
            if (key.isKeyDown(milliSec))
                key.notifyObserver();
    }
    
    protected void switchToGameObject(Class<?> gameClass){
        engine.nextGameID = engine.idMap.get(gameClass.getName());
        finish();
    }
    
}
