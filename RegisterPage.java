import javax.swing.*;

public class RegisterPage {

    JFrame f = new JFrame("Build Your Bike");
    JPanel panel = new JPanel();

    public RegisterPage() {
        f.add(panel);




        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
