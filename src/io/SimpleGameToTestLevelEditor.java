/**
 * @author Michael Zhou (Dominator008)
 */
package io;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import core.gamestate.Game2D;
import core.gamestate.GameEngine2D;
import core.playfield.AdvancedPlayField;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import core.playfield.scroller.GameScroller;
import core.playfield.scroller.KeepLeftFirstPlayerGameScroller;
import demo.DemoGameEngine;


public class SimpleGameToTestLevelEditor extends Game2D
{

    public SimpleGameToTestLevelEditor(GameEngine2D engine) {
	super(engine);
    }

    
    private AdvancedPlayField myPlayfield;
    private Background myBackground;
    private GameScroller myGameScroller;
    private SpriteGroup myPlayers;
    private SpriteGroup myCharacters;
    private SpriteGroup mySetting;
    private Sprite myHero;
    private int LEVEL_WIDTH = 1000;
    private int LEVEL_HEIGHT = 500;


    private void hardCodedLoadLevel ()
    {
        LevelState loadedState = LevelState.load(new File("saves/level1.lvl"));
        myPlayfield = LevelLoader.loadLevel(loadedState, this);
        /*
         * for (Point p: loadedState.getSpriteMap().keySet()) { Sprite tocreate
         * = loadedState.getSpriteMap().get(p).getSprite();
         * tocreate.setLocation(p.x, p.y); myPlayfield.add(tocreate); }
         */
        myHero = myPlayfield.getPlayers().getActiveSprite(); // RANDOM
       //myBackground =
       //     new ImageBackground(getImage(loadedState.getBackgroundImageSrc()));
        //myPlayfield.setBackground(myBackground);
        //myGameScroller = new GameScroller(myPlayers, myBackground);
    }


    public void initResources ()
    {
        // TODO LOAD STUFF GOES HERE
        hardCodedLoadLevel();
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());
        // Sprite Loading
        myPlayers = myPlayfield.addGroup(new SpriteGroup("Player Group"));
        myCharacters = myPlayfield.addGroup(new SpriteGroup("Character Group"));
        mySetting = myPlayfield.addGroup(new SpriteGroup("Setting Group"));
    }


    public void update (long arg0)
    {
        myPlayfield.update(arg0);
        if (keyDown(KeyEvent.VK_UP))
        {
            myHero.setVerticalSpeed(-0.5);
        }
        else if (keyDown(KeyEvent.VK_DOWN))
        {
            myHero.setVerticalSpeed(0.5);
        }
        else if (keyDown(KeyEvent.VK_RIGHT))
        {
            myHero.setHorizontalSpeed(0.5);
        }
        else if (keyDown(KeyEvent.VK_LEFT))
        {
            myHero.setHorizontalSpeed(-0.5);
        }
        else myHero.setSpeed(0, 0);
    }


    public void render (Graphics2D arg0)
    {
        //myGameScroller.scroll();
        myPlayfield.render(arg0);
    }


    @Override
    public boolean isWin() {
	// TODO Auto-generated method stub
	return false;
    }


    @Override
    public boolean isFail() {
	// TODO Auto-generated method stub
	return false;
    }


    @Override
    public void registerNextLevel() {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void registerGameOverEvent() {
	// TODO Auto-generated method stub
	
    }

}
