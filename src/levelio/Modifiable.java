package levelio;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Modifiable {
    
    public static enum Classification {
	PHYSICS, GAMEPLAY
    }
    public Classification classification();
}
