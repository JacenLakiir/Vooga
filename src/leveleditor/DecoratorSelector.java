package leveleditor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.*;

import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class DecoratorSelector extends JDialog {

    private DecoratorSelector myInstance;
    
    private LevelEditor myView;
    private SpriteBuilder myParent;
    private Class<?> myTarget;
    private Map<JCheckBox, Class<?>> myCheckBoxClassMap;
    private JButton myConfirmButton;
    
    public DecoratorSelector(LevelEditor view, SpriteBuilder parent,
	    Class<?> target, GameElement kernel) {
	super(parent, "Select Decorators", true);
	myInstance = this;
	myParent = parent;
	myView = view;
	myTarget = target;
	myCheckBoxClassMap = new HashMap<JCheckBox, Class<?>>();
	JPanel boxpanel = new JPanel();
	setLayout(new GridLayout(2, 1));
	setupCheckBoxes(boxpanel);
	add(boxpanel);
	myConfirmButton = new JButton("Confirm");
	add(myConfirmButton);
	myConfirmButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		getDecoratedSprite();
		myInstance.dispose();
	    }
	});
	setLocationRelativeTo(null);
	setSize(300, 200);
	pack();
	setVisible(true);
    }
    
    private void setupCheckBoxes(JPanel boxpanel) {
	Set<Class<?>> decorators = ClassTreeUtils.getDecorators("core, demo", myTarget);
	System.out.println(decorators);
	int rows = (int) Math.ceil((double)decorators.size() / 2.0);
	boxpanel.setLayout(new GridLayout(rows, 4));
	for (Class<?> clazz: decorators) {
	    JCheckBox created = new JCheckBox();
	    JLabel boxlabel = new JLabel(clazz.getSimpleName());
	    boxpanel.add(boxlabel);
	    boxpanel.add(created);
	    myCheckBoxClassMap.put(created, clazz);
	}
	if (decorators.size() % 2 == 1) {
	    for (int i = 0; i < 2; i++)
		boxpanel.add(new JLabel());
	}
    }
    
    protected void getDecoratedSprite() {
	List<Class<?>> toconstruct = new ArrayList<Class<?>>();
	for (JCheckBox tocheck: myCheckBoxClassMap.keySet()) {
	    if (tocheck.isSelected())
		toconstruct.add(myCheckBoxClassMap.get(tocheck));
	}
	GameElement target = null;
	try {
	    target = (GameElement) myTarget.getConstructor(PhysicsAttributes.class)
		    .newInstance(myView.getDefaultPhysicsAttributes());
	    for (Class<?> decorator: toconstruct) {
		target = (GameElement) decorator.getConstructor(myTarget)
			.newInstance(target);
		myParent.addAdditionalClass(decorator);
	    }
	    myParent.setKernel(target);
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	} catch (NoSuchMethodException e) {
	    e.printStackTrace();
	} catch (SecurityException e) {
	    e.printStackTrace();
	}

    }

}
