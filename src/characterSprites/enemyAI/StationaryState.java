package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class StationaryState implements EnemyState
{

    private Enemy enemy;
    
    public StationaryState (Enemy e)
    {
        enemy = e;
    }

    @Override
    public void update (long milliSec)
    {
        // not implemented yet
        
        // can switch to attack or dead
    }
}
