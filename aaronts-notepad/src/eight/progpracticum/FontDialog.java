/**
 * Aaron Schraufnagel.
 */
package eight.progpracticum;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * The <code>FontDialog</code> class builds the Dialog box for the user to select font
 * settings.
 * 
 * @author Aaron Schraufnagel
 * @version Nov. 24th, 2013
 * @see java.awt.BorderLayout
 * @see java.awt.Component
 * @see java.awt.Font
 * @see java.awt.Frame
 * @see java.awt.GraphicsEnvironment
 * @see java.awt.GridLayout
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.ActionListener
 * @see java.awt.event.ItemEvent
 * @see java.awt.event.ItemListener
 * @see javax.swing.JButton
 * @see javax.swing.JComboBox
 * @see javax.swing.JDialog
 * @see javax.swing.JLabel
 * @see javax.swing.JPanel
 * @see javax.swing.JTextField
 * @see javax.swing.SwingUtilities
 * 
 * @cust.inv None
 */
@SuppressWarnings("serial")
public class FontDialog extends JPanel {
    /**
     * Represents the panel that holds the textarea.
     */
    private JPanel myPanel;
    /**
     * Represents the panel that holds the OK and Cancel buttons.
     */
    private JPanel myButtonPanel;
    /**
     * Represents the label that indicates font type.
     */
    private JLabel myTypeLabel;
    /**
     * Represents the label that indicates font style.
     */
    private JLabel myStyleLabel;
    /**
     * Represents the label that indicates font size.
     */
    private JLabel mySizeLabel;
    /**
     * Represents the text field that indicates font type.
     */
    private JTextField myTypeText;
    /**
     * Represents the text field that indicates font style.
     */
    private JTextField myStyleText;
    /**
     * Represents the text field that indicates font size.
     */
    private JTextField mySizeText;
    /**
     * Represents the combobox with choices for font type.
     */
    private JComboBox<String> myTypeBox;
    /**
     * Represents the combobox with choices for font style.
     */
    private JComboBox<String> myStyleBox;
    /**
     * Represents the combobox with choices for font size.
     */
    private JComboBox<Integer> mySizeBox;
    /**
     * Represents the OK button.
     */
    private JButton myOkButton;
    /**
     * Represents the cancel button.
     */
    private JButton myCancelButton;
    
    /**
     * Represents the dialog box for font selection.
     */
    private JDialog myDialog;
    /**
     * Represents the font that the user selects.
     */
    private Font myFont;
    /**
     * Action event that is fired whenever a font is chosen.
     */
    private FontBoxAction myFontBoxAction;
    /**
     * Boolean that indicates if the user has selected OK button.
     */
    private boolean myOk;
    
    /**
     * Constructor that displays the FontDialog to the user.
     * 
     * @custom.post FontDialog is displayed to the user
     */
    public FontDialog() {
        setLayout(new BorderLayout());
        
        myPanel = new JPanel();
        
        myTypeLabel = new JLabel("Type:");
        myStyleLabel = new JLabel("Style:");
        mySizeLabel = new JLabel("Size:");
        
        myTypeText = new JTextField();
        myStyleText = new JTextField();
        mySizeText = new JTextField();
        
        myTypeBox = new JComboBox<>();
        myStyleBox = new JComboBox<>();
        mySizeBox = new JComboBox<>();
        
        setupDialog();
        
        myFontBoxAction = new FontBoxAction();
        myTypeBox.addItemListener(myFontBoxAction);
        myStyleBox.addItemListener(myFontBoxAction);
        mySizeBox.addItemListener(myFontBoxAction);
        
        myFont = new Font(myTypeText.getText(), Font.PLAIN, 12);
        
        myPanel.setLayout(new GridLayout(3, 3));
        myPanel.add(myTypeLabel);
        myPanel.add(myStyleLabel);
        myPanel.add(mySizeLabel);
        myPanel.add(myTypeText);
        myPanel.add(myStyleText);
        myPanel.add(mySizeText);
        myPanel.add(myTypeBox);
        myPanel.add(myStyleBox);
        myPanel.add(mySizeBox);
        add(myPanel, BorderLayout.CENTER);

        myOkButton = new JButton("Ok");
        myOkButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent anEvent) {
                myOk = true;
                myDialog.setVisible(false);
            }
        });

        myCancelButton = new JButton("Cancel");
        myCancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent anEvent) {
                myDialog.setVisible(false);
            }
        });

        myButtonPanel = new JPanel();
        myButtonPanel.add(myOkButton);
        myButtonPanel.add(myCancelButton);
        add(myButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * The font components of the dialog are setup.
     * 
     * @custom.post The comboboxes and textfields for each font customization are
     * initialized
     */
    public void setupDialog() {
        myTypeText.setEditable(false);
        myStyleText.setEditable(false);
        mySizeText.setEditable(false);
        
        final GraphicsEnvironment gEnvironment =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        final Font[] fonts = gEnvironment.getAllFonts();
        
        for (Font f: fonts) {
            myTypeBox.addItem(f.getFamily());
            if ("sansserif".equals(f.getFamily().toString().toLowerCase())) {
                myTypeBox.setSelectedItem(f.getFamily());
            }
        }
        myTypeText.setText(myTypeBox.getSelectedItem().toString());
        myTypeText.setFont(new Font(myTypeText.getText(), Font.PLAIN, 12));
        
        myStyleBox.addItem("Regular");
        myStyleBox.addItem("Italic");
        myStyleBox.addItem("Bold");
        myStyleBox.addItem("Bold Italic");
        myStyleText.setText(myStyleBox.getSelectedItem().toString());
        myStyleText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        
        final int[] fontSizes = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48,
                                 72};
        for (int i = 0; i < fontSizes.length; i++) {
            mySizeBox.addItem(fontSizes[i]);
        }
        mySizeBox.setSelectedItem(12);
        mySizeText.setText(mySizeBox.getSelectedItem().toString());
        mySizeText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    }
    
    /**
     * Show the panel in a dialog.
     * @param aParent a component in the owner frame or null
     * @param aTitle the dialog window title
     * @return myOk boolean indicating the user has selected OK
     * @custom.post The dialog is shown to the user
     */
    public boolean showDialog(Component aParent, String aTitle) {
        myOk = false;

        Frame owner = null;
        if (aParent instanceof Frame) {
            owner = (Frame) aParent;
        } else {
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, aParent);
        }

        if (myDialog == null || myDialog.getOwner() != owner) {
            myDialog = new JDialog(owner, true);
            myDialog.add(this);
            myDialog.pack();
            myDialog.setLocationRelativeTo(null);
        }

        myDialog.setTitle(aTitle);
        myDialog.setVisible(true);
        return myOk;
    }
    
    /**
     * Returns the chosen font.
     * @return myFont the chosen font is returned
     */
    public Font getMyFont() {
        return myFont;
    }

    /**
     * Private inner class that is impelemented whenever a font is selected.
     */
    private class FontBoxAction implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent anEvent) {
            final Object source = anEvent.getItemSelectable();
            
            if (source == myTypeBox) {
                myTypeText.setText(myTypeBox.getSelectedItem().toString());
                myTypeText.setFont(new Font(myTypeText.getText(), Font.PLAIN, 12));
            } else if (source == myStyleBox) {
                myStyleText.setText(myStyleBox.getSelectedItem().toString());
                if (myStyleBox.getSelectedIndex() == 0) {
                    myStyleText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
                } else if (myStyleBox.getSelectedIndex() == 1) {
                    myStyleText.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
                } else if (myStyleBox.getSelectedIndex() == 2) {
                    myStyleText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
                } else if (myStyleBox.getSelectedIndex() == 3) {
                    myStyleText.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD,
                                                 12));
                }
            } else if (source == mySizeBox) {
                mySizeText.setText(mySizeBox.getSelectedItem().toString());
            }
            
            if (myStyleBox.getSelectedIndex() == 0) {
                myFont = new Font(myTypeText.getText(), Font.PLAIN,
                                  Integer.parseInt(mySizeText.getText()));
            } else if (myStyleBox.getSelectedIndex() == 1) {
                myFont = new Font(myTypeText.getText(), Font.ITALIC,
                                  Integer.parseInt(mySizeText.getText()));
            } else if (myStyleBox.getSelectedIndex() == 2) {
                myFont = new Font(myTypeText.getText(), Font.BOLD,
                                  Integer.parseInt(mySizeText.getText()));
            } else if (myStyleBox.getSelectedIndex() == 3) {
                myFont = new Font(myTypeText.getText(), Font.ITALIC | Font.BOLD,
                                  Integer.parseInt(mySizeText.getText()));
            }
        }
    }
}
