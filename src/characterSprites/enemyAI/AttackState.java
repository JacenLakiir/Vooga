package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class AttackState implements EnemyState
{
    private Enemy enemy;
    
    public AttackState (Enemy e)
    {
        enemy = e;
    }

    @Override
    public void update (long milliSec)
    {
        // not implemented properly yet
        enemy.fight();
        
        // can switch to dead or previous state (patrol or stationary)
    }
    
}
