import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ViewInventory {

    JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JButton backButton = new JButton("Back");


    //Table stuff
    String[] tableList = {"Frame Sets", "Handlebars", "Wheels", "Assembled Bikes"};
    JComboBox<String> selectTable = new JComboBox<>(tableList);
    String[] frameColumns = {"Product ID", "Serial No", "Brand", "Name", "Gears", "Shocks", "Size (cm)", "Cost (£)", "Stock"};
    String[] handleColumns = {"Product ID", "Serial No", "Brand", "Name", "Style", "Cost (£)", "Stock"};
    String[] bikeColumns = {"Product ID", "Brand", "Bike Name", "Gears", "Shocks", "Frame Size (cm)", "Handlebar Style", "Wheel Style", "Wheel Diameter (cm)", "Brakes", "Total Cost (£)"};
    String[] wheelColumns = {"Product ID", "Serial No", "Brand", "Name", "Style", "Diameter (cm)", "Brakes", "Cost (£)", "Stock"};
    JTable productTable = new JTable(new DefaultTableModel(frameColumns,0)) {
        //MAKE TABLE UNEDITABLE BY USER
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel productModel = (DefaultTableModel) productTable.getModel();


    //Search stuff
    JComboBox<String> selectColumn = new JComboBox<>(frameColumns);
    JTextField searchColumnFor = new JTextField(50);
    JButton searchButton = new JButton("Search");


    public ViewInventory(String username) {

        JFrame f = new JFrame("Inventory - Build-a-Bike Ltd.");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        //adding panel borders JUST FOR EASIER VISUALISING THE PANEL LOCATIONS
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding panels to frame
        f.add(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        leftPanel.add(topPanel, BorderLayout.PAGE_START);
        leftPanel.add(middlePanel, BorderLayout.CENTER);
        leftPanel.add(bottomPanel, BorderLayout.PAGE_END);

        topPanel.add(selectTable);
        topPanel.add(selectColumn);
        topPanel.add(searchColumnFor);
        topPanel.add(searchButton);
        searchButton.setMargin(new Insets(5,5,5,5));
        searchButton.setBackground(new Color(59, 89, 182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));

        //middlePanel.add();

        bottomPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));

        productTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        productTable.setRowHeight(24);
        productTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 22));
        productTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPanel = new JScrollPane(productTable);
        rightPanel.add(scrollPanel);

        populateTable(selectTable.getSelectedItem().toString());

        selectTable.setEditable(false);

        searchButton.addActionListener(ae -> {
            if(searchColumnFor.getText() != null && searchColumnFor.getText().length() > 0) {
                if(DBDriver.confirm("Search the table for any occurrence of: '" + searchColumnFor.getText() +
                        "', in column: '" + selectColumn.getSelectedItem().toString() + "'?") == 0) {
                    populateTable(selectTable.getSelectedItem().toString(), returnActualColumnName(selectColumn.getSelectedItem().toString()), searchColumnFor.getText());
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please input a valid search parameter");
                populateTable(selectTable.getSelectedItem().toString());
            }
        });

        selectTable.addActionListener(ae -> {
            populateTable(selectTable.getSelectedItem().toString());
            selectColumn.removeAllItems();
            DefaultComboBoxModel<String> cbm;
            switch(selectTable.getSelectedItem().toString()) {
                case "Frame Sets" -> {cbm = new DefaultComboBoxModel<>(frameColumns); selectColumn.setModel(cbm);}
                case "Handlebars" -> {cbm = new DefaultComboBoxModel<>(handleColumns); selectColumn.setModel(cbm);}
                case "Assembled Bikes" -> {cbm = new DefaultComboBoxModel<>(bikeColumns); selectColumn.setModel(cbm);}
                default -> {cbm = new DefaultComboBoxModel<>(wheelColumns); selectColumn.setModel(cbm);} //default is wheels
            }
        });

        backButton.addActionListener(ae -> {
            f.dispose();
            new StaffPage(username);
        });
    }

    public void populateTable(String table) {
        productModel.setRowCount(0);
        productModel.setColumnCount(0);
        ArrayList<String> orderList;
        switch (table) {
            case "Frame Sets" -> { orderList = DBDriver.frameSetSelectAll(); productModel.setColumnIdentifiers(frameColumns); }
            case "Handlebars" -> { orderList = DBDriver.handleBarSelectAll(); productModel.setColumnIdentifiers(handleColumns); }
            case "Assembled Bikes" -> { orderList = DBDriver.assembledBikeSelectAll(); productModel.setColumnIdentifiers(bikeColumns); }
            default -> { orderList = DBDriver.wheelSelectAll(); productModel.setColumnIdentifiers(wheelColumns); } //default is wheels
        };
        for (String s : orderList) {
            productModel.addRow(s.split(","));
        }
    }

    public void populateTable(String table, String column, String input) {
        productModel.setRowCount(0);
        productModel.setColumnCount(0);
        ArrayList<String> orderList;
        switch (table) {
            case "Frame Sets" -> { orderList = DBDriver.selectProductLikeInput(table, column, input); productModel.setColumnIdentifiers(frameColumns); }
            case "Handlebars" -> { orderList = DBDriver.selectProductLikeInput(table, column, input); productModel.setColumnIdentifiers(handleColumns); }
            case "Assembled Bikes" -> { orderList = DBDriver.selectProductLikeInput(table, column, input); productModel.setColumnIdentifiers(bikeColumns); }
            default -> { orderList = DBDriver.selectProductLikeInput(table, column, input); productModel.setColumnIdentifiers(wheelColumns); } //default is wheels
        };
        for (String s : orderList) {
            productModel.addRow(s.split(","));
        }
    }

    public String returnActualColumnName(String column) {
        switch(column) {
            case "Serial No" -> {return "serialNo";}
            case "Brand" -> {return "brandName";}
            case "Name", "Bike Name" -> {return "productName";}
            case "Gears" -> {return "gears";}
            case "Shocks" -> {return "shocks";}
            case "Size (cm)", "Frame Size (cm)" -> {return "size";}
            case "Wheel Style" -> {return "style";}
            case "Style", "Handlebar Style" -> {return "handlebarStyle";}
            case "Diameter (cm)", "Wheel Diameter (cm)" -> {return "diameter";}
            case "Brakes" -> {return "brakes";}
            case "Cost (£)" -> {return "unitCost";}
            case "Total Cost (£)" -> {return "total";}
            case "Stock" -> {return "Stock";}
            default -> {return "productId";} //default is id
        }
    }

}
