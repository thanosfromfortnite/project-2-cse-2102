import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to view customer profile
public class GuiDisplayProfile extends GuiBaseMenuOption implements ActionListener {
    JLabel[] attributeLabel = new JLabel[13];
    JLabel[] valueLabel = new JLabel[13];
    String[] attributes = {"Admin ID", "First Name", "Last Name", "Address", "Phone Number", "Income", "Status", "Use", "Vehicle Model", "Vehicle Year", "Vehicle Type", "Vehicle Method"};

    // GuiDisplayProfile constructor
    public GuiDisplayProfile(GuiMainMenu mainMenu, CustomerProf customerProf) {
        // Call parent constructor
        super(mainMenu);

        // Remove unneeded fields
        this.remove(adminIDLabel);
        this.remove(adminIDTextField);
        this.remove(lastNameLabel);
        this.remove(lastNameTextField);

        // Set title and size
        this.setTitle("Display Profile");
        this.setSize(500, 620);

        // Header label
        headerLabel.setText("Customer Profile");
        headerLabel.setBounds(125, 20, 300, 50);

        // Get customer profile information
        String[] values = {customerProf.getAdminID(), customerProf.getFirstName(), customerProf.getLastName(), customerProf.getAddress(), customerProf.getPhone(),
                Float.toString(customerProf.getIncome()), customerProf.getStatus(), customerProf.getUse(),
                customerProf.getVehicleInfo().getModel(), customerProf.getVehicleInfo().getYear(), customerProf.getVehicleInfo().getType(), customerProf.getVehicleInfo().getMethod()};

        // Close button
        button.setText("Close");
        button.setBounds(175, 520, 150, 40);

        // Set labels for attribute name and value for customer profile
        for (int i = 0; i < 12; i++) {
            attributeLabel[i] = new JLabel(attributes[i] + ":");
            attributeLabel[i].setBounds(100, 70+32*i, 150, 50);
            attributeLabel[i].setFont(new Font("serif", Font.PLAIN, 18));
            this.add(attributeLabel[i]);

            valueLabel[i] = new JLabel(values[i]);
            valueLabel[i].setBounds(300, 70+32*i, 300, 50);
            valueLabel[i].setFont(new Font("serif", Font.PLAIN, 18));
            this.add(valueLabel[i]);
        }

        // Display screen
        this.setVisible(true);
    }

    public void actionHandler(ActionEvent e) {
        // Return to main menu if close button is clicked
        if (e.getSource() == button) {
            menu.returnToMainMenu(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionHandler(e);
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new display profile screen
            new GuiDisplayProfile(new GuiMainMenu(db), db.findFirstProfile("PA1"));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
