import com.golden.gamedev.Game;


public class Enemy extends Character{
    Game myGame;
    public Enemy(Game game){
        myGame = game;
    }
    
    public void update(long milliSec){
        super.update(milliSec);
    }
}
