import java.util.HashMap;
import java.util.Map;

public class MorseTranslator {

    // For Singleton design pattern -> should only ever have one MorseTranslator
    private static MorseTranslator instance;
    
    // For detailed logging
    public static boolean VERBOSE = true;

    // Either between "encode" or "decode", determines what input the user is prompted for (tried using enums, shit sucked)
    public String mode;

    // Hashmaps for Alphanumeric -> Morse (encodeList) and Morse -> Alphanumeric (decodeList)
    public final static HashMap<Character, String> encodeList;
    static {
        encodeList = new HashMap<>();
        // Letters
        encodeList.put('a', ".-");
        encodeList.put('b', "-...");
        encodeList.put('c', "-.-.");
        encodeList.put('d', "-..");
        encodeList.put('e', ".");
        encodeList.put('f', "..-.");
        encodeList.put('g', "--.");
        encodeList.put('h', "....");
        encodeList.put('i', "..");
        encodeList.put('j', ".---");
        encodeList.put('k', "-.-");
        encodeList.put('l', ".-..");
        encodeList.put('m', "--");
        encodeList.put('n', "-.");
        encodeList.put('o', "---");
        encodeList.put('p', ".--.");
        encodeList.put('q', "--.-");
        encodeList.put('r', ".-.");
        encodeList.put('s', "...");
        encodeList.put('t', "-");
        encodeList.put('u', "..-");
        encodeList.put('v', "...-");
        encodeList.put('w', ".--");
        encodeList.put('x', "-..-");
        encodeList.put('y', "-.--");
        encodeList.put('z', "--..");

        // Numbers
        encodeList.put('0', "-----");
        encodeList.put('1', ".----");
        encodeList.put('2', "..---");
        encodeList.put('3', "...--");
        encodeList.put('4', "....-");
        encodeList.put('5', ".....");
        encodeList.put('6', "-....");
        encodeList.put('7', "--...");
        encodeList.put('8', "---..");
        encodeList.put('9', "----.");

        // Valid Special Characters
        encodeList.put('?', "..--..");
        encodeList.put('!', "-.-.--");
        encodeList.put('.', ".-.-.-");
        encodeList.put(',', "--..--");
        encodeList.put(';', "-.-.-.");
        encodeList.put(':', "---...");
        encodeList.put('+', ".-.-.");
        encodeList.put('-', "-....-");
        encodeList.put('/', "-..-.");
        encodeList.put('=', "-...-");

        if(VERBOSE) System.out.println("encodeList = " + encodeList);
    }
    public final static HashMap<String, Character> decodeList;
    static {
        decodeList = new HashMap<>();
        // Copies encodeList in reverse: <K, V> pairs become <V, K>
        for(Map.Entry<Character, String> entry : encodeList.entrySet()) {
            decodeList.put(entry.getValue(), entry.getKey());
        }
        if(VERBOSE) System.out.println("decodeList = " + decodeList);
        // // Letters
        // decodeList.put(".-", 'a');
        // decodeList.put("-...", 'b');
        // decodeList.put("-.-.", 'c');
        // decodeList.put("-..", 'd');
        // decodeList.put(".", 'e');
        // decodeList.put("..-.", 'f');
        // decodeList.put("--.", 'g');
        // decodeList.put("....", 'h');
        // decodeList.put("..", 'i');
        // decodeList.put(".---", 'j');
        // decodeList.put("-.-", 'k');
        // decodeList.put(".-..", 'l');
        // decodeList.put("--", 'm');
        // decodeList.put("-.", 'n');
        // decodeList.put("---", 'o');
        // decodeList.put(".--.", 'p');
        // decodeList.put("--.-", 'q');
        // decodeList.put(".-.", 'r');
        // decodeList.put("...", 's');
        // decodeList.put("-", 't');
        // decodeList.put("..-", 'u');
        // decodeList.put("...-", 'v');
        // decodeList.put(".--", 'w');
        // decodeList.put("-..-", 'x');
        // decodeList.put("-.--", 'y');
        // decodeList.put("--..", 'z');

        // // Numbers
        // decodeList.put("-----", '0');
        // decodeList.put(".----", '1');
        // decodeList.put("..---", '2');
        // decodeList.put("...--", '3');
        // decodeList.put("....-", '4');
        // decodeList.put(".....", '5');
        // decodeList.put("-....", '6');
        // decodeList.put("--...", '7');
        // decodeList.put("---..", '8');
        // decodeList.put("----.", '9');

        // // Valid Special Characters
        // decodeList.put("..--..", '?');
        // decodeList.put("-.-.--", '!');
        // decodeList.put(""
        // decodeList.put(
        // decodeList.put(
        // decodeList.put(
        // decodeList.put(
        // decodeList.put(
        // decodeList.put(
        // decodeList.put(

            
    }
    

    // Singleton design pattern -> shouldn't be more than one of these, but the first call to getInstance() makes it.
    public static MorseTranslator getInstance() {
        if(instance == null) {
            instance = new MorseTranslator();
        }
        return instance;
    }

    public void setMode(String s) {
        switch(s) {
            case "encode":
                this.mode = "encode";
                break;
            
            case "decode":
                this.mode = "decode";
                break;

            default:
                System.err.println("setMode() passed an invalid mode setting..");
        }
        if(VERBOSE) System.out.println("MorseTranslator mode set to: " + this.mode);
    }
    // Encodes a single alphanumeric character to its counterpart in Morse code, and returns the encoded character.
    public static String encodeChar(Character c) {
        return encodeList.get(Character.toLowerCase(c));
    }

    // Encodes an alphanumeric String of any length to Morse code, returns the encoded String (chars split with '|').
    public static String encode(String s) {
        String encodedString = "|";
        for (char c : s.toCharArray()) {
            String encodedChar = encodeChar(c);
            if (encodedChar != null) {
                encodedString += encodedChar + "|";
            } else if(c == ' ') {
                encodedString += " |";
            } else {
                if(VERBOSE) System.out.println("\'" + c + "\'" + " not valid Morse character or space, omitting...");
            }
        }
        if(VERBOSE) System.out.println("\"" + s + "\"" + "\ntranslated to:\n" + encodedString);
        return encodedString;
    }

    // When passed a single Morse character (as a String), decode and return it.
    public static Character decodeChar(String s) {
        if(s.length() > 6) {
            System.err.println("Error, decodeChar() was passed a Morse sequence longer than the max of 6.");
        }
        return decodeList.get(s);
    }

    public static String decode(String s) {
        String decodedString = "";
        boolean endOfWord = false;
        String[] arr = s.split("[|]");
        for (String str : arr) {
            if(VERBOSE) System.out.println(str);
            if(str.equals(" ")) {
                if(VERBOSE) System.out.println("End of word detected, adding space to decoded string...");
                decodedString += " ";
                continue;
            }
            Character decodedChar = decodeChar(str);

            if(VERBOSE) System.out.println(decodedChar);
            if(decodedChar != null) {
                decodedString += (endOfWord) ? decodedChar + " " : decodedChar;
            } else {
                if(VERBOSE) System.out.println("\'" + str + "\'" + " not valid Morse sequence, omitting...");
            }
            endOfWord = false;
        }
        return decodedString;
    }


}
