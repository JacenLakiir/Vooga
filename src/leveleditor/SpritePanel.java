/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import leveleditor.eventhandlers.CopySpriteListener;
import levelio.SpriteWrapper;

@SuppressWarnings("serial")
public class SpritePanel extends JScrollPane {
    
    private Map<JLabel, SpriteWrapper> myUniqueLabelWrapperMap;
    private Set<SpriteWrapper> myUniqueWrapperSet;
    private Set<String> myUniqueNameSet;
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
	myUniqueLabelWrapperMap = new HashMap<JLabel, SpriteWrapper>();
	myUniqueWrapperSet = new HashSet<SpriteWrapper>();
	myUniqueNameSet = new HashSet<String>();
    }
    
    protected void importSprite(SpriteWrapper wrapper) {
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
	myInternalPanel.repaint();
    }
    
    public void deleteSpriteLabel(JLabel label) {
	myUniqueNameSet.remove(myUniqueLabelWrapperMap.get(label).getName());
	myUniqueWrapperSet.remove(myUniqueLabelWrapperMap.get(label));
	myUniqueLabelWrapperMap.remove(label);
	myInternalPanel.remove(label);
	myInternalPanel.setLayout(new GridLayout(myUniqueWrapperSet.size(), 1));
	myInternalPanel.revalidate();
	myInternalPanel.repaint();
    }

    protected void loadSprites(List<SpriteWrapper> sprites) {
	myInternalPanel.removeAll();
	myInternalPanel.revalidate();
	for (SpriteWrapper sw: sprites)
	    if (!myUniqueNameSet.contains(sw.getName())) {
		myUniqueWrapperSet.add(sw);
		myUniqueNameSet.add(sw.getName());
	    }
	for (SpriteWrapper wrapper: myUniqueWrapperSet)
	    importSprite(wrapper);
    }
    
    public Map<JLabel, SpriteWrapper> getUniqueLabelWrapperMap() {
	return myUniqueLabelWrapperMap;
    }

}