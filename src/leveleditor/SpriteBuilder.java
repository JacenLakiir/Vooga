/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
import levelio.SpriteWrapper.SpriteGroupIdentifier;

@SuppressWarnings("serial")
public class SpriteBuilder extends JFrame {
    
    private LevelEditor myView;
    private static SpriteBuilder myInstance;
    private JTextField namefield;
    private JLabel imagelabel;
    private ButtonGroup myTypeGroup;
    private JRadioButton myPlayerButton;
    private JRadioButton myCharacterButton;
    private JRadioButton mySettingButton;
    private JRadioButton myItemButton;
    private Map<JRadioButton, SpriteGroupIdentifier> myTypeMap = 
	    new HashMap<JRadioButton, SpriteGroupIdentifier>() {{
	put(myPlayerButton, SpriteGroupIdentifier.PLAYER);
	put(myCharacterButton, SpriteGroupIdentifier.CHARACTER);
	put(mySettingButton, SpriteGroupIdentifier.SETTING);
	put(myItemButton, SpriteGroupIdentifier.ITEM);
    }};
    
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
   	myTypeGroup.setSelected(myCharacterButton.getModel(), true);
   	JPanel radiopanel = new JPanel();
	radiopanel.setBorder(BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Sprite Group"));
   	radiopanel.setLayout(new GridLayout(1, 4));
   	radiopanel.add(myPlayerButton);
   	radiopanel.add(myCharacterButton);
   	radiopanel.add(mySettingButton);
   	radiopanel.add(myItemButton);
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
		imagelabel.setIcon(new ImageIcon(f.getCanonicalPath()));
		imagelabel.setLayout(null);
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}
	
    }
    
    private class SpriteCreateListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    SpriteGroupIdentifier gid = checkSelectedButton();
	    
	}
	
    }
    
    private SpriteGroupIdentifier checkSelectedButton() {
	for (Enumeration<AbstractButton> e = myTypeGroup.getElements(); e.hasMoreElements(); ) {
	    JRadioButton b = (JRadioButton) e.nextElement();
	    if (b.getModel() == myTypeGroup.getSelection()) {
	    	return myTypeMap.get(b);
	    }
	}
	return null;
    }
    
}