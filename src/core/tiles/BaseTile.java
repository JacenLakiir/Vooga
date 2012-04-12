/**
 * @author Ian McMahon
 */

package core.tiles;

import com.golden.gamedev.GameObject;

public class BaseTile extends Tile {
	
	public BaseTile (GameObject owner) {
		super(owner);
		this.isUnmovable = true;
		this.isPenetrable = false;
	}

}
