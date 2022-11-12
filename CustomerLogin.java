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

        JPanel logo = new JPanel();
        f.add(logo, BorderLayout.NORTH);


        JLabel logoName = new JLabel("BUILD-A-BIKE");
        logoName.setFont(new Font("Arial",Font.BOLD,25));

        logo.add(logoName);




        JLabel name = new JLabel("ENTER YOUR ORDER DETAILS");
        name.setFont(new Font("Arial",Font.BOLD,25));
        name.setBounds(575, 200, 1000,100);
        panel.add(name);

        //label
        JLabel order_id = new JLabel("Order ID");
        order_id.setBounds(550,300,80,25);
        panel.add(order_id);


        //Text field
        JTextField text = new JTextField(20);
        text.setBounds(650,300,165,25);
        panel.add(text);

        //label
        JLabel address = new JLabel("Address");
        address.setBounds(550,350,80,25);
        panel.add(address);

        //Text field
        JTextField add_text = new JTextField(20);
        add_text.setBounds(650,350,165,25);
        panel.add(add_text);





        //Button

        JButton login = new JButton("LOGIN");
        login.setBounds(750,400,80,25);
        login.setMargin(new Insets(5,5,5,5));
        login.setBackground(new Color(59, 89, 182));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(login);

        JButton back = new JButton("BACK");
        back.setBounds(650,400,80,25);
        back.setMargin(new Insets(5,5,5,5));
        back.setBackground(new Color(59, 89, 182));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 12));
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
