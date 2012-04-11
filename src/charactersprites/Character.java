/**
 * @author Kuang Han
 */

package charactersprites;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;


@SuppressWarnings("serial")
public abstract class Character extends GameElement{
    public Character(GameObject game) {
        super(game);
    }
    
    public Character() {
        super();
    }
}
