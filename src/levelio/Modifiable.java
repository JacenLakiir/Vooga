package levelio;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Modifiable {
    
    public static enum classification {
	PHYSICS, GAMEPLAY
    }
    public classification classification();
}
