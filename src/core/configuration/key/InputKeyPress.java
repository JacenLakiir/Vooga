package core.configuration.key;


/**
 * 
 * @author Hui Dong
 *
 */
public class InputKeyPress extends Key{



    @Override
    public boolean isKeyDown(long milliSec) {
  
        return getMyGame().keyPressed(Integer.parseInt(keyValue));
    }
}
