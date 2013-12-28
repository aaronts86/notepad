/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 * The <code>TextEditor</code> class adds the textarea to the frame with a scroll pane.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see java.awt.Dimension
 * @see java.awt.Font
 * @see java.awt.Toolkit
 * @see javax.swing.JScrollPane
 * @see javax.swing.JTextPane
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class TextEditor extends JScrollPane {
    /**
     * Variable the holds the default toolkit value.
     */
    private final Toolkit myKit = Toolkit.getDefaultToolkit();
    /**
     * Variable that holds the screen size.
     */
    private final Dimension myScreenSize = myKit.getScreenSize();
    /**
     * Variable that holds the screen width.
     */
    private final int myScreenWidth = (int) Math.round(myScreenSize.width * 0.85);
    
    /**
     * Variable that holds the screen height.
     */
    private final int myScreenHeight = (int) Math.round(myScreenSize.height * 0.85);
    /**
     * Variable that holds the size of the screen.
     */
    private final Dimension myDefaultSize = new Dimension(myScreenWidth, myScreenHeight);
    
    /**
     * Represents the textarea of the Notepad.
     */
    private JEditorPane myTextPane;
    
    /**
     * Paramaterized constructor that adds the textarea to the scrollpane and displays it.
     * @param aTextArea the textarea of the Notepad
     * @custom.post the textarea is added to the Notepad
     */
    public TextEditor(final JEditorPane aTextPane) {
        super(aTextPane);
        myTextPane = aTextPane;
        myTextPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setPreferredSize(myDefaultSize);
    }
}
