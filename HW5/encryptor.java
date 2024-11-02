import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	// constructor
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		plain_text = text;
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	/**
	 * This method generates a keystream for the encription process.
	 * The keystream is a sequence of characters that is based on the key and has the same length as the plaintext.
	 *
	 * <p>If the length of the plaintext is greater than the length of the key, the method extends
	 * the key by repeating its characters until its lengths matches.
	 *
	 * <p>If the length of the plaintext is less than the length of the key, the method trims the key to match the lengths.
	 */
	private void generate_keystream() {
		// Initialize a StringBuilder to build the keystream
		StringBuilder str = new StringBuilder();
		
		// Determine the minimum length between the plain text and the key
		int min = Math.min(plain_text.length(), key.length());
		
		// Append the key to the StringBuilder up to the minimum length
		str.append(key, 0, min);
		
		// If the plain text is longer than the key, repeat the key until the lengths match
		for(int i = min; i < plain_text.length(); i++) {
			str.append(key.charAt(i % min));
		}
		
		// Convert the StringBuilder to a string and assign it to the keystream
		keystream = str.toString();
	}
	
	/**
	 * This method generates the cipher text for the encryption process.
	 * <p>
	 * The method iterates over each character in the plaintext message and the corresponding character in the keystream.
	 * For each pair of characters, it uses the map sheme to find its encrypted value and appends it to the cipher text.
	 */
	private void generate_cipher_text() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < plain_text.length(); i++) {
			char keyRow = plain_text.charAt(i);
			char keyCol = keystream.charAt(i);
			char value = map.get(keyRow).get(keyCol);
			str.append(value);
		}
		cipher_text = str.toString();
	}
	
	// This method is a getter for the 'keystream' variable.
	public String get_keystream() {
		return keystream;
	}
	
	// This method is a getter for the 'cipher_text' variable.
	public String get_cipher_text() {
		return cipher_text;
	}
}
