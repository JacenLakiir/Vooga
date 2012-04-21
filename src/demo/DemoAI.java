package demo;

import java.awt.Color;
import java.awt.Graphics2D;
import com.golden.gamedev.object.background.ColorBackground;
import core.characters.NPC;
import core.characters.Player;
import core.characters.ai.EvadeState;
import core.characters.ai.FollowState;
import core.collision.CharacterPlatformCollision;
import core.collision.GameElementCollision;
import core.collision.PlayerCollectibleItemCollision;
import core.gamestate.GameEngine2D;
import core.gamestate.GameObject2D;
import core.gamestate.Pause;
import core.items.AutoInUseAutoNotInUseItem;
import core.keyconfiguration.KeyAnnotation;
import core.keyconfiguration.KeyConfig;
import core.keyconfiguration.MouseInput;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import core.playfield.AdvancedPlayField;
import core.playfield.scroller.KeepLeftFirstPlayerGameScroller;
import core.tiles.ActionDecorator;
import core.tiles.BreakableDecorator;
import core.tiles.ItemDecorator;
import core.tiles.Tile;
import demo.custom.Goomba;
import demo.custom.Mario;

/**
 * @author Eric Mercer (JacenLakiir)
 * 
 * For testing NPC AI
 */
public class DemoAI extends GameObject2D
{
   
    private AdvancedPlayField   myPlayfield;  
    
    public DemoAI (GameEngine2D arg0)
    {
        super(arg0);
    }

    @Override
    public void initResources ()
    { 
        
        myPlayfield = new AdvancedPlayField(10000, 500);
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());
        myPlayfield.setBackground(new ColorBackground(Color.gray, 640, 480));
        
        Player mario = new Mario(this, new PhysicsAttributes());
        setKeyList(new KeyConfig(this,false).getKeyList());
        addMouse(new MouseInput(this, mario,"sequence"));
        //add the element or game you want the key to control
        addKeyListeners(mario);
        addKeyListeners(this);
        addMouseListeners(mario);
        
        mario.setLocation(25, 400);
        mario.addState("points", 0);
        mario.addState("hitPoints", 10);
        mario.addState("lives", 3);
        myPlayfield.addPlayer(mario);
        
//        NPC goomba1 = new Goomba(this, new PhysicsAttributes());
//        goomba1.setLocation(325, 400);
//        myPlayfield.addCharacter(goomba1);
//       
//        NPC goomba2 = new Goomba(this, new PhysicsAttributes());
//        goomba2.addPossibleState(new PatrolState(goomba2, 1, 150));
//        goomba2.setLocation(575, 400);
//        myPlayfield.addCharacter(goomba2);
//        
//        NPC goomba3 = new Goomba(this, new PhysicsAttributes());
//        goomba3.addPossibleState(new MoveState(goomba3, 1, true));
//        goomba3.setLocation(275, 400);
//        myPlayfield.addCharacter(goomba3);
        
//        NPC koopa1 = new Koopa(this, new PhysicsAttributes());
//        koopa1.addPossibleState(new MoveState(koopa1, 1, true));
//        koopa1.setLocation(500, 400);
//        myPlayfield.addCharacter(koopa1);
//        
//        NPC koopa2 = new Koopa(this, new PhysicsAttributes());
//        koopa2.addPossibleState(new MoveState(koopa2, 1, true));
//        koopa2.setLocation(300, 400);
//        myPlayfield.addCharacter(koopa2);
        
        NPC goomba4 = new Goomba(this, new PhysicsAttributes());
        goomba4.addPossibleState(new FollowState(goomba4, mario, 1, 200));
        goomba4.setLocation(300, 400);
        myPlayfield.addCharacter(goomba4);
        
        NPC goomba5 = new Goomba(this, new PhysicsAttributes());
        goomba5.addPossibleState(new EvadeState(goomba5, mario, 2, 200));
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

        AutoInUseAutoNotInUseItem coin = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        coin.setImages(this.getImages("resources/Coin.png", 1, 1));
        coin.setActive(false);
        coin.addState("points", 3);
        myPlayfield.addItem(coin);
        
        ItemDecorator block1 = new ItemDecorator(new Tile(this, new PhysicsAttributes()));
        block1.setMass(6);
        block1.setMovable(false);
        block1.setImages(this.getImages("resources/Block1.png", 1, 1));
        block1.setLocation(100, 200);
        block1.addItem(coin);
        myPlayfield.addSetting(block1);

        ActionDecorator block2 = new BreakableDecorator(new Tile(this, new PhysicsAttributes()), 1);
        block2.setBottomAction(true);
        block2.setImages(this.getImages("resources/Block2Break.png", 8, 1));
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
                                      new PlayerCollectibleItemCollision());
        
        // currently no way to check all characters against setting in one collision
        
        // tried adding 'mario' to player and character spritegroups and then
        // checking characters against setting, but physics didn't work
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                                      myPlayfield.getSetting(),
                                      new CharacterPlatformCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                                      myPlayfield.getSetting(),
                                      new CharacterPlatformCollision());
        
        // same flaws as the two collisions above and uses GameElementCollision
        // since no PlayerCharacterCollision or CharacterCharacterCollision exists
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                                      myPlayfield.getCharacters(),
                                      new GameElementCollision());
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                                      myPlayfield.getCharacters(),
                                      new GameElementCollision());
    }
    
    @Override
    public void update (long t)
    {
        super.update(t);
        myPlayfield.update(t);
    }
    

    @Override
    public void render (Graphics2D g)
    {
        myPlayfield.render(g);
    }
    
    @KeyAnnotation(action = "ESC")
    public void pause(){
        switchToGameObject(Pause.class);
    }

}