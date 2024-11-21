package Práctica.ProgrammingLabs.Ignacio;
import java.util.*;

public class assignment4 {
    private static final Scanner KEYBOARD= new Scanner(System.in);
    public static void main(String[] args) {
        //Definition of variables.
        double price=0, result, resultGeneralDiscount, resultDiscountMinor;
        int option,numTickets, age, numTicketsminor=0, seat_position;
        final double GENERALDISCOUNT=0.9,DISCOUNTMINORS=0.8;
        final int AGE_LIMIT_MINOR=18, num_Seats=20;
        boolean ageData;
        char[] seats= new char[num_Seats];
        //Defining the list in the initial moment.
        for (int i=0;i<seats.length;i++) {
            seats[i]= 'A';
        }
        
        //Show the main menu.
        do {
            System.out.print("\nMain menu:\n1.Establish the price for the train ticket\n2.Purchase tickets\n3.Exit the program\n");
            System.out.print("Select an option:");
            option=KEYBOARD.nextInt();
            
            switch (option) {
                case 1:
                    //Ask for the price of the train ticket.
                    System.out.println("Introduce the price of the tickets:");
                    price=KEYBOARD.nextFloat();
                    //Checking price is valid.
                    while (price<=0) {
                        System.out.println("\nERROR! Introduce again the price of the tickets:");
                        price=KEYBOARD.nextInt();
                    }
                    break;
                case 2:
                    //Check if option 1 was selected previously.
                    if (price==0) {
                        System.out.println("\nERROR! It is necessary to select the price of tickets. Try again");
                    }
                    else{
                        //Ask the user for the number of ticktets.
                        System.out.println("Introduce the number of tickets you want to purchase:");
                        numTickets=KEYBOARD.nextInt();

                        if (numTickets<=num_Seats) {
                            while (numTickets<0) {
                                System.out.println("\nERROR! Introduce again the number of tickets you want to purchase:");
                                numTickets=KEYBOARD.nextInt();
                                }
                            //Ask the user for the age of each passenger.
                            ageData=false;
                            while(!ageData) {
                                for (int i=1;i<=numTickets;i++) {
                                    System.out.println("Introduce the age of passenger " + i + ":");
                                    age=KEYBOARD.nextInt();
                                    //Count the number of tickets for minors.
                                    if(age<AGE_LIMIT_MINOR) {
                                        numTicketsminor=numTicketsminor+1;
                                        }
                                    }
                                if(numTicketsminor < numTickets) {
                                        ageData = true;
                                } else {
                                    System.out.println("ERROR. The ages entered indicate that there is no adults. Re-enter ages.");
                                    numTicketsminor=0;
                                    }
                                }
                                System.out.println("The number of tickets for minors is: " + numTicketsminor);

                                //Printing the seats available.
                                System.out.println("The current situation of the seats is:");

                                for (int i=0;i<seats.length;i++) {
                                    System.out.print(i+1 + ":" + seats[i] + " ");
                                }

                            //The user selects their seats.
                            for (int i=1;i<=numTickets;i++) {
                                //Checking if the seat selected is already occupied and that is not out of bounds.
                                do {
                                    System.out.println("\nEnter the seat number for passenger " + i + ":");
                                    seat_position=KEYBOARD.nextInt();

                                    if (seats[seat_position-1]=='O') {
                                        System.out.println("This seat is already occupied. Select other one");
                                    }

                                    if (seat_position >20 || seat_position<1) {
                                        System.out.println("ERROR. The seat must be between 1 and 20");
                                    }
                                }while(seats[seat_position-1]=='O' || seat_position >20 || seat_position<1);
                                seats[seat_position-1]='O';
                            }
                            
                            //Showing the occupied seats.
                            System.out.println("The current seat occupancy is:");
                            for (int i=0;i<seats.length;i++) {
                                System.out.print(i+1 + ":" + seats[i] + " ");
                            }
                            //Calculating the result price of tickets.
                            result=price*numTickets;
                            System.out.printf("\nThe final price is: %.2f", result); 

                            //Applying or not the discount for minors.
                            if (numTicketsminor>0) {
                                resultDiscountMinor= price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                System.out.printf("\nThere is a discount of a 20%% on tickets for minors: %.2f",resultDiscountMinor);
                            }
                            
                            
                            //Applying or not the general discount
                            if (numTickets>12 && numTicketsminor<=0) {
                                resultGeneralDiscount=price*numTickets*GENERALDISCOUNT;
                                System.out.printf("\nAs you have bought more than 12 tickets, we will apply a 10%% discount on the previous price: %.2f", resultGeneralDiscount);
                                }

                            if(numTickets>12 && numTicketsminor>0) {
                                resultGeneralDiscount=(price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor))*GENERALDISCOUNT;
                                System.out.printf("\nAs you have bought more than 12 tickets, we will apply a 10%% discount on the previous price: %.2f", resultGeneralDiscount);
                            }
                        }
                        else{
                            System.out.println("There are not enough seats for everyone");
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
