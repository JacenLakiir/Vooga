import java.awt.event.KeyEvent;
import java.util.HashMap;
import KeyConfiguration.KeyConfig;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.input.AWTInput;


@SuppressWarnings("serial")
public class Player extends Character{
    Game myGame;
    KeyConfig keyConfig;
    
    public Player(Game game) {
        myGame = game;
        keyConfig = new KeyConfig();
        keyConfig.initialization();
    }
    
    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        HashMap<Integer, String> map = keyConfig.getKeyMap();
        for(int keyValue : map.keySet()){
            if(myGame.keyDown(keyValue)){
                String action = map.get(keyValue);
                if(action.equals("jump")){
                    this.addAcceleration(0, 2*stdGravity);
                }
                if(action.equals("left")){
                    this.addAcceleration(-0.5*stdGravity, 0);
                }
                if(action.equals("right")){
                    this.addAcceleration(0.5*stdGravity,0);
                }
            }
        }
    }


}
