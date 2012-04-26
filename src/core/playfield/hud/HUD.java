package core.playfield.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.font.SystemFont;


public class HUD implements Serializable
{
    
	private static final long serialVersionUID = 2509857881191452463L;

	public final static int TOP_LEFT = 0, TOP_RIGHT = 1, BOTTOM_LEFT = 2, BOTTOM_RIGHT = 3;
    
    HUDGroup[] myHUDGroups = new HUDGroup[4];


    public HUD ()
    {
    	myHUDGroups[TOP_LEFT] = new HUDGroup(new VerticalFlowLayout(0, 0));
    	myHUDGroups[TOP_RIGHT] = new HUDGroup(new VerticalFlowLayout(100, 0));
    	myHUDGroups[BOTTOM_LEFT] = new HUDGroup(new VerticalFlowLayout(0, 100));
    	myHUDGroups[BOTTOM_RIGHT] = new HUDGroup(new VerticalFlowLayout(100, 100));
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
