public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
	
	// constructor
	public preprocessor(String str) {
		initial_string = str;
		preprocessed_string = "";
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	
	// capitalize the initial_string variable
	private void capitalize() {
		preprocessed_string = initial_string.replace('i', 'I').toUpperCase();
	}

	// Remove all characters that are not in the english alphabet
	private void clean() {
		preprocessed_string = preprocessed_string.replaceAll("[^A-Z]", "");
	}
	
	// getter for preprocessed_string variable
	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}