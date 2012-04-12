/**
 * @author Kuang Han
 */

package core.characters;


import com.golden.gamedev.Game;


@SuppressWarnings("serial")
public abstract class Character extends GameElement{
    public Character(Game game) {
        super(game);
    }
    
    public Character() {
        super();
    }
}
