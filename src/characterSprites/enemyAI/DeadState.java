package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer
 */
public class DeadState implements EnemyState
{
    private Enemy enemy;
    
    public DeadState (Enemy e)
    {
        enemy = e;
    }
}
