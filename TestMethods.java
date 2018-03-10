import java.io.File;
import java.util.Scanner;

public class TestMethods {
    public static void main(String[] args) {
       String invalidReason = "Start";
	   invalidReason += "Middle";
	   invalidReason = validateChapter(invalidReason);
	   System.out.println(invalidReason);
    } //end main()
	
	public static String validateChapter(String invalidReason) { 
		
		invalidReason += "End";
		return invalidReason;
	}
}