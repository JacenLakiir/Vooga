/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package leveleditor;

import gameMOVEthisSTUFFout.LevelState;
import gameMOVEthisSTUFFout.SpriteWrapper;

import java.awt.Point;
import java.io.File;
import java.util.Map;


public class VoogaLevelEditorModel
{
    protected LevelState myLevelState;


    public VoogaLevelEditorModel ()
    {}


    protected void loadLevel (File file)
    {
        myLevelState = LevelState.loadFile(file);
    }


    protected void saveLevel (String path,
                              String backgroundimgsrc,
                              Map<Point, SpriteWrapper> spritemap)
    {
        LevelState level = new LevelState(backgroundimgsrc, spritemap);
        level.save(path);
    }

}
