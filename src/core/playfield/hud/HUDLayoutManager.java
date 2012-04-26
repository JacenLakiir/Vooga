package core.playfield.hud;

import java.awt.Point;
import java.io.Serializable;


public abstract class HUDLayoutManager implements Serializable
{
    private final static long serialVersionUID = 1235573338455171053L;
    protected int width, height;
    protected Point nextPosition;


    public HUDLayoutManager (int width, int height)
    {
        this.width = width;
        this.height = height;
    }


    public abstract Point nextWidgetPosition (HUDWidget h);

}
