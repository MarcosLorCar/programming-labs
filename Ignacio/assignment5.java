package Práctica.ProgrammingLabs.Ignacio;
import java.util.*;

public class assignment5 {
    private static final Scanner KEYBOARD= new Scanner(System.in);
    public static void main(String[] args) {
        //Defining the variables
        final int rows=6,columns=5;
        int option,option_purchase, available_seats=rows*columns,occupied_seats=0, cont=0;
        int numTickets=0,numTicketsminor,positionRow,positionColumn,seats_toChange,position_changeRow=0,position_changeColumn=0;
        double price=0,result, resultDiscountMinor;
        final double GENERALDISCOUNT=0.8,DISCOUNTMINORS=0.9;
        char seats[][]= new char[rows][columns];
        boolean isValid;
        

        //At the start, all the seats are available

        for (int i=0;i<rows;i++) {
            for (int j = 0; j <columns; j++) {
                seats[i][j]='A';
            }
        }

        //Showing the current seat situation.

        do {
            System.out.println("Welcome to the train ticket management software");
            System.out.println("The current seat status is as follows:");

            for (int i = 0; i<(seats[0].length); i++) {
                System.out.printf("        %d",i+1);
            }
            
            for (int i = 0; i < seats.length; i++) {
                System.out.printf("\nRow %d:",i+1);
                for (int j = 0; j < seats[0].length; j++) {
                    System.out.print("  " + seats[i][j] + "      ");
                }
            }
            //Asking for the price of the ticket.
            do {
                System.out.println("\nEnter the price of the ticket:");
                System.out.println("Remember that the ticket price must be greater than 0.");
                price=KEYBOARD.nextInt();

                if (price<=0) {
                    System.out.println("Invalid price entered. Please try again.");
                }
            } while (price<=0);

            //Showing the available seats.
            available_seats=(available_seats-occupied_seats);
            System.out.printf("Currently there are %d available seats.\n\n",available_seats);

            //Showing the menu of options.
            do {
                System.out.println("Choose one of the following options:");
                System.out.println("1. Purchase tickets");
                System.out.println("2. Change tickets");
                System.out.println("3. Show seat status");
                System.out.println("4. Exit the program");
                //Asking the option
                System.out.println("Enter the number of the option you wish to choose:");
                option=KEYBOARD.nextInt();

                if (option==1 && available_seats==0) {
                    System.out.println("Purchase of tickets is not possible because there are no more available seats.");
                }
                else{
                    switch (option) {
                        case 1:
                        //Asking the total number of tickets and number of tickets for minors.
                        do {
                            System.out.println("Enter the number of tickets you wish to purchase. You must buy at least 1.");
                            System.out.printf("There are %d available seats.\n",available_seats);
                            numTickets=KEYBOARD.nextInt();
                            System.out.println("\nIntroduce how many tickets are for minors. Remember a minimum of 1 adult is required.");
                            numTicketsminor=KEYBOARD.nextInt();
    
                            if (numTickets<=0) {
                                System.out.println("ERROR! Invalid number of tickets selected. Try again.");
                            }
    
                            if (numTicketsminor<0) {
                                System.out.println("ERROR! Invalid number of tickets for minors selected. Try again ");
                            }
    
                            if (numTicketsminor>=numTickets) {
                                System.out.println("ERROR! Minors should be accompanied by an adult and the number of tickets for minors exceed the total number of tickets. Try again ");
                            }
    
                            if (numTickets>available_seats) {
                                System.out.println("Sorry, but there are not enough seats available. Try again");
                            }
                        } while (numTicketsminor>=numTickets || numTickets>available_seats || numTickets<=0 || numTicketsminor<0);
    
                        //Showing the menu for selecting the seats:
                            do {
                                System.out.println("Choose one of the following options for purchasing tickets:");
                                System.out.println("1. Manual seat selection.");
                                System.out.println("2. Automatic seat selection.");
                                System.out.println("3. Selection of adjacent seats.");
                                System.out.println("4. Exit the purchase menu.");
    
                                System.out.println("Enter the number of the option you want to choose");
                                option_purchase=KEYBOARD.nextInt();
    
                                switch (option_purchase) {
                                    case 1:
                                    //Showing again the seats available.
                                    for (int i = 0; i<(seats[0].length); i++) {
                                        System.out.printf("        %d",i+1);
                                    }
            
                                    for (int i = 0; i < seats.length; i++) {
                                        System.out.printf("\nRow %d:",i+1);
                                        for (int j = 0; j < seats[0].length; j++) {
                                            System.out.print("  " + seats[i][j] + "      ");
                                        }
                                    }
                                    System.out.println("\n");
                                
                                        //Manual selection of the seats.
                                        occupied_seats=0;
                                        isValid=false;
                                        for (int i=1;i<=numTickets;i++) {
                                            do {
                                                System.out.printf("\nEnter the row for seat of ticket number %d\n",i);
                                                positionRow=KEYBOARD.nextInt();
    
                                                System.out.printf("\nEnter the column for seat of ticket number: %d\n",i);
                                                positionColumn=KEYBOARD.nextInt();

                                                if ((positionRow)>rows || (positionRow)<0 || (positionColumn)>rows || (positionColumn)<0) {
                                                    System.out.println("Sorry. The position selected is out of range. Try again.");
                                                }
                                                else {
                                                    if (seats[positionRow-1][positionColumn-1]=='O') {
                                                    System.out.println("Sorry. This seat is already occupied. Select again");
                                                    isValid=true;
                                                    }
                                                }

                                            } while (!isValid || (positionRow-1)>rows || (positionRow-1)<0 || (positionColumn-1)>rows || (positionColumn-1)<0 );
                                            seats[positionRow-1][positionColumn-1]='O';
                                            occupied_seats++;
                                            System.out.println("Seat has been selected.");
                                        }
                                        
                                        //Showing the invoice:
                                        System.out.println("Invoice:");
                                        System.out.printf("  Number of tickets: %d\n",numTickets);
                                        System.out.printf("  Price per ticket: %.2f\n", price);
    
                                        result=price*numTickets;
                                        //Calculating the different prices with each discount.
                                        if (numTickets>12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            result=resultDiscountMinor*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n",result);
                                        }
                                        else if (numTickets>12 && numTicketsminor<=0 ) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            result=result*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
                                        else if (numTickets<=12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None \n");
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            System.out.printf("  Total price: %.2f\n", resultDiscountMinor);
                                        }
                                        else if (numTickets<=12 && numTicketsminor<=0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None\n");
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
    
                                        available_seats=(available_seats-occupied_seats);
                                        System.out.printf("  Currently there are %d available seats.\n\n",available_seats);
                                        option_purchase=4;
                                    
                                    
                                        break;
                                    case 2:
                                
                                        //Automatic selection of each seat.
                                        occupied_seats=0;
                                        for (int i=0;i<numTickets;i++) {
                                            do {
                                                positionRow= (int)  (Math.random()*rows);
                                                positionColumn= (int) (Math.random()*columns);
    
                                            } while (seats[positionRow][positionColumn]=='O');
                                            seats[positionRow][positionColumn]='O';
                                            occupied_seats++;
                                            System.out.printf("The seat in row %d and column %d has been selected.\n",positionRow+1,positionColumn+1);
                                        }
                                        
                                        //Showing the invoice:
                                        System.out.println("Invoice:");
                                        System.out.printf("  Number of tickets: %d\n",numTickets);
                                        System.out.printf("  Price per ticket: %.2f\n", price);
    
                                        result=price*numTickets;
                                        //Calculating the different prices with each discount.
                                        if (numTickets>12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            result=resultDiscountMinor*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n",result);
                                        }
                                        else if (numTickets>12 && numTicketsminor<=0 ) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            result=result*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
                                        else if (numTickets<=12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None \n");
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            System.out.printf("  Total price: %.2f\n", resultDiscountMinor);
                                        }
                                        else if (numTickets<=12 && numTicketsminor<=0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None\n");
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
    
                                        available_seats=(available_seats-occupied_seats);
                                        System.out.printf("  Currently there are %d available seats.\n\n",available_seats);
                                        option_purchase=4;
                                    
                                        break;
                                    case 3:
                                    occupied_seats=0;
                                    cont=0;
                                    //Selection of adjacent seats
                                    if (numTickets>seats[0].length) {
                                        System.out.println("It is not possible to assign adjacent seats, because the number of tickets exceed the number of columns. Try again");
                                    }
                                    else{
                                        //Detecting the available seats in a row.
                                        for (int i=0;i<seats.length; i++) {
                                            for (int j=0;j<seats[0].length; j++) {
                                                if (seats[i][j]=='A') {
                                                    cont++;
                                                }
                                                if (numTickets==cont) {
                                                    for (int k=0;k<numTickets;k++) {
                                                        System.out.printf("The seat in row %d and column %d has been selected\n",i+1,k+1);
                                                        seats[i][k]='O';
                                                        occupied_seats++;
                                                    }
                                                    j=seats[0].length; //Breaking the loop that iterates rows.
                                                    i=seats.length;  //Breaking the loop that iterates columns.
                                                }
                                            }//End of loop for columns.
                                        } //End of loop for rows.
                        
                                        //Showing the invoice:
                                        System.out.println("Invoice:");
                                        System.out.printf("  Number of tickets: %d\n",numTickets);
                                        System.out.printf("  Price per ticket: %.2f\n", price);
    
                                        result=price*numTickets;
                                        //Calculating the different prices with each discount.
                                        if (numTickets>12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            result=resultDiscountMinor*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n",result);
                                        }
                                        else if (numTickets>12 && numTicketsminor<=0 ) {
                                            System.out.printf("  Discount applied for purchase in bulk: %.2f %% \n",GENERALDISCOUNT*100);
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            result=result*GENERALDISCOUNT;
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
                                        else if (numTickets<=12 && numTicketsminor>0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None \n");
                                            System.out.printf("  Discount applied for tickets for minors: %.2f %% \n",DISCOUNTMINORS*100);
                                            resultDiscountMinor=price*numTicketsminor*DISCOUNTMINORS + price*(numTickets-numTicketsminor);
                                            System.out.printf("  Total price: %.2f\n", resultDiscountMinor);
                                        }
                                        else if (numTickets<=12 && numTicketsminor<=0) {
                                            System.out.printf("  Discount applied for purchase in bulk: None\n");
                                            System.out.printf("  Discount applied for tickets for minors: None\n");
                                            System.out.printf("  Total price: %.2f\n", result);
                                        }
                                        available_seats=(available_seats-occupied_seats);
                                        System.out.printf("  Currently there are %d available seats.\n\n",available_seats);
                                        option_purchase=4;
                                        }
    
                                        break;
                                    case 4:
                                    occupied_seats=0;
                                    //Exiting the purchase menu.
                                        System.out.println("Exiting the purchase menu.");
                                        break;
                                    default:
                                        System.out.println("ERROR! Invalid option entered. Try again.");
                                        break;
                                }
                            } while (option_purchase!=4);
                            break;
                        case 2:
                        //maing tickets.
                        if (numTickets<=0) { //Checking the number of tickets is greater than 0.
                            System.out.println("\nERROR! It is necessary to purchase tickets. Try again\n");
                        }
                        else{
                            //Showing the seat status
                            for (int i = 0; i<(seats[0].length); i++) {
                                System.out.printf("        %d",i+1);
                            }
    
                            for (int i = 0; i < seats.length; i++) {
                                System.out.printf("\nRow %d:",i+1);
                                for (int j = 0; j < seats[0].length; j++) {
                                    System.out.print("  " + seats[i][j] + "      ");
                                }
                            }
                            System.out.println("\n");
                            //Asking for the number of tickets to be changed.
                            System.out.println("Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.");
                            seats_toChange=KEYBOARD.nextInt();
    
                            if (seats_toChange>occupied_seats) {
                                System.out.println("ERROR! Seats selected to change are greater than the number of seats occupied.");
                            }
                            else if (seats_toChange>available_seats) {
                                System.out.println("ERROR! There are not enough seats to change");
                            }
                            else {
                                System.out.printf("%d seats will be changed.\n",seats_toChange);
    
                            //Asking for the position of the tickets which are wanted to change.
                                for (int i=0;i<numTickets;i++) {
                                    do {
                                        System.out.println("Select the row of the seat you want to change:");
                                        positionRow=KEYBOARD.nextInt();
                                        System.out.println("Select the column of the seat you want to change:");
                                        positionColumn=KEYBOARD.nextInt();
    
                                        if (seats[positionRow-1][positionColumn-1]=='A') {
                                            System.out.println("ERROR! This seat is available. Try selecting an occupied seat.");
                                        }
                                        else{
                                            System.out.println("Select the row of the seat you want to change to:");
                                            position_changeRow=KEYBOARD.nextInt();
                                            System.out.println("Select the column of the seat you want to change to:");
                                            position_changeColumn=KEYBOARD.nextInt();
    
                                            if (seats[position_changeRow-1][position_changeColumn-1]=='O') {
                                                System.out.println("This seat is already occupied, try selecting an available one.");
                                            }
                                        }
                                        
                                    } while (seats[positionRow-1][positionColumn-1]=='A' || seats[position_changeRow-1][position_changeColumn-1]=='O');
                                    seats[positionRow-1][positionColumn-1]='A';
                                    seats[position_changeRow-1][position_changeColumn-1]='O';
                                }
                            }
                            
                        //Showing the available seats.
                            System.out.printf("  Currently there are %d available seats.\n\n",available_seats);
                        }
                            
                            break;
                        case 3:
                        //Showing the seat status.
                            for (int i = 0; i<(seats[0].length); i++) {
                                System.out.printf("        %d",i+1);
                            }
    
                            for (int i = 0; i < seats.length; i++) {
                                System.out.printf("\nRow %d:",i+1);
                                for (int j = 0; j < seats[0].length; j++) {
                                    System.out.print("  " + seats[i][j] + "      ");
                                }
                            }
                            System.out.println("\n");
                            break;
                        case 4:
                        //Ending the program.
                            System.out.println("End of the program. Thank you for using our services");
                            break;
                        default:
                            System.out.println("ERROR! Invalid option entered. Try again.");
                            break;
                    }
                }

            } while (option!=4);
            

        } while (option!=4);

    }
}
