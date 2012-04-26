package demo;

import java.awt.Color;
import java.awt.Graphics2D;
import com.golden.gamedev.object.background.ColorBackground;
import core.characters.Character;
import core.characters.ai.*;
import core.collision.*;
import core.configuration.key.KeyAnnotation;
import core.configuration.key.KeyParser;
import core.gamestate.*;
import core.items.*;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import core.playfield.*;
import core.playfield.scroller.*;
import core.tiles.*;
import demo.custom.*;

/**
 * @author Siyang Chen
 * @author Hui Dong
 * @author Kevin Han
 * @author Ian McMahon
 * @author Eric Mercer (JacenLakiir)
 * @author Kathleen Oshima
 * @author Glenn Rivkees (grivkees)
 * @author Michael Zhou
 */
public class DemoPlayfield extends Game2D {
    
    private AdvancedPlayField myPlayfield;
    private double endOfPlatform;
    private  Character mario;

    public DemoPlayfield(GameEngine2D arg0) {
        super(arg0);
    }

    public void initResources() {
        // Playfield Init
        myPlayfield = new AdvancedPlayField(10000, 500);
        myPlayfield.setBackground(new ColorBackground(Color.gray));
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());

        // Collisions
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getSetting(), new GameElementCollision());

        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getItems(), new CharacterCollectibleItemCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getSetting(), new GameElementCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getCharacters(), new GameElementCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getCharacters(), new GameElementCollision());

		// Sprite Init / Or load funcitonality
		// SpriteGroups already exist in AdvancedPlayfield
		// use addItem(sprite), addPlayer(), addCharacter(), or addSetting()

        // Sprite Init / Or load funcitonality
        // SpriteGroups already exist in AdvancedPlayfield
        // use addItem(sprite), addPlayer(), addCharacter(), or addSetting()

        mario = new Mario(this, new PhysicsAttributes());
        setKeyList(new KeyParser(this, false, new DemoKeyAdapter("key_type")).parseKeyConfig("configurations/keyconfig.json"));
        //add the element or the game you want the key to control
        addKeyListeners(mario);
        addKeyListeners(this);
        mario.setLocation(25, 400);
        mario.addAttribute("hitPoints", 10);
        mario.addAttribute("points", 0);
        mario.addAttribute("lives", 3);
        myPlayfield.addPlayer(mario);
        
        Character koopa1 = new Koopa(this, new PhysicsAttributes());
        koopa1.addPossibleState("Move", new MoveState(koopa1, 1, true));
        koopa1.setLocation(500, 400);
        myPlayfield.addCharacter(koopa1);
        
        Character goomba1 = new Goomba(this, new PhysicsAttributes());
        goomba1.addPossibleState("Move", new MoveState(goomba1, 1, true));
        goomba1.setLocation(800, 400);
        myPlayfield.addCharacter(goomba1);
        
        Character goomba2 = new Goomba(this, new PhysicsAttributes());
        goomba2.addPossibleState("Move", new MoveState(goomba2, 1, true));
        goomba2.setLocation(900, 400);
        myPlayfield.addCharacter(goomba2);
        
        Character goomba3 = new Goomba(this, new PhysicsAttributes());
        goomba3.addPossibleState("Move", new MoveState(goomba3, 1, true));
        goomba3.setLocation(1000, 400);
        myPlayfield.addCharacter(goomba3);
        
        Character goomba4 = new Goomba(this, new PhysicsAttributes());
        goomba4.addPossibleState("Patrol", new PatrolState(goomba4, 1, 325));
        goomba4.setLocation(575, 200);
        myPlayfield.addCharacter(goomba4);

        Tile temp1 = new Tile(this, new PhysicsAttributes());
        temp1.setImages(this.getImages("resources/Bar.png", 1, 1));
        temp1.setLocation(0, 440);
        myPlayfield.addSetting(temp1);

        Tile temp2 = new Tile(this, new PhysicsAttributes());
        temp2.setImages(this.getImages("resources/Bar.png", 1, 1));
        temp2.setLocation(600, 440);
        myPlayfield.addSetting(temp2);

        ActionDecorator block2 = new BreakableDecorator(new Tile(this, new PhysicsAttributes()), 2);
        block2.setBottomAction(true);
        block2.setImages(this.getImages("resources/Block2Break.png", 8, 1));
        block2.setLocation(160, 200);
        myPlayfield.addSetting(block2);

        AutoInUseAutoNotInUseItem coin = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        coin.setImages(this.getImages("resources/Coin.png", 1, 1));
        coin.setActive(false);
        coin.addAttribute("points", 3);
        myPlayfield.addItem(coin);
        
        AutoInUseAutoNotInUseItem poison = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        poison.setImages(this.getImages("resources/Poison.png", 1, 1));
        poison.setActive(true);
        poison.getPhysicsAttribute().setMovable(false);
        poison.setLocation(300, 400);
        poison.setTimerStart(1000);
        poison.setTimerEnd(4000);
        poison.addAttribute("hitPoints", -1);
        myPlayfield.addItem(poison);
        
        ItemDecorator block1 = new ItemDecorator(new Tile(this, new PhysicsAttributes()));
        block1.setBottomAction(true);
        block1.setImages(this.getImages("resources/Block1.png", 1, 1));
        block1.setLocation(100, 200);
        block1.addItem(coin);
        myPlayfield.addSetting(block1);
        
        MovingDecorator middleBar = new MovingDecorator(new Tile(this,
				new PhysicsAttributes()));
		middleBar.setLocation(260, 240);
		middleBar.setEndLocation(700, 60);
		middleBar.setMoveSpeed(0.05);
        middleBar.setImages(getImages("resources/SmallBar.png", 1, 1));
        myPlayfield.addSetting(middleBar);

      ActionDecorator otherBar = new FallingDecorator(new Tile(this, new PhysicsAttributes()), 1000);
      otherBar.setTopAction(true);
      otherBar.setImages(getImages("resources/SmallBar.png",1,1));
      otherBar.setLocation(1250, 300);
      endOfPlatform = otherBar.getX() + otherBar.getWidth() - 30;
      myPlayfield.addSetting(otherBar);
	}

	public void update(long arg0) {
	    super.update(arg0);
		myPlayfield.update(arg0);
	}


	public void render(Graphics2D arg0) {
		myPlayfield.render(arg0);
	}

    @KeyAnnotation(action = "ESC")
    public void pause(){
        switchToGameObject(Pause.class);
    }

    @Override
    public boolean isWin() {
        if(mario.getX() >= endOfPlatform)
            return true;
        return false;
    }

    @Override
    public void registerNextLevel() {
        setNextLevel(DemoHUD.class);       
    }

    @Override
    public boolean isFail() {
        //this demo will not fail
        return false;
    }

    @Override
    public void registerGameOverEvent() {
        //this demo will not fail
    }
	
}
