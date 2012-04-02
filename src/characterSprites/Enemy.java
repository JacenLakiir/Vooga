package characterSprites;

import characterSprites.attacks.Attack;
import characterSprites.enemyAI.AttackState;
import characterSprites.enemyAI.DeadState;
import characterSprites.enemyAI.EnemyState;
import characterSprites.enemyAI.PatrolState;
import characterSprites.enemyAI.StationaryState;
import com.golden.gamedev.Game;

/**
 * @author ericmercer (JacenLakiir)
 */
public class Enemy extends Character
{
    private EnemyState stationaryState;
    private EnemyState patrolState;
    private EnemyState attackState;
    private EnemyState deadState;
    
    private Game myGame;
    private EnemyState myState;
    private EnemyState myPreviousState;
    private Attack myAttack;
    
    private Double myStartX;
    private double myPatrolRadius;

    public Enemy (Game game, double patrolRadius)
    {
        super(game);
        
        stationaryState = new StationaryState(this);
        patrolState = new PatrolState(this);
        attackState = new AttackState(this);
        deadState = new DeadState(this);
        
        myGame = game;
        myState = (patrolRadius != 0) ? patrolState : stationaryState;
        myStartX = null;
        myPatrolRadius = patrolRadius;
    }
    
    public void update (long milliSec)
    {       
        if (myStartX == null)       myStartX = getX();
        
        myState.update(milliSec);
        super.update(milliSec);
    }
    
    public void fight ()
    {
        myAttack.useAttack();
    }
        
    public void setState (EnemyState state)
    {
        myPreviousState = myState;
        myState = state;
    }
    
    public void setAttack(Attack attack)
    {
        myAttack = attack;
    }
    
    public double getStartX ()
    {
        return myStartX;
    }
    
    public double getPatrolRadius ()
    {
        return myPatrolRadius;
    }
    
    public EnemyState getCurrentState ()
    {
        return myState;
    }
    
    public EnemyState getPreviousState ()
    {
        return myPreviousState;
    }
    
        
    /* each state needs its own getter so that transitions don't need to
     * instantiate a new state object when setting the current state
     */
  
    public EnemyState getStationaryState ()
    {
        return stationaryState;
    }

    public EnemyState getPatrolState ()
    {
        return patrolState;
    }

    public EnemyState getAttackState ()
    {
        return attackState;
    }

    public EnemyState getDeadState ()
    {
        return deadState;
    }
    
}