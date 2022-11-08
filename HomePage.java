import javax.swing.*;
import java.awt.*;
public class HomePage {
    private final JFrame f = new JFrame("BUILD A BIKE");


    public HomePage() {

        Container contentPane = f.getContentPane();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JButton clogin = new JButton("CUSTOMER LOGIN");
        contentPane.add(clogin);
        JButton slogin = new JButton("STAFF LOGIN");
        contentPane.add(slogin);
        JButton browse = new JButton("BROWSE");
        contentPane.add(browse);
        contentPane.setLayout(new FlowLayout());

        clogin.addActionListener(ae -> {
            f.dispose();
            new CustomerPage();
        });

        slogin.addActionListener(e -> {
            f.dispose();
            new StaffPage();
        });

        browse.addActionListener(ae -> {
            f.dispose();
            new BikePage();
        });

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public static void main(String[] args) {

        new HomePage();
    }
}


