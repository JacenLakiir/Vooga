package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import keyconfiguration.KeyAnnotation;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
/**
 * 
 * @author Hui Dong
 *
 */
public class Pause extends MenuGameObject{
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    static{
        map.put(0, GameEngine2D.GAME);
        map.put(1, GameEngine2D.GAME);
        map.put(2, GameEngine2D.MENU);
    }
    private int optionID = 0;
    GameEngine2D engine;
    private Background background;
    private BufferedImage arrow;

    
    
    
    public Pause(GameEngine arg0) {
        super(arg0);
        engine = (GameEngine2D) arg0;
    }

    @Override
    public void initResources() {
        super.initResources();
        background = new ImageBackground(getImage("resources/StarDust.jpg"), 640, 480);
        arrow = getImage("resources/MenuArrow.png");
    }

    @Override
    public void render(Graphics2D graphic) {
        background.render(graphic);
        graphic.setColor( Color.WHITE );

        graphic.drawString("CONTINUE", 320, 240);
        graphic.drawString("RESTART", 320, 260);
        graphic.drawString("Menu", 320, 280);        
        graphic.drawImage(arrow, 300, 230 + optionID*20, null);
    }

    @Override
    public void update(long arg0) {
        super.update(arg0);
      }

    
    @KeyAnnotation(action = "down")
    public void  down(){
        if(optionID < 2){
            optionID++;
        }
    }
    
    
    @KeyAnnotation(action = "up")
    public void up(){
        if(optionID > 0){
            optionID--;
        }
    }
    
    @KeyAnnotation(action = "enter")
    public void nextGameObject() {
        if(optionID ==1 || optionID == 0){
            engine.continueGame();
        }
        if(optionID != 0){
            engine.initResources();
        }
        if(optionID == 2)
            engine.nextGameID = GameEngine2D.MENU;
        finish();        
    }

    
    
}
