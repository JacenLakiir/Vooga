package core.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.keyconfiguration.KeyAnnotation;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import demo.DemoAI;
import demo.DemoHUD;
import demo.DemoPlayfield;

/**
 * 
 * @author Hui Dong
 *
 */
public class Menu extends MenuGameObject{
    private Background background;
    private BufferedImage arrow;
    private static Map<Integer, String> map = new HashMap<Integer, String>();
    public Menu(GameEngine2D engine) {
        super(engine);
    }

    @Override
    public void buildMenu() {
        addOptionToMenu("DemoAI", DemoAI.class);
        addOptionToMenu("DemoHUD", DemoHUD.class);
        addOptionToMenu("DemoPlayfield", DemoPlayfield.class);
        addOptionToMenu("Exit");
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
    public void update(long arg0) {
        super.update(arg0);
    }

    
    @KeyAnnotation(action = "enter")
    public void nextGameObject(){
        if(getOptionID() == getNumberOfItems() - 1 ){
            finish();
            return;
        }
        switchToGameObject(getNextGameObject());
        finish();
    }



}
