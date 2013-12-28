/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Main class for the Notepad that runs the application.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * 
 * @custom.inv None
 */
public final class NotepadMain {
    /**
     * Private constructor that inhibits the <code>NotepadMain</code> class from being
     * instantiated.
     */
    private NotepadMain() {
        throw new IllegalStateException("Cannot instantiate NotepadMain");
    }
    
    /**
     * Main method that runs the application.
     * 
     * @param aRgs String arguments aren't used in this application to run it.
     * @custom.post the application is run
     */
    public static void main(String[] aRgs) {
        EventQueue.invokeLater(new Runnable()
            {
                public void run() {
                    final JFrame frame = new NotepadFrame();
                    frame.setVisible(true);
                }
        });
    }
}
