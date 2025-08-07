import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Window {
    private JFrame frame;
    private JPanel panel;

    public Window() {
        initialise();
    }

    private void initialise() {
        initialiseWindow();
        
    }
    // Initialises the Window object when "= new Window();" is used.
    private void initialiseWindow() {
        this.frame = new JFrame("The Morse's Name was Friday");
        // Gets the display screen's resolution
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int)size.getWidth();
        int maxHeight = (int)size.getHeight();
        // Sets the app size to half of the display resolution
        frame.setSize(maxWidth/2, maxHeight/2);
        frame.setResizable(false);
        // Centers the JFrame on the middle of the screen
        frame.setLocationRelativeTo(null);
        // Shuts down the entire program on close (good for primary window)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Makes the window invisible
        frame.setVisible(true);
        System.out.println("JFrame setup completed successfully.");
    }
}
