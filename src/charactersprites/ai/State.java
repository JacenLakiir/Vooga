package charactersprites.ai;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public interface State
{
   
    public void execute (long milliSec);
    
    public boolean isActive ();
    
}
