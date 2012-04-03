package charactersprites;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItemSprite extends Player {

	public CollectibleItemSprite(Game game) {
	    super(game);
    }
		
	public abstract double attackPower();
	public abstract double defensePower();

}



