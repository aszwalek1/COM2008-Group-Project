import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewInventory {

    JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    JPanel leftPanel = new JPanel(new GridLayout(4, 1));
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel topMiddlePanel = new JPanel();
    JPanel botMiddlePanel = new JPanel();
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


    //Stock stuff
    JButton incStockButton = new JButton("Increase Stock");
    JButton decStockButton = new JButton("Decrease Stock");
    JTextField setStockTo = new JTextField(11);
    JButton setStockButton = new JButton("Set Stock");


    //GUI components for add products
    JPanel newProductPanel = new JPanel(new GridLayout(8, 1));
    JTextField brandNameTxtFld = new JTextField(30);//universal inputs
    JTextField productNameTxtFld = new JTextField(100);
    JTextField unitCostTxtFld = new JTextField(11);
    JTextField stockTxtFld = new JTextField(11);
    JLabel brandNameLabel = new JLabel("Brand Name:"); //universal labels
    JLabel productNameLabel = new JLabel("Product Name:");
    JLabel unitCostLabel = new JLabel("Unit Cost (£):");
    JLabel stockLabel = new JLabel("Current Stock:");
    JButton newFrameSetButton = new JButton("New Frame Set");
    JPanel newFrameSetPanel = new JPanel(new GridLayout(2, 1));
    JPanel newFrameSetTopPanel = new JPanel(new GridLayout(6, 1));
    JTextField gears = new JTextField(11);
    JComboBox<String> shocks = new JComboBox<>(new String[]{"false", "true"});
    JTextField size = new JTextField(11);
    JButton newHandlebarButton = new JButton("New Handlebar");
    JPanel newHandlebarPanel = new JPanel(new GridLayout(2, 1));
    JPanel newHandlebarTopPanel = new JPanel(new GridLayout(2, 1));
    JComboBox<String> handlebarStyle = new JComboBox<>(new String[]{"Straight", "High", "Dropped"});
    JButton newWheelButton = new JButton("New Wheel");
    JPanel newWheelPanel = new JPanel(new GridLayout(2, 1));
    JPanel newWheelTopPanel = new JPanel(new GridLayout(6, 1));
    JComboBox<String> style = new JComboBox<>(new String[]{"Road", "Mountain", "Hybrid"});
    JTextField diameter = new JTextField(11);
    JComboBox<String> brakes = new JComboBox<>(new String[]{"Rim", "Disk"});
    JLabel gearsLabel = new JLabel("Number of Gears:");
    JLabel shocksLabel = new JLabel("Has Shocks (?):");
    JLabel sizeLabel = new JLabel("Size (cm):");
    JLabel handlebarStyleLabel = new JLabel("Style:");
    JLabel styleLabel = new JLabel("Style:");
    JLabel diameterLabel = new JLabel("Diameter (cm):");
    JLabel brakesLabel = new JLabel("Brake Type:");


    public ViewInventory(String username) {

        JFrame f = new JFrame("Inventory - Build-a-Bike Ltd.");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topMiddlePanel.setLayout(new BoxLayout(topMiddlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        newFrameSetPanel.setLayout(new BoxLayout(newFrameSetPanel, BoxLayout.Y_AXIS));
        newHandlebarPanel.setLayout(new BoxLayout(newHandlebarPanel, BoxLayout.Y_AXIS));
        newWheelPanel.setLayout(new BoxLayout(newWheelPanel, BoxLayout.Y_AXIS));

        //adding panel borders JUST FOR EASIER VISUALISING THE PANEL LOCATIONS
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topMiddlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding panels to frame
        f.add(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        leftPanel.add(topPanel, BorderLayout.PAGE_START);
        leftPanel.add(topMiddlePanel, BorderLayout.CENTER);
        leftPanel.add(botMiddlePanel, BorderLayout.CENTER);
        leftPanel.add(bottomPanel, BorderLayout.PAGE_END);

        topPanel.add(selectTable);
        topPanel.add(selectColumn);
        topPanel.add(searchColumnFor);
        topPanel.add(searchButton);
        searchButton.setMargin(new Insets(5,5,5,5));
        searchButton.setBackground(new Color(59, 89, 182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));

        topMiddlePanel.add(incStockButton);
        incStockButton.setMargin(new Insets(5,5,5,5));
        incStockButton.setBackground(new Color(59, 89, 182));
        incStockButton.setForeground(Color.WHITE);
        incStockButton.setFont(new Font("Arial", Font.BOLD, 20));
        incStockButton.setToolTipText("Click to increase the selected product's stock by 1.");
        topMiddlePanel.add(decStockButton);
        decStockButton.setMargin(new Insets(5,5,5,5));
        decStockButton.setBackground(new Color(59, 89, 182));
        decStockButton.setForeground(Color.WHITE);
        decStockButton.setFont(new Font("Arial", Font.BOLD, 20));
        decStockButton.setToolTipText("Click to decrease the selected product's stock by 1.");
        topMiddlePanel.add(setStockTo);
        topMiddlePanel.add(setStockButton);
        setStockButton.setMargin(new Insets(5,5,5,5));
        setStockButton.setBackground(new Color(59, 89, 182));
        setStockButton.setForeground(Color.WHITE);
        setStockButton.setFont(new Font("Arial", Font.BOLD, 20));
        setStockButton.setToolTipText("Click to set the selected products stock to the specified new amount.");

        botMiddlePanel.add(newFrameSetButton);
        newFrameSetButton.setMargin(new Insets(5,5,5,5));
        newFrameSetButton.setBackground(new Color(59, 89, 182));
        newFrameSetButton.setForeground(Color.WHITE);
        newFrameSetButton.setFont(new Font("Arial", Font.BOLD, 20));
        newFrameSetButton.setToolTipText("Click to add a new Frame Set product.");
        botMiddlePanel.add(newHandlebarButton);
        newHandlebarButton.setMargin(new Insets(5,5,5,5));
        newHandlebarButton.setBackground(new Color(59, 89, 182));
        newHandlebarButton.setForeground(Color.WHITE);
        newHandlebarButton.setFont(new Font("Arial", Font.BOLD, 20));
        newHandlebarButton.setToolTipText("Click to add a new Handlebar product.");
        botMiddlePanel.add(newWheelButton);
        newWheelButton.setMargin(new Insets(5,5,5,5));
        newWheelButton.setBackground(new Color(59, 89, 182));
        newWheelButton.setForeground(Color.WHITE);
        newWheelButton.setFont(new Font("Arial", Font.BOLD, 20));
        newWheelButton.setToolTipText("Click to add a new Wheel product.");

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

        //GUI components for add products
        newProductPanel.add(brandNameLabel);
        newProductPanel.add(brandNameTxtFld);
        newProductPanel.add(productNameLabel);
        newProductPanel.add(productNameTxtFld);
        newProductPanel.add(unitCostLabel);
        newProductPanel.add(unitCostTxtFld);
        newProductPanel.add(stockLabel);
        newProductPanel.add(stockTxtFld);

        newFrameSetPanel.add(newFrameSetTopPanel);
        newFrameSetTopPanel.add(gearsLabel);
        newFrameSetTopPanel.add(gears);
        newFrameSetTopPanel.add(shocksLabel);
        newFrameSetTopPanel.add(shocks);
        newFrameSetTopPanel.add(sizeLabel);
        newFrameSetTopPanel.add(size);

        newHandlebarPanel.add(newHandlebarTopPanel);
        newHandlebarTopPanel.add(handlebarStyleLabel);
        newHandlebarTopPanel.add(handlebarStyle);

        newWheelPanel.add(newWheelTopPanel);
        newWheelTopPanel.add(styleLabel);
        newWheelTopPanel.add(style);
        newWheelTopPanel.add(diameterLabel);
        newWheelTopPanel.add(diameter);
        newWheelTopPanel.add(brakesLabel);
        newWheelTopPanel.add(brakes);


        searchButton.addActionListener(ae -> {
            if(searchColumnFor.getText() != null && searchColumnFor.getText().length() > 0) {
                if(DBDriver.confirm("Search the table for any occurrence of: '" + searchColumnFor.getText() +
                        "', in column: '" + selectColumn.getSelectedItem().toString() + "'?") == 0) {
                    populateTable(selectTable.getSelectedItem().toString(), returnActualColumnName(selectColumn.getSelectedItem().toString()), searchColumnFor.getText());
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please input a valid search parameter");
            }
        });

        selectTable.addActionListener(ae -> {
            populateTable(selectTable.getSelectedItem().toString());
            updateSelectColumn();
        });

        incStockButton.addActionListener(ae -> {
           if (productTable.getSelectedRow() != -1) {
               DBDriver.updateProductStock(productTable.getValueAt(productTable.getSelectedRow(), 0).toString(), "increase");
               populateTable(selectTable.getSelectedItem().toString());
               updateSelectColumn();
           } else {
               JOptionPane.showMessageDialog(f, "Please select a valid product from the product table");
           }
        });

        decStockButton.addActionListener(ae -> {
            if (productTable.getSelectedRow() != -1) {
                if (Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), productTable.getColumnCount()-1).toString()) > 0) {
                    DBDriver.updateProductStock(productTable.getValueAt(productTable.getSelectedRow(), 0).toString(), "decrease");
                    populateTable(selectTable.getSelectedItem().toString());
                    updateSelectColumn();
                } else {
                    JOptionPane.showMessageDialog(f, "Product stock cannot be negative");
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please select a valid product from the product table");
            }
        });

        setStockButton.addActionListener(ae -> {
            if (productTable.getSelectedRow() != -1) {
                if (Integer.parseInt(setStockTo.getText()) >= 0 && setStockTo.getText().length() <= 11) {
                    DBDriver.setProductStock(productTable.getValueAt(productTable.getSelectedRow(), 0).toString(), setStockTo.getText());
                    populateTable(selectTable.getSelectedItem().toString());
                    updateSelectColumn();
                } else {
                    JOptionPane.showMessageDialog(f, "Product stock cannot be negative");
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please select a valid product from the product table");
            }
        });

        newFrameSetButton.addActionListener(ae -> {
            addInputsToProductPanel(0);
            int result = JOptionPane.showConfirmDialog(null, newFrameSetPanel,
                    "Please Enter New Frame Set Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                DBDriver.createFrameSet(brandNameTxtFld.getText(), productNameTxtFld.getText(),
                        Integer.parseInt(gears.getText()), shocks.getSelectedIndex(), Double.parseDouble(size.getText()),
                        Double.parseDouble(unitCostTxtFld.getText()), Integer.parseInt(stockTxtFld.getText()));
                populateTable("Frame Sets");
                updateSelectColumn();
            }
        });

        newHandlebarButton.addActionListener(ae -> {
            addInputsToProductPanel(1);
            int result = JOptionPane.showConfirmDialog(null, newHandlebarPanel,
                    "Please Enter New Handlebar Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                DBDriver.createHandlebar(brandNameTxtFld.getText(), productNameTxtFld.getText(),
                        handlebarStyle.getSelectedItem().toString(), Double.parseDouble(unitCostTxtFld.getText()),
                        Integer.parseInt(stockTxtFld.getText()));
                populateTable("Handlebars");
                updateSelectColumn();
            }
        });

        newWheelButton.addActionListener(ae -> {
            addInputsToProductPanel(2);
            int result = JOptionPane.showConfirmDialog(null, newWheelPanel,
                    "Please Enter New Wheel Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                DBDriver.createWheel(brandNameTxtFld.getText(), productNameTxtFld.getText(),
                        style.getSelectedItem().toString(), Double.parseDouble(diameter.getText()), brakes.getSelectedItem().toString(),
                        Double.parseDouble(unitCostTxtFld.getText()), Integer.parseInt(stockTxtFld.getText()));
                populateTable("Wheels");
                updateSelectColumn();
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

    public void updateSelectColumn(){
        selectColumn.removeAllItems();
        DefaultComboBoxModel<String> cbm;
        switch(selectTable.getSelectedItem().toString()) {
            case "Frame Sets" -> {cbm = new DefaultComboBoxModel<>(frameColumns); selectColumn.setModel(cbm);}
            case "Handlebars" -> {cbm = new DefaultComboBoxModel<>(handleColumns); selectColumn.setModel(cbm);}
            case "Assembled Bikes" -> {cbm = new DefaultComboBoxModel<>(bikeColumns); selectColumn.setModel(cbm);}
            default -> {cbm = new DefaultComboBoxModel<>(wheelColumns); selectColumn.setModel(cbm);} //default is wheels
        }
    }

    public void addInputsToProductPanel(int productType){
        switch(productType){
            case 0 -> {
                newFrameSetPanel.add(newProductPanel, BorderLayout.PAGE_END);
            }
            case 1 -> {
                newHandlebarPanel.add(newProductPanel, BorderLayout.PAGE_END);
            }
            default -> { //wheels is default
                newWheelPanel.add(newProductPanel, BorderLayout.PAGE_END);
            }
        }
    }

}
