/**
 * @author Ian McMahon
 */

package tiles;

import com.golden.gamedev.Game;

public class BaseTile extends Tile {
	
	public BaseTile (Game owner) {
		super(owner);
		this.isUnmovable = true;
		this.isPenetrable = false;
	}

}
