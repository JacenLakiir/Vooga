package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import keyconfiguration.KeyConfig;

import mario.Goomba;
import mario.Mario;

import setting.*;
import voogaobject.MergedCollision;
import voogaobject.GamePlayField;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

import charactersprites.NPC;
import charactersprites.Player;
import charactersprites.ai.MoveState;
import charactersprites.ai.PatrolState;

/**
 * @author Eric Mercer (JacenLakiir)
 * 
 * For testing NPC AI
 */
public class DemoGame4 extends Game{

    GamePlayField    playfield;  
    Background       background;
    KeyConfig        keyConfig;
    
    @Override
    public void initResources() { 
        playfield = new GamePlayField();
        
        background = new ColorBackground(Color.gray, 640, 480);
        playfield.setBackground(background);
        
        BufferedImage[] images = this.getImages("resources/Mario1.png", 1, 1);
        Player mario = new Mario(this);
        keyConfig = new KeyConfig(mario,this);
        keyConfig.parseKeyConfig("configurations/keyConfig.json");
        mario.setKeyList(keyConfig.getInputKeyList());
        mario.setImages(images);
        mario.setLocation(25, 20);
        
        images = this.getImages("resources/Goomba.png", 1, 1);
        NPC goomba1 = new Goomba(this);
        goomba1.setImages(images);
        goomba1.setLocation(350, 20);
        goomba1.setMovable(true);
        
        NPC goomba2 = new Goomba(this);
        goomba2.addPossibleState(new PatrolState(goomba2, 75));
        goomba2.setImages(images);
        goomba2.setLocation(500, 20);
        goomba2.setMovable(true);
        
        NPC goomba3 = new Goomba(this);
        goomba3.addPossibleState(new MoveState(goomba3, 1, true));
        goomba3.setImages(images);
        goomba3.setLocation(300, 20);
        goomba3.setMovable(true);
        
        images = this.getImages("resources/Bar.png", 1, 1);
        Platform floor = new BasePlatform(this);
        floor.setImages(images);
        floor.setLocation(0, 440);
        
        Platform ceiling = new BasePlatform(this);
        ceiling.setImages(images);
        ceiling.setLocation(70, -20);

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
        
        images = this.getImages("resources/Wall.png", 1, 1);
        Platform wall1 = new BasePlatform(this);
        wall1.setImages(images);
        wall1.setLocation(0, 0);
        
        Platform wall2 = new BasePlatform(this);
        wall2.setImages(images);
        wall2.setLocation(620, 0);

        SpriteGroup blocks = new SpriteGroup("block");
        blocks.add(floor);
        blocks.add(ceiling);
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(wall1);
        blocks.add(wall2);
        
        SpriteGroup characters = new SpriteGroup("characters");
        characters.add(mario);
        characters.add(goomba1);
        characters.add(goomba2);
        characters.add(goomba3);
        
        playfield.addGroup(blocks);
        playfield.addGroup(characters);
        
        MergedCollision collision = new MergedCollision();
        collision.addSpriteGroup(blocks);
        collision.addSpriteGroup(characters);
        
        playfield.addCollisionGroup(collision);
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
        game.setup(new DemoGame4(), new Dimension(640,480), false);
        game.start();
    }


}