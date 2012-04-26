package core.playfield.hud;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class HUD implements Serializable
{
    
	private static final long serialVersionUID = 2509857881191452463L;

	public final static int TOP_LEFT = 0, TOP_RIGHT = 1, BOTTOM_LEFT = 2, BOTTOM_RIGHT = 3;
    
    HUDGroup[] myHUDGroups = new HUDGroup[4];


    public HUD (int w, int h)
    {
    	myHUDGroups[TOP_LEFT] = new HUDGroup(new VerticalFlowLayout(0, 0, false, false));
    	myHUDGroups[TOP_RIGHT] = new HUDGroup(new VerticalFlowLayout(w, 0, false, true));
    	myHUDGroups[BOTTOM_LEFT] = new HUDGroup(new VerticalFlowLayout(0, h, true, false));
    	myHUDGroups[BOTTOM_RIGHT] = new HUDGroup(new VerticalFlowLayout(w, h, true, true));
    }


    public void addWidget (HUDWidget w, int Position)
    {
    	myHUDGroups[Position].addWidget(w);
    }


    public void update (long t)
    {
    	myHUDGroups[TOP_LEFT].update(t);
    	myHUDGroups[TOP_RIGHT].update(t);
    	myHUDGroups[BOTTOM_LEFT].update(t);
    	myHUDGroups[BOTTOM_RIGHT].update(t);
    }


    public void render (Graphics2D g)
    {
    	myHUDGroups[TOP_LEFT].render(g);
    	myHUDGroups[TOP_RIGHT].render(g);
    	myHUDGroups[BOTTOM_LEFT].render(g);
    	myHUDGroups[BOTTOM_RIGHT].render(g);
    }


    private void writeObject (ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(myHUDGroups);
    }
}
