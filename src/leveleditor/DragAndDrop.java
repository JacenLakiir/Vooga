package leveleditor;
import java.awt.*;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.event.*;

public class DragAndDrop extends JFrame {
    private DragAndDrop myInstance;
    public JPanel canvaspane;
    private JMenu[] myMenu = { new JMenu("File"), new JMenu("Edit"), };
    private JMenuItem[] myFileItem = { new JMenuItem("Open"),
	    new JMenuItem("Exit"), };
    private JMenuBar myMenuBar = new JMenuBar();
    private JFileChooser myFileChooser = new JFileChooser();

    public DragAndDrop() {
	setGlassPane(new JPanel());
	this.getGlassPane().setSize(1024, 768);
	((JComponent)this.getGlassPane()).setOpaque(false);
	((JComponent)this.getGlassPane()).setLayout(null);
	DragSource.getDefaultDragSource().addDragSourceMotionListener(
		new DragSourceMotionListener() {
		    @Override
		    public void dragMouseMoved(DragSourceDragEvent e) {
			Component jl = ((DragSourceContext) e.getSource())
				.getComponent();
			System.out.println("jl is " + jl);
			System.out.println("jl parent is " + jl.getParent());
			Point newP = myInstance.getGlassPane().getMousePosition();
			System.out.println(newP);
			if (newP == null || newP.x < 0 || newP.y < 0) return;
			jl.setLocation(myInstance.getGlassPane().getMousePosition());
			jl.getParent().repaint();
			System.out.println(jl.getLocation());
			myInstance.getGlassPane().setVisible(false);
		    }
		});
	this.setLayout(new GridBagLayout());
	myInstance = this;
	for (JMenuItem item : myFileItem) {
	    myMenu[0].add(item);
	    // item.addActionListener(this);
	}
	for (JMenu temp : myMenu) {
	    myMenuBar.add(temp);
	}
	this.setJMenuBar(myMenuBar);
	this.setSize(1024, 768);
	this.setLocation(0, 0);

	int gridx = 0;
	int gridy = 0;
	int gridwidth = 1;
	int gridheight = 1;
	int weightx = 10;
	int weighty = 1;
	int anchor = GridBagConstraints.WEST;
	int fill = GridBagConstraints.HORIZONTAL;
	Insets inset = new Insets(0, 0, 0, 0);
	int ipadx = 0;
	int ipady = 0;
	GridBagConstraints constraints = new GridBagConstraints(gridx, gridy,
		gridwidth, gridheight, weightx, weighty, anchor, fill, inset,
		ipadx, ipady);
	this.setLayout(new GridBagLayout());

	ImageIcon icon = new ImageIcon("./StarDust.jpg");
	JLabel imagelabel = new JLabel(icon);
	JScrollPane canvas = new JScrollPane();
	canvas.setPreferredSize(new Dimension(800, 600));
	canvas.setLayout(new ScrollPaneLayout());
	this.add(canvas, constraints, 0);
	canvaspane = new JPanel();
	canvaspane.setLayout(null);
	canvaspane.setPreferredSize(new Dimension(2048, 600));
	new DropTarget(canvaspane, DnDConstants.ACTION_COPY_OR_MOVE,
		new ImageDropTargetListener());
	canvaspane.setLocation(0, 0);
	canvaspane.setEnabled(true);
	canvas.getViewport().add(canvaspane);

	constraints.gridx = 1;
	constraints.gridy = 0;

	DragObjectListener listener = new DragObjectListener();
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(2, 1));
	panel.setLocation(0, 0);
	panel.setSize(800, 600);
	panel.setOpaque(false);
	JButton b = new JButton("asdf");
	b.setLocation(500, 300);
	panel.add(b);

	JLabel gundamlabel = new JLabel(new ImageIcon("./W-Gundam.png"));
	canvaspane.add(imagelabel);
	imagelabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
	gundamlabel.setBounds(200, 200, 200, 200);
	gundamlabel.addMouseListener(listener);
	this.add(gundamlabel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setLocation(0, 0);
	System.out.println(this.getLocation());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	new DragAndDrop();
    }

    private class DragObjectListener implements MouseInputListener {

	Point p = new Point(0, 0);

	public DragObjectListener() {}

	public void mouseMoved(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
	    if (SwingUtilities.isRightMouseButton(e)) {
		e.getSource();
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
	    System.out.println("pressed called");
	    JComponent com = (JComponent) e.getSource();
	    TransferHandler handler = new ImageSelection();
	    com.setTransferHandler(handler);
	    handler.exportAsDrag(com, e, DnDConstants.ACTION_COPY);
	}

    }
	private JLabel olddragged;

    private class ImageDropTargetListener extends DropTargetAdapter {
	Point p = new Point(0, 0);
	private boolean isCopy = false;
	@Override
	public void dragEnter(DropTargetDragEvent event) {}

	public void drop(DropTargetDropEvent event) {
	    System.out.println("drop called");
	    myInstance.getGlassPane().setVisible(false);
	    event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
	    Transferable transferable = event.getTransferable();
	    DataFlavor[] flavors = transferable.getTransferDataFlavors();
	    if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
		System.out.println("is flavor");
		TransferHandler handler = new ImageSelection();
		handler.importData(canvaspane, transferable);
	    }
	    if (olddragged != null) {
    		olddragged.setVisible(false);
    		canvaspane.remove(olddragged);
	    }
	    olddragged = null;
	    event.dropComplete(true);
	}
    }

    public class ImageSelection extends TransferHandler implements Transferable {

	private final DataFlavor flavors[] = { DataFlavor.imageFlavor };

	private JLabel source;

	private Image image;

	public int getSourceActions(JComponent c) {
	    return TransferHandler.COPY | TransferHandler.MOVE;
	}

	public boolean canImport(JComponent comp, DataFlavor flavor[]) {
	    System.out.println("canImport called");
	    System.out.println(canvaspane.getLocation());
	    if (!(comp instanceof JLabel)) {
		return false;
	    }
	    for (int i = 0, n = flavor.length; i < n; i++) {
		for (int j = 0, m = flavors.length; j < m; j++) {
		    if (flavor[i].equals(flavors[j])) {
			return true;
		    }
		}
	    }
	    return false;
	}

	public Transferable createTransferable(JComponent comp) {
	    source = null;
	    image = null;

	    if (comp instanceof JLabel) {
		JLabel label = (JLabel) comp;
		Icon icon = label.getIcon();
		if (icon instanceof ImageIcon) {
		    image = ((ImageIcon) icon).getImage();
		    source = label;
		    return this;
		}
	    }
	    return null;
	}

	
	public boolean importData(JComponent comp, Transferable t) {
	    System.out.println("import called");
	    System.out.println(comp);
	    if (comp instanceof JPanel) {
		System.out.println("check passed");
		JLabel label = new JLabel();
		JPanel pane = (JPanel) comp;
		System.out.println(t);
		System.out.println("t printed");
		if (t.isDataFlavorSupported(flavors[0])) {
		    try {
			image = (Image) t.getTransferData(flavors[0]);
			ImageIcon icon = new ImageIcon(image);
			System.out.println(image.getWidth(null));
			label.setIcon(icon);
			label.setTransferHandler(new ImageSelection());
			label.addMouseListener(new MouseAdapter() {

			    @Override
			    public void mousePressed(MouseEvent e) {
				System.out.println("mouse press called");
				JComponent com = (JComponent) e.getSource();
				if (!(com instanceof JLabel)) return;
				System.out.println(com);
				TransferHandler handler = new ImageSelection();
				com.setTransferHandler(handler);
				handler.exportAsDrag(com, e,
					TransferHandler.MOVE);
				olddragged = (JLabel) com;
			    }
			});
			canvaspane.add(label, 0);
			Point p = MouseInfo.getPointerInfo().getLocation();
			Point panep = pane.getLocationOnScreen();
			System.out.println("p y is: " + p.y);
			System.out.println("pane y is: " + pane.getY());
			label.setBounds(canvaspane.getMousePosition().x,
				canvaspane.getMousePosition().y,
				image.getWidth(null), image.getHeight(null));
			System.out.println("set label");
			
			
			
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
		return image;
	    }
	    return null;
	}

	public DataFlavor[] getTransferDataFlavors() {
	    return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
	    return flavor.equals(DataFlavor.imageFlavor);
	}
    }
    
}