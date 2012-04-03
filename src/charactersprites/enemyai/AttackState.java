package charactersprites.enemyai;

import charactersprites.Enemy;

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


    public void update (long milliSec)
    {
        // not implemented properly yet
        enemy.fight();
        
        // can switch to dead or previous state (patrol or stationary)
    }
    
}
