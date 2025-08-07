import java.util.Scanner;

public class DialogueManager {
    /* Instance Variables */
    private Scanner scanner;

    /* Constructor/s */
    public DialogueManager() {
        initialiseDM();
    }

    /* Methods */
    // Initialises a DialogueManager object
    private void initialiseDM() {
        // Instantiates scanner, sets its input stream to be stdin
        this.scanner = new Scanner(System.in);
    }

    // Prompts the user with question (if non-blank), returns the user's answer String
    public String promptForString(String question) {
        if(!question.isEmpty()) System.out.println(question);
        while(true) {
            if(this.scanner.hasNext()) {
                return this.scanner.nextLine().trim();
            }
        }
    }

}
