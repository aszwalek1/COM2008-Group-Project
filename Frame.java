import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        setTitle("Bike Company");
        setSize(1280,720);

        setLocationRelativeTo(null); //puts the frame in the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //makes it full screen:
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        //deletes the title bar and the close option:
        //setUndecorated(true);
    }
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
    }

}
