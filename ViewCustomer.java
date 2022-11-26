import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewCustomer {

    JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JButton backButton = new JButton("Back");

    //Search stuff
    String[] searchFromList = {"Customer ID", "Forename", "Surname", "House No.", "Street Name", "City Name", "Postcode"};
    JComboBox<String> searchFrom = new JComboBox<>(searchFromList);
    JTextField searchFor = new JTextField(50);
    JButton search = new JButton("Search");


    //Table stuff
    String[] columnNames = {"Customer ID", "Forename", "Surname", "House No.", "Street Name", "City Name", "Postcode"};
    JTable custAddrTable = new JTable(new DefaultTableModel(columnNames,0)) {
        //MAKE TABLE UNEDITABLE BY USER
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel custAddrModel = (DefaultTableModel) custAddrTable.getModel();



    public ViewCustomer(String username) {

        JFrame f = new JFrame("Customer details - Build-a-Bike Ltd.");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        //adding panel borders
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding panels to frame
        f.add(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(searchFrom);
        topPanel.add(searchFor);
        topPanel.add(search);
        search.setMargin(new Insets(5,5,5,5));
        search.setBackground(new Color(59, 89, 182));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Arial", Font.BOLD, 20));

        custAddrTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        custAddrTable.setRowHeight(24);
        custAddrTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        custAddrTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPanel = new JScrollPane(custAddrTable);
        rightPanel.add(scrollPanel);

        populateTable();

        searchFrom.setEditable(false);

        search.addActionListener(ae -> {
            if(searchFor.getText() != null && searchFor.getText().length() > 0) {
                if(DBDriver.confirm("Search the table for any occurrence of: '" + searchFor.getText() +
                        "', in column: '" + searchFrom.getSelectedItem().toString() + "'?") == 0) {
                    populateTable(searchFrom.getSelectedIndex(), searchFor.getText());
                    }
            } else {
                populateTable();
            }
        });

        backButton.addActionListener(ae -> {
            f.dispose();
            new StaffPage(username);
        });
    }

    public void populateTable() {
        custAddrModel.setRowCount(0);
        ArrayList<String> orderList = DBDriver.custAddrSelectAll();
        for (String s : orderList) {
            custAddrModel.addRow(s.split(","));
        }
    }

    public void populateTable(int columnIndex, String input) {
        custAddrModel.setRowCount(0);
        ArrayList<String> orderList = DBDriver.custAddrSelectAll(columnIndex, input);
        for (String s : orderList) {
            custAddrModel.addRow(s.split(","));
        }
    }

}
