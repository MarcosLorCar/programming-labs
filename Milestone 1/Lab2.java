/*
MARCOS LORO CARRASCO
IGNACIO ROMERO TORRES
RAFAEL CAMILO RUBIO QUINTERO
GONZALO SOBRINO DE LA FLOR
*/
import java.util.Scanner;
public class Lab2 {
    private static final Scanner KEYBOARD= new Scanner(System.in);
    public static void main(String[] args) {
        //Creation of variables
        double price, result, resultDiscountMinor, resultGeneralDiscount;
        int numTickets, numTicketsminor;
        final double DISCOUNTMINORS=0.8, GENERALDISCOUNT=0.9;

        //Definition of variables
        System.out.print("What is the price of your train ticket?\n");
        price=KEYBOARD.nextFloat();

        System.out.print("How many tickets would you like to purchase?\n");
        numTickets=KEYBOARD.nextInt();

        //Checking number of tickets is greater than 0.
        if (numTickets>0) {
            System.out.print("How many tickets for minors would you like to purchase?\n");
            numTicketsminor=KEYBOARD.nextInt();
            
            //Checking tickets for minors are less than the total of tickets.
            if ((numTicketsminor>=0) && (numTicketsminor<numTickets)) {
                result=price*numTickets;
    
                System.out.printf("The final price is: %.2f", result); 
    
                resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                //Applying the discount for minors
                System.out.printf("\nThere is a discount of a 20%% on tickets for minors: %.2f", resultDiscountMinor);
                //Applying or not the general discount
                if(numTickets>12) {
                    resultGeneralDiscount=resultDiscountMinor*GENERALDISCOUNT;
                    System.out.printf("\nAs you have bought more than 12 tickets, we will apply a 10%% discount on the previous price: %.2f", resultGeneralDiscount);
                    System.out.print("\nEnd of the program...");
                }
                else{
                    System.out.print("\nEnd of the program...");
                    
                }
            }
            else{
                System.out.println("ERROR!, number of tickets for minors are greater than the total number of tickets. Try again");
                System.out.print("End of the program...");
                
            }
            
        }
        else {
            System.out.println("ERROR!, impossible number of tickets introduced. Try again");
            System.out.print("End of the program...");
             }
    }
}
