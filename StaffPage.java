import javax.swing.*;
import java.awt.*;


public class StaffPage {

    public StaffPage() {

        JButton back = new JButton("GO BACK");
        JButton con = new JButton("CONTINUE");
        JFrame f = new JFrame("Staff");

        //login panel
        JPanel loginPanel = new JPanel();
        loginPanel = new JPanel(new GridBagLayout());
        JButton loginButton = new JButton("LOGIN");


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
