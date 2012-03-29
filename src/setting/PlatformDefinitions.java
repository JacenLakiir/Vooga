package setting;

import com.golden.gamedev.Game;

public class PlatformDefinitions  {
	
	public static Platform makePlatformA(Game owner, String imgSrc, double x, double y){
		return new BreakableDecorator(new BasePlatform(owner, imgSrc, x, y));
	}

}
