/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor.eventhandlers;

import io.SpriteWrapper;
import java.awt.Component;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.MouseInputListener;
import leveleditor.LevelEditor;
import leveleditor.SpriteEditor;

public class CopySpriteListener implements MouseInputListener {

    private LevelEditor myView;
    
    public CopySpriteListener(LevelEditor view) {
	myView = view;
    }
    
    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
	Component jl = ((DragSourceContext) e.getSource()).getComponent();
	jl.setLocation(jl.getParent().getMousePosition());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(final MouseEvent e) {
	JLabel label = (JLabel) e.getComponent();
	if (SwingUtilities.isRightMouseButton(e)) {
	    JPopupMenu editMenu = new JPopupMenu();
	    JMenuItem myEditItem = new JMenuItem("Edit");
	    editMenu.add(myEditItem);
	    myEditItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    JLabel label =  (JLabel) e.getComponent();
		    SpriteWrapper toedit = myView.getSpritePanel()
			    .getUniqueLabelWrapperMap().get(label);
		    SpriteEditor.getInstance(myView, toedit);
		}
	    });
	    JMenuItem myDeleteItem = new JMenuItem("Delete");
	    editMenu.add(myDeleteItem);
	    myDeleteItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    myView.getSpritePanel().deleteSpriteLabel((JLabel) e.getComponent());
		}
	    });
	    editMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	else {
	    TransferHandler handler = new SpriteCreateTransferHandler(myView);
	    label.setTransferHandler(handler);
	    handler.exportAsDrag(label, e, DnDConstants.ACTION_COPY);
	}
    }

}