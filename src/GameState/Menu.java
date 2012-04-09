package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class Menu extends GameObject{
    private int gameID = 0;
    GameEngine2D engine;
    private Background background;
    private BufferedImage arrow;


    public Menu(GameEngine2D engine) {
        super(engine);
        this.engine = engine;
    }

    @Override
    public void initResources() {
        background = new ImageBackground(getImage("resources/StarDust.jpg"), 640, 480);
        arrow = getImage("resources/MenuArrow.png");
    }

    @Override
    public void render(Graphics2D graphic) {
        background.render(graphic);
        graphic.setColor( Color.WHITE );

        graphic.drawString("START", 320, 240);
        graphic.drawString("EXIT", 320, 280);
        graphic.drawImage(arrow, 300, 230 + gameID*20, null);
    }

    @Override
    public void update(long arg0) {
        if(keyDown(KeyEvent.VK_ENTER)){
            enter();
        }
        if(keyDown(KeyEvent.VK_UP)){
            up();
        }
        if(keyDown(KeyEvent.VK_DOWN)){
            down();
        }
    }
    
    
    private void  down(){
        if(gameID < 1){
            gameID++;
        }
    }
    
    private void up(){
        if(gameID > 0){
            gameID--;
        }
    }
    
    private void enter(){
        System.out.println(gameID);
        engine.nextState(gameID);
        finish();
    }

}
