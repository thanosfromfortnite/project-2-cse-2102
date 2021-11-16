import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Base menu option for create, delete, update, display, exit, and find profile classes
public class GuiBaseMenuOption extends JFrame implements ActionListener {
    JButton button;
    JTextField adminIDTextField, lastNameTextField;
    JLabel headerLabel, adminIDLabel, lastNameLabel, errorLabel;
    GuiMainMenu menu;
    // Need to add other selection options

    // GuiBaseMenuOption constructor
    GuiBaseMenuOption(GuiMainMenu mainMenu) {
        // Set menu variable to be mainMenu input
        menu = mainMenu;

        // Return to main menu when window is closed
        final GuiBaseMenuOption guiBaseMenuOption = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.returnToMainMenu(guiBaseMenuOption);
            }
        });

        // Set background, color, and size

        // Button
        // Need to add styling
        button = new JButton();
        button.addActionListener(this);

        // Header label
        // Need to add styling
        headerLabel = new JLabel();

        // Admin ID label
        // Need to add styling
        adminIDLabel = new JLabel("Admin ID: ");

        // Last name label
        // Need to add styling
        lastNameLabel = new JLabel ("Last Name: ");

        // Admin ID text field
        // Need to add styling
        adminIDTextField = new JTextField(20);

        // Last name text field
        // Need to add styling
        lastNameTextField = new JTextField(20);

        // Default error label
        // Need to add styling
        errorLabel = new JLabel("Customer profile not found.", SwingConstants.CENTER);

        // Add components
        this.add(button);
        this.add(headerLabel);
        this.add(adminIDLabel);
        this.add(lastNameLabel);
        this.add(adminIDTextField);
        this.add(lastNameTextField);

        // Set resizable to false
        this.setResizable(false);
    }

    // Action handler which subclassees override
    public void actionHandler() {}

    public void actionPerformed(ActionEvent event) {
        // Call action handler and close window if button is clicked
        // Catch exception, display error label, and update window if there is an exception
        if (event.getSource() == button) {
            try {
                actionHandler();
                this.dispose();
            } catch (Exception e) {
                this.add(errorLabel);
                this.repaint();
            }
        }
    }
}
