import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Base message used for deleting profile and when there are no customer profiles
public class GuiBaseMessage extends JFrame implements ActionListener {
    JButton button;
    GuiMainMenu menu;
    JLabel headerLabel, messageLabel;

    // GuiBaseMessage constructor
    GuiBaseMessage(GuiMainMenu mainMenu) {
        // Set menu variable to be mainMenu input
        menu = mainMenu;

        // Return to main menu when window is closed
        final GuiBaseMessage guiBaseMessage = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.returnToMainMenu(guiBaseMessage);
            }
        });

        // Set background, color, and size
        Color c1 = new Color(21, 210, 131);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);

        // Back button
        button = new JButton();
        button.setBounds(175, 200, 150, 40);
        button.setFont(new Font("serif", Font.PLAIN, 20));
        button.addActionListener(this);

        // Header label
        headerLabel = new JLabel();
        headerLabel.setBounds(145, 100, 300, 30);
        headerLabel.setFont(new Font("serif", Font.BOLD, 20));

        // Message label
        messageLabel = new JLabel();
        messageLabel.setBounds(60, 130, 400, 30);
        messageLabel.setFont(new Font("serif", Font.PLAIN, 18));

        // Add components
        this.add(button);
        this.add(headerLabel);
        this.add(messageLabel);

        // Set resizable to false
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        // Return to main menu if back button is clicked
        if (e.getSource() == button) {
            menu.returnToMainMenu(this);
        }
    }
}
