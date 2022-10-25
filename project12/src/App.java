import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame
{
    private Container c = getContentPane();

    public App()
    {
        c.setLayout(new FlowLayout());
    }
    public static void main(String[] args)
    {
        App frame = new App();
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
