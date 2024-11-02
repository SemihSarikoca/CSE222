import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	// constructor
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		cipher_text = text;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	/**
	 * This method generates a keystream for the decryption process.
	 * The keystream is a sequence of characters that is based on the key and has the same length as the ciphertext.
	 *
	 * <p>If the length of the ciphertext is greater than the length of the key, the method extends
	 * the key by repeating its characters until its lengths matches.
	 *
	 * <p>If the length of the ciphertext is less than the length of the key, the method trims the key to match the lengths.
	 */
	private void generate_keystream() {
		// Initialize a StringBuilder to build the keystream
		StringBuilder str = new StringBuilder();
		
		// Determine the minimum length between the cipher text and the key
		int min = Math.min(cipher_text.length(), key.length());
		
		// Append the key to the StringBuilder up to the minimum length
		str.append(key, 0, min);
		
		// If the cipher text is longer than the key, repeat the key until the lengths match
		for(int i = min; i < cipher_text.length(); i++) {
			str.append(key.charAt(i % min));
		}
		
		// Convert the StringBuilder to a string and assign it to the keystream
		keystream = str.toString();
	}
	
	/**
	 * This method generates the plaintext for the decryption process.
	 * <p>
	 * The method iterates over each character in the cipher text and the corresponding character in the keystream.
	 * For each pair of characters, it uses the map scheme to find the row that contains the cipher character for the given column (keystream character)
	 * and appends its key to the plaintext.
	 */
	private void generate_plain_text() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < cipher_text.length(); i++) {
			char value = cipher_text.charAt(i);
			char keyCol = keystream.charAt(i);
			
			Iterator<Character> it = map.keySet().iterator();
			while (it.hasNext()) {
				char keyRow = it.next();
				if(map.get(keyRow).get(keyCol).equals(value)) {
					str.append(keyRow);
					break;
				}
			}
		}
		plain_text = str.toString();
	}
	
	// This method is a getter for the 'keystream' variable.
	public String get_keystream() {
		return keystream;
	}
	
	// This method is a getter for the 'plain_text' variable.
	public String get_plain_text() {
		return plain_text;
	}
}
