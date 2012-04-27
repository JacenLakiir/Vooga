/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import io.SpriteWrapper;
import io.SpriteWrapper.SpriteGroupIdentifier;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsAttributes;


@SuppressWarnings("serial")
public class SpriteBuilder extends JFrame {
    
    private LevelEditor myView;
    private static SpriteBuilder myInstance;
    private JTextField namefield;
    private String myImageSrc;
    private JLabel imagelabel;
    private ButtonGroup myTypeGroup;
    private JRadioButton myPlayerButton;
    private JRadioButton myCharacterButton;
    private JRadioButton mySettingButton;
    private JRadioButton myItemButton;
    private Map<JRadioButton, SpriteGroupIdentifier> myTypeMap;
    
    public static SpriteBuilder getInstance(LevelEditor view) {
	if (myInstance != null)
	    myInstance.dispose();
	myInstance = new SpriteBuilder(view);
	myInstance.setVisible(true);
	return myInstance;
    }
    
    private SpriteBuilder(LevelEditor view) {
	myView = view;
	setSize(800, 400);
	setLocationRelativeTo(view);
	setTitle("Build Sprite");
	setLayout(new GridLayout(1, 2));
	JPanel leftpanel = new JPanel();
	leftpanel.setLayout(new GridLayout(2, 1));
	imagelabel = new JLabel(new ImageIcon());
   	Border imageborder = BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Image");
   	//imagepanel.setBorder(imageborder);
   	//imagepanel.setBackground(Color.black);
	//imagepanel.add(imagelabel);
   	imagelabel.setBorder(imageborder);
	leftpanel.add(imagelabel);
	JTree classtree = myView.getClassTree();
	JScrollPane treepane = new JScrollPane(classtree);
	treepane.setBorder(BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Java Class"));
   	leftpanel.add(treepane);
	add(leftpanel);
	JPanel rightpanel = new JPanel();
	rightpanel.setLayout(new GridLayout(3, 1));
	add(rightpanel);
	myPlayerButton = new JRadioButton("Player");
	myCharacterButton = new JRadioButton("Character");
	mySettingButton = new JRadioButton("Setting");
	myItemButton = new JRadioButton("Item");
   	myTypeGroup = new ButtonGroup();
   	myTypeGroup.add(myPlayerButton);
   	myTypeGroup.add(myCharacterButton);
   	myTypeGroup.add(mySettingButton);
   	myTypeGroup.add(myItemButton);
   	myTypeGroup.setSelected(myPlayerButton.getModel(), true);
   	JPanel radiopanel = new JPanel();
	radiopanel.setBorder(BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Sprite Group"));
   	radiopanel.setLayout(new GridLayout(4, 1));
   	radiopanel.add(myPlayerButton);
   	radiopanel.add(myCharacterButton);
   	radiopanel.add(mySettingButton);
   	radiopanel.add(myItemButton);
   	myTypeMap = new HashMap<JRadioButton, SpriteGroupIdentifier>();
   	myTypeMap.put(myPlayerButton, SpriteGroupIdentifier.PLAYER);
   	myTypeMap.put(myCharacterButton, SpriteGroupIdentifier.CHARACTER);
   	myTypeMap.put(mySettingButton, SpriteGroupIdentifier.SETTING);
   	myTypeMap.put(myItemButton, SpriteGroupIdentifier.ITEM);
   	rightpanel.add(radiopanel);
	JPanel namepanel = new JPanel();
	//namepanel.setLayout(new GridLayout(1, 2));
   	JLabel namelabel = new JLabel("Name:");
   	namefield = new JTextField();
   	namefield.setPreferredSize(new Dimension(80, 20));
   	namepanel.add(namelabel);
   	namepanel.add(namefield);
   	rightpanel.add(namepanel);
   	JPanel buttonpanel = new JPanel();
   	buttonpanel.setLayout(new GridLayout(3, 1));
   	JButton load = new JButton("Load Image");
   	load.addActionListener(new LoadImageListener());
   	JButton confirm = new JButton("Confirm");
   	JButton cancel = new JButton("Cancel");
   	cancel.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   		myInstance.dispose();
   	    }
   	});
   	buttonpanel.add(load);
   	buttonpanel.add(confirm);
   	confirm.addActionListener(new SpriteCreateListener());
   	buttonpanel.add(cancel);
   	rightpanel.add(buttonpanel);
   	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   	//pack();
    }
    
    private class LoadImageListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    File f = myView.loadFile("Load Image...");
	    if (f == null) return;
	    try {
		myImageSrc = f.getCanonicalPath();
		imagelabel.setIcon(new ImageIcon(myImageSrc));
		imagelabel.setLayout(null);
		myInstance.toFront();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}
	
    }
    
    private class SpriteCreateListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    if (myImageSrc == null) {
		myView.showError("Please specify an image file!");
		myInstance.toFront();
		return;
	    }
	    SpriteGroupIdentifier gid = checkSelectedButton();
	    String name = namefield.getText();
	    JTree tree = myView.getClassTree();
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	    if (node == null) {
		myView.showError("Please specify a special attribute or load a file first!");
		return;
	    }
	    Class<?> clazz = (Class<?>) node.getUserObject();
	    GameElement kernel = null;
	    try {
		kernel = (GameElement) clazz.getConstructor(PhysicsAttributes.class)
		    .newInstance(myView.getDefaultPhysicsAttributes());
	    } catch (InstantiationException e1) {
		e1.printStackTrace();
	    } catch (IllegalAccessException e1) {
		e1.printStackTrace();
	    } catch (IllegalArgumentException e1) {
		e1.printStackTrace();
	    } catch (InvocationTargetException e1) {
		e1.printStackTrace();
	    } catch (NoSuchMethodException e1) {
		e1.printStackTrace();
	    } catch (SecurityException e1) {
		e1.printStackTrace();
	    }
	    kernel.setTag(namefield.getText());
	    SpriteWrapper created = new SpriteWrapper(name, gid, myView.getDefaultPhysicsAttributesMap(), 
		    myImageSrc, kernel);
	    myView.getSpritePanel().importSprite(created);
	    myInstance.dispose();
	    SpriteEditor.getInstance(myView, created);
	}
	
    }
    
    private SpriteGroupIdentifier checkSelectedButton() {
	for (Enumeration<AbstractButton> e = myTypeGroup.getElements(); e.hasMoreElements(); ) {
	    JRadioButton b = (JRadioButton) e.nextElement();
	    if (b.getModel().equals(myTypeGroup.getSelection()))
		return myTypeMap.get(b);
	}
	return null;
    }
    
}