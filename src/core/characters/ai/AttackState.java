package core.characters.ai;

import core.characters.Character;

public class AttackState extends State
{

    private Character beingAttacked;
    private double myAttackRange;
    
    public AttackState (Character character, Character toAttack, double attackRange)
    {
        super(character);
        beingAttacked = toAttack;
        myAttackRange = attackRange;
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myCharacter.getDistance(beingAttacked) <= myAttackRange);
    }

    @Override
    public void execute (long milliSec)
    {
        // TODO Auto-generated method stub
//        myCharacter.useWeapon();
//        beingAttacked.getDirection();
    }
    
    private double phi (double x)
    {
        return Math.exp(-x*x / 2) / Math.sqrt(2 * Math.PI);
    }

    private double phi (double x, double mu, double sigma)
    {
        return phi((x - mu) / sigma) / sigma;
    }
    
    public static void main (String[] args)
    {
        System.out.println();
    }

}
