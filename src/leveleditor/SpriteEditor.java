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
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class SpriteEditor extends JFrame {
    
    private static SpriteEditor myInstance;
    private LevelEditor myView;
    private SpriteWrapper mySprite;
    private Map<JTextField, SpriteAttribute> myTextFieldAttributeMap;
    private Map<JCheckBox, SpriteAttribute> myCheckBoxAttributeMap;
    
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
	myCheckBoxAttributeMap = new HashMap<JCheckBox, SpriteAttribute>();
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
	JTabbedPane editpanel = new JTabbedPane();
   	Border panelborder = BorderFactory.createTitledBorder
   		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Edit");
   	editpanel.setBorder(panelborder);
   	JPanel gameplaypanel = new JPanel();
   	JPanel physicspanel = new JPanel();
   	String[] classifications = {"Gameplay", "Physics"};
   	int maxrows = findMaxRows(mySprite.getMergedAttributeMap(), classifications);
   	generateFields(gameplaypanel, "Gameplay", maxrows);
   	generateFields(physicspanel, "Physics", maxrows);
   	editpanel.addTab("Gameplay", gameplaypanel);
   	editpanel.addTab("Physics", physicspanel);
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
   	confirm.addActionListener(new EditSpriteListener());
   	confirmcancelpanel.add(cancel);
   	rightpanel.add(confirmcancelpanel);
   	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   	pack();
    }
    
    private int findMaxRows(Map<SpriteAttribute, Serializable> mergedmap, 
	    String[] classifications) {
	int maxrows = 0;
	for (String str: classifications) {
	    int currsize = filterMap(mergedmap, str).keySet().size();
	    if (currsize > maxrows) maxrows = currsize;
	}
	return maxrows;
    }
    
    private Map<SpriteAttribute, Serializable> 
    	filterMap(Map<SpriteAttribute, Serializable> tofilter, String classification) {
	Map<SpriteAttribute, Serializable> filtered = new HashMap<SpriteAttribute, Serializable>();
	for (SpriteAttribute sa: tofilter.keySet())
	    if (sa.getClassification().equals(classification))
		filtered.put(sa, tofilter.get(sa));
	return filtered;
    }
	
    public void generateFields(JPanel editpanel, String classification, int maxrows) {
	Map<SpriteAttribute, Serializable> attributemap = mySprite.getMergedAttributeMap();
	attributemap = filterMap(attributemap, classification);
	editpanel.setLayout(new GridLayout(maxrows, 2));
	TreeSet<SpriteAttribute> attributeset = new TreeSet<SpriteAttribute>();
	attributeset.addAll(attributemap.keySet());
	for (SpriteAttribute sa: attributeset) {
	    if (!sa.getAttributeType().equals(Boolean.class) && 
		    !sa.getAttributeType().equals(Boolean.TYPE)) {
		JLabel attributelabel = new JLabel(sa.getName() + ": ");
		JTextField attributefield = new JTextField();
		if (attributemap.get(sa) != null)
		    attributefield.setText(attributemap.get(sa).toString());
		editpanel.add(attributelabel);
		editpanel.add(attributefield);
		myTextFieldAttributeMap.put(attributefield, sa);
	    }
	}
	for (SpriteAttribute sa: attributeset) {
	    if (sa.getAttributeType().equals(Boolean.class) || 
		    sa.getAttributeType().equals(Boolean.TYPE)) {
		JCheckBox attributebox = new JCheckBox();
		JLabel attributelabel = new JLabel(sa.getName() + ": ");
		editpanel.add(attributelabel);
		editpanel.add(attributebox);
		myCheckBoxAttributeMap.put(attributebox, sa);
		if (attributemap.get(sa) != null && (Boolean) attributemap.get(sa).equals(true))
		    attributebox.setSelected(true);
		myCheckBoxAttributeMap.put(attributebox, sa);
	    }
	}
	for (int i = 0; i < 2 * (maxrows - attributemap.keySet().size()); i++) {
	    editpanel.add(new JLabel());
	}
    }
    
    private class EditSpriteListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
	    for (JTextField textfield : myTextFieldAttributeMap.keySet()) {
		Double value = Double.parseDouble(textfield.getText());
		mySprite.updateAttributeMap(myTextFieldAttributeMap.get(textfield), value);
	    }
	    for (JCheckBox checkbox : myCheckBoxAttributeMap.keySet()) {
		Boolean value = checkbox.isSelected();
		mySprite.updateAttributeMap(myCheckBoxAttributeMap.get(checkbox), value);
	    }
	    myInstance.dispose();
	}
	
    }
    
}