/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The <code>FileMenu</code> class builds the file menu that's part of the menubar in 
 * the Notepad.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see java.awt.Font
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.KeyEvent
 * @see java.awt.print.PrinterException
 * @see java.io.BufferedReader
 * @see java.io.BufferedWriter
 * @see java.io.File
 * @see java.io.FileNotFoundException
 * @see java.io.FileReader
 * @see java.io.FileWriter
 * @see java.io.IOException
 * @see javax.swing.JFileChooser
 * @see javax.swing.JMenu
 * @see javax.swing.JMenuItem
 * @see javax.swing.JOptionPane
 * @see javax.swing.JTextPane
 * @see javax.swing.KeyStroke
 * @see javax.swing.filechooser.FileNameExtensionFilter
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class FileMenu extends JMenu {
    /**
     * Represents a New menu item that resets the notepad.
     */
    private JMenuItem myNewMenuItem;
    /**
     * Represents a Open menu item that prompts the user to open a text file.
     */
    private JMenuItem myOpenMenuItem;
    /**
     * Represents a Save menu item that prompts the user to save the textarea.
     */
    private JMenuItem mySaveMenuItem;
    /**
     * Represents a Print menu item that prompts the user to print the textarea.
     */
    private JMenuItem myPrintMenuItem;
    /**
     * Represents a Exit menu item that closes the program.
     */
    private JMenuItem myExitMenuItem;
    
    /**
     * Represents the text area of the Notepad.
     */
    private JTextPane myTextPane;
    /**
     * Inner class action that is called when a user clicks a File menu item.
     */
    private FileAction myFileAction;
    /**
     * Inner class action that's called when a user clicks the print menu item.
     */
    private PrintAction myPrintAction;
    /**
     * Represents a filechooser object for saving or opening a text file.
     */
    private JFileChooser myFileChooser;
    /**
     * Represents a filewriter for opening a file to write to.
     */
    private FileWriter myFileWriter;
    /**
     * Represents a BufferedWriter object to write to the chosen file.
     */
    private BufferedWriter myWriter;
    /**
     * Represents a filereader object for opening a file to read from.
     */
    private FileReader myFileReader;
    /**
     * Represents a BufferedReader object to reader from the chosen file.
     */
    private BufferedReader myReader;
    
    /**
     * Parameterized constructor for <code>FileMenu</code> that constructs the File menu
     * that's part of the menubar in the Notepad.
     * @param aTextArea the text area of the Notepad 
     * @custom.post The FIle menu is constructed.
     */
    public FileMenu(final JTextPane aTextPane) {
        super("File");
        setMnemonic(KeyEvent.VK_F);
        
        myTextPane = aTextPane;
        
        myNewMenuItem = new JMenuItem("New");
        myOpenMenuItem = new JMenuItem("Open");
        mySaveMenuItem = new JMenuItem("Save");
        myPrintMenuItem = new JMenuItem("Print");
        myExitMenuItem = new JMenuItem("Exit");
        
        myFileAction = new FileAction();
        myPrintAction = new PrintAction();
        
        myNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        myNewMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                final int response = JOptionPane.showConfirmDialog(null,
                               "Beginning a new text file will remove all contents of the "
                               + "notepad. Are you sure?", "New", JOptionPane.YES_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    myTextPane.setText(null);
                    myTextPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
                }
            }
            
        });
        
        myOpenMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        myOpenMenuItem.addActionListener(myFileAction);
        
        mySaveMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        mySaveMenuItem.addActionListener(myFileAction);
        
        myPrintMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
        myPrintMenuItem.addActionListener(myPrintAction);
        
        myExitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        myExitMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent anEvent) {
                final int response = JOptionPane.showConfirmDialog(null,
                               "If you exit, you will lose all unsaved text files. "
                               + "Are you sure?", "New", JOptionPane.YES_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        add(myNewMenuItem);
        add(myOpenMenuItem);
        add(mySaveMenuItem);
        add(myPrintMenuItem);
        addSeparator();
        add(myExitMenuItem);
    }
    
    /**
     * Private inner class that represents the FileAction whenever a user selects a file
     * menu item to open or save a file.
     * 
     * @custom.post FIleAction is implemented on menu item
     */
    private class FileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            if (myFileChooser == null) {
                myFileChooser = new JFileChooser();
            }
            
            final Object source = anEvent.getSource();
            
            myFileChooser.resetChoosableFileFilters();
            
            int select = -1;
            
            if (source == mySaveMenuItem) {
                myFileChooser.setFileFilter(new FileNameExtensionFilter
                ("TXT format (*.txt)", "txt"));
                myFileChooser.setFileFilter(new FileNameExtensionFilter
                ("RTF format (*.rtf)", "rtf"));
                select = myFileChooser.showSaveDialog(null);
            } else if (source == myOpenMenuItem) {
                myFileChooser.setFileFilter(new FileNameExtensionFilter
                ("Text Files", "txt"));
                select = myFileChooser.showOpenDialog(null);
            }
            
            File result;
            
            if (select == JFileChooser.APPROVE_OPTION) {
                result = myFileChooser.getSelectedFile();
                if (result == null) {
                    return;
                }
                if (source == mySaveMenuItem) {
                    final String extension =
                            myFileChooser.getFileFilter().getDescription().substring(0, 3);
                    try {
                        final String file = result.getPath() + "." + extension;
                        myFileWriter = new FileWriter(new File(file));
                        myWriter = new BufferedWriter(myFileWriter);
                        myTextPane.write(myWriter);
                        myWriter.close();
                    } catch (final IOException e) {
                        System.out.println("Unable to write to file");
                    }
                } else if (source == myOpenMenuItem) {
                    try {
                        myFileReader = new FileReader(result);
                        myReader = new BufferedReader(myFileReader);
                        myTextPane.read(myReader, null);
                        myReader.close();
                    } catch (final FileNotFoundException e) {
                        System.out.println("Unable to open file");
                    } catch (final IOException e) {
                        System.out.println("Unable to read contents of file");
                    }
                }
            }
        }
    }
    
    /**
     * Private inner class that represents the PrintAction whenever a user selects a file
     * menu item to print a file.
     * 
     * @custom.post PrintAction is implemented on print menu item
     */
    private class PrintAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            try {
                myTextPane.print();
            } catch (final PrinterException e) {
                System.out.println("Unable to print text contents");
            }
        }
    }
}
