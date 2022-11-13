import javax.swing.*;

public class RegisterPage {

    JFrame f = new JFrame("Build Your Bike");
    JPanel mainPanel = new JPanel();

    public RegisterPage() {
        f.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // FORENAME
        // SURNAME
        // HOUSE NUMBER
        // ROAD NAME
        // CITY NAME
        // POSTCODE

        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
