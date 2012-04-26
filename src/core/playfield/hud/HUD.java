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
    private final static long serialVersionUID = 912402285760541314L;
    private GameFont font =
        new SystemFont(new Font("Monospaced", Font.BOLD, 12));
    private ArrayList<HUDWidget> myWidgets = new ArrayList<HUDWidget>();
    private HUDLayoutManager layoutManager;


    public HUD (HUDLayoutManager m)
    {
        this.layoutManager = m;
    }


    public void addWidget (HUDWidget w)
    {
        myWidgets.add(w);
        w.setPosition(layoutManager.nextWidgetPosition(w));
    }


    public void update (long t)
    {
        for (HUDWidget w : myWidgets)
        {
            w.update(t);
        }
    }


    public void render (Graphics2D g)
    {
        // g.clearRect(0,0,layoutManager.width,layoutManager.height);
        for (HUDWidget w : myWidgets)
        {
            g.setColor(Color.black);
            w.render(g, this);
        }
    }


    public GameFont getFont ()
    {
        return font;
    }


    private void writeObject (ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(myWidgets);
        stream.writeObject(layoutManager);
    }
}
