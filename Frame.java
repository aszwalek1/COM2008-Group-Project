import javax.swing.*;
public class Frame extends JFrame {

    public Frame() {
        setTitle("Bike Company");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //makes it full screen:
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        //deletes the title bar and the close option:
        //setUndecorated(true);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Frame();

    }

}
