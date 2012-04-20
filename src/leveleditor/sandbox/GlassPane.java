package leveleditor.sandbox;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class GlassPane extends JComponent {
    private int x=0, y=0, k=25,z=1;
    private boolean showDot;
    private MouseInputAdapter mia = new MouseInputAdapter(){

        @Override
        public void mouseDragged(MouseEvent me) {
            setPos(me.getX(), me.getY());
        }

        @Override
        public void mousePressed(MouseEvent me) {
            setShow(true);
            setPos(me.getX(), me.getY());   
        }

        @Override
        public void mouseReleased(MouseEvent me){
            setShow(false);
            setVisible(false);
        }
    };

    public void setShow(boolean b){ this.showDot = b; }

   /* public void paint(Graphics g) {
        if (showDot) {
            g.setColor(new Color(0.0f, 0.25f, 1.0f));
            g.fillOval(x - k, y - k, 2*k, 2*k);
        }
    }*/
    
    @Override
    protected void paintComponent(Graphics g) {
        if (showDot) {
            Graphics gCopy = g.create();

            gCopy.setColor(new Color(0.0f, 0.25f, 1.0f));
            gCopy.fillOval(x - k, y - k, 2*k, 2*k);

            gCopy.dispose();
        }
    }

    public void setPos(int x, int y) {
        int tmpX = this.x, tmpY = this.y; 
        this.x = x; this.y = y;
        repaint(tmpX - k , tmpY-k, 2*k+5, 2*k+5);
        repaint(this.x-k, this.y-k, 2*k+5, 2*k+5);        
    }

    public GlassPane() {
        addMouseListener(mia); 
        addMouseMotionListener(mia);
        setOpaque(false);
        this.validate();
    }
}