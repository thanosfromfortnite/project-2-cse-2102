import javax.swing.*;
import java.awt.*;
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
    String[] statusTypeOptions = {"Active", "Inactive"};
    String[] useTypeOptions = {"Business", "Personal", "Both"};
    String[] vehicleTypeOptions = {"Sedan", "Hatchback", "Luxury", "Sport", "Other"};
    String[] methodTypeOptions = {"New", "Certified Pre-Owned", "Used", "Other"};

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
        Color c1 = new Color(21, 210, 131);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);

        // Button
        button = new JButton();
        button.setBounds(175, 220, 150, 40);
        button.setFont(new Font("serif", Font.PLAIN, 20));
        button.addActionListener(this);

        // Header label
        headerLabel = new JLabel();
        headerLabel.setBounds(160, 20, 300, 50);
        headerLabel.setFont(new Font("serif", Font.PLAIN, 35));

        // Admin ID label
        adminIDLabel = new JLabel("Admin ID: ");
        adminIDLabel.setBounds(70, 80, 150, 50);
        adminIDLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Last name label
        lastNameLabel = new JLabel ("Last Name: ");
        lastNameLabel.setBounds(70, 130, 150, 50);
        lastNameLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Admin ID text field
        adminIDTextField = new JTextField(20);
        adminIDTextField.setBounds(180, 88, 260, 33);
        adminIDTextField.setFont(new Font("serif", Font.PLAIN, 20));

        // Last name text field
        lastNameTextField = new JTextField(20);
        lastNameTextField.setBounds(180, 138, 260, 33);
        lastNameTextField.setFont(new Font("serif", Font.PLAIN, 20));

        // Default error label
        errorLabel = new JLabel("Customer profile not found.", SwingConstants.CENTER);
        errorLabel.setBounds(0, 175, 500, 33);
        errorLabel.setFont(new Font("serif", Font.BOLD, 15));
        errorLabel.setForeground(Color.red);

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

    // Action handler which subclasses override
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
