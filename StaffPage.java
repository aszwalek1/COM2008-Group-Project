import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StaffPage implements ActionListener {

    //Frame
    JFrame f = new JFrame("Staff - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();


    // All Buttons
    JButton backButton = new JButton("Back");
    JButton getBrowseButton = new JButton("Browse");
    JButton setOrderStatusFulfilledButton = new JButton("Fulfilled");
    JButton setOrderStatusConfirmedButton = new JButton("Confirmed");
    JButton customerButton = new JButton("View Customer");
    JButton inventoryButton = new JButton(("View Inventory"));

    // Column Names
    String[] columnNames = {
            "Order ID", "Staff Username", "Customer ID", "Frame-set", "Handlebar", "Wheels", "Order Date", "Total Price", "Status"};

    //Table
    JTable ordersTable = new JTable(new DefaultTableModel(columnNames,0))
    {
        //MAKE TABLE UNEDITABLE BY USER
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel orderModel = (DefaultTableModel) ordersTable.getModel();

    public StaffPage(String username) {

        //Setting layouts
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        //adding panel borders
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding panels to frame
        f.add(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(middlePanel, BorderLayout.NORTH);
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Adding Main Buttons
        bottomPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(getBrowseButton);
        getBrowseButton.setMargin(new Insets(5,5,5,5));
        getBrowseButton.setBackground(new Color(59, 89, 182));
        getBrowseButton.setForeground(Color.WHITE);
        getBrowseButton.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(customerButton);
        customerButton.setMargin(new Insets(5,5,5,5));
        customerButton.setBackground(new Color(59, 89, 182));
        customerButton.setForeground(Color.WHITE);
        customerButton.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(inventoryButton);
        inventoryButton.setMargin(new Insets(5,5,5,5));
        inventoryButton.setBackground(new Color(59, 89, 182));
        inventoryButton.setForeground(Color.WHITE);
        inventoryButton.setFont(new Font("Arial", Font.BOLD, 20));






        middlePanel.add(setOrderStatusConfirmedButton);
        setOrderStatusConfirmedButton.setMargin(new Insets(5,5,5,5));
        setOrderStatusConfirmedButton.setBackground(new Color(59, 89, 182));
        setOrderStatusConfirmedButton.setForeground(Color.WHITE);
        setOrderStatusConfirmedButton.setFont(new Font("Arial", Font.BOLD, 20));
        setOrderStatusConfirmedButton.setToolTipText("<html>This button will set the order status to be \"Confirmed\". <br>" +
                " Only do this when the order has been paid for.</html>");

        middlePanel.add(setOrderStatusFulfilledButton);
        setOrderStatusFulfilledButton.setMargin(new Insets(5,5,5,5));
        setOrderStatusFulfilledButton.setBackground(new Color(59, 89, 182));
        setOrderStatusFulfilledButton.setForeground(Color.WHITE);
        setOrderStatusFulfilledButton.setFont(new Font("Arial", Font.BOLD, 20));
        setOrderStatusFulfilledButton.setToolTipText("<html>This button will set the order status to be \"Fulfilled\". <br>" +
                " Only do this when the order has been paid for and the bike delivered.</html>");



        //Table
        ordersTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        ordersTable.setRowHeight(24);
        ordersTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        ordersTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPanel = new JScrollPane(ordersTable);
        rightPanel.add(scrollPanel);

        populateTable();

        //Buttons actions
        backButton.addActionListener(this);
        customerButton.addActionListener(this);
        getBrowseButton.addActionListener(this);
        setOrderStatusFulfilledButton.addActionListener(this);
        setOrderStatusConfirmedButton.addActionListener(this);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setOrderStatusConfirmedButton.addActionListener(ae -> {
            if(ordersTable.getSelectedRow() != -1) {
                if(!ordersTable.getValueAt(ordersTable.getSelectedRow(),8).equals("Confirmed")) {
                    if(DBDriver.confirm("Are you sure you'd like to change this order's status to: Confirmed ?") == 0) {
                        DBDriver.UpdateOrderStatus(Integer.parseInt(ordersTable.getValueAt(ordersTable.getSelectedRow(),0).toString()), "Confirmed", username);
                        populateTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "This order's status is already: Confirmed");
                }
            }
        });

        setOrderStatusFulfilledButton.addActionListener(ae -> {
            if (ordersTable.getSelectedRow() != -1) {
                if (!ordersTable.getValueAt(ordersTable.getSelectedRow(), 8).equals("Fulfilled")) {
                    if (DBDriver.confirm("Are you sure you'd like to change this order's status to: Fulfilled ?") == 0) {
                        DBDriver.UpdateOrderStatus(Integer.parseInt(ordersTable.getValueAt(ordersTable.getSelectedRow(), 0).toString()), "Fulfilled");
                        populateTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "This order's status is already: Fulfilled");
                }
            }
        });

    }


    public void populateTable() {
        orderModel.setRowCount(0);
        ArrayList<String> orderList = DBDriver.allOrders();
        for (String s : orderList) {
            orderModel.addRow(s.split(","));
        }
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            f.dispose();
            new HomePage();
        } else if(e.getSource() == getBrowseButton) {
            f.dispose();
            new BrowsePage(0);
        } else if(e.getSource() == customerButton) {
            f.dispose();
            new ViewCustomer();
        } else if(e.getSource() == inventoryButton) {
            f.dispose();
            new ViewInventory();
        }
    }




    public static void main(String[] args) {

        new StaffPage("Staff1"); //if running from current file, logged-in user will default to 'Staff1'.
    }

}
