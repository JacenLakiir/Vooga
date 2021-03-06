package demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
import core.configuration.key.KeyAnnotation;
import core.configuration.key.KeyParser;
import core.gamestate.GameEngine2D;
import core.gamestate.MenuGameObject;
import demo.custom.DemoKeyAdapter;


/**
 * @author Hui Dong
 */
@SuppressWarnings("serial")
public class Pause extends MenuGameObject
{

    private Background background;
    private BufferedImage arrow;

    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int TEXT_HEIGHT = 20;


    public Pause (GameEngine2D engine)
    {
        super(engine);
    }


    @Override
    public void buildMenu ()
    {
        addOptionToMenu("Continue");
        addOptionToMenu("Save");
        addOptionToMenu("Restart");
        addOptionToMenu("Menu");
    }


    @Override
    public void initResources ()
    {
        super.initResources();
        background =
            new ImageBackground(getImage("resources/StarDust.jpg"),
                                WINDOW_WIDTH,
                                WINDOW_HEIGHT);
        arrow = getImage("resources/MenuArrow.png");
    }


    @Override
    public void render (Graphics2D graphic)
    {
        background.render(graphic);
        graphic.setColor(Color.WHITE);
        int i = 0;
        for (String name : getOptionNames())
        {
            graphic.drawString(name, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 + i *
                                                       20);
            i++;
        }
        graphic.drawImage(arrow, WINDOW_WIDTH / 2 - 20, WINDOW_HEIGHT / 2 -
                                                        TEXT_HEIGHT / 2 +
                                                        getOptionID() *
                                                        TEXT_HEIGHT, null);
    }


    @Override
    public void update (long milliSec)
    {
        super.update(milliSec);
    }


    @KeyAnnotation(action = "enter")
    public void nextGameObject ()
    {
        if (getOptionID() == 0)
        {
            continueGame();
        }
        if (getOptionID() == 1)
        {
            saveNextGame();
        }
        if (getOptionID() == 2)
        {
            restartGame();
        }
        if (getOptionID() == getNumberOfItems() - 1)
        {
            switchToGameObject(Menu.class);
            reset();
        }
        finish();
    }


    @Override
    public void initialKeyList ()
    {
        setKeyList(new KeyParser(this, true, new DemoKeyAdapter("key_type")).parseKeyConfig("configurations/keyconfig.json"));

    }

}
