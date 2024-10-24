package Ignacio;
import java.util.*;

class assignment3optional {
    private static final Scanner KEYBOARD= new Scanner(System.in);
    public static void main(String[] args) {
        //Definition of variables
        double price=0, result, resultGeneralDiscount, resultDiscountMinor;
        int option,numTickets, age, numTicketsminor=0;
        final double GENERALDISCOUNT=0.8,DISCOUNTMINORS=0.9;
        //Show the main menu
        do {
            System.out.print("\nMain menu:\n1.Establish the price for the train ticket\n2.Purchase tickets\n3.Exit the program\n");
            System.out.print("Select an option:");
            option=KEYBOARD.nextInt();
            
            switch (option) {
                case 1:
                    //Ask for the price of the train ticket
                    System.out.println("Introduce the price of the tickets:");
                    price=KEYBOARD.nextFloat();
                    //Checking price is valid
                    while (price<=0) {
                        System.out.println("\nERROR! Introduce again the price of the tickets:");
                        price=KEYBOARD.nextInt();
                    }
                    break;
                case 2:
                    //Check if option 1 was selected previously
                    if (price==0) {
                        System.out.println("\nERROR! It is necessary to select the price of tickets. Try again");
                        break;
                    }
                    else{
                        //Ask the user for the number of ticktets
                        System.out.println("Introduce the number of tickets you want to purchase:");
                        numTickets=KEYBOARD.nextInt();

                        while (numTickets<0) {
                            System.out.println("\nERROR! Introduce again the number of tickets you want to purchase:");
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
                    break;
                case 3:
                    System.out.print("End of program...");
                    break;
        
                default:
                    System.out.println("ERROR! Invalid option was selected, try again:");
                    System.out.print("Main menu:\n1.Establish the price for the train ticket\n2.Purchase tickets\n3.Exit the program\n");
                    System.out.print("Select an option:");
                    option=KEYBOARD.nextInt();
                    break;
                }
        } while(option!=3);
    }
}
