/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor.eventhandlers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.TransferHandler;

import leveleditor.LevelEditor;
import levelio.SpriteWrapper;

public class SpriteDropTargetListener extends DropTargetAdapter {

    private DataFlavor spriteWrapperFlavor;
    private LevelEditor myView;

    public SpriteDropTargetListener(LevelEditor view) {
	myView = view;
	String spriteWrapperType = DataFlavor.javaJVMLocalObjectMimeType
		+ ";class=" + SpriteWrapper.class.getName();
	try {
	    spriteWrapperFlavor = new DataFlavor(spriteWrapperType);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public void dragEnter(DropTargetDragEvent event) {}

    public void drop(DropTargetDropEvent event) {
	// System.out.println("drop called");
	event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
	Transferable transferable = event.getTransferable();
	// DataFlavor[] flavors = transferable.getTransferDataFlavors();
	if (transferable.isDataFlavorSupported(spriteWrapperFlavor)) {
	    TransferHandler handler = new SpriteSelectionHandler(myView);
	    handler.importData(myView.getCanvas().getCanvasPane(), transferable);
	}
	// System.out.println(mySpriteLabelSrcMap.keySet().size());
	event.dropComplete(true);
    }
    
}