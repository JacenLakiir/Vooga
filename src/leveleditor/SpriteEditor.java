/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import io.SpriteAttribute;
import io.SpriteWrapper;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class SpriteEditor extends JFrame {
    
    private static SpriteEditor myInstance;
    private LevelEditor myView;
    private SpriteWrapper mySprite;
    private Map<JTextField, SpriteAttribute> myTextFieldAttributeMap;
    
    public static SpriteEditor getInstance(LevelEditor view, SpriteWrapper wrapper) {
	if (myInstance != null)
	    myInstance.dispose();
	myInstance = new SpriteEditor(view, wrapper);
	myInstance.setVisible(true);
	return myInstance;
    }
    
    private SpriteEditor(LevelEditor view, SpriteWrapper wrapper) {
	myView = view;
	mySprite = wrapper;
	myTextFieldAttributeMap = new HashMap<JTextField, SpriteAttribute>();
	setSize(300, 200);
	setLocationRelativeTo(view);
	setTitle("Edit Sprite");
	setLayout(new GridLayout(1, 2));
	JLabel imagelabel = new JLabel(new ImageIcon(wrapper.getImageSrc()));
   	Border imageborder = BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Image");
   	imagelabel.setBorder(imageborder);
	add(imagelabel);
	JPanel rightpanel = new JPanel();
	rightpanel.setLayout(new GridLayout(2, 1));
	add(rightpanel);
	JPanel editpanel = new JPanel();
   	Border panelborder = BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Edit");
   	editpanel.setBorder(panelborder);
   	editpanel.setLayout(new GridLayout(1, 2));
   	generateFields(wrapper, editpanel);
   	rightpanel.add(editpanel);
   	JPanel confirmcancelpanel = new JPanel();
   	confirmcancelpanel.setLayout(new GridLayout(2, 1));
   	JButton confirm = new JButton("Confirm");
   	JButton cancel = new JButton("Cancel");
   	cancel.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   		myInstance.dispose();
   	    }
   	});
   	confirmcancelpanel.add(confirm);
   	//confirm.addActionListener(new EditSpriteListener());
   	confirmcancelpanel.add(cancel);
   	rightpanel.add(confirmcancelpanel);
   	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   	pack();
    }
	
    public void generateFields(SpriteWrapper wrapper, JPanel editpanel) {
	Map<SpriteAttribute, Serializable> attributemap = wrapper.getAttributeMap();
	Map<SpriteAttribute, Serializable> physicsattrmap = wrapper.getPhysicsAttributeMap();
	editpanel.setLayout(new GridLayout(attributemap.keySet().size() 
		+ physicsattrmap.keySet().size(), 2));
	TreeSet<SpriteAttribute> attributeset = new TreeSet<SpriteAttribute>();
	attributeset.addAll(attributemap.keySet());
	attributeset.addAll(physicsattrmap.keySet());
	for (SpriteAttribute sa: attributeset) {
	    if (!sa.getAttributeType().equals(Boolean.class) && 
		    !sa.getAttributeType().equals(Boolean.TYPE)) {
		JLabel attributelabel = new JLabel(sa.getName() + ": ");
		JTextField attributefield = new JTextField();
		if (attributemap.get(sa) != null)
		    attributefield.setText(attributemap.get(sa).toString());
		if (physicsattrmap.get(sa) != null)
		    attributefield.setText(physicsattrmap.get(sa).toString());
		editpanel.add(attributelabel);
		editpanel.add(attributefield);
		myTextFieldAttributeMap.put(attributefield, sa);
	    }
	}
    }
    
//    private class EditSpriteListener implements ActionListener {
//	
//	public void actionPerformed(ActionEvent e) {
//	    BufferedImage image = VoogaUtilities.getImageFromString(myImagesrc);
//	    myView.getSpritePanel().importSprite(new SpriteWrapper(new Sprite(image), 
//		    namefield.getText(), myImagesrc));
//	    myInstance.dispose();
//	}
//	
//    }
    
}
