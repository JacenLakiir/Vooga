package leveleditor.sandbox;
import game.SpriteWrapper;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class TestGsonSprite {
    
    public TestGsonSprite() {
	    SpriteWrapper test = null;
	    test = new SpriteWrapper(new Sprite(), "resources/W-Gundam.png");
	   //Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    Gson gson = new Gson();
	    String jsonString = gson.toJson(test);
	    System.out.println("Generated json text: " + jsonString);
	    //Type spriteType = new TypeToken<Sprite>(){}.getType();
	    SpriteWrapper testresume = gson.fromJson(jsonString, SpriteWrapper.class);
	    testresume.reconstruct();
	    System.out.println(testresume.getSprite().getImage());
    }
    
    public static void main(String[] args) {
	new TestGsonSprite();
    }

}
