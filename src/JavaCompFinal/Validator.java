package JavaCompFinal;

import java.util.*;
/**
 * Validator.java - This class is to validate user entries.
 * @author Anjana
 *
 */
public class Validator {
/**
 * This method is to verify given input value is integer.
 * @param sc - scanner object
 * @param prompt - value entered by user
 * @return - integer
 */
	public static int getInt(Scanner sc, String prompt) {
      int i = 0;
      boolean isValid = false; 
      while (isValid == false) {
      	System.out.print(prompt);
          if (sc.hasNextInt()) {
              i = sc.nextInt();
              isValid = true;
          } else {
              System.out.println("Error! Invalid integer value. Try again.");
          }
          sc.nextLine();  // discard any other data entered on the line
      }
      return i;
  }
	/**
	 * This method is to verify given input value is integer and falls in defined range.
	 * @param sc - scanner object
	 * @param prompt - value entered by user
	 * @param min - minimum value of the defined range
	 * @param max - maximum value of the defined range
	 * @return integer
	 */
  public static int getIntWithInRange(Scanner sc, String prompt,
          int min, int max)
  {
      int i = 0;
      boolean isValid = false;
      while (isValid == false) {
          i = getInt(sc, prompt);
          if (i < min) {
              System.out.println("Error! Number must be >= " + min + ".");
          } else if (i > max) {
              System.out.println("Error! Number must be <= " + max + ".");
          } else {
              isValid = true;
          }  
      }
      return i;
  }
} //end Validator class
	
	

