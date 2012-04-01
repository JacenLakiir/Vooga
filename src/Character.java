import java.awt.image.BufferedImage;



@SuppressWarnings("serial")
public class Character extends NewtonianSprite{
    
    public void set(BufferedImage[] images, double x, double y) {
        this.setImages(images);
        this.setLocation(x, y);
    }
    
    @Override
    public void update(long milliSec) {
        
        super.update(milliSec);
    }
}
