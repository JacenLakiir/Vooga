/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

import java.io.Serializable;
import com.golden.gamedev.object.Background;
import core.playfield.AdvancedPlayField;
import core.sprites.AdvancedSpriteGroup;


public abstract class GameScroller implements Serializable
{
    private final static long serialVersionUID = -2205006028763469774L;
    protected AdvancedSpriteGroup myPlayers;
    protected Background myBackground;
    protected AdvancedPlayField myPF;


    public void setPF (AdvancedPlayField pf)
    {
        myPF = pf;
    }


    public void setPlayers (AdvancedSpriteGroup players)
    {
        myPlayers = players;
    }


    public void setBackground (Background bkg)
    {
        myBackground = bkg;
    }


    public void changeScroller (GameScroller gs)
    {
        myPF.setGameScroller(gs);
    }


    public abstract void scroll ();

}
