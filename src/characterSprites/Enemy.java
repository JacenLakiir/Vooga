package characterSprites;
import characterSprites.enemyAI.AttackState;
import characterSprites.enemyAI.DeadState;
import characterSprites.enemyAI.EnemyState;
import characterSprites.enemyAI.PatrolState;
import characterSprites.enemyAI.StandState;
import com.golden.gamedev.Game;


public class Enemy extends Character
{
    EnemyState standState;
    EnemyState patrolState;
    EnemyState attackState;
    EnemyState deadState;
    
    EnemyState myState = standState;
    
    Game myGame;

    public Enemy (Game game)
    {
        myGame = game;
        
        standState = new StandState(this);
        patrolState = new PatrolState(this);
        attackState = new AttackState(this);
        deadState = new DeadState(this);
    }
    
    public void update (long milliSec)
    {
        super.update(milliSec);
    }
    
    public void setState (EnemyState state)
    {
        myState = state;
    }
}
