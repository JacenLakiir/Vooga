package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer
 */
public class PatrolState implements EnemyState
{

    private Enemy enemy;
    
    public PatrolState (Enemy e)
    {
        enemy = e;
    }
}
