/**
  * @author Glenn Rivkees (grivkees)
 */

package game;

import java.awt.Color;
import java.awt.Graphics2D;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ParallaxBackground;
import collision.*;


public class PlatformGame extends Game
{
    private AdvancedPlayField myPlayfield;


    public void initResources ()
    {
    	// Playfield Init
    	myPlayfield = new AdvancedPlayField(10000, 500);
    	myPlayfield.setBackground(new ColorBackground(Color.gray, 640, 480));
    	myPlayfield.setGameScroller(new CenterFirstPlayerGameScroller());

        // Collisions
        myPlayfield.addCollisionGroup(myPlayers,
                                      mySetting,
                                      new CharacterPlatformCollision());
        // add collectable item to platform collision
        // add collectable item to player/character collision
    }


    public void update (long arg0)
    {
        myPlayfield.update(arg0);
    }


    public void render (Graphics2D arg0)
    {
        myPlayfield.render(arg0);
    }
}
