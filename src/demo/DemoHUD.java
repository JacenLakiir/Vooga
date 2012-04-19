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
import core.items.CollectibleInstantItem;
import core.items.CollectibleTimelapseItem;
import core.keyconfiguration.KeyAnnotation;
import core.keyconfiguration.KeyConfig;
import core.playfield.AdvancedPlayField;
import core.playfield.hud.DataProxy;
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
        myPlayfield = new AdvancedPlayField(1000, 500);
        myPlayfield.setBackground(new ColorBackground(Color.gray));
        myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());
        
        
        myPlayfield.addHUDWidget(new TextWidget("Coins", new DataProxy(){
			public double getDouble() {
				return myPlayfield.getPlayer().getMyStateValue("points");
			}
        }));
        
        myPlayfield.addHUDWidget(new TextWidget("Level", new DataProxy(){
			public double getDouble() {
				return myPlayfield.getPlayer().getMyStateValue("level");
			}
        }));

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

        Player temp = new Mario(this);
        keyList = new KeyConfig(this,false).getKeyList();
        //add the element or the game you want the key to control
        addKeyListeners(temp);
        addKeyListeners(this);
        temp.setImages(this.getImages("resources/Mario1.png", 1, 1));
        temp.setLocation(25, 400);
        temp.setMyHP(10);
        myPlayfield.addPlayer(temp);
        
        NPC koopa1 = new Koopa(this);
        koopa1.addPossibleState(new MoveState(koopa1, 1, true));
        koopa1.setImages(this.getImages("resources/Koopa.png", 1, 1));
        koopa1.setLocation(500, 400);
        koopa1.setMovable(true);
        myPlayfield.addCharacter(koopa1);
        
        NPC goomba1 = new Goomba(this);
        goomba1.addPossibleState(new MoveState(goomba1, 1, true));
        goomba1.setImages(this.getImages("resources/Goomba.png", 1, 1));
        goomba1.setLocation(800, 400);
        goomba1.setMovable(true);
        myPlayfield.addCharacter(goomba1);
        
        NPC goomba2 = new Goomba(this);
        goomba2.addPossibleState(new MoveState(goomba2, 1, true));
        goomba2.setImages(this.getImages("resources/Goomba.png", 1, 1));
        goomba2.setLocation(900, 400);
        goomba2.setMovable(true);
        myPlayfield.addCharacter(goomba2);
        
        NPC goomba3 = new Goomba(this);
        goomba3.addPossibleState(new MoveState(goomba3, 1, true));
        goomba3.setImages(this.getImages("resources/Goomba.png", 1, 1));
        goomba3.setLocation(1000, 400);
        goomba3.setMovable(true);
        myPlayfield.addCharacter(goomba3);
        
        NPC goomba4 = new Goomba(this);
        goomba4.addPossibleState(new PatrolState(goomba4, 1, 325));
        goomba4.setImages(this.getImages("resources/Goomba.png", 1, 1));
        goomba4.setLocation(575, 200);
        goomba4.setMovable(true);
        myPlayfield.addCharacter(goomba4);

        Tile temp1 = new FrictionlessDecorator(new BaseTile(this));
        temp1.setImages(this.getImages("resources/Bar.png", 1, 1));
        temp1.setLocation(0, 440);
        myPlayfield.addSetting(temp1);

        Tile temp2 = new BaseTile(this);
        temp2.setImages(this.getImages("resources/Bar.png", 1, 1));
        temp2.setLocation(600, 440);
        myPlayfield.addSetting(temp2);

        Tile block2 = new BreakableDecorator(new BaseTile(this));
        block2.setImages(this.getImages("resources/Block2Break.png", 8, 1));
        block2.setLocation(160, 200);
        myPlayfield.addSetting(block2);
        
        Tile block3 = new PushableDecorator(new BaseTile(this));
        block3.setImages(getImages("resources/Block3.png", 1, 1));
        block3.setLocation(200, 400);
        myPlayfield.addSetting(block3);

        CollectibleInstantItem coin = new CollectibleInstantItem(this);
        coin.setImages(this.getImages("resources/Coin.png", 1, 1));
        coin.setActive(false);
        coin.addState("points", 3);
        myPlayfield.addItem(coin);

        CollectibleTimelapseItem poison = new CollectibleTimelapseItem(this);
        poison.setImages(this.getImages("resources/Poison.png", 1, 1));
        poison.setActive(true);
        poison.setMovable(false);
        poison.setLocation(300, 400);
        poison.setTimerStart(1000);
        poison.setTimerEnd(4000);
        poison.addState("hitPoints", -1);
        myPlayfield.addItem(poison);
        
        ItemDecorator block1 = new ItemDecorator(new BaseTile(this));
        block1.setMass(6);
        block1.setMovable(false);
        block1.setImages(this.getImages("resources/Block1.png", 1, 1));
        block1.setLocation(100, 200);
        block1.addItem(coin);
        myPlayfield.addSetting(block1);
        
        Tile middleBar = new MovingDecorator(new BaseTile(this), 260,
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
