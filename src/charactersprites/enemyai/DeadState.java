package charactersprites.enemyai;

import charactersprites.Enemy;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class DeadState implements EnemyState
{
    private Enemy enemy;
    
    public DeadState (Enemy e)
    {
        enemy = e;
    }

    @Override
    public void update (long milliSec)
    {
        enemy.setActive(false);
    }
}
