import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import characterSprites.Character;
import characterSprites.NewtonianCollision;
import characterSprites.Player;


public class TestingGame extends Game{

    PlayField        playfield;  
    Background       background;

    @Override
    public void initResources() {
        playfield = new PlayField();
        
        background = new ColorBackground(Color.gray, 640, 480);
        playfield.setBackground(background);

        
        BufferedImage[] images = this.getImages("resources/Mario1.png", 1, 1);
        Player mario = new Player(this);
        mario.set(images, 40, 0);

        images = this.getImages("resources/Bar.png", 1, 1);
        Character floor = new Character();
        floor.setMovable(false);
        floor.set(images, 0, 420);
        
        images = this.getImages("resources/Block1.png", 1, 1);
        Character block1 = new Character();
        block1.setMovable(false);
        block1.set(images, 100, 200);
        
        images = this.getImages("resources/Block2.png", 1, 1);
        Character block2 = new Character();
        block2.setMovable(false);
        block2.set(images, 300, 200);
        
        SpriteGroup blocks = new SpriteGroup("block");
        blocks.add(floor);
        blocks.add(block1);
        blocks.add(block2);
        
        SpriteGroup players = new SpriteGroup("player");
        players.add(mario);
        
        playfield.addGroup(blocks);
        playfield.addGroup(players);
        
        NewtonianCollision collision = new NewtonianCollision();
        playfield.addCollisionGroup(players, blocks, collision);
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
        game.setup(new TestingGame(), new Dimension(640,480), false);
        game.start();
    }


}
