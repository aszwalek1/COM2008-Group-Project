import javax.swing.*;
import java.awt.*;

public class HomePage {

    public HomePage() {

        JFrame f = new JFrame("Home Page - Build-a-Bike Ltd.");
        JPanel panel= new JPanel();
        f.getContentPane();
        panel.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel);


        JLabel name = new JLabel("WELCOME TO BUILD-A-BIKE");
        name.setFont(new Font("Arial",Font.BOLD,25));
        name.setBounds(575, 200, 1000,100);
        panel.add(name);


//        ImageIcon image = new ImageIcon("bike.jpg");
//        JLabel imageLabel = new JLabel(image);
//        panel.add(imageLabel);
//        imageLabel.setBounds(500,500,80,20);
//        imageLabel.setVisible(true);




        JButton clogin = new JButton(" CUSTOMER");
        Dimension size = clogin.getPreferredSize();
        clogin.setBounds(550,400 ,size.width, size.height);
        clogin.setSize(100,50);
        clogin.setMargin(new Insets(5,5,5,5));
        clogin.setBackground(new Color(59, 89, 182));
        clogin.setForeground(Color.WHITE);
        clogin.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(clogin);

        JButton slogin = new JButton("STAFF");
        Dimension size1 = slogin.getPreferredSize();
        slogin.setBounds(700,400 ,size1.width, size1.height);
        slogin.setSize(100,50);
        slogin.setBackground(new Color(59, 89, 182));
        slogin.setForeground(Color.WHITE);
        slogin.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(slogin);

        JButton browse = new JButton("BROWSE");
        Dimension size2 = browse.getPreferredSize();
        browse.setBounds(850,400 ,size2.width, size2.height);
        browse.setSize(100,50);
        browse.setBackground(new Color(59, 89, 182));
        browse.setForeground(Color.WHITE);
        browse.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(browse);


        clogin.addActionListener(ae -> {
          f.dispose();
           new CustomerLogin();
        });

       slogin.addActionListener(e -> {
            f.dispose();
            new StaffLogin();
        });

        browse.addActionListener(ae -> {
            f.dispose();
            new BrowsePage();
        });


        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {

        new HomePage();
    }
}


