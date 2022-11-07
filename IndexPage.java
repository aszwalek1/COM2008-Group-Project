import java.awt.Container;
import javax.swing.*;

public class IndexPage extends Frame {
    public IndexPage() {
        setTitle("Main Page");
        JButton button = new JButton("Button");

        Container contentPane = getContentPane();
        contentPane.add(button);
    }
    public static void main(String[] args) {
        IndexPage indexP = new IndexPage();
        indexP.setVisible(true);
    }
}
