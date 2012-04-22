package demo;

import java.awt.Color;	
import java.awt.Graphics2D;


import com.golden.gamedev.object.background.ColorBackground;

import core.characters.NPC;
import core.characters.Player;
import core.characters.ai.MoveState;
import core.characters.ai.PatrolState;
import core.collision.CharacterPlatformCollision;
import core.collision.GameElementCollision;
import core.collision.PlayerCollectibleItemCollision;
import core.gamestate.GameEngine2D;
import core.gamestate.GameObject2D;
import core.gamestate.Pause;
import core.items.AutoInUseAutoNotInUseItem;
import core.items.AutoNotInUseItem;
import core.items.CollectibleItem;
import core.items.SetInUseSetNotInUseItem;
import core.keyconfiguration.KeyAnnotation;
import core.keyconfiguration.KeyConfig;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import core.playfield.AdvancedPlayField;
import core.playfield.hud.BarWidget;
import core.playfield.hud.DataProxy;
import core.playfield.hud.IconWidget;
import core.playfield.hud.TextWidget;
import core.playfield.scroller.KeepLeftFirstPlayerGameScroller;
import core.tiles.*;
import demo.custom.Goomba;
import demo.custom.Koopa;
import demo.custom.Mario;

/**
 * @author Glenn Rivkees (grivkees)
 */
public class DemoHUD extends GameObject2D {
    
    private AdvancedPlayField myPlayfield;
    
    public DemoHUD(GameEngine2D arg0) {
        super(arg0);
    }

    public void initResources() {
   	
        // Playfield Init
        myPlayfield = new AdvancedPlayField(2000, 500);
        myPlayfield.setBackground(new ColorBackground(Color.gray));
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());

        // Collisions
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getSetting(), new CharacterPlatformCollision());

        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getItems(), new PlayerCollectibleItemCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getSetting(), new CharacterPlatformCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
                myPlayfield.getCharacters(), new GameElementCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getCharacters(), new GameElementCollision());

        // Sprite Init / Or load funcitonality
        // SpriteGroups already exist in AdvancedPlayfield
        // use addItem(sprite), addPlayer(), addCharacter(), or addSetting()

        Player temp = new Mario(this, new PhysicsAttributes());
        setKeyList(new KeyConfig(this,false).getKeyList());
        //add the element or the game you want the key to control
        addKeyListeners(temp);
        addKeyListeners(this);
        temp.setLocation(25, 400);
        temp.addState("hitPoints", 10);
        temp.addState("points", 0);
        temp.addState("hitPoints", 10);
        temp.addState("lives", 3);
        myPlayfield.addPlayer(temp);
        
        
        // HUD must be init after player
        myPlayfield.addHUDWidget(new TextWidget("Coins", new DataProxy(){
			public Object get() {
				return myPlayfield.getPlayer().getMyStateValue("points");
			}
        }));
        
        
        myPlayfield.addHUDWidget(new IconWidget("Lives", this.getImage("resources/life.png"), new DataProxy(){
			public Object get() {
				return myPlayfield.getPlayer().getMyStateValue("lives");
			}
        }));
        
        myPlayfield.addHUDWidget(new BarWidget("HP", new DataProxy(){
			public Object get() {
				return myPlayfield.getPlayer().getMyStateValue("hitPoints");
			}
        }));
                
        NPC koopa1 = new Koopa(this, new PhysicsAttributes());
        koopa1.addPossibleState(new MoveState(koopa1, 1, true));
        koopa1.setLocation(500, 400);
        myPlayfield.addCharacter(koopa1);
        
        NPC goomba1 = new Goomba(this, new PhysicsAttributes());
        goomba1.addPossibleState(new MoveState(goomba1, 1, true));
        goomba1.setLocation(800, 400);
        myPlayfield.addCharacter(goomba1);
        
        NPC goomba2 = new Goomba(this, new PhysicsAttributes());
        goomba2.addPossibleState(new MoveState(goomba2, 1, true));
        goomba2.setLocation(900, 400);
        myPlayfield.addCharacter(goomba2);
        
        NPC goomba3 = new Goomba(this, new PhysicsAttributes());
        goomba3.addPossibleState(new MoveState(goomba3, 1, true));
        goomba3.setLocation(1000, 400);
        myPlayfield.addCharacter(goomba3);
        
        NPC goomba4 = new Goomba(this, new PhysicsAttributes());
        goomba4.addPossibleState(new PatrolState(goomba4, 1, 325));
        goomba4.setLocation(575, 200);
        myPlayfield.addCharacter(goomba4);

        Tile temp1 = new FrictionlessDecorator(new Tile(this, new PhysicsAttributes()));
        temp1.setImages(this.getImages("resources/IceFloor.png", 1, 1));
        temp1.setLocation(600, 440);
        myPlayfield.addSetting(temp1);

        Tile temp2 = new Tile(this, new PhysicsAttributes());
        temp2.setImages(this.getImages("resources/Bar.png", 1, 1));
        temp2.setLocation(0, 440);
        myPlayfield.addSetting(temp2);

        ActionDecorator block2 = new BreakableDecorator(new Tile(this, new PhysicsAttributes()), 1);
        block2.setBottomAction(true);
        block2.setImages(this.getImages("resources/Block2Break.png", 8, 1));
        block2.setLocation(160, 200);
        myPlayfield.addSetting(block2);
        
        ActionDecorator block3 = new PushableDecorator(new Tile(this, new PhysicsAttributes()));
        block3.setRightAction(true);
        block3.setLeftAction(true);
        block3.setImages(getImages("resources/Block3.png", 1, 1));
        block3.setLocation(200, 400);
        myPlayfield.addSetting(block3);

        ItemDecorator block1 = new ItemDecorator(new Tile(this, new PhysicsAttributes()));
        block1.setBottomAction(true);
        block1.setImages(this.getImages("resources/Block1.png", 1, 1));
        block1.setLocation(100, 200);
        myPlayfield.addSetting(block1);
        
        for(int i=0; i<10; i++){
        	CollectibleItem coin = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        	coin.setImages(this.getImages("resources/Coin.png", 1, 1));
        	coin.setActive(false);
        	coin.addState("points", 3);
        	block1.addItem(coin);
        	myPlayfield.addItem(coin);
        }
        
        CollectibleItem coin2 = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        coin2.setImages(this.getImages("resources/Coin.png", 1, 1));
        coin2.setActive(true);
        coin2.setMovable(false);
        coin2.setLocation(300, 300);
        coin2.addState("points", 3);
        myPlayfield.addItem(coin2);
        
        CollectibleItem fireball = new SetInUseSetNotInUseItem(this, new PhysicsAttributes());
        fireball.setImages(this.getImages("resources/Fireball.png", 4, 1));
        fireball.setLoopAnim(true);
        fireball.setAnimate(true);
        fireball.setActive(true);
        fireball.setMovable(false);
        fireball.setLocation(350, 400);
        fireball.addState("attackPower", 2);
        myPlayfield.addItem(fireball);
        
        AutoNotInUseItem poison = new AutoInUseAutoNotInUseItem(this, new PhysicsAttributes());
        poison.setImages(this.getImages("resources/Poison.png", 1, 1));
        poison.setActive(true);
        poison.setMovable(false);
        poison.setLocation(300, 400);
        poison.setTimerStart(1000);
        poison.setTimerEnd(4000);
        poison.addState("hitPoints", -1);
        myPlayfield.addItem(poison);
        
        Tile middleBar = new MovingDecorator(new Tile(this, new PhysicsAttributes()), 260,
                240, 700, 60, 0.05);
        middleBar.setImages(getImages("resources/SmallBar.png", 1, 1));
        myPlayfield.addSetting(middleBar);

    }

    public void update (long t) {
        super.update(t);
        myPlayfield.update(t);
    }
    
    public void render(Graphics2D g) {
    	myPlayfield.render(g);
    }
    
    @KeyAnnotation(action = "ESC")
    public void pause(){
        switchToGameObject(Pause.class);
    }
    
}
