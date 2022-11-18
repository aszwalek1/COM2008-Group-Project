import javax.swing.*;
import java.awt.*;

public class StaffPage extends JFrame {

    public StaffPage() {

        //frames
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setTitle("Staff Page");



        //panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        JPanel westPanel = new JPanel();
        JPanel centralPanel = new JPanel();
        JPanel eastPanel = new JPanel();

    }

    public static void main(String[] args) {

        new StaffPage();
    }
}


