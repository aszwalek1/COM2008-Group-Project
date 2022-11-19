import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffPage implements ActionListener {

    //Frame
    JFrame f = new JFrame("Staff - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel toptopPanel = new JPanel();


    // All Buttons
    JButton backButton = new JButton("Back");
    JButton browseButton = new JButton("View Customer");

    JButton getBrowseButton = new JButton("BROWSE");




    public StaffPage() {

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



        //Adding Main Buttons
        leftPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        rightPanel.add(browseButton);
        browseButton.setMargin(new Insets(5,5,5,5));
        browseButton.setBackground(new Color(59, 89, 182));
        browseButton.setForeground(Color.WHITE);
        browseButton.setFont(new Font("Arial", Font.BOLD, 20));

        leftPanel.add(getBrowseButton);
        getBrowseButton.setMargin(new Insets(5,5,5,5));
        getBrowseButton.setBackground(new Color(59, 89, 182));
        getBrowseButton.setForeground(Color.WHITE);
        getBrowseButton.setFont(new Font("Arial", Font.BOLD, 20));
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 30)));
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        topPanel.add(Box.createRigidArea(new Dimension(50, 30)));



        //Scroll Panel

        //scrollPanel.setViewportView(table);

        // Column Names
        String[] columnNames = {
                "Order ID", "Status" };


        // Data to be displayed in the JTable
        String[][] data = {
                {"12345", "Pending"},
                {"78978", "In processs"},
                {"1455", "Pending"},
                {"7855", "In processs"}
        };




        //Table
        JTable ordersTable = new JTable(data, columnNames);
        ordersTable.setBounds(30, 40, 200, 300);
        ordersTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        ordersTable.setRowHeight(24);
        ordersTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));

        JScrollPane scrollPanel = new JScrollPane(ordersTable);
        middlePanel.add(scrollPanel);


        //Buttons actions
        backButton.addActionListener(this);
        browseButton.addActionListener(this);

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
        else if(e.getSource() == browseButton) {
            f.dispose();
            new BrowsePage();
        }
    }
}
