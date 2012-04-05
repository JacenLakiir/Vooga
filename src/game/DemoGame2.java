package game;
/**
 * @author Kuang Han
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import keyconfiguration.KeyConfig;

import mario.Mario;


import collision.CharacterPlatformCollision;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

import charactersprites.Player;
import setting.*;

public class DemoGame2 extends Game{

    PlayField        playfield;  
    Background       background;
    KeyConfig        keyConfig;
    @Override
    public void initResources() {
        
        playfield = new PlayField();
        background = new ColorBackground(Color.gray, 640, 480);
        playfield.setBackground(background);

        BufferedImage[] images = this.getImages("resources/Mario1.png", 1, 1);
        Player mario = new Mario(this);
        keyConfig = new KeyConfig(mario,this);
        mario.setKeyList(keyConfig.getInputKeyList());
        mario.setImages(images);
        mario.setLocation(25, 20);

        images = this.getImages("resources/Water2.png", 1, 1);
        Platform water = new PenetrableDecorator(new BasePlatform(this), 0.2);
        water.setDensity(1);
        water.setDragCoefficient(.2);
        water.setImages(images); 
        water.setLocation(0, 240);

        images = this.getImages("resources/Bar.png", 1, 1);
        Platform floor = new BasePlatform(this);
        floor.setImages(images);
        floor.setLocation(0, 440);
        
        Platform ceiling = new BasePlatform(this);
        ceiling.setImages(images);
        ceiling.setLocation(70, -20);
        
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
//        block1.setMovable(true);
//        block1.setDensity(0.8);
        block1.setMass(6);
        block1.setImages(images);
        block1.setLocation(100, 200);

        images = this.getImages("resources/Block2.png", 1, 1);
        Platform block2 = new BreakableDecorator(new BasePlatform(this));
//        block2.setMovable(true);
//        block2.setDensity(1.005);
        block2.setMass(6);
        block2.setImages(images);
        block2.setLocation(160, 200);

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
        

        playfield.addGroup(blocks);
        playfield.addGroup(characters);

        CharacterPlatformCollision collision = new CharacterPlatformCollision();
        playfield.addCollisionGroup(characters, blocks, collision);
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
