package core.playfield.hud;

import java.awt.Point;


public class VerticalFlowLayout extends HUDLayoutManager
{

    public VerticalFlowLayout (int startX, int startY)
    {
        super(startX, startY);
        this.nextPosition = new Point(startY, startY);
    }


    public Point nextWidgetPosition (HUDWidget w)
    {
        Point temp = this.nextPosition;
        this.nextPosition =
            new Point((int) temp.getX(), (int) temp.getY() + w.height);
        return temp;
    }

}
