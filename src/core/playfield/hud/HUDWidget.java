package core.playfield.hud;

import java.awt.Graphics2D;
import java.awt.Point;


public abstract class HUDWidget
{

    protected int xPos, yPos, height, width;


    public HUDWidget (int w, int h)
    {
        width = w;
        height = h;
    }


    public void setPosition (Point p)
    {
        xPos = (int) p.getX() + 10;
        yPos = (int) p.getY() + 10;
    }


    public abstract void update (long t);


    public abstract void render (Graphics2D g, HUDGroup h);

}
