/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.StyledEditorKit;

/**
 * The <code>FormatMenu</code> class builds the Format menu which is apart of the menubar
 * of the Notepad.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.ActionListener
 * @see java.awt.event.ItemEvent
 * @see java.awt.event.ItemListener
 * @see java.awt.event.KeyEvent
 * @see javax.swing.JCheckBoxMenuItem
 * @see javax.swing.JFrame
 * @see javax.swing.JMenu
 * @see javax.swing.JMenuItem
 * @see javax.swing.JTextPane
 * @see javax.swing.KeyStroke
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class FormatMenu extends JMenu {
    /**
     * Represents a check box in the menu to set word wrap on or off.
     */
    private JCheckBoxMenuItem myWordWrapBox;
    /**
     * Represents a menu item to set the font.
     */
    private JMenuItem myFontMenuItem;
    /**
     * Represents the frame of the Notepad.
     */
    private JFrame myFrame;
    /**
     * Represents the textarea of the Notepad.
     */
    private JTextPane myTextPane;
    /**
     * Represents the dialog for selecting the font.
     */
    private FontDialog myDialog;
    
    /**
     * Inner class action whenever the wrap checkbox is selected.
     */
    private WrapAction myWrapAction;
    /**
     * Inner class action whenever the Font Dialog is to be opened.
     */
    private FontDialogAction myFontDialogAction;
    
    /**
     * The parameterized constructor that adds the format menu to the menubar.
     * 
     * @param aFrame represents the frame of the notepad
     * @param aTextPane represents the textarea of the notepad
     * @custom.post the format menu is added to the menubar
     */
    public FormatMenu(final JFrame aFrame, final JTextPane aTextPane) {
        super("Format");
        setMnemonic(KeyEvent.VK_O);
        
        myFrame = aFrame;
        myTextPane = aTextPane;
        
        myWrapAction = new WrapAction();
        myFontDialogAction = new FontDialogAction();
        
        myWordWrapBox = new JCheckBoxMenuItem("Word Wrap");
        myFontMenuItem = new JMenuItem("Font");
        
        myWordWrapBox.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
        myWordWrapBox.addItemListener(myWrapAction);
        
        myFontMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
        myFontMenuItem.addActionListener(myFontDialogAction);
        
        add(myWordWrapBox);
        add(myFontMenuItem);
    }
    
    /**
     * Private inner class that represents an action whenever the user clicks the Textwrap
     * menu checkbox item.
     */
    private class WrapAction implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent anEvent) {
            if (myWordWrapBox.isSelected()) {
            } else {

            }
        }
    }
    
    /**
     * Private inner class that represents an action whenever the Font menu item is selected
     * if the font dialog is not yet opened.
     */
    private class FontDialogAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            // if first time, construct dialog

            if (myDialog == null || !myTextPane.getFont().equals(myDialog.getMyFont())) {
                myDialog = new FontDialog();
            }
            
            // pop up dialog; returns true if a person picked ok button
            if (myDialog.showDialog(myFrame, "Font")) {
                if (myTextPane.getSelectedText() != null) {
                    myTextPane.replaceSelection("Hello");
                }
                myTextPane.setFont(myDialog.getMyFont());
            }
        }
    }
}
