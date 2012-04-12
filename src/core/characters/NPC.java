package core.characters;

import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.Game;

import core.characters.ai.State;
import core.characters.attacks.AttackBehavior;

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
        for (State s : myPossibleStates)
            if (s.isActive())
                myCurrentStates.add(s);
            
        for (State s : myCurrentStates)
            s.execute(milliSec);
            
        super.update(milliSec);
        
        myCurrentStates.clear();
    }
    
    public void fight ()
    {
        myAttack.useAttack();
    }
    
    public void setCurrentState (State state)
    {
        myCurrentStates.clear();
        myCurrentStates.add(state);
    }
        
    public void setCurrentStates (List<State> state)
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
    
    public List<State> getCurrentStates ()
    {
        return myCurrentStates;
    }

}