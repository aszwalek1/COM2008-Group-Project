import javax.swing.*;
import java.awt.*;

public class BrowsePage {

    //Frame
    JFrame f = new JFrame(" Browse - Build-a-Bike Ltd.");

    //Buttons
    JButton backButton = new JButton("Back");
    JButton centralOrderButton = new JButton("Order");
    JButton eastOrderButton = new JButton("Order");
    JButton temporaryRegistration = new JButton("Register here to buy a bike");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel westPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    JPanel eastPanel = new JPanel();

    public BrowsePage() {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(westPanel);
        mainPanel.add(centralPanel);
        mainPanel.add(eastPanel);

        //Setting layout
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));


        //  WEST PANEL

        westPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 12));

        backButton.addActionListener(al -> {
            f.dispose();
            new HomePage();
        });


        //  CENTRAL PANEL

        // label in the middle panel
        centralPanel.add(Box.createRigidArea(new Dimension(50, 40)));
        JLabel centralLabel = new JLabel("Choose a ready bike to order");
        centralLabel.setFont(new Font("Verdana",Font.BOLD, 18));
        centralPanel.add(centralLabel);

        //Dropdown options and order button
        centralPanel.add(Box.createRigidArea(new Dimension(50, 140)));
        String[] bikeStrings = { "Bike1", "Bike2", "Bike3", "Bike4", "Bike5" };
        JComboBox<String> bikeList = new JComboBox<>(bikeStrings);
        centralPanel.add(bikeList);
        centralPanel.add(Box.createRigidArea(new Dimension(50, 100)));

        //Label for the bike name
        JLabel bikeNameLabel = new JLabel("Type the name for your bike in the field below");
        bikeNameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        centralPanel.add(bikeNameLabel);

        //Text field for the bike name
        JTextField bikeNameField = new JTextField("");
        centralPanel.add(bikeNameField);
        centralPanel.add(Box.createRigidArea(new Dimension(200, 20)));

        //Order button in the central panel
        centralPanel.add(centralOrderButton);
        centralOrderButton.setMargin(new Insets(5,5,5,5));
        centralOrderButton.setBackground(new Color(59, 89, 182));
        centralOrderButton.setForeground(Color.WHITE);
        centralOrderButton.setFont(new Font("Arial", Font.BOLD, 20));



        centralPanel.add(Box.createRigidArea(new Dimension(50, 300)));

        //Action Listener for the Order button
        centralOrderButton.addActionListener(e -> {
            if(bikeNameField.getText().length() == 0) {
                JOptionPane.showMessageDialog(f, "Please type the bike name to proceed");
            }
            else{
                String bikeName = bikeNameField.getText();
                f.dispose();
                new CustomerPage();
            }

        });


        // TEMPORARY BUTTON TO ACCESS THE REGISTRATION PAGE
        // SHOPPERS ARE SUPPOSED TO CLICK THE ORDER BUTTON TO GO TO THE REGISTRATION PAGE
        // AND LOGGED IN CUSTOMERS DO NOT GO TO THE REGISTRATION PAGE AT ANY POINT


        temporaryRegistration.setMargin(new Insets(5,5,5,5));
        temporaryRegistration.setBackground(new Color(59, 89, 182));
        temporaryRegistration.setForeground(Color.WHITE);
        temporaryRegistration.setFont(new Font("Arial", Font.BOLD, 20));
        centralPanel.add(temporaryRegistration);

        temporaryRegistration.addActionListener(e -> {
            f.dispose();
            new RegisterPage();
        });



        // EAST PANEL

        //Dropdown components options and order button
        eastPanel.add(Box.createRigidArea(new Dimension(50, 40)));
        JLabel eastLabel = new JLabel("Customise your bike by choosing the components (£10 Assembly Charge) ");
        eastLabel.setFont(new Font("Verdana",Font.BOLD, 16));
        eastPanel.add(eastLabel);
        eastPanel.add(Box.createRigidArea(new Dimension(50, 80)));

        JLabel frameLabel1 = new JLabel("Choose the frame for your bike");
        frameLabel1.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(frameLabel1);

        JLabel frameLabel2 = new JLabel("(frame-set includes a frame and frame forks as standard)  ");
        frameLabel2.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(frameLabel2);

        JLabel frameLabel3 = new JLabel("(it comes with pre-specified gears to suit the frame) ");
        frameLabel3.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(frameLabel3);

        String[] frameString = { "frame set size1 with shocks", "frame set size2 no shocks",
                "frame set size3 with shocks",
                "frame set size4 no shocks"};
        JComboBox<String> frameSetList = new JComboBox<>(frameString);
        eastPanel.add(frameSetList);
        eastPanel.add(Box.createRigidArea(new Dimension(50, 80)));

        JLabel handlebarLabel = new JLabel("Choose the style of the handlebar");
        handlebarLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(handlebarLabel);
        String[] handlebarStrings = { "Straight Handlebar", "High Handlebar", "Dropped Handlebar" };
        JComboBox<String> handlebarList = new JComboBox<>(handlebarStrings);
        eastPanel.add(handlebarList);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 80)));

        JLabel wheelsLabel = new JLabel("Choose the wheels for your bike");
        wheelsLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(wheelsLabel);
        String[] wheelStyleStrings = { "Road style, 40cm, with rim brake system",
                "Mountain Style, 50cm, with disk brake system",
                "Hybrid Style, 65cm, with rim brake system",
                "Mountain Style, 60cm, with disk brake system" };
        JComboBox<String> wheelStyleList = new JComboBox<>(wheelStyleStrings);
        eastPanel.add(wheelStyleList);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 80)));

        JLabel nameLabel = new JLabel("Type the name for your bike in the field below");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(nameLabel);



        JTextField nameField = new JTextField("");
        eastPanel.add(nameField);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 20)));

        eastPanel.add(eastOrderButton);
        eastOrderButton.setMargin(new Insets(5,5,5,5));
        eastOrderButton.setBackground(new Color(59, 89, 182));
        eastOrderButton.setForeground(Color.WHITE);
        eastOrderButton.setFont(new Font("Arial", Font.BOLD, 20));

        eastPanel.add(Box.createRigidArea(new Dimension(200, 110)));



        //Action Listener for the Order button

        /*
            This should go to the payment page if it is a logged in customer
            or go to register page if no one is logged in

         */
        eastOrderButton.addActionListener(e -> {
            if(nameField.getText().length() == 0) {
                JOptionPane.showMessageDialog(f, "Please type the bike name to proceed");
            }
            else {
                String bikeName = nameField.getText();
                System.out.println(bikeName);
                f.dispose();
                new CustomerPage();
            }
        });



        //add panels to the frame
        f.add(westPanel, BorderLayout.WEST);
        f.add(centralPanel, BorderLayout.CENTER);
        f.add(eastPanel, BorderLayout.EAST);


        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
}


        /*          USING DATABASE FOR DROP-DOWNS ??
        //Drop-Down menu for ready bikes
        //Get Bikes from a table from the Database
        ArrayList<String> readyBikes = new ArrayList<>();
        try {
            Connection con = DBDriver.getConnection();
            String query = "select bikes from DBTable";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                //shows Name data in combobox
                //Add data to the array list
                readyBikes.add(rs.getString("BikeID")); //?????????????????
            }
            pst.close();
            DBDriver.closeConnection(con);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return readyBikes;
        */
