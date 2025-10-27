package temp;

import java.util.Date;

public class generateEmailDemo {
	
	public static void main(String[]args) {

	Date date = new Date();
	String dateString = date.toString();
	//System.out.println(dateString);
	String noSpaceDateString = dateString.replaceAll(" ", "");
	//System.out.println(noSpaceDateString);
	String noSpaceAndNoColonDateString = noSpaceDateString.replaceAll("\\:", "");
	//System.out.println(noSpaceAndNoColonDateString);
    String uniqueEmailWithTimeStamp = noSpaceAndNoColonDateString + "@gmail.com";	
    System.out.println(uniqueEmailWithTimeStamp);
			
	
		
	}
}
