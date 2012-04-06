/**
 * @author Ian McMahon
 */

package setting;

import com.golden.gamedev.Game;

public class BasePlatform extends Platform {
	
	public BasePlatform (Game owner) {
		super(owner);
		this.isUnmovable = true;
		this.isPenetrable = false;
	}

//	public void hitFromBottomAction() {	}
//
//	public void hitFromTopAction() {	}
//
//	public void hitFromLeftAction() {	}
//
//	public void hitFromRightAction() {	}

}
