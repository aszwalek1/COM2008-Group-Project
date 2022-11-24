import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class OrderSumPage {
    JFrame f = new JFrame("Order Summary - Build-a-Bike Ltd.");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(4, 1));

    JPanel topPanel = new JPanel();

    JPanel middlePanel = new JPanel();

    JPanel botPanel = new JPanel();

    //Bot Buttons
    JButton cancelButton = new JButton("Cancel");
    JButton payButton = new JButton("Pay For Order");


    //Top Labels
    JLabel pageLabel = new JLabel("ORDER SUMMARY");
    JLabel customerLabel = new JLabel("Customer Id: ");
    JLabel detailsLabel = new JLabel("Order Details: ");

    //Middle Panels
    JPanel namePanel = new JPanel();
    JPanel framePanel = new JPanel();
    JPanel handlebarPanel = new JPanel();
    JPanel wheelPanel = new JPanel();
    JPanel costPanel = new JPanel();
    JPanel datePanel = new JPanel();

    //Middle Labels
    JLabel nameLabel = new JLabel("Bike Name: ");
    JLabel frameLabel = new JLabel("Frame-Set: 1x - ");
    JLabel handlebarLabel = new JLabel("Handlebars: 1x - ");
    JLabel wheelLabel = new JLabel("Wheels: 2x - ");
    JLabel costLabel = new JLabel("Total Cost: ");
    JLabel dateLabel = new JLabel("Date: ");

    //Middle Text Fields
    JTextField nameField = new JTextField("        ");
    JTextField frameField = new JTextField("        ");
    JTextField handlebarField = new JTextField("        ");
    JTextField wheelField = new JTextField("        ");
    JTextField costField = new JTextField("        ");

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    String str = formatter.format(date);
    JTextField dateField = new JTextField(str);

    public OrderSumPage(int customerId, int frameId, int handlebarId, int wheelId, String bikeName) {

        //Top Panel Setting and Parts
        customerLabel.setText(customerLabel.getText()+customerId);
        customerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pageLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        detailsLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        customerLabel.setFont(new Font("Verdana", Font.BOLD, 17));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(pageLabel);
        topPanel.add(pageLabel);
        topPanel.add(Box.createRigidArea(new Dimension(50, 30)));
        topPanel.add(customerLabel);
        topPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        topPanel.add(detailsLabel);

        mainPanel.add(topPanel);

        //Middle Panel Setting and Parts
        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
        framePanel.setLayout(new BoxLayout(framePanel,BoxLayout.X_AXIS));
        handlebarPanel.setLayout(new BoxLayout(handlebarPanel,BoxLayout.X_AXIS));
        wheelPanel.setLayout(new BoxLayout(wheelPanel,BoxLayout.X_AXIS));
        costPanel.setLayout(new BoxLayout(costPanel,BoxLayout.X_AXIS));
        datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.X_AXIS));

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(namePanel);
        middlePanel.add(framePanel);
        middlePanel.add(handlebarPanel);
        middlePanel.add(wheelPanel);
        middlePanel.add(costPanel);
        middlePanel.add(datePanel);

        int bikeId;
        if(wheelId == 0)
        {
            bikeId = frameId;
        }
        else
        {
            DBDriver.createBike(frameId,handlebarId,wheelId,bikeName);
            bikeId = DBDriver.getLatestProductId();
        }


        String[] bike = DBDriver.getBike(bikeId);

        nameField.setText(bike[0]);
        frameField.setText(bike[1]);
        handlebarField.setText(bike[2]);
        wheelField.setText(bike[3]);
        costField.setText(bike[4]);

        namePanel.add(Box.createRigidArea(new Dimension(210, 20)));
        namePanel.add(nameLabel);
        nameField.setEditable(false);
        namePanel.add(nameField);
        namePanel.add(Box.createRigidArea(new Dimension(210, 20)));

        framePanel.add(Box.createRigidArea(new Dimension(210, 20)));
        framePanel.add(Box.createRigidArea(new Dimension(25, 20)));

        framePanel.add(frameLabel);
        frameField.setEditable(false);
        framePanel.add(frameField);
        framePanel.add(Box.createRigidArea(new Dimension(210, 20)));

        handlebarPanel.add(Box.createRigidArea(new Dimension(210, 20)));
        handlebarPanel.add(Box.createRigidArea(new Dimension(25, 20)));
        handlebarPanel.add(handlebarLabel);
        handlebarField.setEditable(false);
        handlebarPanel.add(handlebarField);
        handlebarPanel.add(Box.createRigidArea(new Dimension(210, 20)));

        wheelPanel.add(Box.createRigidArea(new Dimension(210, 20)));
        wheelPanel.add(Box.createRigidArea(new Dimension(25, 20)));

        wheelPanel.add(wheelLabel);
        wheelField.setEditable(false);
        wheelPanel.add(wheelField);
        wheelPanel.add(Box.createRigidArea(new Dimension(210, 20)));

        costPanel.add(Box.createRigidArea(new Dimension(210, 20)));
        costPanel.add(costLabel);
        costField.setEditable(false);
        costPanel.add(costField);
        costPanel.add(Box.createRigidArea(new Dimension(210, 20)));

        datePanel.add(Box.createRigidArea(new Dimension(210, 20)));
        datePanel.add(dateLabel);
        dateField.setEditable(false);
        datePanel.add(dateField);
        datePanel.add(Box.createRigidArea(new Dimension(210, 20)));

        mainPanel.add(middlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(50, 30)));

        //Bot Panel Setting and Parts

        payButton.setBackground(new Color(59, 89, 182));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 20));

        cancelButton.setBackground(new Color(59, 89, 182));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 20));

        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));
        botPanel.add(payButton);
        botPanel.add(Box.createRigidArea(new Dimension(50, 30)));
        botPanel.add(cancelButton);

        mainPanel.add(botPanel);

        //Overall

        f.add(mainPanel);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        payButton.addActionListener(ae -> {
            if(DBDriver.confirm("Are you sure you'd like to confirm and pay for your order?") == JOptionPane.YES_OPTION) {
                DBDriver.createOrder(customerId, bikeId,Double.parseDouble(bike[4].split("£")[1]));
                int orderId = 0;
                orderId = DBDriver.getLatestOrderId();
                JOptionPane.showMessageDialog(null,"You may now go to the front desk and pay at the till. You will need to tell the staff member either your Order Id OR your Customer Id and Details. IMPORTANT : YOUR ORDER ID IS "+orderId+".","Finalising Order",JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                new HomePage();
            }
        });

        cancelButton.addActionListener(ae -> {

            if(DBDriver.confirm("Are you sure you'd like to cancel your order? If you do you will be signed out and returned to the Home Page. You may access your assembled bike from the ready bikes to order section at a later time.") == JOptionPane.YES_OPTION){
                f.dispose();
                new HomePage();
            }
        });

    }

}
