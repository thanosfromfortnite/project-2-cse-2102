import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// Allows user to choose an option from a menu
public class GuiMainMenu extends JFrame implements ActionListener {
    JButton selectButton;
    JRadioButton[] buttons = new JRadioButton[6];
    CustomerProfDB db;

    // GuiMainMenu constructor
    GuiMainMenu(CustomerProfDB database) {
        // Set db variable to be database input
        db = database;

        // Confirm exit with user when window is closed
        final GuiMainMenu guiMainMenu = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guiMainMenu.setVisible(false);
                new GuiExit(guiMainMenu);
            }
        });

        // Set background, title, and size
        // Need to add styling
        this.setTitle("Main Menu");

        // Set select button
        // Need to add styling
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);

        // Set header label
        // Need to add styling
        JLabel headerLabel = new JLabel("Integrated Customer System");

        // JRadio button menu options
        ButtonGroup buttonGroup = new ButtonGroup();
        buttons[0] = new JRadioButton("Create Profile");
        buttons[1] = new JRadioButton("Delete Profile");
        buttons[2] = new JRadioButton("Update Profile");
        buttons[3] = new JRadioButton("Find/Display Profile");
        buttons[4] = new JRadioButton("Display All Profiles");
        buttons[5] = new JRadioButton("Exit");

        // Set values for menu option buttons
        // Need to add styling

        // Add components
        this.add(selectButton);
        this.add(headerLabel);

        // Set resizable to false and visible to true
        this.setResizable(false);
        this.setVisible(true);
    }

    // Allows user to return to main menu from any screen
    public void returnToMainMenu(JFrame currScreen) {
        this.setVisible(true);
        currScreen.dispose();
    }

    // Executes user selection
    public void actionPerformed(ActionEvent e) {
        // Variable for user selected option
        int choice = -1;
        // Iterate through buttons and set choice variable to be user selected option
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected())
                choice = i;
        }
        switch (choice) {
            case 0:
                // Hide main menu
                this.setVisible(false);
                // Open create profile screen
                new GuiCreateProfile(this);
                break;
            case 1:
                // Hide main menu
                this.setVisible(false);
                // Display no profiles screen if customer list is empty, else open delete profile screen
                if (db.getCustomerList().isEmpty()) {
                    new GuiNoProfilesMsg(this);
                } else {
                    new GuiDeleteProfile(this);
                }
                break;
            case 2:
                // Hide main menu
                this.setVisible(false);
                // Display no profiles screen if customer list is empty, else open update profile screen
                if (db.getCustomerList().isEmpty()) {
                    new GuiNoProfilesMsg(this);
                } else {
                    new GuiUpdateProfile(this);
                }
                break;
            case 3:
                // Hide main menu
                this.setVisible(false);
                // Display no profiles screen if customer list is empty, else open find profile screen
                if (db.getCustomerList().isEmpty()) {
                    new GuiNoProfilesMsg(this);
                } else {
                    new GuiFindProfile(this);
                }
                break;
            case 4:
                // Hide main menu
                this.setVisible(false);
                // Display no profiles screen if customer list is empty, else open display all profiles screen
                if (db.getCustomerList().isEmpty()) {
                    new GuiNoProfilesMsg(this);
                } else {
                    new GuiDisplayAllProfilesPrompt(this);
                }
                break;
            case 5:
                // Hide main menu
                this.setVisible(false);
                // Open exit confirmation screen
                new GuiExit(this);
                break;
            default:
                // Do nothing for default
                break;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            db.initializeDB("src/customers.txt");
            new GuiMainMenu(db);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

}
