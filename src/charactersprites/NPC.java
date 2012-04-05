package charactersprites;

import java.util.ArrayList;
import java.util.List;
import charactersprites.attacks.AttackBehavior;
import charactersprites.ai.AttackState;
import charactersprites.ai.DeadState;
import charactersprites.ai.EvasiveState;
import charactersprites.ai.State;
import charactersprites.ai.PatrolState;
import charactersprites.ai.StationaryState;
import com.golden.gamedev.Game;

/**
 * @author ericmercer (JacenLakiir)
 */

@SuppressWarnings("serial")
public class NPC extends Character
{
    private State stationaryState;
    private State patrolState;
    private State attackState;
    private State evasiveState;
    private State deadState;
    
    private List<State> myState;
    private List<State> myPreviousState;
    private AttackBehavior myAttack;
    
    private Double myStartX;
    private double myPatrolRadius;

    public NPC (Game game, double patrolRadius)
    {
        super(game);
        
        stationaryState = new StationaryState(this);
        patrolState = new PatrolState(this);
        attackState = new AttackState(this);
        deadState = new DeadState(this);
        evasiveState = new EvasiveState(this);
        
        myState = new ArrayList<State>();
        myPreviousState = new ArrayList<State>();
        myState.add(patrolRadius != 0 ? patrolState : stationaryState);
        myStartX = null;
        myPatrolRadius = patrolRadius;
    }
    
    public void update (long milliSec)
    {       
        if (myStartX == null)       myStartX = getX();
        
        for (State s : myState)
            s.execute(milliSec);
        super.update(milliSec);
    }
    
    public void fight ()
    {
        myAttack.useAttack();
    }
        
    public void setState (List<State> state)
    {
        myPreviousState = myState;
        myState.clear();
        myState.addAll(state);
    }
    
    public void setAttack(AttackBehavior attack)
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
    
    public List<State> getCurrentState ()
    {
        return myState;
    }
    
    public List<State> getPreviousState ()
    {
        return myPreviousState;
    }
    
        
    /* each state needs its own getter so that transitions don't need to
     * instantiate a new state object when setting the current state
     */
  
    public State getStationaryState ()
    {
        return stationaryState;
    }

    public State getPatrolState ()
    {
        return patrolState;
    }

    public State getAttackState ()
    {
        return attackState;
    }

    public State getDeadState ()
    {
        return deadState;
    }
    
    public State getEvasiveState ()
    {
        return evasiveState;
    }
    
}