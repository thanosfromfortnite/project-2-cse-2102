import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Confirms exit with user
public class GuiExit extends GuiBaseMenuOption implements ActionListener {
    JButton exitButton;

    // GuiExit constructor
    GuiExit(GuiMainMenu mainMenu) {
        super(mainMenu);

        // Remove unneeded fields
        this.remove(adminIDLabel);
        this.remove(lastNameLabel);
        this.remove(adminIDTextField);
        this.remove(lastNameTextField);

        // Set title
        this.setTitle("Exit");

        // Exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(120, 170, 100, 40);
        exitButton.setFont(new Font("serif", Font.PLAIN, 20));
        exitButton.addActionListener(this);

        // Cancel button
        button.setText("Cancel");
        button.setBounds(275, 170, 100, 40);

        // Confirm exit label
        JLabel exitLabel = new JLabel("Are you sure you want to exit?");
        exitLabel.setBounds(123, 90, 400, 50);
        exitLabel.setFont(new Font("serif", Font.BOLD, 20));

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
                warningLabel.setBounds(new Rectangle(50, 105, 400, 50));
                warningLabel.setFont(new Font("serif", Font.BOLD, 15));
                warningLabel.setForeground(Color.red);
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
