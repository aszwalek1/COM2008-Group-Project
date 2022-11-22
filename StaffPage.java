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

    JPanel topmostPanel = new JPanel();

    JPanel detailsPanel = new JPanel();




    // All Buttons
    JButton backButton = new JButton("Back");
    JButton customerButton = new JButton("View Customer");

    JButton getBrowseButton = new JButton("Browse");

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

    public StaffPage() {

        //Setting layouts
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        detailsPanel.add(Box.createRigidArea(new Dimension(70,10)));




        //Adding panels to the main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.add(middlePanel);
        middlePanel.add(topPanel);
        topPanel.add(topmostPanel);
        topPanel.add(detailsPanel);



        //Labels and text fields for  for inventory

        JLabel title = new JLabel("INVENTORY");
        title.setFont(new Font("Arial", Font.BOLD, 20));



        JLabel wheels = new JLabel("WHEELS");


        JLabel frames = new JLabel("FRAME-SETS");


        JLabel handle = new JLabel("HANDLE");



        //All text field
        JTextField wheels_in = new JTextField("                 ");

        JTextField frames_in = new JTextField("                 ");

        JTextField handle_in = new JTextField("        ");






        //Adding Main Buttons
        leftPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        rightPanel.add(customerButton);
        customerButton.setMargin(new Insets(5,5,5,5));
        customerButton.setBackground(new Color(59, 89, 182));
        customerButton.setForeground(Color.WHITE);
        customerButton.setFont(new Font("Arial", Font.BOLD, 20));

        leftPanel.add(getBrowseButton);
        getBrowseButton.setMargin(new Insets(5,5,5,5));
        getBrowseButton.setBackground(new Color(59, 89, 182));
        getBrowseButton.setForeground(Color.WHITE);
        getBrowseButton.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(Box.createRigidArea(new Dimension(50, 100)));


        topmostPanel.add(title);
        detailsPanel.add(wheels);
        detailsPanel.add(wheels_in);

        detailsPanel.add(frames);
        detailsPanel.add(frames_in);

        detailsPanel.add(handle);
        detailsPanel.add(handle_in);




/// MAKE A DROP DOWN BOX FOR THE OPTION
        //Scroll Panel


        //Table
        ordersTable.setBounds(30, 40, 200, 300);
        ordersTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        ordersTable.setRowHeight(24);
        ordersTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        ordersTable.getTableHeader().setReorderingAllowed(false);

        ArrayList<String> orderList = DBDriver.allOrders();
        for (String s : orderList) {
            orderModel.addRow(s.split(","));
            System.out.println(s);
        }


        JScrollPane scrollPanel = new JScrollPane(ordersTable);
        middlePanel.add(scrollPanel);


        //Buttons actions
        backButton.addActionListener(this);
        customerButton.addActionListener(this);
        getBrowseButton.addActionListener(this);


        //Adding rest of the panels to
        f.add(leftPanel, BorderLayout.WEST);
        f.add(rightPanel, BorderLayout.EAST);
        f.add(middlePanel, BorderLayout.CENTER);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            f.dispose();
            new HomePage();
        }
        else if(e.getSource() == getBrowseButton) {
            f.dispose();
            new BrowsePage();
        }
//        else if (e.getSource() == customerButton) {
//            f.dispose();
//            new CustomerPage();
//        }
    }


}
