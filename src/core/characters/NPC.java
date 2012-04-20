package core.characters;

import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.GameObject;

import core.characters.ai.State;

/**
 * @author ericmercer (JacenLakiir)
 */
public class NPC extends Character {

    private static final long serialVersionUID = 112309211231209215L;

    private transient List<State> myPossibleStates;
    private transient List<State> myCurrentStates;

    public NPC(GameObject game)
    {
    	super(game);
    	myPossibleStates = new ArrayList<State>();
    	myCurrentStates = new ArrayList<State>();
    }

    public NPC(GameObject game, List<State> possibleStates)
    {
    	super(game);
    	myPossibleStates = possibleStates;
    	myCurrentStates = new ArrayList<State>();
    }

    public void update(long milliSec)
    {
    	for (State s : myPossibleStates)
    	    if (s.isActive())
    		myCurrentStates.add(s);
    
    	for (State s : myCurrentStates)
    	    s.execute(milliSec);
    
    	super.update(milliSec);
    
    	myCurrentStates.clear();
    }

    public void addPossibleState (State state)
    {
        myPossibleStates.add(state);
    }

    public void deactivateAllOtherStates (State toRemainActive)
    {
    	for (State s : myPossibleStates)
    	    if (!s.equals(toRemainActive))
    		s.setActive(false);
    }

    public List<State> getPossibleStates () {
        return myPossibleStates;
    }

}