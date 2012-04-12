/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;


public class LevelState
{
    public String myBackgroundSrc;
    public Map<Point, SpriteWrapper> mySpriteMap;


    public LevelState (String background, Map<Point, SpriteWrapper> spritemap)
    {
        mySpriteMap = new HashMap<Point, SpriteWrapper>(spritemap);
        myBackgroundSrc = background;
    }


    public static LevelState loadFile (File file)
    {
        Gson gson = new Gson();
        LevelState level = gson.fromJson(readJsonFile(file), LevelState.class);
        level.reconstruct();
        return level;
    }


    private void reconstruct ()
    {
        for (SpriteWrapper sp : mySpriteMap.values())
            sp.reconstruct();
    }


    public void save (String url)
    {
        Gson gson =
            new GsonBuilder().setPrettyPrinting()
                             .enableComplexMapKeySerialization()
                             .create();
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(url));
            gson.toJson(this, out);
            out.close();
        }
        catch (JsonIOException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private static String readJsonFile (File file)
    {
        Scanner in = null;
        try
        {
            in = new Scanner(new FileInputStream(file)).useDelimiter("\\Z+");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return in.next();
    }

}
