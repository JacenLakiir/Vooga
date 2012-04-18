/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import levelio.LevelState;
import levelio.SpriteWrapper;

@SuppressWarnings("serial")
public class LevelEditor extends JFrame {
    
    protected LevelEditor myView;
    protected LevelEditorController myController;
    private Canvas myCanvas;
    private SpritePanel mySpritePanel;
    private JMenuBar myMenuBar = new JMenuBar();
    
    public LevelEditor(LevelEditorController controller) {
	myView = this;
	myController = controller;
	setTitle("Vooga Level Editor (Demo Version)");
	setLayout(new GridBagLayout());
	setUpMenu();
	setJMenuBar(myMenuBar);
	setSize(1024, 768);
	//setLocation(0, 0);
	int gridx = 0, gridy = 0, gridwidth = 1, gridheight = 1, weightx = 10,
		weighty = 1, anchor = GridBagConstraints.WEST, 
		fill = GridBagConstraints.HORIZONTAL, ipadx = 0, ipady = 0;
	Insets inset = new Insets(0, 0, 0, 0);
	GridBagConstraints constraint = new GridBagConstraints(gridx, gridy,
		gridwidth, gridheight, weightx, weighty, anchor, fill, inset,
		ipadx, ipady);
	myCanvas = new Canvas(this, constraint);
	
	constraint.gridx = 1;
	constraint.gridy = 0;
	mySpritePanel = new SpritePanel(this, constraint);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	//setLocation(0, 0);
	getContentPane().setBackground(Color.GRAY);
    }
    
    private void setUpMenu() {
	JMenu[] myMenu = { new JMenu("File"), new JMenu("Edit")};
	for (JMenu temp : myMenu) myMenuBar.add(temp);
	JMenuItem myLoad = new JMenuItem("Load");
	myLoad.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		File f = loadFile("Load Level File...");
		if (f == null) return;
		myController.initialize(f);
	    }
	});
	myMenu[0].add(myLoad);
	JMenuItem myExport = new JMenuItem("Export");
	myExport.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		File f = loadFile("Save As...");
		if (f == null) return;
		myController.export(f);
	    }
	});
	myMenu[0].add(myExport);
	JMenuItem myExit = new JMenuItem("Exit");
	myExit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		System.exit(0);
	    }
	});
	myMenu[0].add(myExit);
	JMenuItem myChangeBackground = new JMenuItem("Change Background");
	myChangeBackground.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		File f = loadFile("Select Background Image...");
		if (f == null) return;
		try {
		    myCanvas.setUpBackground(f.getCanonicalPath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	});
	myMenu[1].add(myChangeBackground);
	JMenuItem myCreateSpriteMunu = new JMenuItem("Create Sprite");
	myCreateSpriteMunu.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		File f = loadFile("Select Sprite Image...");
		if (f == null) return;
		try {
		    createSprite(f.getCanonicalPath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	});
	myMenu[1].add(myCreateSpriteMunu);
    }
    
    private File loadFile(String title) {
        JFileChooser fc = new JFileChooser(".");
        fc.setDialogTitle(title);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        else
            return null;
    }

    private void createSprite(String imagesrc) {
	SpriteEditPanel spriteeditpane = SpriteEditPanel.getInstance(myView, imagesrc);
    }
    
    protected void loadSprites(Map<Point, SpriteWrapper> spritemap) {
	myCanvas.loadSprites(spritemap);
	mySpritePanel.loadSprites(spritemap);
    }
    
    protected void prepareForSave(String path) {
	if (myCanvas.getBackgroundImageSrc() == null) return;
	HashMap<Point, SpriteWrapper> spritemap = new HashMap<Point, SpriteWrapper>();
	for (JLabel l: myCanvas.getLabelWrapperMap().keySet()) {
	    spritemap.put(l.getLocation(), 
		    new SpriteWrapper(myCanvas.getLabelWrapperMap().get(l)));
	}
	new LevelState(myCanvas.getBackgroundImageSrc(), spritemap).save(path);
    }
    
    public Canvas getCanvas() {
	return myCanvas;
    }
    
    public SpritePanel getSpritePanel() {
	return mySpritePanel;
    }
    
}