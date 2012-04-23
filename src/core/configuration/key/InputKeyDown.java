package core.configuration.key;


/**
 * 
 * @author Hui Dong
 *
 */
public class InputKeyDown extends Key{


    public boolean isKeyDown(long milliSec){
        if(isSystemOnly())
            return false;
        return getMyGame().keyDown(Integer.parseInt(keyValue));
    }
}
