import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Scanner;

public class App implements Runnable {
    JFrame appFrame;
    Scanner userInputScanner;
    MorseTranslator m;

    boolean VERBOSE = true;

    public void setupWindow() {
        appFrame = new JFrame("The Morse's Name was Friday");
        
        // Gets the display screen's resolution
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int)size.getWidth();
        int maxHeight = (int)size.getHeight();

        appFrame.setSize(maxWidth/2, maxHeight/2);

        // Centers the JFrame on the middle of the screen
        appFrame.setLocationRelativeTo(null);

        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appFrame.setVisible(true);

        if(VERBOSE) System.out.println("JFrame setup completed successfully.");
    }

    public void setup() {
        setupWindow();
        userInputScanner = new Scanner(System.in);
        m = MorseTranslator.getInstance();
        // Prompt user to encode or decode:
        System.out.println("Enter \"E\" to Encode to Morse or \"D\" to Decode from Morse:");
        String userinput = userInputScanner.nextLine().trim();
        while(!(userinput.equals("E") || userinput.equals("e") || userinput.equals("D") || userinput.equals("d"))) {
            System.err.println("Invalid input. Please input either \"E\" or \"D\"");
            userinput = userInputScanner.nextLine();
        }
        // Need to add way to set mode HERE
        if(VERBOSE) System.out.println("Setup completed successfully.");
    }

    public void run() {
        setup();
        if(m.mode == MorseTranslator.Mode.ENCODE) {
            System.out.println("a");
            while(true) {
                System.out.print("Enter a message to encode: ");
                String userinput = userInputScanner.nextLine();
                while(userinput == "") {
                    System.err.println("Invalid input. Please input a non-empty string.");
                    userinput = userInputScanner.nextLine();
                }
                System.out.println("Encoded Message: " + MorseTranslator.encode(userinput.trim()));
            }
        } else if(m.mode == MorseTranslator.Mode.DECODE) { // <- temp, need to change
                        System.out.println("b");

            while(true) {
                System.out.print("Enter Morse code: ");
                String userinput = userInputScanner.nextLine();
                while(userinput == "") {
                    System.err.println("Invalid input. Please ensure characters are separated with \'|\' and words with \'/\'.");
                    userinput = userInputScanner.nextLine();
                }
                System.out.println("Decoded Message: " + MorseTranslator.decode(userinput.trim()));
            }
        } else {
            System.out.println(m.mode);
        }
    }
}
