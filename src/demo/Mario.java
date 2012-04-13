package demo;

import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.Player;

/**
 * @author Kuang Han
 */
@SuppressWarnings("serial")
public class Mario extends Player{

    public Mario(GameObject game) {
        super(game);
        resetStrength();    
    }

    @Override
    public void update(long t) {
        super.update(t);
        resetStrength();    
    }

    public void resetStrength() {
        strengthUp = 2;
        strengthDown = 0;
        strengthLeft = strengthRight = 0.5;
    }

    @Override
    public void specialSkill() {        
    }

    @Override
    public void checkDead() {
    	
//        if (this.getX()<-10 || this.getX()>650 || this.getY()>500) {
//            System.out.println("Dead");
//            myGame.stop();
//            return;
//        }
    }
    
    @Override
    public void afterHitFromRightBy (GameElement e)
    {
        if (e instanceof Goomba)
        {
            System.out.println("Dead");
            return;
        }
        if (e instanceof Koopa)
        {
            handleKoopaSideCollision((Koopa) e);
            return;
        }
    }
    
    @Override
    public void afterHitFromLeftBy (GameElement e)
    {
        if (e instanceof Goomba)
        {
            System.out.println("Dead");
            return;
        }
        if (e instanceof Koopa)
        {
            handleKoopaSideCollision((Koopa) e);
            return;
        }
    }
    
    @Override
    public void afterHitFromTopBy (GameElement e)
    {
        if (e instanceof Goomba || e instanceof Koopa)
        {
            System.out.println("Dead");
            return;
        }
    }
    
    private void handleKoopaSideCollision (Koopa k)
    {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            System.out.println("Dead");
        else if (!k.isInShellState())
            System.out.println("Dead");
    }

}
