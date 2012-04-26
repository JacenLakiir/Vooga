package core.playfield.hud;

import java.awt.Point;


public class VerticalFlowLayout extends HUDLayoutManager
{

	private static final long serialVersionUID = 7979018578539774757L;
	
	int deltY;
	boolean alignRight;

	public VerticalFlowLayout (int startX, int startY, boolean growUp, boolean alignRight)
    {
        super(startX, startY);
        this.nextPosition = new Point(startX, startY);
        if (growUp) { 
        	deltY = -1;
        	this.nextPosition.y = this.nextPosition.y - 40;
        }
        else deltY = 1;
        this.alignRight = alignRight;
    }


    public Point nextWidgetPosition (HUDWidget w)
    {
        Point temp = this.nextPosition;
        this.nextPosition =
            new Point((int) temp.getX(), (int) temp.getY() + w.height*deltY);
        if (alignRight) {
        	temp.x -= w.width;
        }
        return temp;
    }

}
