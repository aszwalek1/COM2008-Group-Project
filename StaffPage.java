import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffPage implements ActionListener {

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
    JScrollPane scrollPanel = new JScrollPane();

    //Buttons
    JButton exitButton = new JButton("BACK");
    JButton browseButton = new JButton("VIEW CUSTOMERS");
    JButton saveDetailsButton = new JButton("VIEW ORDERS");


    //Main Labels
    JLabel pageLabel = new JLabel("STAFF PAGE");
//    JLabel detailsLabel = new JLabel("Your Personal Details: ");
    JLabel ordersLabel = new JLabel("Your Orders: ");





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
        middlePanel.add(scrollPanel);
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
//        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptopPanel.add(pageLabel);
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 30)));
//        toptopPanel.add(detailsLabel);
        toptopPanel.add(Box.createRigidArea(new Dimension(50, 50)));



        //Orders Label
        topPanel.add(ordersLabel);

        //Formatting Labels
        pageLabel.setFont(new Font("Verdana", Font.BOLD, 20));
//        detailsLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        ordersLabel.setFont(new Font("Verdana", Font.BOLD, 17));


        //Scroll Panel
        //scrollPanel.setViewportView(table);







        //Buttons actions

        exitButton.addActionListener(this);
        browseButton.addActionListener(this);





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
    }
}
