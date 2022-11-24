import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowsePage {

    //Frame
    JFrame f = new JFrame(" Browse - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel westPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    JPanel eastPanel = new JPanel();

    //Buttons
    JButton backButton = new JButton("Back");
    JButton centralOrderButton = new JButton("Order Pre-assembled");
    JButton eastOrderButton = new JButton("Order Your Custom Made");
    //JButton temporaryRegistration = new JButton("Register here to buy a bike");



    public BrowsePage(int customerId) {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(westPanel);
        mainPanel.add(centralPanel);
        mainPanel.add(eastPanel);

        //Setting layout
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));


        //  WEST PANEL

        westPanel.add(backButton);
        backButton.setMargin(new Insets(5, 5, 5, 5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        backButton.addActionListener(al -> {
            f.dispose();
            new HomePage();
        });


        //  CENTRAL PANEL

        // label in the middle panel
        centralPanel.add(Box.createRigidArea(new Dimension(400, 40)));
        JLabel centralLabel = new JLabel("Choose a ready bike to order");
        centralLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        centralPanel.add(centralLabel);

        centralPanel.add(Box.createRigidArea(new Dimension(50, 162)));

        // Combo Box with ready bikes from the database
        ArrayList<String> assembledBikesList = new ArrayList<>(DBDriver.allAssembledBikes());
        String[] bikeStrings = assembledBikesList.toArray(new String[assembledBikesList.size()]);
        JComboBox<String> bikeList = new JComboBox<>(bikeStrings);
        centralPanel.add(bikeList);
        centralPanel.add(Box.createRigidArea(new Dimension(50, 100)));

        //Order button in the central panel
        centralPanel.add(centralOrderButton);
        centralOrderButton.setMargin(new Insets(5, 5, 5, 5));
        centralOrderButton.setBackground(new Color(59, 89, 182));
        centralOrderButton.setForeground(Color.WHITE);
        centralOrderButton.setFont(new Font("Arial", Font.BOLD, 20));


        centralPanel.add(Box.createRigidArea(new Dimension(50, 300)));


        // EAST PANEL

        //Dropdown components options and order button
        eastPanel.add(Box.createRigidArea(new Dimension(50, 40)));
        JLabel eastLabel = new JLabel("Customise your bike by choosing the components ");
        eastLabel.setFont(new Font("Verdana", Font.BOLD, 16));
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
        eastPanel.add(Box.createRigidArea(new Dimension(200, 20)));

        // Combo Box with frame options from the database
        ArrayList<String> frameList = new ArrayList<>(DBDriver.allFramesInStock());
        String[] frameString = frameList.toArray(new String[frameList.size()]);
        JComboBox<String> frameSetList = new JComboBox<>(frameString);
        eastPanel.add(frameSetList);

        eastPanel.add(Box.createRigidArea(new Dimension(50, 80)));

        JLabel handlebarLabel = new JLabel("Choose the style of the handlebar");
        handlebarLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(handlebarLabel);

        // Combo Box with handlebar options from the database
        ArrayList<String> handlebarsList = new ArrayList<>(DBDriver.allHandlebarsInStock());
        String[] handlebarStrings = handlebarsList.toArray(new String[handlebarsList.size()]);
        JComboBox<String> handlebarList = new JComboBox<>(handlebarStrings);
        eastPanel.add(handlebarList);

        eastPanel.add(Box.createRigidArea(new Dimension(200, 80)));

        JLabel wheelsLabel = new JLabel("Choose the wheels for your bike");
        wheelsLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(wheelsLabel);

        // Combo Box with wheels options from the database
        ArrayList<String> wheelList = new ArrayList<>(DBDriver.allWheelsInStock());
        String[] wheelStyleStrings = wheelList.toArray(new String[wheelList.size()]);
        JComboBox<String> wheelStyleList = new JComboBox<>(wheelStyleStrings);
        eastPanel.add(wheelStyleList);

        eastPanel.add(Box.createRigidArea(new Dimension(200, 50)));

        double cost = 10+Double.parseDouble(String.valueOf(handlebarList.getSelectedItem()).split("£")[1])+
                Double.parseDouble(String.valueOf(frameSetList.getSelectedItem()).split("£")[1])+
                Double.parseDouble(String.valueOf(wheelStyleList.getSelectedItem()).split("£")[1]);
        cost = Math.round(cost * 100.0) / 100.0;
        JLabel costLabel = new JLabel("Total Cost(Including Assembly Charge): £"+cost);
        costLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(costLabel);

        eastPanel.add(Box.createRigidArea(new Dimension(200, 40)));

        // Bike name label
        JLabel nameLabel = new JLabel("Your Bike's Name:");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        eastPanel.add(nameLabel);

        // Bike name text field
        JTextField nameField = new JTextField("");
        eastPanel.add(nameField);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 20)));

        // Order Button on the right
        eastPanel.add(eastOrderButton);
        eastOrderButton.setMargin(new Insets(5, 5, 5, 5));
        eastOrderButton.setBackground(new Color(59, 89, 182));
        eastOrderButton.setForeground(Color.WHITE);
        eastOrderButton.setFont(new Font("Arial", Font.BOLD, 20));

        eastPanel.add(Box.createRigidArea(new Dimension(200, 80)));

        //Action Listeners

        eastOrderButton.addActionListener(e -> {
            if (nameField.getText().length() == 0 && !DBDriver.isAlpha(nameField.getText())) {
                JOptionPane.showMessageDialog(f, "Please type a name for your bike that is only letters to proceed");
            } else {
                String bikeName = nameField.getText();
                int frameId = Integer.parseInt(frameSetList.getSelectedItem().toString().split("\\.")[0]);
                int handlebarId = Integer.parseInt(handlebarList.getSelectedItem().toString().split("\\.")[0]);
                int wheelId = Integer.parseInt(wheelStyleList.getSelectedItem().toString().split("\\.")[0]);

                if(customerId!=0)
                {

                    new OrderSumPage(customerId,frameId,handlebarId,wheelId,bikeName);
                }
                else
                {
                    new RegisterPage(frameId,handlebarId,wheelId,bikeName);
                }
                f.dispose();

            }
        });

        centralOrderButton.addActionListener(e -> {
            int assembledBikeId = Integer.parseInt(bikeList.getSelectedItem().toString().split("\\.")[0]);
            if(customerId!=0)
            {
                new OrderSumPage(customerId,assembledBikeId,0,0,"");
            }
            else
            {
                new RegisterPage(assembledBikeId,0,0,"");
            }
            f.dispose();

        });

        wheelStyleList.addActionListener (e -> {
            double totalcost = 10+
                    Double.parseDouble(String.valueOf(handlebarList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(frameSetList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(wheelStyleList.getSelectedItem()).split("£")[1]);

            totalcost = Math.round(totalcost * 100.0) / 100.0;

            costLabel.setText("Total Cost(Including Assembly Charge): £"+totalcost);

        });
        frameSetList.addActionListener (e -> {
            double totalcost = 10+
                    Double.parseDouble(String.valueOf(handlebarList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(frameSetList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(wheelStyleList.getSelectedItem()).split("£")[1]);

            totalcost = Math.round(totalcost * 100.0) / 100.0;

            costLabel.setText("Total Cost(Including Assembly Charge): £"+totalcost);

        });
        handlebarList.addActionListener (e -> {
            double totalcost = 10+
                    Double.parseDouble(String.valueOf(handlebarList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(frameSetList.getSelectedItem()).split("£")[1])+
                    Double.parseDouble(String.valueOf(wheelStyleList.getSelectedItem()).split("£")[1]);
            totalcost = Math.round(totalcost * 100.0) / 100.0;

            costLabel.setText("Total Cost(Including Assembly Charge): £"+totalcost);

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