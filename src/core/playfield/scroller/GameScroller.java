/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

import java.io.Serializable;
import com.golden.gamedev.object.Background;
import core.sprites.SpriteGroup;


public abstract class GameScroller implements Serializable
{
    private final static long serialVersionUID = -2205006028763469774L;
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
