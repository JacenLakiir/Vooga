/**
 * @author Glenn Rivkees (grivkees)
 */

package core.playfield;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ParallaxBackground;
import core.characters.*;
import core.characters.Character;
import core.collision.GameElementCollision;
import core.collision.SideScrollerBoundsCollision;
import core.items.CollectibleItem;
import core.physicsengine.physicsplugin.BuoyancyPlugin;
import core.physicsengine.physicsplugin.PhysicsPlugin;
import core.physicsengine.physicsplugin.RestitutionAndFrictionPlugin;
import core.physicsengine.physicsplugin.ViscosityPlugin;
import core.playfield.hud.HUD;
import core.playfield.hud.HUDWidget;
import core.playfield.hud.VerticalFlowLayout;
import core.playfield.scroller.GameScroller;
import core.tiles.Tile;


public class AdvancedPlayField extends PlayField
{

    private GameScroller gamescroller;
    private HUD hud;

    private SpriteGroup Players;
    private SpriteGroup Characters;
    private SpriteGroup Setting;
    private SpriteGroup Items;
    private List<PhysicsPlugin> physicsPlugins;


    /*
     * Initialize PlayField, Background, and common SpriteGroups
     */
    public AdvancedPlayField (int width, int height)
    {
        super(new ParallaxBackground(new Background[] {
                new Background(width, height),
                new Background() }));
        Players = this.addGroup(new SpriteGroup("Player Group"));
        Characters = this.addGroup(new SpriteGroup("Character Group"));
        Setting = this.addGroup(new SpriteGroup("Setting Group"));
        Items = this.addGroup(new SpriteGroup("Setting Group"));
        physicsPlugins = new ArrayList<PhysicsPlugin>();
        initializeDefaultPhysicsPlugins();

        // Add Bounds Collsion
        this.addCollisionGroup(this.getPlayers(),
                               null,
                               new SideScrollerBoundsCollision(this.getBackground()));

        hud = new HUD(new VerticalFlowLayout(100, 100));
    }


    /*
     * Initialize Default PhysicsPlugins
     */
    private void initializeDefaultPhysicsPlugins ()
    {
        physicsPlugins.add(new BuoyancyPlugin());
        physicsPlugins.add(new ViscosityPlugin());
        physicsPlugins.add(new RestitutionAndFrictionPlugin());
    }


    /*
     * Add Customized PhysicsPlugins
     */
    public void addPhysicsPlugin (PhysicsPlugin p)
    {
        physicsPlugins.add(p);
    }


    /*
     * Remove PhysicsPlugins
     */
    public void removePhysicsPlugin (PhysicsPlugin p)
    {
        physicsPlugins.remove(p);
    }


    /*
     * Override default method to add physics plug-ins.
     */
    @Override
    public void addCollisionGroup (SpriteGroup group1,
                                   SpriteGroup group2,
                                   CollisionManager collisionGroup)
    {
        if (collisionGroup instanceof GameElementCollision)
        {
            ((GameElementCollision) collisionGroup).setPhysicsPlugIns(physicsPlugins);
        }
        super.addCollisionGroup(group1, group2, collisionGroup);
    }


    /*
     * GameScroller Methods
     */

    public void setGameScroller (GameScroller gs)
    {
        gamescroller = gs;
        gs.setBackground(this.getBackground());
        gs.setPlayers(Players);
    }


    /*
     * Heads Up Display
     */
    public void addHUDWidget (HUDWidget w)
    {
        hud.addWidget(w);
    }


    /*
     * Additional Render Stuff
     */
    public void render (Graphics2D g)
    {
        gamescroller.scroll();
        super.render(g);
        hud.render(g);
    }


    public void update (long t)
    {
        super.update(t);
        hud.update(t);
    }


    /*
     * Set Background
     */

    public void setBackground (Background backgr)
    {
        Background[] bkg =
            ((ParallaxBackground) this.getBackground()).getParallaxBackground();
        bkg[1] = backgr;
        super.setBackground(new ParallaxBackground(bkg));
    }


    public void setBackground ()
    {
        setBackground(Background.getDefaultBackground());
    }


    /*
     * Common Sprite Groups
     */
    public void addPlayer (Player p)
    {
        Players.add(p);
    }


    public void addCharacter (Character c)
    {
        Characters.add(c);
    }


    public void addItem (CollectibleItem ci)
    {
        Items.add(ci);
    }


    public void addSetting (Tile p)
    {
        Setting.add(p);
    }


    /*
     * Get Groups
     */
    public SpriteGroup getPlayers ()
    {
        return Players;
    }


    public Player getPlayer ()
    {
        return (Player) Players.getActiveSprite();
    }


    public SpriteGroup getCharacters ()
    {
        return Characters;
    }


    public SpriteGroup getItems ()
    {
        return Items;
    }


    public SpriteGroup getSetting ()
    {
        return Setting;
    }


    public List<PhysicsPlugin> getPhysicsPlugins ()
    {
        return Collections.unmodifiableList(physicsPlugins);
    }

}
