package core.playfield.hud;

import java.awt.Point;


public class VerticalFlowLayout extends HUDLayoutManager
{

    public VerticalFlowLayout (int width, int height)
    {
        super(width, height);
        this.nextPosition = new Point(0, 0);
    }


    public Point nextWidgetPosition (HUDWidget w)
    {
        Point temp = this.nextPosition;
        this.nextPosition =
            new Point((int) temp.getX(), (int) temp.getY() + w.height);
        return temp;
    }

}
