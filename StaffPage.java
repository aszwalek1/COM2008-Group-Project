import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StaffPage {

    //Frame
    JFrame f = new JFrame("Staff - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel leftPanel = new JPanel(new GridLayout(3, 1));
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel(new GridLayout(1, 4));
    JPanel middlePanel = new JPanel(new GridLayout(1, 3));
    JPanel bottomPanel = new JPanel(new GridLayout(1, 2));


    // All Buttons
    JButton backButton = new JButton("Back");
    JButton getBrowseButton = new JButton("Browse");
    JButton customerButton = new JButton("View Customer");
    JButton inventoryButton = new JButton(("View Inventory"));
    JButton setOrderStatusFulfilledButton = new JButton("Fulfilled");
    JButton setOrderStatusConfirmedButton = new JButton("Confirmed");
    JTextField searchColumnFor = new JTextField(130);
    JButton searchButton = new JButton("Search");

    // Column Names
    String[] columnNames = {"Order ID", "Staff Username", "Customer ID", "Frame-set", "Handlebar", "Wheels", "Order Date", "Total Price", "Status"};
    JComboBox<String> selectColumn = new JComboBox<>(columnNames);

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
        topPanel.add(backButton);
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



        middlePanel.add(selectColumn);
        middlePanel.add(searchColumnFor);
        middlePanel.add(searchButton);
        searchButton.setMargin(new Insets(5,5,5,5));
        searchButton.setBackground(new Color(59, 89, 182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));


        bottomPanel.add(setOrderStatusConfirmedButton);
        setOrderStatusConfirmedButton.setMargin(new Insets(5,5,5,5));
        setOrderStatusConfirmedButton.setBackground(new Color(59, 89, 182));
        setOrderStatusConfirmedButton.setForeground(Color.WHITE);
        setOrderStatusConfirmedButton.setFont(new Font("Arial", Font.BOLD, 20));
        setOrderStatusConfirmedButton.setToolTipText("<html>This button will set the order status to be \"Confirmed\". <br>" +
                " Only do this when the order has been paid for.</html>");

        bottomPanel.add(setOrderStatusFulfilledButton);
        setOrderStatusFulfilledButton.setMargin(new Insets(5,5,5,5));
        setOrderStatusFulfilledButton.setBackground(new Color(59, 89, 182));
        setOrderStatusFulfilledButton.setForeground(Color.WHITE);
        setOrderStatusFulfilledButton.setFont(new Font("Arial", Font.BOLD, 20));
        setOrderStatusFulfilledButton.setToolTipText("<html>This button will set the order status to be \"Fulfilled\". <br>" +
                " Only do this when the order has been paid for and the bike delivered.</html>");

        middlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        //Table
        ordersTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        ordersTable.setRowHeight(24);
        ordersTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        ordersTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPanel = new JScrollPane(ordersTable);
        rightPanel.add(scrollPanel);

        populateTable();

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
                        if (DBDriver.decreaseOrderStock(Integer.parseInt(ordersTable.getValueAt(ordersTable.getSelectedRow(), 0).toString()))) {
                            DBDriver.UpdateOrderStatus(Integer.parseInt(ordersTable.getValueAt(ordersTable.getSelectedRow(), 0).toString()), "Fulfilled");
                            populateTable();
                        } else {
                            JOptionPane.showMessageDialog(f, "There is not enough stock for this order to be fulfilled.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "This order's status is already: Fulfilled");
                }
            }
        });

        customerButton.addActionListener(ae -> {
            f.dispose();
            new ViewCustomer(username);
        });

        inventoryButton.addActionListener(ae -> {
            f.dispose();
            new ViewInventory(username);
        });

        searchButton.addActionListener(ae -> {
            if(searchColumnFor.getText() != null && searchColumnFor.getText().length() > 0 && validateSearchFor()) {
                if(DBDriver.confirm("Search the table for any occurrence of: '" + searchColumnFor.getText() +
                        "', in column: '" + selectColumn.getSelectedItem().toString() + "'?") == 0) {
                    populateTable(selectColumn.getSelectedIndex(), searchColumnFor.getText());
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please input a valid search parameter");
                populateTable();
            }
        });

        backButton.addActionListener(ae -> {
            f.dispose();
            new HomePage();
        });

        getBrowseButton.addActionListener(ae -> {
            f.dispose();
            new BrowsePage(0);
        });

    }


    public void populateTable() {
        orderModel.setRowCount(0);
        ArrayList<String> orderList = DBDriver.allOrders();
        for (String s : orderList) {
            orderModel.addRow(s.split(","));
        }
    }

    public void populateTable(int column, String input) {
        orderModel.setRowCount(0);
        ArrayList<String> orderList = DBDriver.allOrders(returnActualColumnName(column), input);
        for (String s : orderList) {
            orderModel.addRow(s.split(","));
        }
    }

    public String returnActualColumnName(int column){
        switch(column) {
            case 1 -> { System.out.println("staffUsername"); return "staffUsername";}
            case 2 -> { return "customerId"; }
            case 3 -> { return "frameName"; }
            case 4 -> { return "handlebarName"; }
            case 5 -> { return "wheelName"; }
            case 6 -> { return "orderDate"; }
            case 7 -> { return "orderCost"; }
            case 8 -> { return "orderStatus"; }
            default -> { return "orderNo"; } //default is orderId
        }
    }

    public boolean validateSearchFor(){
        int column = selectColumn.getSelectedIndex();
        String input = searchColumnFor.getText();
        switch(column) {
            case 0,2 ->{
                if (input.length() <= 11) {
                    return DBDriver.isNo(input);
                }
            }
            case 1 -> {
                if(input.length() <= 30){
                    System.out.println("staffUsername");
                    return DBDriver.isNoOrAlpha(input);
                }
            }
            case 6 -> {
                if (input.length() <= 10) {
                    return true;
                }
            }
            case 7 -> {
                if (input.length() <= 11) {
                    try {
                        Double.parseDouble(input);
                        return true;
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                }
            }
            case 8 -> {
                return DBDriver.isAlpha(input);
            }
            default -> { //default is frame name, handle name and wheel name, they are the same type with same max length of text field
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        new StaffPage("Staff1");
        //if running from current file, logged-in user will default to 'Staff1'.
        //only used to quickly bypass the staff login process for easy testing
    }

}
