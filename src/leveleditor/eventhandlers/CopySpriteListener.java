/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor.eventhandlers;

import java.awt.Component;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceContext;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.TransferHandler;
import javax.swing.event.MouseInputListener;

import leveleditor.LevelEditor;

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

    public void mousePressed(MouseEvent e) {
	// System.out.println("pressed called");
	JLabel label = (JLabel) e.getComponent();
	TransferHandler handler = new SpriteSelectionHandler(myView);
	label.setTransferHandler(handler);
	// currentSrc = myUniqueSpriteLabelSrcMap.get(label);
	// System.out.println("preparing to copy: " + currentSrc);
	handler.exportAsDrag(label, e, DnDConstants.ACTION_COPY);
    }

}