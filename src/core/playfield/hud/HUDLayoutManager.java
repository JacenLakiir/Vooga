package core.playfield.hud;

import java.awt.Point;
import java.io.Serializable;


public abstract class HUDLayoutManager implements Serializable
{
    private final static long serialVersionUID = 1235573338455171053L;
    protected int startX, startY;
    protected Point nextPosition;


    public HUDLayoutManager (int startX, int startY)
    {
        this.startX = startX;
        this.startY = startY;
    }


    public abstract Point nextWidgetPosition (HUDWidget h);

}
