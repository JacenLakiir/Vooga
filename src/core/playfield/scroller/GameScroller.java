/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

import java.io.Serializable;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

import core.playfield.AdvancedPlayField;


public abstract class GameScroller implements Serializable
{
    private final static long serialVersionUID = -2205006028763469774L;
    protected SpriteGroup myPlayers;
    protected Background myBackground;
    protected AdvancedPlayField myPF;
    
    public void setPF (AdvancedPlayField pf) {
    	myPF = pf;
    }

    public void setPlayers (SpriteGroup players)
    {
        myPlayers = players;
    }


    public void setBackground (Background bkg)
    {
        myBackground = bkg;
    }
    
    public void changeScroller (GameScroller gs) {
    	myPF.setGameScroller(gs);
    }

    public abstract void scroll ();

}
