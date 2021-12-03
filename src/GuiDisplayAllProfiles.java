import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to view all customer profiles under an admin ID
public class GuiDisplayAllProfiles extends GuiDisplayProfile implements ActionListener {
    JButton nextButton;
    JLabel headerLabel;
    String id;

    // GuiDisplayAllProfiles constructor
    GuiDisplayAllProfiles(GuiMainMenu mainMenu, CustomerProf firstProf, String adminID) {
        // Call parent constructor
        super(mainMenu, firstProf);
        // Set title
        this.setTitle("Display All Profiles");
        // Set id variable to adminID input
        id = adminID;

        // Add next button and move close button if more than one customer profile exists
        if (mainMenu.db.existsMoreThanOne(id)) {
            // Next profile button
            nextButton = new JButton("Next Profile");
            nextButton.setBounds(275, 520, 150, 40);
            nextButton.setFont(new Font("serif", Font.PLAIN, 20));
            nextButton.addActionListener(this);
            this.add(nextButton);
            // Close button
            button.setBounds(75, 520, 150, 40);
        } else {
            // Center close button
            button.setBounds(175, 520, 150, 40);
        }

        button.addActionListener(this);

        // Header label
        headerLabel = new JLabel("Customer Profile");
        headerLabel.setBounds(150, 20, 300, 50);
        headerLabel.setFont(new Font("serif", Font.PLAIN, 35));

        // Display screen
        this.setVisible(true);
    }

    @Override
    public void actionHandler(ActionEvent e) {
        // Call parent action handler
        super.actionHandler(e);
        // Execute if next button is clicked
        if (e.getSource() == nextButton) {
            try {
                // Set current profile to next profile under the admin ID
                CustomerProf currentProf = menu.db.findNextProfile();
                while (currentProf != null && !currentProf.getAdminID().equals(id)) {
                    currentProf = menu.db.findNextProfile();
                }
                // Get customer profile information
                String[] values = {currentProf.getAdminID(), currentProf.getFirstName(), currentProf.getLastName(), currentProf.getAddress(), currentProf.getPhone(),
                        Float.toString(currentProf.getIncome()), currentProf.getStatus(), currentProf.getUse(),
                        currentProf.getVehicleInfo().getModel(), currentProf.getVehicleInfo().getYear(), currentProf.getVehicleInfo().getType(), currentProf.getVehicleInfo().getMethod()};
                // Set attributes with corresponding values
                for (int i = 0; i < 12; i++) {
                    valueLabel[i].setText(values[i]);
                    valueLabel[i].setBounds(300, 70+32*i, 300, 50);
                    valueLabel[i].setFont(new Font("serif", Font.PLAIN, 18));
                }
                // Update component
                this.repaint();
            } catch (Exception exception) {
                // Remove next button and center close button if no more customer profiles
                this.remove(nextButton);
                button.setBounds(175, 520, 150, 40);
                this.repaint();
            }
        }
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new display all profiles screen
            new GuiDisplayAllProfiles(new GuiMainMenu(db), db.findFirstProfile(), "PA1");
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
