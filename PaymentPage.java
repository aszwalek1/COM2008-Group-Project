import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage implements ActionListener {
    //Frame
    JFrame f = new JFrame("Build Your Bike");

    //Panel
    JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel rightTopPanel = new JPanel();
    JPanel rightBottomPanel = new JPanel();

    //Buttons
    JButton backButton = new JButton("Back");
    JButton payButton = new JButton("Pay now");

    //labels
    JLabel payLabel = new JLabel("Click the button below to pay now");


    public PaymentPage() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add panels
        rightPanel.add(rightTopPanel);
        rightPanel.add(rightBottomPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        //Back button action
        backButton.addActionListener(al -> {
            f.dispose();
            new BikePage();
        });

        //Pay button action
        payButton.addActionListener(this);
        payButton.setFont(new Font("Verdana", Font.PLAIN, 20));

        //Layout
        rightPanel.setLayout(new GridLayout(2,1));

        leftPanel.add(backButton, BorderLayout.WEST);
        rightTopPanel.add(payLabel);
        payLabel.setFont(new Font("Verdana",Font.BOLD, 30));
        rightBottomPanel.add(payButton);

        f.add(leftPanel, BorderLayout.WEST);
        f.add(rightPanel, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == payButton) {
            JOptionPane.showMessageDialog(f, "Transaction Completed");
            f.dispose();
            new HomePage();
        }
    }
}
