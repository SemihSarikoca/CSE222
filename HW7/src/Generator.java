import java.security.SecureRandom;

/**
 * This class is used to generate random symbols.
 * It uses a SecureRandom object to generate random indices for the ALPHABET string.
 */
public class Generator {
    /**
     * The alphabet string used to generate random symbols.
     */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * The SecureRandom object used to generate random indices for the ALPHABET string.
     */
    private static final SecureRandom RANDOM = new SecureRandom();
    
    /**
     * Generates a random symbol of the given length.
     * It uses the RANDOM object to generate random indices for the ALPHABET string,
     * and appends the corresponding characters to a StringBuilder object.
     * Finally, it returns the string representation of the StringBuilder object.
     *
     * @param length The length of the random symbol to be generated.
     * @return The generated random symbol.
     */
    public static String randomSymbol(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }
}
