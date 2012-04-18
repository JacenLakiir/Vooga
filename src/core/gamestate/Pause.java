package core.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
    private DemoGameEngine engine;
    private Background background;
    private BufferedImage arrow;
       
    
    public Pause(GameEngine engine) {
        super(engine);
        this.engine = (DemoGameEngine) engine;
    }

    @Override
    public void initResources() {
        super.initResources();
        background = new ImageBackground(getImage("resources/StarDust.jpg"), 640, 480);
        arrow = getImage("resources/MenuArrow.png");
        numberOfItems = 2;
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
    public void update(long milliSec) {
        super.update(milliSec);
      }
    
    
    @KeyAnnotation(action = "enter")
    public void nextGameObject() {
        if(optionID ==1 || optionID == 0){
            engine.continueGame();
        }
        if(optionID != 0){
            engine.initResources();
        }
        if(optionID == numberOfItems)
            engine.nextGameID = engine.getGameID(Menu.class.getName());
        finish();        
    }

    
    
}
