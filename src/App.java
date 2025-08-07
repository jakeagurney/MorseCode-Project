public class App implements Runnable {

    /* VARIABLES */
    public Window appWindow;
    public DialogueManager jarvis;
    public MorseTranslator morseCore;

    // For detailed logging
    static boolean VERBOSE = true;

    /* CONSTRUCTORS */
    public App() {
        setup();
    }

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

    public void setupMode() {
        // Ask user to input their mode - encode or decode:
        String userInput = jarvis.promptForString("Enter \"E\" to Encode to Morse or \"D\" to Decode from Morse:");
        while(!(userInput.equals("E") || userInput.equals("e") || userInput.equals("D") || userInput.equals("d"))) {
            System.err.println("Invalid input.");
            userInput = jarvis.promptForString("Enter \"E\" to Encode to Morse or \"D\" to Decode from Morse:");
        }

        // Sets mode based on input:
        if(userInput.equals("E") || userInput.equals("e")) morseCore.setMode("encode");
        if(userInput.equals("D") || userInput.equals("d")) morseCore.setMode("decode");
    }

    // Master setup function, calls all helper setup functions
    public void setup() {
        appWindow = new Window();
        jarvis = new DialogueManager();
        morseCore = MorseTranslator.getInstance();

        setupMode();

        if(VERBOSE) System.out.println("Setup completed successfully.\n");
        clearTerminal();
    }

    public void run() {
        switch(morseCore.mode) {
            case "encode":
                while(true) {
                    String userInput = jarvis.promptForString("Enter a message to encode: ");
                    while(userInput.isBlank()) {
                        System.err.println("Invalid input. Please input a non-empty string.");
                        userInput = jarvis.promptForString("Enter a message to encode: ");
                    }
                    System.out.println("Encoded Message: " + morseCore.encode(userInput.trim()) + "\n");
                }
            
            case "decode":
                while(true) {
                    String userInput = jarvis.promptForString("Enter Morse Code to decode: ");
                    while(userInput.isBlank()) {
                        System.err.println("Invalid input. Please input a non-empty string.");
                        userInput = jarvis.promptForString("Enter Morse Code to decode: ");
                    }
                    System.out.println("Decoded Message: " + morseCore.decode(userInput.trim()));
                    // Add in future a "back" and "exit" option, calling clean(0) with the 2nd option.
                }

            default:
                System.err.println("Invalid mode, terminating...");
                System.exit(1);
        }
    }
}
