import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerPage implements ActionListener {

    //Frame
    JFrame f = new JFrame("Customer Page - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel toptopPanel = new JPanel();
    JPanel detailsPanel = new JPanel();

    //Buttons
    JButton exitButton = new JButton("Exit");
    JButton browseButton = new JButton("Create new order");
    JButton saveDetailsButton = new JButton("Save changes");
    JButton deleteButton = new JButton("Cancel the order");


    //Main Labels
    JLabel pageLabel = new JLabel("CUSTOMER PAGE");
    JLabel detailsLabel = new JLabel("Your Personal Details: ");
    JLabel ordersLabel = new JLabel("Your Orders: ");
    JLabel explainLabel = new JLabel(
            "Choose the order and click the button below to cancel it");

    // Details Labels

    JLabel nameLabel = new JLabel("Forename: ");
    JLabel surnameLabel = new JLabel("Surname: ");
    JLabel houseLabel = new JLabel("House Number: ");
    JLabel roadLabel = new JLabel("Road Name: ");
    JLabel cityLabel = new JLabel("City: ");
    JLabel postcodeLabel = new JLabel("Postcode: ");

    JLabel customerIdLabel = new JLabel();



    //Text Fields
    JTextField firstname = new JTextField("                 ");
    JTextField surname = new JTextField("                 ");
    JTextField houseNo = new JTextField("        ");
    JTextField roadName = new JTextField("                   ");
    JTextField cityName = new JTextField("                 ");
    JTextField postcode = new JTextField("             ");

    JTable ordersTable = new JTable(new DefaultTableModel(new Object[]{"Order ID", "Date", "Frame-set", "Handlebar", "wheels", "total price", "status" },0))
    {
        //MAKE TABLE UNEDITABLE BY USER
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel orderModel = (DefaultTableModel) ordersTable.getModel();


    public CustomerPage(int customerId) {

        ArrayList<String> customerDetails = DBDriver.customerDetails(customerId);
        firstname.setText(customerDetails.get(0));
        surname.setText(customerDetails.get(1));
        houseNo.setText(customerDetails.get(2));
        roadName.setText(customerDetails.get(3));
        cityName.setText(customerDetails.get(4));
        postcode.setText(customerDetails.get(5));

        //Setting layouts
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        toptopPanel.setLayout(new BoxLayout(toptopPanel, BoxLayout.Y_AXIS));

        //Adding panels to the main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.add(middlePanel);

        middlePanel.add(topPanel);
        topPanel.add(toptopPanel);
        topPanel.add(detailsPanel);


        //Adding Main Buttons
        leftPanel.add(exitButton);
        exitButton.setMargin(new Insets(5,5,5,5));
        exitButton.setBackground(new Color(59, 89, 182));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));

        rightPanel.add(browseButton);
        browseButton.setMargin(new Insets(5,5,5,5));
        browseButton.setBackground(new Color(59, 89, 182));
        browseButton.setForeground(Color.WHITE);
        browseButton.setFont(new Font("Arial", Font.BOLD, 20));

        //Adding Main Labels
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptopPanel.add(pageLabel);
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 30)));
        toptopPanel.add(detailsLabel);
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 50)));

        //Customer Details
        customerIdLabel.setText(""+customerId);
        customerIdLabel.setVisible(false);
        detailsPanel.add(customerIdLabel);
        detailsPanel.add(nameLabel);
        detailsPanel.add(firstname);
        detailsPanel.add(surnameLabel);
        detailsPanel.add(surname);
        detailsPanel.add(houseLabel);
        detailsPanel.add(houseNo);
        detailsPanel.add(roadLabel);
        detailsPanel.add(roadName);
        detailsPanel.add(cityLabel);
        detailsPanel.add(cityName);
        detailsPanel.add(postcodeLabel);
        detailsPanel.add(postcode);
        detailsPanel.add(Box.createRigidArea(new Dimension(50, 10)));

        detailsPanel.add(saveDetailsButton);
        saveDetailsButton.setMargin(new Insets(5,5,5,5));
        saveDetailsButton.setBackground(new Color(59, 89, 182));
        saveDetailsButton.setForeground(Color.WHITE);
        saveDetailsButton.setFont(new Font("Arial", Font.BOLD, 20));

        //Orders Labels
        topPanel.add(ordersLabel);
        topPanel.add(Box.createRigidArea(new Dimension(50, 10)));
        topPanel.add(explainLabel);
        topPanel.add(Box.createRigidArea(new Dimension(50, 10)));

        //Delete order button
        topPanel.add(deleteButton);
        deleteButton.setBackground(new Color(59, 89, 182));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));


        topPanel.add(Box.createRigidArea(new Dimension(50, 30)));

        //Formatting Labels
        pageLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        detailsLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        ordersLabel.setFont(new Font("Verdana", Font.BOLD, 17));


        //Scroll Panel

        //scrollPanel.setViewportView(table);

        // Data to be displayed in the JTable

        ArrayList<String> orderList = DBDriver.allOrdersFromCustomer(customerId);
        for (String s : orderList) {
            orderModel.addRow(s.split(","));
        }

        //Table
        ordersTable.setBounds(30, 40, 250, 300);
        ordersTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        ordersTable.setRowHeight(24);
        ordersTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        ordersTable.getTableHeader().setReorderingAllowed(false);

        //ordersTable.setCell

        JScrollPane scrollPanel = new JScrollPane(ordersTable);
        middlePanel.add(scrollPanel);


        //Buttons actions
        exitButton.addActionListener(this);
        browseButton.addActionListener(this);
        deleteButton.addActionListener(this);
        saveDetailsButton.addActionListener(this);

        f.add(leftPanel, BorderLayout.WEST);
        f.add(rightPanel, BorderLayout.EAST);
        f.add(middlePanel, BorderLayout.CENTER);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton) {
            f.dispose();
            new HomePage();
        }
        else if(e.getSource() == browseButton) {
            f.dispose();
            new BrowsePage();
        }
        else if(e.getSource() == saveDetailsButton)
        {
            //Validate Details
            //Prompt if they want to update their details
            //Update details
            if(firstname.getText().length() == 0 ||
                    surname.getText().length() == 0 ||
                    houseNo.getText().length() == 0 ||
                    roadName.getText().length() == 0 ||
                    cityName.getText().length() == 0 ||
                    postcode.getText().length() == 0) {

                JOptionPane.showMessageDialog(f, "Please provide information in all fields to continue");
            }
            else if (!DBDriver.isAlpha(firstname.getText()) || !DBDriver.isAlpha(surname.getText()))
            {
                JOptionPane.showMessageDialog(f, "Invalid Forename/Surname. They can contain only letters e.g. Smith");
            }
            else if(!DBDriver.isNoOrAlpha(houseNo.getText()))
            {
                JOptionPane.showMessageDialog(f, "Invalid House Number. It can contain only letters and numbers e.g. 18 ,18B, B");
            }
            else if(!DBDriver.isAlphaOrSpace(roadName.getText()) || !DBDriver.isAlphaOrSpace(cityName.getText()))
            {
                JOptionPane.showMessageDialog(f, "Invalid Road Name/City Name. They can contain only letters and Spaces e.g. Newton le Willows, Manchester");
            }
            else if(!DBDriver.isPostcode(postcode.getText()))
            {
                JOptionPane.showMessageDialog(f, "Invalid Postcode. It must be a valid UK postcode which contains only letters, numbers and a single space. Valid Formats '## ###', '### ###','#### ###', e.g. BT23 6QS");
            }
            else if(firstname.getText().length()>30 || surname.getText().length()>30 ||
                    houseNo.getText().length()>11 || roadName.getText().length()>30 ||
                    cityName.getText().length()>30){
                JOptionPane.showMessageDialog(f, "Details provided are too long. Please shorten to continue");
            }
            else {
                //Add Customer To DB
                if(DBDriver.confirm("Are you sure you'd like to update your details?") == 0)
                {
                    DBDriver.UpdateCustomer(Integer.parseInt(customerIdLabel.getText()), firstname.getText().toLowerCase(),surname.getText().toLowerCase(),
                            houseNo.getText().toUpperCase(),roadName.getText().toLowerCase(),cityName.getText().toLowerCase(),postcode.getText().toUpperCase());
                    JOptionPane.showMessageDialog(f, "Success!");
                }
            }
        }
        else if(e.getSource() == deleteButton)
        {
            if(ordersTable.getSelectedRow() != -1)
            {
                if(ordersTable.getValueAt(ordersTable.getSelectedRow(),6).equals("Pending"))
                {
                    if(DBDriver.confirm("Are you sure you'd like to delete this order?") == 0)
                    {
                        DBDriver.DeleteOrder(Integer.parseInt(ordersTable.getValueAt(ordersTable.getSelectedRow(),0).toString()));
                        orderModel.removeRow(ordersTable.getSelectedRow());
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(f, "Sorry, you can only cancel orders which are pending, as they have yet to be paid for.");
                }

            }
        }
    }

}
