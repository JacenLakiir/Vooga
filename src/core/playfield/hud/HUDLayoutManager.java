package core.playfield.hud;

import java.awt.Point;
import java.io.Serializable;


public abstract class HUDLayoutManager implements Serializable
{
    private final static long serialVersionUID = 1235573338455171053L;
    protected int dimmX, dimmY;
    protected Point nextPosition;


    public HUDLayoutManager (int dimmX, int dimmY)
    {
        this.dimmX = dimmX;
        this.dimmY = dimmY;
    }


    public abstract Point nextWidgetPosition (HUDWidget h);

}
