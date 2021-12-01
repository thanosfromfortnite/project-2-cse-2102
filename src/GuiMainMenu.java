import javax.swing.*;
import java.awt.*;
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
        Color c1 = new Color(21, 210, 131);
        this.getContentPane().setBackground(c1);
        this.setTitle("Main Menu");
        this.setLayout(null);
        this.setSize(500, 620);

        // Set select button
        selectButton = new JButton("Select");
        selectButton.setBounds(175, 450, 150, 40);
        selectButton.setFont(new Font("serif", Font.PLAIN, 20));
        selectButton.addActionListener(this);

        // Set header label
        JLabel headerLabel = new JLabel("Integrated Customer System");
        headerLabel.setBounds(50, 20, 500, 50);
        headerLabel.setFont(new Font("serif", Font.PLAIN, 35));

        // JRadio button menu options
        ButtonGroup buttonGroup = new ButtonGroup();
        buttons[0] = new JRadioButton("Create Profile");
        buttons[1] = new JRadioButton("Delete Profile");
        buttons[2] = new JRadioButton("Update Profile");
        buttons[3] = new JRadioButton("Find/Display Profile");
        buttons[4] = new JRadioButton("Display All Profiles");
        buttons[5] = new JRadioButton("Exit");

        // Set properties for menu option buttons
        int yInc = 0;
        for (JRadioButton button : buttons) {
            button.setBounds(150, 100+yInc, 300, 40);
            button.setFont(new Font("serif", Font.PLAIN, 22));
            button.setBackground(null);
            buttonGroup.add(button);
            this.add(button);
            yInc += 50;
        }

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
//                    new GuiFindProfile(this);
                    new GuiFindProfile();
                }
                break;
            case 4:
                // Hide main menu
                this.setVisible(false);
                // Display no profiles screen if customer list is empty, else open display all profiles screen
                if (db.getCustomerList().isEmpty()) {
                    new GuiNoProfilesMsg(this);
                } else {
//                    new GuiDisplayAllProfilesPrompt(this);
                    new GuiDisplayAllProfilesPrompt();
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
