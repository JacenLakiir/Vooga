/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

public class ShowPlayfieldGameScroller extends GameScroller
{
    private final static long serialVersionUID = 3180414135401018121L;

    int xPos = 0;

    public void scroll ()
    {
        myBackground.setLocation(xPos, 0);
        xPos += 5;
        // After scroll, change to normal screenscroller
        if (xPos > this.myBackground.getWidth() - 100) {
        	GameScroller newgs = new KeepLeftFirstPlayerGameScroller();
    		newgs.setPF(myPF);
    		newgs.setBackground(myBackground);
    		newgs.setPlayers(myPlayers);
    		this.changeScroller(newgs);
        }
    }

}
