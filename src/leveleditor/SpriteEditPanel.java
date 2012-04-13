package leveleditor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import levelio.SpriteWrapper;

import com.golden.gamedev.object.Sprite;

public class SpriteEditPanel extends JFrame {
    
    private static SpriteEditPanel myInstance;
    private VoogaLevelEditorModel myModel;
    private VoogaLevelEditor myView;
    private VoogaLevelEditorController myController;
    private JTextField namefield;
    
    public static SpriteEditPanel getInstance(VoogaLevelEditorModel model, 
	    VoogaLevelEditor view, VoogaLevelEditorController controller, String imagesrc) {
	if (myInstance != null)
	    myInstance.dispose();
	myInstance = new SpriteEditPanel(model, view, controller, imagesrc);
	myInstance.setVisible(true);
	return myInstance;
    }
    
    private SpriteEditPanel(VoogaLevelEditorModel model, VoogaLevelEditor view, 
	    VoogaLevelEditorController controller, String imagesrc) {
	myModel = model;
	myView = view;
	myController = controller;
	setSize(300, 200);
	setLocationRelativeTo(view);
	setTitle("Edit Sprite");
	setLayout(new GridLayout(1, 2));
	JLabel imagelabel = new JLabel(new ImageIcon(imagesrc));
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
   	JLabel namelabel = new JLabel("Name:");
   	namefield = new JTextField();
   	editpanel.add(namelabel);
   	editpanel.add(namefield);
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
   	confirm.addActionListener(new CreateSpriteListener(imagesrc));
   	confirmcancelpanel.add(cancel);
   	rightpanel.add(confirmcancelpanel);
   	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   	pack();
    }
    
    private class CreateSpriteListener implements ActionListener {
	private String myImagesrc;
	
	public CreateSpriteListener(String imagesrc) {
	    myImagesrc = imagesrc;
	}

	public void actionPerformed(ActionEvent e) {
	    BufferedImage image = VoogaUtilities.getImageFromString(myImagesrc);
	    myView.importSprite(new SpriteWrapper(new Sprite(image), 
		    namefield.getText(), myImagesrc));
	    myInstance.dispose();
	}
	
    }
    
}
