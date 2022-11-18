import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class StaffLogin {




    public StaffLogin() {
        //frame
        JFrame f = new JFrame("Staff Login - Build-a-Bike Ltd.");
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);



        JPanel logo = new JPanel();
        f.add(logo, BorderLayout.NORTH);


        JLabel logoName = new JLabel("BUILD-A-BIKE");
        logoName.setFont(new Font("Arial",Font.BOLD,25));
        logo.add(logoName);

        //Panel
        JPanel panel = new JPanel();
        f.add(panel);
        panel.setLayout(null);



        JLabel name = new JLabel("STAFF LOGIN");
        name.setFont(new Font("Arial",Font.BOLD,25));
        name.setBounds(700, 200, 1000,100);
        panel.add(name);

        //label
        JLabel username = new JLabel("LOGIN ID");
        username.setBounds(650,300,80,25);
        panel.add(username);

        JLabel password = new JLabel("PASSWORD");
        password.setBounds(650,350,80,25);
        panel.add(password);


        //Username field
        JTextField user = new JTextField(20);
        user.setBounds(750,300,165,25);
        panel.add(user);

        //Password field
        JPasswordField pass = new JPasswordField();
        pass.setBounds(750,350,165,25);
        panel.add(pass);

        //Button

        JButton login = new JButton("LOGIN");
        login.setBounds(800,400,80,25);
        login.setBackground(new Color(59, 89, 182));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(login);


        login.addActionListener(ae -> {
            try {
                if (DBDriver.staffLogin(user.getText(), String.valueOf(pass.getPassword()))){
                    JOptionPane.showMessageDialog(f, "Login Success");
                } else {
                    JOptionPane.showMessageDialog(f, "Login Failed");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });



        JButton back = new JButton("BACK");
        back.setBounds(700,400,80,25);
        back.setBackground(new Color(59, 89, 182));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(back);



        back.addActionListener(ae -> {
            f.dispose();
            new HomePage();
        });

    }

    public static void main(String[] args) {

        new StaffLogin();
    }
}
