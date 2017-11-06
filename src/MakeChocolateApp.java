import java.util.Scanner;

// ***************************************************************************
// ***  Coding Challenge 1 - Make Chocolate {Logic, Mathematical}          ***
// ***  -----------------------------------                                ***
// ***                                                                     ***
// *** File Name:    MakeChocolateeApp.java                                ***
// *** Written By:   Denise Clark                                          ***
// *** Date Written: 31 October 2017                                       ***
// *** Description:  Create an Application that Does the Following:        ***
// ***                                                                     ***
// ***               Make a Package of Chocolate of "goal" kilos.          ***
// ***               -- Small Bars = 1 kilo                                ***
// ***               -- Large Bars = 5 kilos                               ***
// ***               Assuming that Large Bars are Always Made First,       ***
// ***               Determine the Number of Small Bars that can be Made   ***
// ***               Based on the Requested Total-Killos of Chocolate and  ***
// ***               the Desired Number of Each Large and Small Bars.      ***
// ***               Return a "-1" to the Caller if the Request Cannot be  ***
// ***               Met.                                                  ***
// ***                                                                     ***
// ***************************************************************************
public class MakeChocolateApp {

   // ************************************************
   // *** Status Class Variables (Global in Scope) ***
   // ************************************************
	 static Scanner sc = new Scanner(System.in);
	 static int totalKilograms = 0;
	 static int largeBars = 0;
	 static int smallBars = 0;
	 final static int LARGE_BAR_KILO = 5;
	  
	 public static void main(String[] args) {

		// **********************************
		// *** Display Application Header ***
		// **********************************
		   System.out.println();
		   System.out.println("*****************************************");		  
		   System.out.println("***     Chocolate Order Request       *** ");
		   System.out.println("*****************************************");
		   System.out.println("Orders are based the Total Kilograms");
		   System.out.println("Large Bars = 5 Kilograms  (processed 1st!)");
		   System.out.println("Small Bars = 1 Kilogram");
		  
		// ***********************************
		// *** Local Variables Declaration ***
		// ***********************************
		  String choice = "y";
		  boolean isValidOrder = true;

	   // ******************************************************************
	   // *** Continue Until Order is Successful or User Request to Exit ***
	   // ******************************************************************
		  while (choice.equalsIgnoreCase("y")) {
		   
		   // *************************************
		   // *** Prompt User for Order Details ***
		   // *************************************
		      promptForOrderDetails();
		  
	       // ***************************************
	       // *** Validate the Order (Small Bars) ***
	       // ***************************************
	          isValidOrder = validateOrder(totalKilograms, largeBars, smallBars);

          // ****************************************************
          // *** Enter a New Order or Correct Existing Order? ***
          // ****************************************************
             if (isValidOrder) {
                 choice = getYNResponse ("Enter a New Order? (y/n)  ");
             } else {
                 choice = getYNResponse ("Re-Enter Order? (y/n)     ");
             }
             
		  } // End While
		  System.out.println("\nThank you");
		  sc.close();
		   
	}  // End Main
	

//  ***************************************************
//  ***                                             ***
//  *** Method: Prompt for Order Details            ***
//  ***                                             ***	
//  ***************************************************
	public static void promptForOrderDetails() {
		 
		int maxValue = 999;
		boolean allowZero = false;
		  
	 // *****************************************************************
     // *** Prompt for Kilograms (Greater than 0 and  Less than 9999) ***
     // *****************************************************************
		totalKilograms = getNumberInput("\nTotal Requested Kilograms: ", allowZero, maxValue);
		      
	 // **************************************************
     // *** Prompt for Large Bars (Between 0 and 9999) ***
     // **************************************************
		allowZero = true;
		maxValue = (int) totalKilograms/LARGE_BAR_KILO;
		largeBars = getNumberInput("Requested Large Bars:      ", allowZero, maxValue);
		     
     // **************************************************
	 // *** Prompt for Small Bars (Between 0 and 9999) ***
	 // **************************************************
		allowZero = true;
		maxValue = 999;
     // maxValue = (int) totalKilograms/5;
	    smallBars = getNumberInput("Requested Small Bars:      ", allowZero, maxValue);
	
	}

	
//  ***************************************************
//  ***                                             ***
//  *** Method: getNumberInput                      ***
//  ***                                             ***
//  ***************************************************
    public static int getNumberInput (String prompt, Boolean allowZero, int maxValue) {
	    boolean isValid = false;
		int num = 0;
		
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextInt())  {
	   	        num = sc.nextInt();	
	   	        
              // ***********************************   	           
	   	      // *** Value is 0 and 0 is Allowed ***
	          // ***********************************   	   	        
	   	        if ((num == 0) && (allowZero)) {
	   	        	 isValid = true;
	   	        	    
	   	        // ***************************************
	   	   	    // *** Value is 0 and 0 is NOT Allowed ***
	   	        // ***************************************
	   	        } else if ((num == 0) && (!allowZero)) {
	   		   	    System.out.println("%%% Please Enter a Value Greater than 0");
	   	         	sc.nextLine();
	   	   		    	
	            // ***************************************** 
	   	   	    // *** Value > 0 and <= to the MAX Value ***
	            // *****************************************   
	   	        } else if ((num > 0) && (num <= maxValue)) {
	  	            isValid = true;
	  	        	    
	  	       // *************************************** 
	  	   	   // *** Value > 0 and > than MAX Value  ***
	  	       // ***************************************
	   	        } else if ((num > 0) && (num > maxValue)) {
	  		   	    System.out.println("%%% Please Enter a Value Less than or Equal to " + maxValue);
	  	   	    	sc.nextLine();
	  	        }
	      // ***********************************   
	   	  // *** Non-Number Value Entered   ***
	      // ***********************************   
		   } else  {
	   	        System.out.println("%%% Please Enter a Numeric Value");
	   	 	   sc.nextLine();
		   }  // End if
			
		}  // End while
			return num;
	}  // End getNumberInput
	
	
//  ***************************************************
//  ***                                             ***
//  *** Method: validateOrder                       ***
//  ***                                             ***
//  ***************************************************
	public static Boolean validateOrder (int totalKilograms, int largeBars, int smallBars) {
		
		boolean isValidOrder = false;
	    int requiredSmallBars = (int) (totalKilograms - (largeBars*LARGE_BAR_KILO));
	    
		System.out.println();
	    if (requiredSmallBars == smallBars) {
	        System.out.println("Order Successfully Placed.");
	        isValidOrder = true;
	    } else if (requiredSmallBars < smallBars) {
            isValidOrder = false;;
	        System.out.println("%%% Order Cannot be Processed");
	        System.out.println("%%% Small Bars Quantity too Large by " + (smallBars-requiredSmallBars));
	    } else if (requiredSmallBars > smallBars) {
	        isValidOrder = false;
	        System.out.println("%%% Order Cannot be Processed");
	        System.out.println("%%% Small Bars Short by " + ((requiredSmallBars-smallBars)*-1));
	    }
	    return isValidOrder;
	    
	}  // End alidateOrder
	
	       
//  ***************************************************
//  ***                                             ***
//  *** Method: getYNResonse                        ***
//  ***                                             ***
//  ***************************************************
	public static String getYNResponse (String prompt) {
		
		boolean isValid = false;
		String choice = "";
		while (!isValid) {
			System.out.print("\n" + prompt);
		   if (sc.hasNext())  {
   	           choice = sc.next();	
	           if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
	               isValid = true;
	           else
	   	           System.out.println("%%% Invalid Entry - Enter Y or N");	        	   
		   } else  {
   	           System.out.println("%%% Invalid Entry - Please Retry");
	       }
	    }  
	    return choice;
    } 
	
}  // End Class