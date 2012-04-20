package leveleditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.golden.gamedev.object.Sprite;

import leveleditor.eventhandlers.MoveSpriteListener;
import leveleditor.eventhandlers.SpriteDropTargetListener;
import leveleditor.eventhandlers.SpriteSelectionHandler;
import levelio.SpriteWrapper;

@SuppressWarnings("serial")
public class Canvas extends JScrollPane {
    
    private Map<JLabel, SpriteWrapper> myLabelWrapperMap;
    private String myBackGroundImgSrc;
    private JLabel myBackGroundLabel;
    private JPanel myCanvasPane;
    private LevelEditor myView;

    public Canvas(LevelEditor view, GridBagConstraints constraint) {
	setPreferredSize(new Dimension(800, 600));
	myView = view;
	myView.add(this, constraint, 0);
	myCanvasPane = new JPanel();
	myCanvasPane.setLayout(null);
	myCanvasPane.setBackground(Color.BLACK);
	Border border = BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
		"Canvas");
	setBorder(border);
	getViewport().add(myCanvasPane);
	myLabelWrapperMap = new HashMap<JLabel, SpriteWrapper>();
    }

    protected void setUpBackground(String imagesrc) {
	if (myBackGroundLabel != null) {
	    myBackGroundLabel.setVisible(false);
	    myCanvasPane.remove(myBackGroundLabel);
	    myCanvasPane.revalidate();
	} else
	    myBackGroundLabel = new JLabel();
	ImageIcon icon = new ImageIcon(imagesrc);
	myBackGroundLabel = new JLabel(icon);
	myCanvasPane.setPreferredSize(new Dimension(icon.getIconWidth(), icon
		.getIconHeight()));
	new DropTarget(myCanvasPane, DnDConstants.ACTION_COPY_OR_MOVE,
		new SpriteDropTargetListener(myView));
	myCanvasPane.setLocation(0, 0);
	myCanvasPane.add(myBackGroundLabel);
	myBackGroundLabel.setBounds(0, 0, icon.getIconWidth(),
		icon.getIconHeight());
	myBackGroundImgSrc = imagesrc;
    }
    
    public void deleteSpriteLabel(JLabel l) {
	l.setVisible(false);
	myCanvasPane.remove(l);
	myCanvasPane.revalidate();
	myLabelWrapperMap.remove(l);
    }
    
    public Map<JLabel, SpriteWrapper> getLabelWrapperMap() {
	return myLabelWrapperMap;
    }
    
    protected String getBackgroundImageSrc() {
	return myBackGroundImgSrc;
    }
    
    protected void loadSprites(HashMap<Point, SpriteWrapper> spritemap) {
	if (myLabelWrapperMap != null) {
	    for (JLabel l: myLabelWrapperMap.keySet()) {
		l.setVisible(false);
		//System.out.println("clearing out l!!!!!!!!");
		myCanvasPane.remove(l);
	    }
	    myLabelWrapperMap.clear();
	    myCanvasPane.revalidate();
	}
	else myLabelWrapperMap = new HashMap<JLabel, SpriteWrapper>();
	for (Point p: spritemap.keySet()) {
	    Sprite sp = spritemap.get(p).getSprite();
	    BufferedImage currentImage = sp.getImage();
	    JLabel label = new JLabel();
	    ImageIcon icon = new ImageIcon(currentImage);
	    label.setIcon(icon);
	    label.setTransferHandler(new SpriteSelectionHandler(myView));
	    label.addMouseListener(new MoveSpriteListener(myView));
	    label.addMouseMotionListener(new MoveSpriteListener(myView));
	    myCanvasPane.add(label, 0);
	    label.setBounds(p.x, p.y,
		   currentImage.getWidth(), currentImage.getHeight());
	    myLabelWrapperMap.put(label, spritemap.get(p));
	}
    }
    
    public JPanel getCanvasPane() {
	return myCanvasPane;
    }
}