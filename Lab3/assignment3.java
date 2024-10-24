package Lab3;
import java.util.*;

class assignment3 {
    private static final Scanner KEYBOARD= new Scanner(System.in);
    public static void main(String[] args) {
        //Definition of variables
        double price, result, resultGeneralDiscount, resultDiscountMinor;
        int numTickets, age, numTicketsminor=0;
        final double GENERALDISCOUNT=0.8,DISCOUNTMINORS=0.9;

        //Ask for the price of the train ticket
        System.out.println("Introduce the price of the tickets:");
        price=KEYBOARD.nextFloat();

        while (price<=0) {
        System.out.println("ERROR! Introduce again the price of the tickets:");
        price=KEYBOARD.nextInt();
        }

        //Ask the user for the number of ticktets
        System.out.println("Introduce the number of tickets you want to purchase:");
        numTickets=KEYBOARD.nextInt();

        while (numTickets<0) {
        System.out.println("ERROR! Introduce again the number of tickets you want to purchase:");
        numTickets=KEYBOARD.nextInt();
        }
        //Ask the user for the age of each passenger.
         do {
            for (int i=1;i<=numTickets;i++) {
            System.out.println("Introduce the age of passenger " + i + ":");
            age=KEYBOARD.nextInt();
            //Count the number of tickets for minors
                if(age<18) {
                numTicketsminor=numTicketsminor+1;
                }
            }
        } while (numTicketsminor>=numTickets);
        System.out.println("The number of tickets for minors is: " + numTicketsminor);
        
        //Calculating the result price of tickets
        result=price*numTickets;
        System.out.printf("The final price is: %.2f", result); 

        resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
        //Applying the discount for minors
        System.out.printf("\nThere is a discount of a 20%% on tickets for minors: %.2f",resultDiscountMinor);
        
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
}
