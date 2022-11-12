import javax.swing.*;
import java.awt.*;

public class CustomerLogin {

    public CustomerLogin() {

        JFrame f = new JFrame("Customer");
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Panel
        JPanel panel = new JPanel();
        f.add(panel);
        panel.setLayout(null);



        JLabel name = new JLabel("ENTER YOUR ORDER DETAILS");
        name.setFont(new Font("Arial",Font.BOLD,25));
        name.setBounds(550, 200, 1000,100);
        panel.add(name);

        //label
        JLabel order_id = new JLabel("Order ID");
        order_id.setBounds(550,300,80,25);
        panel.add(order_id);


        //Text field
        JTextField text = new JTextField(20);
        text.setBounds(650,300,165,25);
        panel.add(text);


        //Button

        JButton login = new JButton("LOGIN");
        login.setBounds(750,400,80,25);
        panel.add(login);

        JButton back = new JButton("BACK");
        back.setBounds(650,400,80,25);
        panel.add(back);



        back.addActionListener(ae -> {
            f.dispose();
            new HomePage();
        });


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }
}
