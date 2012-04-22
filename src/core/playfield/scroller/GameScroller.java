/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;


public abstract class GameScroller
{
    protected SpriteGroup myPlayers;
    protected Background myBackground;


    public void setPlayers (SpriteGroup players)
    {
        myPlayers = players;
    }


    public void setBackground (Background bkg)
    {
        myBackground = bkg;
    }


    public abstract void scroll ();

}
