package leveleditor.sandbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

/**
 * A custom Swing component that displays a simple digital clock. Demonstrates
 * how to add copy and drag support to a Swing component with TransferHandler.
 */
public class DigitalClock extends JLabel {
  DateFormat format; // How to display the time in string form

  int updateFrequency; // How often to update the time (in milliseconds)

  Timer timer; // Triggers repeated updates to the clock

  public DigitalClock() {
    // Set default values for our properties
    setFormat(DateFormat.getTimeInstance(DateFormat.MEDIUM, getLocale()));
    setUpdateFrequency(1000); // Update once a second

    // Specify a Swing TransferHandler object to do the dirty work of
    // copy-and-paste and drag-and-drop for us. This one will transfer
    // the value of the "time" property. Since this property is read-only
    // it will allow drags but not drops.
    setTransferHandler(new TransferHandler("time"));

    // Since JLabel does not normally support drag-and-drop, we need an
    // event handler to detect a drag and start the transfer.
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        getTransferHandler().exportAsDrag(DigitalClock.this, e, TransferHandler.COPY);
      }
    });

    // Before we can have a keyboard binding for a Copy command,
    // the component needs to be able to accept keyboard focus.
    setFocusable(true);
    // Request focus when we're clicked on
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        requestFocus();
      }
    });
    // Use a LineBorder to indicate when we've got the keyboard focus
    addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent e) {
        setBorder(LineBorder.createBlackLineBorder());
      }

      public void focusLost(FocusEvent e) {
        setBorder(null);
      }
    });

    // Now bind the Ctrl-C keystroke to a "Copy" command.
    InputMap im = new InputMap();
    im.setParent(getInputMap(WHEN_FOCUSED));
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "Copy");
    setInputMap(WHEN_FOCUSED, im);

    // And bind the "Copy" command to a pre-defined Action that performs
    // a copy using the TransferHandler we've installed.
    ActionMap am = new ActionMap();
    am.setParent(getActionMap());
    am.put("Copy", TransferHandler.getCopyAction());
    setActionMap(am);

    // Create a javax.swing.Timer object that will generate ActionEvents
    // to tell us when to update the displayed time. Every updateFrequency
    // milliseconds, this timer will cause the actionPerformed() method
    // to be invoked. (For non-GUI applications, see java.util.Timer.)
    timer = new Timer(updateFrequency, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setText(getTime()); // set label to current time string
      }
    });
    timer.setInitialDelay(0); // Do the first update immediately
    timer.start(); // Start timing now!
  }

  // Return the current time as a String.
  // This is the property accessor method used by the TransferHandler.
  // Since there is a getter, but no setter, the TransferHandler will
  // reject any attempts to drop data on us.
  public String getTime() {
    // Use the DateFormat object to convert current time to a string
    return format.format(new Date());
  }

  // Here are two related property setter methods
  public void setFormat(DateFormat format) {
    this.format = format;
  }

  public void setUpdateFrequency(int ms) {
    this.updateFrequency = ms;
  }
}