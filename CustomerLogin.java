import javax.swing.*;
import java.awt.*;

public class CustomerLogin {

    public CustomerLogin() {

        JFrame f = new JFrame("Customer Login - Build-a-Bike Ltd.");
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

        //order id label
        JLabel order_id = new JLabel("Order ID");
        order_id.setBounds(550,300,80,25);
        panel.add(order_id);


        //Text field
        JTextField text = new JTextField(20);
        text.setBounds(650,300,165,25);
        panel.add(text);

//        //label
//        JLabel address = new JLabel("Address");
//        address.setBounds(550,350,80,25);
//        panel.add(address);

//        //Text field
//        JTextField add_text = new JTextField(20);
//        add_text.setBounds(650,350,165,25);
//        panel.add(add_text);

        //message
        JLabel info_id = new JLabel("Don't remember your Order ID? Enter your Address to login");
        info_id.setBounds(550,400,400,25);
        panel.add(info_id);

        // name
        JLabel name_id = new JLabel("NAME");
        name_id.setBounds(550,300,400,25);
        panel.add( name_id);
        name_id.setVisible(false);

        //name field
        JTextField name_text = new JTextField(20);
        name_text.setBounds(650,300,165,25);
        panel.add(name_text);
        name_text.setVisible(false);

        // surname
        JLabel surname_id = new JLabel("SURNAME");
        surname_id.setBounds(550,350,400,25);
        panel.add( surname_id);
        surname_id.setVisible(false);

        //surname field
        JTextField surname_text = new JTextField(20);
        surname_text.setBounds(650,350,165,25);
        panel.add(surname_text);
        surname_text.setVisible(false);


        // house number
        JLabel house_id = new JLabel("HOUSE NO.");
        house_id.setBounds(550,400,400,25);
        panel.add(house_id);
        house_id.setVisible(false);

        //house field
        JTextField house_text = new JTextField(20);
        house_text.setBounds(650,400,165,25);
        panel.add(house_text);
        house_text.setVisible(false);

        // POST CODE
        JLabel post_id = new JLabel("POST CODE");
        post_id.setBounds(550,450,400,25);
        panel.add(post_id);
        post_id.setVisible(false);


        //post field
        JTextField post_text = new JTextField(20);
        post_text.setBounds(650,450,165,25);
        panel.add(post_text);
        post_text.setVisible(false);






        //All Buttons

        JButton login = new JButton("LOGIN");
        login.setBounds(800,600,80,25);
        login.setMargin(new Insets(5,5,5,5));
        login.setBackground(new Color(59, 89, 182));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(login);

        JButton back = new JButton("BACK");
        back.setBounds(700,600,80,25);
        back.setMargin(new Insets(5,5,5,5));
        back.setBackground(new Color(59, 89, 182));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(back);

        JButton address = new JButton("ENTER ADDRESS");
        address.setBounds(550,600,80,25);
        address.setMargin(new Insets(5,5,5,5));
        address.setBackground(new Color(59, 89, 182));
        address.setSize(120,25);
        address.setForeground(Color.WHITE);
        address.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(address);



        back.addActionListener(ae -> {
            f.dispose();
            new HomePage();
        });

        login.addActionListener(ae -> {
            f.dispose();
            new CustomerPage();
        });
//
//

        address.addActionListener(ae -> {
            if (ae.getSource() == address) {
                text.setVisible(false);
                order_id.setVisible(false);
                info_id.setVisible(false);
                name_id.setVisible(true);
                name_text.setVisible(true);
                surname_id.setVisible(true);
                surname_text.setVisible(true);
                house_id.setVisible(true);
                house_text.setVisible(true);
                post_id.setVisible(true);
                post_text.setVisible(true);

            }
        });



        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        CustomerLogin customer = new CustomerLogin();
    }
}
