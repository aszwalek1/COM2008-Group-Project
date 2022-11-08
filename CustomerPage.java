import javax.swing.*;
import java.awt.*;

public class CustomerPage {

    public CustomerPage() {
        JButton back = new JButton("GO BACK");
        JButton con = new JButton("CONTINUE");

        JFrame f = new JFrame("Customer");

        Container contentPane = f.getContentPane();
        contentPane.add(back);
        contentPane.add(con);
        contentPane.setLayout(new FlowLayout());

        back.addActionListener(ae -> {
            f.dispose();
            new HomePage();
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setVisible(true);
    }
}
