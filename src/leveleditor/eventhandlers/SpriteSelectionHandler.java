package leveleditor.eventhandlers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import leveleditor.LevelEditor;
import levelio.SpriteWrapper;

@SuppressWarnings("serial")
public class SpriteSelectionHandler extends TransferHandler implements
	Transferable {

    private LevelEditor myView;
    private final DataFlavor flavors[] = new DataFlavor[1];
    // private JLabel source;
    private SpriteWrapper spritewrapper;
    private DataFlavor spriteWrapperFlavor;

    public SpriteSelectionHandler(LevelEditor view) {
	myView = view;
	String spriteWrapperType = DataFlavor.javaJVMLocalObjectMimeType
		+ ";class=" + SpriteWrapper.class.getName();
	try {
	    spriteWrapperFlavor = new DataFlavor(spriteWrapperType);
	    flavors[0] = spriteWrapperFlavor;
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public int getSourceActions(JComponent c) {
	return TransferHandler.COPY;
    }

    public boolean canImport(JComponent comp, String flavor[]) {
	// System.out.println("canImport called");
	// System.out.println(myCanvaspane.getLocation());
	if (!(comp instanceof JLabel))
	    return false;
	for (int i = 0, n = flavor.length; i < n; i++)
	    for (int j = 0, m = flavors.length; j < m; j++)
		if (flavor[i].equals(flavors[j]))
		    return true;
	return false;
    }

    public Transferable createTransferable(JComponent comp) {
	// source = null;
	spritewrapper = null;
	if (comp instanceof JLabel) {
	    JLabel label = (JLabel) comp;
	    spritewrapper = myView.getSpritePanel().getUniqueLabelWrapperMap()
		    .get(label);
	    // source = label;
	    return this;
	}
	return null;
    }

    public boolean importData(JComponent comp, Transferable t) {
	// System.out.println("import called");
	// System.out.println(comp);
	if (comp instanceof JPanel) {
	    // System.out.println("check passed");
	    JLabel label = new JLabel();
	    // System.out.println(t);
	    // System.out.println("t printed");
	    if (t.isDataFlavorSupported(flavors[0])) {
		try {
		    spritewrapper = (SpriteWrapper) t
			    .getTransferData(flavors[0]);
		    ImageIcon icon = new ImageIcon(spritewrapper.getImageSrc());
		    label.setIcon(icon);
		    label.setTransferHandler(new SpriteSelectionHandler(myView));
		    label.addMouseListener(new MoveSpriteListener(myView));
		    label.addMouseMotionListener(new MoveSpriteListener(myView));
		    JPanel canvaspane = myView.getCanvas().getCanvasPane();
		    canvaspane.add(label, 0);
		    label.setBounds(canvaspane.getMousePosition().x,
			    canvaspane.getMousePosition().y,
			    icon.getIconWidth(), icon.getIconHeight());
		    // System.out.println("importing!!! " + currentSrc);
		    myView.getCanvas().getLabelWrapperMap()
			    .put(label, spritewrapper);
		    return true;
		} catch (UnsupportedFlavorException ignored) {
		    ignored.printStackTrace();
		} catch (IOException ignored) {
		    ignored.printStackTrace();
		}
	    }
	}
	return false;
    }

    public Object getTransferData(DataFlavor flavor) {
	if (isDataFlavorSupported(flavor)) {
	    return spritewrapper;
	}
	return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
	return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
	return flavor.equals(spriteWrapperFlavor);
    }
}