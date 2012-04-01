package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer
 */
public class StandState implements EnemyState
{

    private Enemy enemy;
    
    public StandState (Enemy e)
    {
        enemy = e;
    }
}
