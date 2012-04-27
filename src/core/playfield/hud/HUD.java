package core.playfield.hud;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class HUD implements Serializable
{
    
	private static final long serialVersionUID = 2509857881191452463L;

	public final static int TOP_LEFT = 0, TOP_RIGHT = 1, BOTTOM_LEFT = 2, BOTTOM_RIGHT = 3, TOP_CENTER = 4, BOTTOM_CENTER = 5;
    
    HUDGroup[] myHUDGroups = new HUDGroup[6];


    public HUD (int w, int h)
    {
    	myHUDGroups[TOP_LEFT] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.DOWNWARD, VerticalFlowLayout.LEFT));
    	myHUDGroups[TOP_CENTER] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.DOWNWARD, VerticalFlowLayout.CENTER));
    	myHUDGroups[TOP_RIGHT] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.DOWNWARD, VerticalFlowLayout.RIGHT));
    	myHUDGroups[BOTTOM_LEFT] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.UPWARD, VerticalFlowLayout.LEFT));
    	myHUDGroups[BOTTOM_CENTER] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.UPWARD, VerticalFlowLayout.CENTER));
    	myHUDGroups[BOTTOM_RIGHT] = new HUDGroup(new VerticalFlowLayout(w, h, VerticalFlowLayout.UPWARD, VerticalFlowLayout.RIGHT));
    }


    public void addWidget (HUDWidget w, int Position)
    {
    	myHUDGroups[Position].addWidget(w);
    }


    public void update (long t)
    {
    	for (HUDGroup hg: myHUDGroups)
    		hg.update(t);
    }


    public void render (Graphics2D g)
    {
    	for (HUDGroup hg: myHUDGroups)
    		hg.render(g);
    }


    private void writeObject (ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(myHUDGroups);
    }
}
