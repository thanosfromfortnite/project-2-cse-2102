import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

// Initializes database at start up
public class GuiInitializeDB extends JFrame implements ActionListener {
    JButton initButton, cancelButton, submitButton;
    JLabel messageLabel, pathLabel, errorLabel;
    JTextField pathTextField;
    String defaultFile;
    CustomerProfDB db;

    // GuiInitDB constructor
    GuiInitializeDB(String file) throws FileNotFoundException {
        // Set defaultFile variable to file input
        defaultFile = file;
        // Create new customer profile database object
        db = new CustomerProfDB(defaultFile);

        // Set background, title, and size
        Color c1 = new Color(21, 210, 131);
        this.getContentPane().setBackground(c1);
        this.setTitle("Initialize Database");
        this.setLayout(null);
        this.setSize(500, 320);

        // Exit application if this window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize button
        initButton = new JButton("Initialize");
        initButton.setBounds(105, 170, 100, 40);
        initButton.setFont(new Font("serif", Font.PLAIN, 20));
        initButton.addActionListener(this);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 170, 100, 40);
        submitButton.setFont(new Font("serif", Font.PLAIN, 20));
        submitButton.addActionListener(this);

        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(275, 170, 100, 40);
        cancelButton.setFont(new Font("serif", Font.PLAIN, 20));
        cancelButton.addActionListener(this);

        // Message label
        messageLabel = new JLabel("Initialize the database?");
        messageLabel.setBounds(140, 90, 400, 50);
        messageLabel.setFont(new Font("serif", Font.BOLD, 20));

        // Path label
        pathLabel = new JLabel("Path to database: ");
        pathLabel.setBounds(20, 90, 150, 50);
        pathLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Path text field
        pathTextField = new JTextField(20);
        pathTextField.setBounds(170, 98, 300, 35);
        pathTextField.setFont(new Font("serif", Font.PLAIN, 20));

        // Error label
        errorLabel = new JLabel("", SwingConstants.CENTER);

        // Add components
        this.add(initButton);
        this.add(cancelButton);
        this.add(messageLabel);

        // Set resizable to false and visible to true
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initDB(String file) {
        try {
            // Initialize database and print success message if it succeeds
            db.initializeDB(file);
            System.out.println("The database was initialized to " + file);
            System.out.println("----------------------------------------------------------------");
            // Create new main menu
            new GuiMainMenu(db);
            // Close window
            this.dispose();
        } catch (FileNotFoundException e) {
            errorLabel.setText("Error initializing database. File not found.");
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Error initializing database. Improper database format.");
        } catch (NoSuchElementException e) {
            errorLabel.setText("Error initializing database.");
        }

        // Set error label if initializing database fails
        errorLabel.setBounds(new Rectangle(90, 135, 350, 33));
        errorLabel.setFont(new Font("serif", Font.BOLD, 15));
        errorLabel.setForeground(Color.red);
        this.add(errorLabel);
        this.repaint();
    }

    public void actionPerformed(ActionEvent event) {
        // Initialize database to default file if cancel button is clicked
        if (event.getSource() == cancelButton) {
            initDB(defaultFile);
        }
        // Remove unnecessary fields and add required fields if initialize button is clicked
        if (event.getSource() == initButton) {
            this.remove(messageLabel);
            this.remove(initButton);
            this.add(submitButton);
            this.add(pathLabel);
            this.add(pathTextField);
            // Update component
            this.repaint();
        }
        // Initialize database to path inputted in pathTextField if submit button is clicked
        if (event.getSource() == submitButton) {
            initDB(pathTextField.getText());
        }
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        new GuiInitializeDB("src/customers.txt");
    }
}