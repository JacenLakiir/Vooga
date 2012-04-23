package core.configuration.mouse;
import com.golden.gamedev.GameObject;

import core.characters.GameElement;

public class MouseInput extends Mouse{
    private GameElement element;
    
    public MouseInput(GameObject game, GameElement element, String action){
        super(game, action);
        this.element = element;
    }

    @Override
    public boolean isMouseClicked() {
        int x1 = (int) element.getX();
        int y1 = (int) element.getY();
        int x2 = element.getWidth() + x1;
        int y2 = y1 +  element.getHeight();
        GameObject game = getGame();
        int x = game.getMouseX();
        int y = game.getMouseY();
        if(game.click()){
            if(x<x1||x>x2)
                return false;
            if(y<y1||y>y2)
                return false;
            return true;
        }
        return false;
    }


    
    
}
