/**
 * @author Glenn Rivkees (grivkees)
 */

package game;

import java.awt.Graphics2D;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ParallaxBackground;
import collision.*;


public class PlatformGame extends Game
{
    private PlayField myPlayfield;
    private Background myBackground;
    private GameScroller myGameScroller;

    private SpriteGroup myPlayers;
    private SpriteGroup myCharacters;
    private SpriteGroup mySetting;


    public void initResources ()
    {
        // TODO LOAD STUFF GOES HERE

        int LEVEL_WIDTH = 10000;
        int LEVEL_HIGHT = 500;

        // Sprite Loading
        myPlayers = myPlayfield.addGroup(new SpriteGroup("Player Group"));
        myCharacters = myPlayfield.addGroup(new SpriteGroup("Character Group"));
        mySetting = myPlayfield.addGroup(new SpriteGroup("Setting Group"));

        // Background Load
        myBackground =
            new ParallaxBackground(new Background[] { new Background(LEVEL_WIDTH,
                                                                     LEVEL_HIGHT) //,
            //new ImageBackground(bgImg, bgImg.getWidth(), bgImg.getHeight())
            });
        myPlayfield.setBackground(myBackground);
        myGameScroller =
            new CenterFirstPlayerGameScroller(myPlayers, myBackground);

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
        myGameScroller.scroll();
        myPlayfield.render(arg0);
    }
}
