/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import core.playfield.AdvancedPlayField;


public class LevelState implements Serializable
{
    private static final long serialVersionUID = 3910981230998281239L;

    private String backgroundPath;
    private List<SpriteWrapper> sprites;


    public LevelState (AdvancedPlayField playField)
    {

    }


    private void reconstruct ()
    {
        for (SpriteWrapper sp : sprites)
            sp.reconstruct();
    }


    public void save (File file)
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


    public void save (String path)
    {
        save(new File(path));
    }


    public static LevelState load (File file)
    {
        LevelState res = null;
        try
        {
            ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(file));
            res = (LevelState) in.readObject();
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        res.reconstruct();
        return res;
    }


    public static LevelState load (String path)
    {
        return load(new File(path));
    }

}
