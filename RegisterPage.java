import javax.swing.*;
import java.awt.*;

public class RegisterPage {

    //Frame
    JFrame f = new JFrame("Build Your Bike");

    //Panels
    JPanel mainPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel blankPanel = new JPanel();

    //Buttons
    JButton backButton = new JButton("Back");
    JButton registerButton = new JButton("Register");

    public RegisterPage() {

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.add(blankPanel);

        // Setting the layouts
        mainPanel.setLayout(new GridLayout(1, 3));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        //adding some space on the left of the frame
        blankPanel.add(Box.createRigidArea(new Dimension(120, 120)));


        // Left panel with the back button
        leftPanel.add(backButton, BorderLayout.WEST);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        leftPanel.add(Box.createRigidArea(new Dimension(100, 120)));

        //Back button action
        backButton.addActionListener(al -> {
            f.dispose();
            new BrowsePage();
        });

        // Right panel with all the text fields

        // FORENAME - label and text field
        rightPanel.add(Box.createRigidArea(new Dimension(200, 50)));
        JLabel forenameLabel = new JLabel("Please type your forename below");
        forenameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(forenameLabel);

        JTextField forenameField = new JTextField("");
        rightPanel.add(forenameField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        // SURNAME
        JLabel surnameLabel = new JLabel("Please type your surname below");
        surnameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(surnameLabel);

        JTextField surnameField = new JTextField("");
        rightPanel.add(surnameField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        // HOUSE NUMBER
        JLabel houseNoLabel = new JLabel("Please type your house number below");
        houseNoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(houseNoLabel);

        JTextField houseNoField = new JTextField("");
        rightPanel.add(houseNoField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        // ROAD NAME
        JLabel roadLabel = new JLabel("Please type your road name below");
        roadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(roadLabel);

        JTextField roadField = new JTextField("");
        rightPanel.add(roadField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        // CITY NAME
        JLabel cityLabel = new JLabel("Please type your city name below");
        cityLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(cityLabel);

        JTextField cityField = new JTextField("");
        rightPanel.add(cityField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        // POSTCODE
        JLabel postcodeLabel = new JLabel("Please type your postcode below");
        postcodeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        rightPanel.add(postcodeLabel);

        JTextField postcodeField = new JTextField("");
        rightPanel.add(postcodeField);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));

        rightPanel.add(registerButton);
        rightPanel.add(Box.createRigidArea(new Dimension(200, 30)));
        registerButton.setMargin(new Insets(5,5,5,5));
        registerButton.setBackground(new Color(59, 89, 182));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));

        //Register button action
        registerButton.addActionListener(al -> {
            if(forenameField.getText().length() == 0 ||
                    surnameField.getText().length() == 0 ||
                    houseNoField.getText().length() == 0 ||
                    roadField.getText().length() == 0 ||
                    cityField.getText().length() == 0 ||
                    postcodeField.getText().length() == 0) {

                JOptionPane.showMessageDialog(f, "Please provide all the information to continue");
            }
            else {
                f.dispose();
                new PaymentPage();
            }

        });

        f.add(rightPanel, BorderLayout.CENTER);
        f.add(leftPanel, BorderLayout.WEST);
        f.add(blankPanel, BorderLayout.EAST);
        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
