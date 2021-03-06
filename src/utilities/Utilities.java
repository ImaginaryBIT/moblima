package utilities;

public class Utilities {
	//to wrap the long string within the fixed width.
	static final int FIXED_WIDTH = 50;
	
	public static String wrap(String longString) {
	    String[] splittedString = longString.split(" ");
	    String resultString = "";
	    String lineString = "";

	    for (int i = 0; i < splittedString.length; i++) {
	        if (lineString.isEmpty()) {
	            lineString += splittedString[i];
	        } else if (lineString.length() + splittedString[i].length() < FIXED_WIDTH) {
	            lineString += splittedString[i];
	        } else {
	            resultString += lineString + "\n";
	            lineString = "";
	        }
	    }

	    if(!lineString.isEmpty()){
	            resultString += lineString + "\n";
	    }

	    return resultString;
	}
}
