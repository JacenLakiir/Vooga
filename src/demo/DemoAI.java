package demo;

import java.awt.Color;	
import java.awt.Graphics2D;
import com.golden.gamedev.object.background.ColorBackground;

import core.characters.Character;
import core.characters.ai.HomingState;
import core.characters.ai.MoveState;
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
import core.tiles.ActionDecorator;
import core.tiles.BreakableDecorator;
import core.tiles.ItemDecorator;
import core.tiles.Tile;
import demo.custom.DemoKeyAdapter;
import demo.custom.Goomba;
import demo.custom.HammerBrother;
import demo.custom.Mario;

/**
 * @author Eric Mercer (JacenLakiir)
 * 
 * For testing NPC AI
 */
@SuppressWarnings("serial")
public class DemoAI extends Game2D
{
   
    private AdvancedPlayField   myPlayfield;  
    private Character mario;
    private double endOfPlatform;
    public DemoAI (GameEngine2D arg0)
    {
        super(arg0);
    }

    @Override
    public void initResources ()
    { 
        
        myPlayfield = new AdvancedPlayField(10000, 500, this.getWidth(), this.getHeight());
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());
        myPlayfield.setBackground(new ColorBackground(Color.gray, 640, 480));
        
         mario = new Mario(this, new PhysicsAttributes());
        setKeyList(new KeyParser(this, false, new DemoKeyAdapter("key_type")).parseKeyConfig("configurations/keyconfig.json"));
        addMouse(new MouseInput(this, mario,"sequence"));
        //add the element or game you want the key to control
        addKeyListeners(mario);
        addKeyListeners(this);
        addMouseListeners(mario);
        mario.setLocation(25, 400);
//        mario.addAttribute("points", 0);
//        mario.addAttribute("hitPoints", 10);
//        mario.addAttribute("lives", 3);
        myPlayfield.addPlayer(mario);
        
//        Character goomba1 = new Goomba(this, new PhysicsAttributes());
//        goomba1.setLocation(325, 400);
//        myPlayfield.addCharacter(goomba1);
//       
//        Character goomba2 = new Goomba(this, new PhysicsAttributes());
//        goomba2.addPossibleState(new PatrolState(goomba2, 1, 150));
//        goomba2.setLocation(575, 400);
//        myPlayfield.addCharacter(goomba2);
//        
//        Character goomba3 = new Goomba(this, new PhysicsAttributes());
//        goomba3.addPossibleState(new MoveState(goomba3, 1, true));
//        goomba3.setLocation(275, 400);
//        myPlayfield.addCharacter(goomba3);
        
//        Character koopa1 = new Koopa(this, new PhysicsAttributes());
//        koopa1.addPossibleState(new MoveState(koopa1, 1, true));
//        koopa1.setLocation(500, 400);
//        myPlayfield.addCharacter(koopa1);
//        
//        Character koopa2 = new Koopa(this, new PhysicsAttributes());
//        koopa2.addPossibleState(new MoveState(koopa2, 1, true));
//        koopa2.setLocation(300, 400);
//        myPlayfield.addCharacter(koopa2);
        
//        Character goomba4 = new Goomba(this, new PhysicsAttributes());
//        goomba4.addPossibleState("Follow", new FollowState(goomba4, mario, 1, 200));
//        goomba4.setLocation(300, 400);
//        myPlayfield.addCharacter(goomba4);
//        
//        Character goomba5 = new Goomba(this, new PhysicsAttributes());
//        goomba5.addPossibleState("Evade", new EvadeState(goomba5, mario, 2, 200));
//        goomba5.setLocation(400, 400);
//        myPlayfield.addCharacter(goomba5);
        
        Character goomba6 = new Goomba(this, new PhysicsAttributes());
        goomba6.addPossibleState("Homing", new HomingState(goomba6, mario, 1000, 1));
        goomba6.setLocation(500, 100);
        myPlayfield.addCharacter(goomba6);
        
        Character hammerBro1 = new HammerBrother(this, new PhysicsAttributes());
        hammerBro1.addPossibleState("Patrol", new MoveState(hammerBro1, 1, true));
        hammerBro1.setLocation(575, 395);
        myPlayfield.addCharacter(hammerBro1);
        
        Tile floor = new Tile(this, new PhysicsAttributes());
        floor.setImages(this.getImages("resources/Bar.png", 1, 1));
        floor.setLocation(0, 440);
        endOfPlatform = floor.getX() + floor.getWidth() - 50;
        myPlayfield.addSetting(floor);
        
        Tile ceiling = new Tile(this, new PhysicsAttributes());
        ceiling.setImages(this.getImages("resources/Bar.png", 1, 1));
        ceiling.setLocation(70, -20);
        myPlayfield.addSetting(ceiling);

        AutoInUseAutoNotInUseItem coin = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        coin.setImages(this.getImages("resources/Coin.png", 1, 1));
        coin.setActive(false);
        coin.addAttribute("points", 3);
        myPlayfield.addItem(coin);
        
        ItemDecorator block1 = new ItemDecorator(new Tile(this, new PhysicsAttributes()));
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

    @Override
    public boolean isWin() {
        return (mario.getX() >= (endOfPlatform));
    }

    @Override
    public void registerNextLevel() {
        setNextLevel(DemoPlayfield.class);
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

}