package leveleditor.eventhandlers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import leveleditor.LevelEditor;
import leveleditor.SpriteEditPanel;

public class MoveSpriteListener implements MouseInputListener,
	MouseMotionListener {

    private Point prel = null;
    private String imagesrc = null;
    private LevelEditor myView;

    public MoveSpriteListener(LevelEditor view) {
	myView = view;
    }
    public void mouseMoved(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
	if (!SwingUtilities.isLeftMouseButton(e))
	    return;
	Component jl = e.getComponent();
	Point p = jl.getParent().getMousePosition();
	if (p == null)
	    return;
	if (prel == null)
	    prel = jl.getMousePosition();
	Point newP = new Point(p.x - prel.x, p.y - prel.y);
	if (newP.x < 0)
	    newP.x = 0;
	if (newP.y < 0)
	    newP.y = 0;
	jl.setLocation(newP);
    }

    public void mouseReleased(MouseEvent e) {
	prel = null;
	((JLabel) e.getComponent())
		.setBorder(BorderFactory.createEmptyBorder());
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(final MouseEvent e) {
	((JLabel) e.getComponent()).setBorder(BorderFactory
		.createLineBorder(Color.YELLOW));
	imagesrc = myView.getCanvas().getLabelWrapperMap().get((JLabel) e.getComponent())
		.getImageSrc();
	if (SwingUtilities.isRightMouseButton(e)) {
	    JPopupMenu editMenu = new JPopupMenu();
	    JMenuItem myEditItem = new JMenuItem("Edit");
	    editMenu.add(myEditItem);
	    myEditItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    SpriteEditPanel.getInstance(myView, imagesrc);
		}
	    });
	    JMenuItem myDeleteItem = new JMenuItem("Delete");
	    editMenu.add(myDeleteItem);
	    myDeleteItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    myView.getCanvas().deleteSpriteLabel((JLabel) e.getComponent());
		}
	    });
	    editMenu.show(e.getComponent(), e.getX(), e.getY());
	}
    }

}