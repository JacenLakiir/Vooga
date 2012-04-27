/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import io.SpriteAttribute;
import io.SpriteWrapper;
import io.annotations.DefaultPhysicsAttribute;
import io.annotations.Modifiable;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import core.physicsengine.physicsplugin.PhysicsAttributes;


@SuppressWarnings("serial")
public class LevelEditor extends JFrame {
    
    protected LevelEditor myView;
    protected LevelEditorController myController;
    private Canvas myCanvas;
    private SpritePanel mySpritePanel;
    private JMenuBar myMenuBar = new JMenuBar();
    private JTree myClassTree;
    private PhysicsAttributes myPhysicsAttributes;
    private Map<SpriteAttribute, Serializable> myDefaultPhysicsAttributeMap;
    
    public LevelEditor(LevelEditorController controller) {
	myView = this;
	myController = controller;
	myClassTree = ClassTreeUtils.getClassTree("core, demo");
	retrievePhysicsAttributes();
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
	    public void actionPerformed(ActionEvent e) {
		SpriteBuilder.getInstance(myView);
	    }
	});
	myMenu[1].add(myCreateSpriteMunu);
    }
    
    protected File loadFile(String title) {
        JFileChooser fc = new JFileChooser(".");
        fc.setDialogTitle(title);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        else
            return null;
    }

    protected void loadSprites(List<SpriteWrapper> sprites) {
	myCanvas.loadSprites(sprites);
	mySpritePanel.loadSprites(sprites);
    }
    
    public Canvas getCanvas() {
	return myCanvas;
    }
    
    public SpritePanel getSpritePanel() {
	return mySpritePanel;
    }
    
    public JTree getClassTree() {
	return myClassTree;
    }
    
    public Map<SpriteAttribute, Serializable> getDefaultPhysicsAttributesMap() {
	return myDefaultPhysicsAttributeMap;
    }
    
    public PhysicsAttributes getDefaultPhysicsAttributes() {
	return myPhysicsAttributes;
    }
    
    private void retrievePhysicsAttributes() {
	myDefaultPhysicsAttributeMap = new HashMap<SpriteAttribute, Serializable>();
	Class<?>[] clazzes = ClassTreeUtils.getClassesInPackages("core", null);
	for (Class<?> clazz: clazzes) {
	    if (clazz.isAnnotationPresent(DefaultPhysicsAttribute.class) &&
		    PhysicsAttributes.class.isAssignableFrom(clazz)) {
		try {
		    myPhysicsAttributes = (PhysicsAttributes) clazz.newInstance();
		    for (Field f: myPhysicsAttributes.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.isAnnotationPresent(Modifiable.class)) {
			    if (!f.getAnnotation(Modifiable.class).type().equals("Map") ||
				    !f.getAnnotation(Modifiable.class).classification().equals("Physics"))
				return;
			    @SuppressWarnings("unchecked")
			    Map<String, Serializable> dummymap = 
				    (Map<String, Serializable>) f.get(myPhysicsAttributes);
			    myDefaultPhysicsAttributeMap = 
				    SpriteAttribute.convertkeyToAttribute(dummymap,
					    f.getAnnotation(Modifiable.class).classification());
			    return;
			}
		    }
		} catch (InstantiationException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		}
		return;
	    }
	}
    }
    
    public void showError(String message) {
	JOptionPane.showMessageDialog(this, message, "LevelEditor Error",
		JOptionPane.ERROR_MESSAGE);
    }
    
}