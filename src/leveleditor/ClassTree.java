package leveleditor;

import java.awt.Color;

import javax.swing.JTree;
import javax.swing.tree.*;

@SuppressWarnings("serial")
public class ClassTree extends JTree {

    public ClassTree(DefaultMutableTreeNode root) {
	super(root);
    }
    
    @Override
    public String convertValueToText(Object value, boolean selected,
	    boolean expanded, boolean leaf, int row, boolean hasFocus) {
	if (value != null) {
	    String sValue = 
		    ((Class<?>) ((DefaultMutableTreeNode) value).getUserObject()).getSimpleName();
	    if (sValue != null) {
		return sValue;
	    }
	}
	return "";
    }
    
}