/**
 * @author Kuang Han
 */

package core.characters;


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
