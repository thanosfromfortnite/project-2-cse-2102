import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Confirms exit with user
public class GuiExit extends GuiBaseMenuOption implements ActionListener {
    JButton exitButton;

    // GuiExit constructor
    GuiExit(GuiMainMenu mainMenu) {
        super(mainMenu);

        // Remove unneeded fields *Need to do

        this.setTitle("Exit");

        // Exit button
        // Need to add styling
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        // Cancel button
        // Need to add styling
        button.setText("Cancel");

        // Confirm exit label
        // Need to add styling
        JLabel exitLabel = new JLabel("Are you sure you want to exit?");

        // Add components
        this.add(exitButton);
        this.add(exitLabel);

        // Set visible to true
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button) {
            menu.returnToMainMenu(this);
        }
        if (event.getSource() == exitButton) {
            try {
                menu.db.writeAllCustomerProf();
                System.exit(0);
            } catch (IOException e) {
                JLabel warningLabel = new JLabel("Error writing customer profiles to database.", SwingConstants.CENTER);
                // Need to add styling
                this.add(warningLabel);
                this.repaint();
            }
        }
    }

    // Test client
    public static void main(String[] args) throws FileNotFoundException {
        // Create new database object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize database
            db.initializeDB("src/customers.txt");
            // Create new exit
            new GuiExit(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch file not found exception and print message to user
            System.out.println("File not found.");
        }
    }
}
