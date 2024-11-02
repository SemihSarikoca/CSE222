import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}
	
	private void fill_english_alphabet() {
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}
	
	/**
	 * This method is used to fill the map with the correct values.
	 * The map is a two-dimensional map where the key of the outer map is a character from the English alphabet,
	 * and the value is another map (the inner map). The key of the inner map is also a character from the English alphabet,
	 * and the value is a character that represents the result of a certain shift operation.
	 * <p>
	 * The shift operation is performed as follows:
	 * For each character c1 in the English alphabet, an inner map is created.
	 * Then, for each character c2 in the English alphabet, the character c2 is mapped to a character that is obtained by
	 * shifting the character c1 by the distance from c1 to c2 in the alphabet, wrapping around at 'Z'.
	 * The result of the shift operation is then put into the inner map.
	 * Finally, the character c1 is mapped to the inner map in the outer map.
	 */
	private void fill_map() {
		Iterator<Character> it1 = english_alphabet.iterator();
		while (it1.hasNext()) {
			char c1 = it1.next();
			Map<Character, Character> inner_map = new HashMap<>();
			Iterator<Character> it2 = english_alphabet.iterator();
			while (it2.hasNext()) {
				char c2 = it2.next();
				inner_map.put(c2, c1);
				c1 = (char) ((c1 - 'A' + 1) % 26 + 'A');
			}
			map.put(c1, inner_map);
		}
	}
	
	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
	}
	
	// getter for map variable
	public Map get_map() {
		return map;
	}
}