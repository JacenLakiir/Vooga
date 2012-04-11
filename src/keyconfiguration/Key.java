package keyconfiguration;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract class Key {
    protected String keyValue;
    protected String action;
    protected KeyObserver observer;
    public abstract boolean isKeyDown(long milliSec);
    
    public String getAction(){
        return action;
    }
    
    public String getValue(){
        return keyValue;
    }
    
    public abstract void notifyObserver();
    
}
