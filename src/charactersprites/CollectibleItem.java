package charactersprites;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItem extends Player {

	public CollectibleItem(Game game) {
		super(game);
	}
	
	public abstract boolean isInUse();
	public abstract double attackPower();
	public abstract double defensePower();
	public abstract double hitPoints();

}
