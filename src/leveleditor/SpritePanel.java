package leveleditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import leveleditor.eventhandlers.CopySpriteListener;
import levelio.GameElementWrapper;

@SuppressWarnings("serial")
public class SpritePanel extends JScrollPane {
    
    private Map<JLabel, GameElementWrapper> myUniqueLabelWrapperMap;
    private Set<GameElementWrapper> myUniqueWrapperSet;
    private LevelEditor myView;
    private JPanel myInternalPanel;

    public SpritePanel(LevelEditor view, GridBagConstraints constraint) {
	myView = view;
	setLocation(0, 0);
	setPreferredSize(new Dimension(200, 600));
	myView.add(this, constraint);
	myInternalPanel = new JPanel();
	myInternalPanel.setLayout(new GridLayout());
	myInternalPanel.setPreferredSize(getPreferredSize());
	myInternalPanel.setBackground(Color.BLACK);
	Border border = BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
		"Sprite Pane");
	setBorder(border);
	getViewport().add(myInternalPanel);
	myUniqueLabelWrapperMap = new HashMap<JLabel, GameElementWrapper>();
	myUniqueWrapperSet = new TreeSet<GameElementWrapper>();
    }
    
    protected void importSprite(GameElementWrapper wrapper) {
	myUniqueWrapperSet.add(wrapper);
	myInternalPanel.setLayout(new GridLayout(myUniqueWrapperSet.size(), 1));
	CopySpriteListener listener = new CopySpriteListener(myView);
	JLabel label = new JLabel(wrapper.getName(), new ImageIcon(wrapper.getImageSrc()), JLabel.CENTER);
	label.setVerticalTextPosition(JLabel.TOP);
	label.setHorizontalTextPosition(JLabel.CENTER);
	label.setForeground(Color.YELLOW);
	myInternalPanel.add(label);
	label.addMouseListener(listener);
	myUniqueLabelWrapperMap.put(label, wrapper);
	myInternalPanel.revalidate();
    }

    protected void loadSprites(Map<Point, GameElementWrapper> spritemap) {
	myInternalPanel.removeAll();
	myInternalPanel.revalidate();
	for (GameElementWrapper sw: spritemap.values())
	    myUniqueWrapperSet.add(sw);
	myUniqueLabelWrapperMap = new HashMap<JLabel, GameElementWrapper>();
	for (GameElementWrapper wrapper: myUniqueWrapperSet)
	    importSprite(wrapper);
    }
    
    public Map<JLabel, GameElementWrapper> getUniqueLabelWrapperMap() {
	return myUniqueLabelWrapperMap;
    }

}