package keyconfiguration;
import charactersprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public abstract class KeyObserver {
    Player player;
    public KeyObserver(Player player){
        this.player = player;
    }
    public void Update(){
        
    }
    
    public abstract void getActoinMethods(String action);
    
}
