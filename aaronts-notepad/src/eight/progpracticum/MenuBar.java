/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;

/**
 * The <code>MenuBar</code> class builds the menubar for the Notepad.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see javax.swing.JFrame
 * @see javax.swing.JMenuBar
 * @see javax.swing.JTextPane
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
    /**
     * Represents the File menu.
     */
    private FileMenu myFileMenu;
    /**
     * Represents the Format menu.
     */
    private FormatMenu myFormatMenu;
    /**
     * Represents the Help menu.
     */
    private HelpMenu myHelpMenu;
    
    /**
     * Parameterized constructor for the MenuBar that creates the menubar to be added
     * to the Notepad.
     * 
     * @param aFrame the frame of the Notepad
     * @param aTextPane the textarea of the Notepad
     * @custom.post the menubar is added to the Notepad
     */
    public MenuBar(final JFrame aFrame, final JTextPane aTextPane) {
        super();
        
        myFileMenu = new FileMenu(aTextPane);
        myFormatMenu = new FormatMenu(aFrame, aTextPane);
        myHelpMenu = new HelpMenu();
        
        add(myFileMenu);
        add(myFormatMenu);
        add(myHelpMenu);
    }
}
