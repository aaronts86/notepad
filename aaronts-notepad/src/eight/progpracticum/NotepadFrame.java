/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextPane;

/**
 * The <code>NotepadFrame</code> class builds the frame for the Notepad.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see java.awt.BorderLayout
 * @see java.awt.Toolkit
 * @see javax.swing.JFrame
 * @see javax.swing.JTextPane
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class NotepadFrame extends JFrame {
    /**
     * Represents the menubar for the Notepad.
     */
    private final MenuBar myMenuBar;
    /**
     * Represents the JScrollPane containing the textarea for the Notepad.
     */
    private final TextEditor myEditor;
    /**
     * Represents the textarea of the Notepad.
     */
    private final JTextPane myTextPane;
    
    /**
     * Constructor the NotepadFrame that adds the components to the frame.
     * 
     * @custom.post the frame is filled with the components for the Notepad
     */
    public NotepadFrame() {
        super("Notepad");      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.jpg"));
        
        myTextPane = new JTextPane();
        myEditor = new TextEditor(myTextPane);
        add(myEditor, BorderLayout.CENTER);
        
        myMenuBar = new MenuBar(this, myTextPane);
        setJMenuBar(myMenuBar);
        
        pack();
        setLocationRelativeTo(null);
    }
}
