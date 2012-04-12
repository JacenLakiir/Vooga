/**
 * @author Glenn Rivkees (grivkees)
 */

package demo;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;





import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.background.ColorBackground;

import core.characters.NPC;
import core.characters.Player;
import core.characters.ai.MoveState;
import core.collision.CharacterPlatformCollision;
import core.collision.GameElementCollision;
import core.collision.PlayerCollectibleItemCollision;
import core.items.CollectibleInstantItem;
import core.items.CollectibleInventoryItem;
import core.items.CollectibleItem;
import core.items.CollectibleTimelapseItem;
import core.keyconfiguration.Key;
import core.keyconfiguration.KeyAnnotation;
import core.keyconfiguration.KeyConfig;
import core.playfield.AdvancedPlayField;
import core.playfield.KeepLeftFirstPlayerGameScroller;
import core.tiles.BaseTile;
import core.tiles.BreakableDecorator;
import core.tiles.ItemDecorator;
import core.tiles.MovingDecorator;
import core.tiles.Tile;

public class DemoPlayfield extends GameObject {
    private AdvancedPlayField myPlayfield;
    private List<Key> keyList;
    private GameEngine engine;
	public DemoPlayfield(GameEngine arg0) {
        super(arg0);
        engine = arg0;
    }


	public void initResources() {
		// Playfield Init
		myPlayfield = new AdvancedPlayField(10000, 500);
		myPlayfield.setBackground(new ColorBackground(Color.gray));
		myPlayfield.setGameScroller(new KeepLeftFirstPlayerGameScroller());

		// Collisions
		myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
		        myPlayfield.getSetting(), new CharacterPlatformCollision());

		myPlayfield.addCollisionGroup(myPlayfield.getPlayers(),
		        myPlayfield.getItems(), new PlayerCollectibleItemCollision());
		
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getSetting(), new CharacterPlatformCollision());
        
        myPlayfield.addCollisionGroup(myPlayfield.getCharacters(),
                myPlayfield.getCharacters(), new GameElementCollision());

		// Sprite Init / Or load funcitonality
		// SpriteGroups already exist in AdvancedPlayfield
		// use addItem(sprite), addPlayer(), addCharacter(), or addSetting()

		Player temp = new Mario(this);
        keyList = new KeyConfig(temp, this).getKeyList();
		temp.setImages(this.getImages("resources/Mario1.png", 1, 1));
		temp.setLocation(25, 20);
		temp.setMyHP(10);
		myPlayfield.addPlayer(temp);
		
		NPC goomba1 = new Goomba(this);
        goomba1.addPossibleState(new MoveState(goomba1, 1, true));
        goomba1.setImages(this.getImages("resources/Goomba.png", 1, 1));
        goomba1.setLocation(200, 20);
        goomba1.setMovable(true);
        myPlayfield.addCharacter(goomba1);

		Tile temp1 = new BaseTile(this);
		temp1.setImages(this.getImages("resources/Bar.png", 1, 1));
		temp1.setLocation(0, 440);
		myPlayfield.addSetting(temp1);

		Tile temp2 = new BaseTile(this);
		temp2.setImages(this.getImages("resources/Bar.png", 1, 1));
		temp2.setLocation(600, 440);
		myPlayfield.addSetting(temp2);

		Tile block2 = new BreakableDecorator(new BaseTile(this));
		block2.setMass(6);
		block2.setMovable(false);
		block2.setImages(this.getImages("resources/Block2Break.png", 8, 1));
		block2.setLocation(160, 200);
		myPlayfield.addSetting(block2);

		CollectibleInstantItem coin = new CollectibleInstantItem(this);
		coin.setImages(this.getImages("resources/Coin.png", 1, 1));
		coin.setActive(false);
		coin.setValue(3);
		System.out.println(coin.getAttackPower());
		myPlayfield.addItem(coin);

		CollectibleTimelapseItem poison = new CollectibleTimelapseItem(this);
		poison.setImages(this.getImages("resources/Poison.png", 1, 1));
		poison.setActive(true);
		poison.setMovable(false);
		poison.setLocation(300, 400);
		poison.setTimer(5);
		poison.setHitPoints(-1/5.0);
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

	public void update(long arg0) {
		myPlayfield.update(arg0);
		checkKeyboardInput(arg0);
	}
	
	public void checkKeyboardInput(long milliSec) {
	        for(Key key : keyList){
	            if(key.isKeyDown(milliSec)){
	                key.notifyObserver();
	            }
	        }
	}
	
	public void render(Graphics2D arg0) {
		myPlayfield.render(arg0);
	}

    @KeyAnnotation(action = "ESC")
    public void pause(){
        engine.nextGameID = GameEngine2D.PAUSE;
        finish();
    }
    
//	public static void main(String[] args) {
//		GameLoader game = new GameLoader();
//		game.setup(new DemoPlayfield(), new Dimension(640, 480), false);
//		game.start();
//	}

}
