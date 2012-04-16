package core.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import core.keyconfiguration.KeyAnnotation;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import demo.DemoGameEngine;
/**
 * 
 * @author Hui Dong
 *
 */
public class Pause extends MenuGameObject{
    private int optionID = 0;
    DemoGameEngine engine;
    private Background background;
    private BufferedImage arrow;

    
    
    
    public Pause(GameEngine arg0) {
        super(arg0);
        engine = (DemoGameEngine) arg0;
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
            engine.nextGameID = engine.getGameID(Menu.class.getName());
        finish();        
    }

    
    
}
