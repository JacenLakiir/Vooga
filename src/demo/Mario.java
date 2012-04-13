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
//        System.out.println("Dead");
//      myGame.stop();            

    }

    public void afterHitFromRightBy (GameElement e) {
        super.afterHitFromRightBy(e);
    }
    
    @Override
    public void afterHitFromLeftBy (GameElement e) {
        super.afterHitFromLeftBy(e);
    }
    
    @Override
    public void afterHitFromTopBy (GameElement e) {
        super.afterHitFromTopBy(e);
    }
    
    public void afterHitFromRightBy (Goomba e) {
        checkDead();
    }
    
    public void afterHitFromLeftBy (Goomba e) {
        checkDead();
    }
    
    public void afterHitFromTopBy (Goomba e) {
        checkDead();
    }
    
    public void afterHitFromRightBy (Koopa k)
    {
        handleKoopaSideCollision(k);
    }
    
    public void afterHitFromLeftBy (Koopa k)
    {
        handleKoopaSideCollision(k);
    }
    
    public void afterHitFromTopBy (Koopa k)
    {
        checkDead();
    }
    
    private void handleKoopaSideCollision (Koopa k)
    {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            checkDead();
        else if (!k.isInShellState())
            checkDead();
    }

}
