import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Scanner;

public class App implements Runnable {

/* VARIABLES */
    public static JFrame appFrame;
    public static Scanner userInputScanner;
    public static MorseTranslator m;

    // For detailed logging
    static boolean VERBOSE = true;

/* METHODS */

    // Clears the terminal
    public void clearTerminal() {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder builder;
        try {
            if(os.contains("win")) {
                // For Windows systems
                builder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // For Mac and Linux
                builder = new ProcessBuilder("clear");
            }
            builder.inheritIO().start().waitFor();

        } catch (Exception e) {
            System.err.println("Error clearing the terminal: " + e.getMessage());
        }
    }

    // Sets up an application window
    public static void setupWindow() {
        appFrame = new JFrame("The Morse's Name was Friday");
        // Gets the display screen's resolution
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int)size.getWidth();
        int maxHeight = (int)size.getHeight();
        // Sets the app size to half of the display resolution
        appFrame.setSize(maxWidth/2, maxHeight/2);
        // Centers the JFrame on the middle of the screen
        appFrame.setLocationRelativeTo(null);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Makes the window invisible
        appFrame.setVisible(false);
        if(VERBOSE) System.out.println("JFrame setup completed successfully.");
    }

    public void setupMode() {
        // Prompt user to encode or decode:
        System.out.println("Enter \"E\" to Encode to Morse or \"D\" to Decode from Morse:");
        String userinput = userInputScanner.nextLine().trim();
        while(!(userinput.equals("E") || userinput.equals("e") || userinput.equals("D") || userinput.equals("d"))) {
            System.err.println("Invalid input. Please input either \"E\" or \"D\"");
            userinput = userInputScanner.nextLine();
        }
        // Sets mode based on input:
        if(userinput.equals("E") || userinput.equals("e")) m.setMode("encode");
        if(userinput.equals("D") || userinput.equals("d")) m.setMode("decode");
    }

    // Master setup function, calls all helper setup functions
    public void setup() {
        setupWindow();
        userInputScanner = new Scanner(System.in);
        m = MorseTranslator.getInstance();
        setupMode();

        if(VERBOSE) System.out.println("Setup completed successfully.\n");
        clearTerminal();
    }

    public void clean(int exitCode) {
        // closes app, frees all resources
        userInputScanner.close();
        appFrame.dispose();
        System.exit(exitCode);
    }

    public void run() {
        setup();
        switch(m.mode) {
            case "encode":
                while(true) {
                    System.out.print("Enter a message to encode: ");
                    String userinput = userInputScanner.nextLine();
                    while(userinput == "") {
                        System.err.println("Invalid input. Please input a non-empty string.");
                        userinput = userInputScanner.nextLine();
                    }
                    System.out.println("Encoded Message: " + MorseTranslator.encode(userinput.trim()) + "\n");
                }
            
            case "decode":
                while(true) {
                    System.out.print("Enter Morse code: ");
                    String userinput = userInputScanner.nextLine();
                    while(userinput == "") {
                        System.err.println("Invalid input. Please ensure characters are separated with \'|\' and words with \'/\'.");
                        userinput = userInputScanner.nextLine();
                    }
                    System.out.println("Decoded Message: " + MorseTranslator.decode(userinput.trim()));
                    // Add in future a "back" and "exit" option, calling clean(0) with the 2nd option.
                }

            default:
                System.err.println("Invalid mode, terminating...");
                clean(1);
        }
    }
}
