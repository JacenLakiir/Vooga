package game;
/**
 * @author Kuang Han
 */

import items.CollectibleInstantItem;
import items.CollectibleInventoryItem;
import items.CollectibleItem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mario.Mario;

import keyconfiguration.KeyConfig;


import charactersprites.Character;
import collision.CharacterPlatformCollision;
import collision.PlayerCollectibleItemCollision;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

import charactersprites.Player;
import setting.*;
import voogaobject.GameElement;
import voogaobject.GameElementCollision;
import voogaobject.GamePlayField;

public class DemoGame2 extends Game{

    GamePlayField    playfield;  
    Background       background;
    KeyConfig        keyConfig ,keyConfig1;
    @Override
    public void initResources() {
               
        playfield = new GamePlayField();
        background = new ColorBackground(Color.gray, 640, 480);
        playfield.setBackground(background);

        BufferedImage[] images = this.getImages("resources/Mario1.png", 1, 1);
        Player mario = new Mario(this);
        keyConfig = new KeyConfig(mario,this);
        mario.setKeyList(keyConfig.getInputKeyList());
        mario.setImages(images);
        mario.setLocation(25, 20);
        
        Mario mario1 = new Mario(this);
        keyConfig1 = new KeyConfig(mario1,this);
        mario1.setKeyList(keyConfig1.getInputKeyList());
        mario1.setImages(images);
        mario1.setLocation(300, 20);

        images = this.getImages("resources/Bar.png", 1, 1);
        Platform floor = new BasePlatform(this);
        floor.setImages(images);
        floor.setLocation(0, 440);
        
        Platform ceiling = new BasePlatform(this);
        ceiling.setImages(images);
        ceiling.setLocation(70, -20);
        
        images = this.getImages("resources/Coin.png", 1, 1);
        CollectibleItem coin = new CollectibleInventoryItem(this);
        coin.setImages(images);
        coin.setLocation(260, 100);
        
        images = this.getImages("resources/SmallBar.png", 1, 1);
        Platform middleBar = new BasePlatform(this);
        middleBar.setImages(images);
        middleBar.setLocation(260, 260);

        images = this.getImages("resources/Wall.png", 1, 1);
        Platform wall1 = new BasePlatform(this);
        wall1.setImages(images);
        wall1.setLocation(0, 0);
        
        Platform wall2 = new BasePlatform(this);
        wall2.setImages(images);
        wall2.setLocation(620, 0);
        
        images = this.getImages("resources/Block1.png", 1, 1);
        Platform block1 = new ItemDecorator(new BasePlatform(this));
        block1.setMass(6);
        block1.setMovable(false);
        block1.setImages(images);
        block1.setLocation(100, 200);

        images = this.getImages("resources/Block2.png", 1, 1);
        Platform block2 = new BreakableDecorator(new BasePlatform(this));
        block2.setMass(6);
        block2.setMovable(false);
        block2.setImages(images);
        block2.setLocation(160, 200);
        
        images = this.getImages("resources/Water2.png", 1, 1);
        @SuppressWarnings("serial")
        Platform water = new BasePlatform(this)
        {
            @Override
            public void afterCollidedWith(GameElement e, int collisionSide) {
                if (e instanceof Mario) {
                    ((Mario) e).setStrength(0.2);
                }
            }
        };
        water.setPenetrable(true);
        water.setDensity(1);
        water.setDragCoefficient(.2);
        water.setImages(images); 
        water.setLocation(0, 240);

        SpriteGroup blocks = new SpriteGroup("block");
        blocks.add(water);
        blocks.add(floor);
        blocks.add(ceiling);
        blocks.add(middleBar);
        blocks.add(wall1);
        blocks.add(wall2);
        blocks.add(block1);
        blocks.add(block2);

        SpriteGroup characters = new SpriteGroup("characters");
        characters.add(mario);
//        characters.add(mario1);
        
        SpriteGroup items = new SpriteGroup("items");
        items.add(coin);

        playfield.addGroup(blocks);
        playfield.addGroup(characters);
        playfield.addGroup(items);

        GameElementCollision collision = new GameElementCollision();
        collision.addSpriteGroup(blocks);
        collision.addSpriteGroup(characters);
        
//        PlayerCollectibleItemCollision playerItemCollision = new PlayerCollectibleItemCollision();
//        playerItemCollision.addSpriteGroup(characters);
//        playerItemCollision.addSpriteGroup(items);
        
        playfield.addCollisionGroup(collision);
        playfield.addCollisionGroup(characters, items, new PlayerCollectibleItemCollision());
    }

    @Override
    public void render(Graphics2D g) {
        playfield.render(g);
    }

    @Override
    public void update(long t) {
        playfield.update(t);
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new DemoGame2(), new Dimension(640,480), false);
        game.start();
    }


}