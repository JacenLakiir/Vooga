package core.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import core.keyconfiguration.KeyAnnotation;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * 
 * @author Hui Dong
 *
 */
public class Pause extends MenuGameObject{
    private Background background;
    private BufferedImage arrow;
       
    
    public Pause(GameEngine2D engine) {
        super(engine);
    }
    
    
    @Override
    public void buildMenu() {
        addOptionToMenu("Continue");
        addOptionToMenu("Restart");
        addOptionToMenu("Menu");        
        
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
        int i = 0;
        for(String name : getNameList()){
            graphic.drawString(name, 320, 240+i*20);
            i++;
        }
        graphic.drawImage(arrow, 300, 230 + getOptionID()*20, null);
    }

    @Override
    public void update(long milliSec) {
        super.update(milliSec);
      }
    
    
    @KeyAnnotation(action = "enter")
    public void nextGameObject() {
        if(getOptionID() == 0){
            getEngine().continueGame();
        }
        if(getOptionID() ==1 ){
            getEngine().restartGame();
        }
        if(getOptionID() == getNumberOfItems() - 1)
            switchToGameObject(Menu.class);
        finish();        
    }

 
    
    
}
