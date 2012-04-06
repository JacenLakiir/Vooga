package charactersprites;

import java.util.ArrayList;
import java.util.List;
import charactersprites.attacks.AttackBehavior;
import charactersprites.ai.State;
import com.golden.gamedev.Game;

/**
 * @author ericmercer (JacenLakiir)
 */

@SuppressWarnings("serial")
public class NPC extends Character
{
    
    private List<State> myPossibleStates;
    private List<State> myCurrentStates;
    private AttackBehavior myAttack;
    
    public NPC (Game game)
    {
        super(game);
        myPossibleStates = new ArrayList<State>();
        myCurrentStates = new ArrayList<State>();
    }
    
    public NPC (Game game, List<State> possibleStates)
    {
        super(game);
        myPossibleStates = possibleStates;
        myCurrentStates = new ArrayList<State>();
    }
    
    public void update (long milliSec)
    {       
        myCurrentStates.clear();
        
        for (State s : myPossibleStates)
            if (s.isActive())
                myCurrentStates.add(s);
            
        for (State s : myCurrentStates)
            s.execute(milliSec);
            
        super.update(milliSec);

    }
    
    public void fight ()
    {
        myAttack.useAttack();
    }
        
    public void setState (List<State> state)
    {
        myCurrentStates.clear();
        myCurrentStates.addAll(state);
    }
    
    public void setAttack(AttackBehavior attack)
    {
        myAttack = attack;
    }
    
    public void addPossibleState (State state)
    {
        myPossibleStates.add(state);
    }
    
    public List<State> getCurrentState ()
    {
        return myCurrentStates;
    }

}