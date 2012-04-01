package characterSprites.enemyAI;

import characterSprites.Enemy;

/**
 * @author Eric Mercer
 */
public class AttackState implements EnemyState
{
    
    private Enemy enemy;
    
    public AttackState (Enemy e)
    {
        enemy = e;
    }
    
}
