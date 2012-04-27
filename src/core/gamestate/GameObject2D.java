package core.gamestate;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.timer.SystemTimer;
import core.configuration.key.Key;
import core.configuration.mouse.Mouse;


public abstract class GameObject2D extends GameObject implements Serializable
{
    private final static long serialVersionUID = 6172500678229491464L;
    private List<Key> keyList;
    private List<Mouse> mouseList = new ArrayList<Mouse>();
    private SystemTimer timer = new SystemTimer();

    public List<Mouse> getMouseList ()
    {
        return mouseList;
    }

    private GameEngine2D engine;


    public void setKeyList (List<Key> keys)
    {
        keyList = keys;
    }


    public void addMouse (Mouse mouse)
    {
        mouseList.add(mouse);
    }


    public GameObject2D (GameEngine2D engine)
    {
        super(engine);
        this.engine = engine;
    }


    @Override
    public abstract void initResources ();


    @Override
    public abstract void render (Graphics2D graphic);


    @Override
    public void update (long milliSec)
    {
        checkKeyboardInput(milliSec);
        checkMouseInput(milliSec);
    }


    protected GameEngine2D getEngine ()
    {
        return engine;
    }


    public void addKeyListeners (Object object)
    {
        for (Key key : keyList)
        {
            key.addKeyListenenr(object);
        }
    }


    public void addMouseListeners (Object object)
    {
        for (Mouse mouse : mouseList)
        {
            mouse.addMouseListenenr(object);
        }
    }


    private void checkKeyboardInput (long milliSec)
    {
        for (Key key : keyList)
            if (key.isKeyDown(timer.getTime())) key.notifyObserver();
    }


    private void checkMouseInput (long milliSec)
    {
        for (Mouse mouse : getMouseList())
        {
            if (mouse.isMouseClicked())
            {
                mouse.notifyObserver();
            }
        }
    }


    public void continueGame ()
    {
        engine.nextGameID = engine.getCurrentGameID();
    }


    public void saveNextGame ()
    {
        continueGame();
        engine.saveNextGame();
    }


    private void writeObject (ObjectOutputStream stream) throws IOException
    {
        System.err.printf("serializing GameObject of type %s\n", getClass());
    }


    public void saveGame (File file)
    {
        try
        {
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(this);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void saveGame (String path)
    {
        saveGame(new File(path));
    }


    public void saveGame ()
    {
        // testing
        saveGame("tmp");
        /*
         * JFileChooser chooser = new JFileChooser();
         * chooser.setDialogType(JFileChooser.SAVE_DIALOG); int returnVal =
         * chooser.showOpenDialog(null); if (returnVal ==
         * JFileChooser.APPROVE_OPTION) { File file = chooser.getSelectedFile();
         * try { saveGame(file); } catch (Exception e) { e.printStackTrace(); }
         * }
         */
    }


    public void restartGame ()
    {
        continueGame();
        engine.initResources();
    }


    public void reset ()
    {
        engine.initResources();
    }


    public void switchToGameObject (Class<? extends GameObject2D> gameClass)
    {
        engine.nextGameID = engine.getIdMap().get(gameClass.getName());
        finish();
    }
}
