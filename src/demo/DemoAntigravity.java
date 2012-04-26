package demo;

import java.awt.Color;  
import java.awt.Graphics2D;


import com.golden.gamedev.object.background.ColorBackground;

import core.characters.Character;
import core.characters.ai.EvadeState;
import core.characters.ai.FollowState;
import core.collision.GameElementCollision;
import core.collision.CharacterCollectibleItemCollision;
import core.configuration.key.KeyAnnotation;
import core.configuration.key.KeyParser;
import core.configuration.mouse.MouseInput;
import core.gamestate.GameEngine2D;
import core.gamestate.Game2D;
import core.items.AutoInUseAutoNotInUseItem;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import core.playfield.AdvancedPlayField;
import core.playfield.scroller.KeepLeftFirstPlayerGameScroller;
import core.tiles.Tile;
import demo.custom.DemoKeyAdapter;
import demo.custom.Goomba;
import demo.custom.MagicBlock;
import demo.custom.Mario;
import demo.custom.Water;


public class DemoAntigravity extends Game2D {

    private AdvancedPlayField   myPlayfield;  
    private Mario mario;
    private Water water;
    private Goomba goomba4, goomba5;
    
    public DemoAntigravity (GameEngine2D arg0) {
        super(arg0);
    }

    @Override
    public void initResources () { 
        myPlayfield = new AdvancedPlayField(10000, 500);
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());
        myPlayfield.setBackground(new ColorBackground(Color.gray, 640, 480));
        
        water = new Water(this, new PhysicsAttributes(), 0.2);
        water.setImages(this.getImages("resources/Water2.png", 1, 1));
        water.setLocation(0, 240);
        myPlayfield.addSetting(water);

        mario = new Mario(this, new PhysicsAttributes());
        setKeyList(new KeyParser(this, false, new DemoKeyAdapter("key_type")).parseKeyConfig("configurations/keyconfig.json"));
        addMouse(new MouseInput(this, mario,"sequence"));
        //add the element or game you want the key to control
        addKeyListeners(mario);
        addKeyListeners(this);
        addMouseListeners(mario);
        mario.getPhysicsAttribute().setDensity(1.02);
        mario.setLocation(25, 0);
        mario.addAttribute("points", 0);
        mario.addAttribute("hitPoints", 10);
        mario.addAttribute("lives", 3);
        myPlayfield.addPlayer(mario);
        
        AutoInUseAutoNotInUseItem magicBlock = new MagicBlock(this, new PhysicsAttributes());
        magicBlock.setImages(this.getImages("resources/MagicBlock.png", 1, 1));
        magicBlock.setActive(true);
        magicBlock.getPhysicsAttribute().setMovable(false);
        magicBlock.setLocation(580, 400);
        myPlayfield.addItem(magicBlock);
        
        goomba4 = new Goomba(this, new PhysicsAttributes());
        goomba4.addPossibleState("Evade", new FollowState(goomba4, mario, 1, 200));
        goomba4.setLocation(300, 400);
        myPlayfield.addCharacter(goomba4);
        
        goomba5 = new Goomba(this, new PhysicsAttributes());
        goomba5.addPossibleState("Evade", new EvadeState(goomba5, mario, 2, 200));
        goomba5.setLocation(400, 400);
        myPlayfield.addCharacter(goomba5);

        
        Tile floor = new Tile(this, new PhysicsAttributes());
        floor.setImages(this.getImages("resources/Bar.png", 1, 1));
        floor.setLocation(0, 440);
        myPlayfield.addSetting(floor);
        
        Tile ceiling = new Tile(this, new PhysicsAttributes());
        ceiling.setImages(this.getImages("resources/Bar.png", 1, 1));
        ceiling.setLocation(70, -20);
        myPlayfield.addSetting(ceiling);
        
        Tile middleBar = new Tile(this, new PhysicsAttributes());
        middleBar.setImages(this.getImages("resources/SmallBar.png", 1, 1));
        middleBar.setLocation(260, 260);
        myPlayfield.addSetting(middleBar);

        
        Tile block1 = new Tile(this, new PhysicsAttributes());
        block1.setImages(this.getImages("resources/Block3.png", 1, 1));
        block1.setLocation(100, 200);
        myPlayfield.addSetting(block1);

        Tile block2 = new Tile(this, new PhysicsAttributes());
        block2.setImages(this.getImages("resources/Block3.png", 8, 1));
        block2.setLocation(160, 200);
        myPlayfield.addSetting(block2);
        
        Tile wall1 = new Tile(this, new PhysicsAttributes());
        wall1.setImages(this.getImages("resources/Wall.png", 1, 1));
        wall1.setLocation(0, 0);
        myPlayfield.addSetting(wall1);
        
        Tile wall2 = new Tile(this, new PhysicsAttributes());
        wall2.setImages(this.getImages("resources/Wall.png", 1, 1));
        wall2.setLocation(620, 0);
        myPlayfield.addSetting(wall2);
        
        
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                                      myPlayfield.getItems(),
                                      new CharacterCollectibleItemCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                                      myPlayfield.getSetting(),
                                      new GameElementCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                                      myPlayfield.getSetting(),
                                      new GameElementCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                                      myPlayfield.getCharacters(),
                                      new GameElementCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                                      myPlayfield.getCharacters(),
                                      new GameElementCollision());
    }
    
    @Override
    public void update (long t) {
        super.update(t);
        myPlayfield.update(t);
    }
    
    
    
    @Override
    public void render (Graphics2D g) {
        myPlayfield.render(g);
    }
    
    @KeyAnnotation(action = "ESC")
    public void pause() {
        switchToGameObject(Pause.class);
    }

    @Override
    public boolean isWin() {
        if(mario.getY() <= -40)
            return true;
        return false;
    }

    @Override
    public void registerNextLevel() {
        setNextLevel(Menu.class);  
    }

    @Override
    public boolean isFail() {
        //game will not fail in this demo
        return false;
    }

    @Override
    public void registerGameOverEvent() {
        //this demo will not fail
    }
    
    public void triggerAntigravity() {
        water.setImages(this.getImages("resources/Water2_Reverse.png", 1, 1));
        water.setLocation(0, 0);
        mario.setImages(this.getImages("resources/Mario1_Reverse.png", 1, 1));
        mario.getPhysicsAttribute().setGravitationalAcceleration(-0.004);
        goomba4.setImages(this.getImages("resources/Goomba_Reverse.png", 1, 1));
        goomba4.getPhysicsAttribute().setGravitationalAcceleration(-0.004);
        goomba5.setImages(this.getImages("resources/Goomba_Reverse.png", 1, 1));
        goomba5.getPhysicsAttribute().setGravitationalAcceleration(-0.004);
        mario.setReversed(true);
    }

}