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

    @Override
    public void update (long milliSec)
    {
        if (enemy.getVelocity().getY() != 0)   return;
        
        int dir = enemy.getDirection();
        if (dir == -1)
        {
            if (enemy.getX() < enemy.getStartX() - enemy.getPatrolRadius())
                enemy.setDirection(1);
        }
        else if (dir == 1)
        {
            if (enemy.getX() > enemy.getStartX() + enemy.getPatrolRadius())
                enemy.setDirection(-1);
        }
        enemy.move(enemy.getDirection(), 0);
        
        // can switch to attack or dead
    }
}
