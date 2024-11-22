package Práctica.ProgrammingLabs.Ignacio;

import java.util.Scanner;

public class assignment6 {
    // Defining the global variables.

    private static final Scanner KEYBOARD = new Scanner(System.in);
    static boolean isValid;
    static final int ROWS = 6, COLUMNS = 5;
    static int option, optionPurchase, availableSeats = ROWS * COLUMNS, occupiedSeats = 0, cont = 0;
    static int numTickets = 0, numTicketsminor, positionRow, positionColumn, seatsToChange, positionChangeRow = 0, positionChangeColumn = 0;
    static double price = 0, result;
    static final double GENERALDISCOUNT = 0.8, DISCOUNTMINORS = 0.9;
    static char seats[][] = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        

        System.out.println("Welcome to the train ticket management software");
        System.out.println("The current seat status is as follows:");

        // At the start, all the seats are available
        seatsAtStart(seats, ROWS, COLUMNS);

        // Showing the current seat situation.
        showStatus(seats);

        // Asking for the price of the ticket.
        getTicketsPrice();

        // Showing the available seats.
        System.out.printf("\nThere are %d available seats\n", calculateAvailableSeats(availableSeats, occupiedSeats));

            // Showing the menu of options.
            do {
                
                option = mainMenu();

            } while (option != 4);

    }
    static void seatsAtStart(char[][] seats, int ROWS , int COLUMNS) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seats[i][j] = 'A';
            }
        }
    }
    // Showing the main menu, in which the other methods are called and used.
    static int mainMenu() {
        System.out.println("Choose one of the following options:");
        System.out.println("1. Purchase tickets");
        System.out.println("2. Change tickets");
        System.out.println("3. Show seat status");
        System.out.println("4. Exit the program");

        availableSeats = calculateAvailableSeats(availableSeats, occupiedSeats);

        // Asking the option
        System.out.println("Enter the number of the option you wish to choose:");
        option = KEYBOARD.nextInt();

        if (option == 1 && calculateAvailableSeats(availableSeats, occupiedSeats) == 0) {

            System.out.println("Purchase of tickets is not possible because there are no more available seats.");

        }
        
        else {
            switch (option) {
                case 1:
                    // Asking the total number of tickets and number of tickets for minors.

                    numTickets = readInRange(1, availableSeats, "Enter the number of tickets you wish to purchase. You must buy at least 1.");
                    numTicketsminor = readInRange(0, numTickets - 1, "\nIntroduce how many tickets are for minors. Remember a minimum of 1 adult is required.");

                    // Showing the menu for selecting the seats:
                    do {
                        System.out.println("\nChoose one of the following options for purchasing tickets:");
                        System.out.println("1. Manual seat selection.");
                        System.out.println("2. Automatic seat selection.");
                        System.out.println("3. Selection of adjacent seats.");
                        System.out.println("4. Exit the purchase menu.");

                        System.out.println("Enter the number of the option you want to choose");
                        optionPurchase = KEYBOARD.nextInt();

                        switch (optionPurchase) {
                            case 1:
                                // Showing again the seats available.
                                showStatus(seats);

                                // Manual selection of the seats.
                                occupiedSeats = 0;
                                isValid = false;
                                for (int i = 0; i < numTickets; i++) {
                                    do{
                                        positionRow = readInRange(1, ROWS, "\nEnter the row for seat of ticket " + (i+1) + ":" );
                                        positionColumn = readInRange(1, COLUMNS, "\nEnter the column for seat of ticket " + (i+1) + ":");

                                        if (seats[positionRow - 1][positionColumn - 1] == 'O') {
                                            System.out.println("Sorry, but the seat selected is already occupied. Try again.");
                                        }

                                    }while(seats[positionRow - 1][positionColumn - 1] == 'O');
                                    seats[positionRow - 1][positionColumn - 1] = 'O';
                                    occupiedSeats++;
                                    System.out.println("Seat has been selected.");
                                }

                                // Showing the invoice:
                                result = price * numTickets;

                                if (numTicketsminor > 0) {
                                    result = price * numTicketsminor * DISCOUNTMINORS + price * (numTickets - numTicketsminor);
                                }

                                if (numTickets > 12) {
                                    result = result * GENERALDISCOUNT;
                                }
                                System.out.println("Invoice:");
                                System.out.printf("  Number of tickets: %d\n", numTickets);
                                System.out.printf("  Price per ticket: %.2f\n", price);

                            
                                // Calculating the different prices with each discount.
                                System.out.println("Invoice:");
                                System.out.println("  Number of tickets: " + numTickets);
                                System.out.println("  Price per ticket: " + price);
                                System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT*100 + "%" : "None"));
                                System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS*100 + "%" : "None"));
                                System.out.printf("  Total price: %.2f", result);

                                System.out.printf("\nThere are %d available seats\n", calculateAvailableSeats(availableSeats, occupiedSeats));
                                optionPurchase = 4;

                                break;
                            case 2:

                                // Automatic selection of each seat.
                                occupiedSeats = 0;
                                for (int i = 0; i < numTickets; i++) {
                                    do {
                                        positionRow = (int) (Math.random() * ROWS);
                                        positionColumn = (int) (Math.random() * COLUMNS);

                                    } while (seats[positionRow][positionColumn] == 'O');
                                    seats[positionRow][positionColumn] = 'O';
                                    occupiedSeats++;

                                    System.out.printf("The seat in row %d and column %d has been selected.\n", positionRow + 1, positionColumn + 1);
                                }

                                // Showing the invoice:
                                result = price * numTickets;

                                if (numTicketsminor > 0) {
                                    result = price * numTicketsminor * DISCOUNTMINORS + price * (numTickets - numTicketsminor);
                                }

                                if (numTickets > 12) {
                                    result = result * GENERALDISCOUNT;
                                }
                                System.out.println("Invoice:");
                                System.out.printf("  Number of tickets: %d\n", numTickets);
                                System.out.printf("  Price per ticket: %.2f\n", price);

                                
                                // Calculating the different prices with each discount.
                                System.out.println("Invoice:");
                                System.out.println("  Number of tickets: " + numTickets);
                                System.out.println("  Price per ticket: " + price);
                                System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT*100 + "%" : "None"));
                                System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS*100 + "%" : "None"));
                                System.out.printf("  Total price: %.2f", result);

                                System.out.printf("\nThere are %d available seats\n", calculateAvailableSeats(availableSeats, occupiedSeats));
                                optionPurchase = 4;

                                break;
                            case 3:
                                occupiedSeats = 0;
                                cont = 0;
                                // Selection of adjacent seats
                                if (numTickets > seats[0].length) {
                                    System.out.println(
                                            "It is not possible to assign adjacent seats, because the number of tickets exceed the number of columns. Try again");
                                } else {
                                    // Detecting the available seats in a row.
                                    for (int i = 0; i < seats.length; i++) {
                                        for (int j = 0; j < seats[0].length; j++) {
                                            if (seats[i][j] == 'A') {
                                                cont++;
                                            }
                                            if (numTickets == cont) {
                                                for (int k = 0; k < numTickets; k++) {
                                                    System.out.printf(
                                                            "The seat in row %d and column %d has been selected\n",
                                                            i + 1, k + 1);
                                                    seats[i][k] = 'O';
                                                    occupiedSeats++;
                                                }
                                                j = seats[0].length; // Breaking the loop that iterates rows.
                                                i = seats.length; // Breaking the loop that iterates columns.
                                            }
                                        } // End of loop for columns.
                                    } // End of loop for rows.

                                    // Showing the invoice:
                                result = price * numTickets;

                                if (numTicketsminor > 0) {
                                    result = price * numTicketsminor * DISCOUNTMINORS + price * (numTickets - numTicketsminor);
                                }

                                if (numTickets > 12) {
                                    result = result * GENERALDISCOUNT;
                                }
                                System.out.println("Invoice:");
                                System.out.printf("  Number of tickets: %d\n", numTickets);
                                System.out.printf("  Price per ticket: %.2f\n", price);

                                // Calculating the different prices with each discount.
                                System.out.println("Invoice:");
                                System.out.println("  Number of tickets: " + numTickets);
                                System.out.println("  Price per ticket: " + price);
                                System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT*100 + "%" : "None"));
                                System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS*100 + "%" : "None"));
                                System.out.printf("  Total price: %.2f", result);
                                }

                                optionPurchase = 4;

                                break;
                            case 4:
                                occupiedSeats = 0;
                                // Exiting the purchase menu.
                                System.out.println("Exiting the purchase menu.");
                                break;
                            default:
                                System.out.println("ERROR! Invalid option entered. Try again.");
                                break;
                        }
                    } while (optionPurchase != 4);
                    break;
                case 2:
                    //Changing tickets.
                    if (numTickets <= 0) { // Checking the number of tickets is greater than 0.
                        System.out.println("\nERROR! It is necessary to purchase tickets. Try again\n");
                    } else {
                        // Showing the seat status
                        showStatus(seats);

                        // Asking for the number of tickets to be changed.
                        availableSeats = calculateAvailableSeats(availableSeats, occupiedSeats);
                        seatsToChange = numTicketsChange(availableSeats, occupiedSeats);

                        // Executing the method for changing the tickets:
                        changeSeats(seatsToChange);
                        
                        // Showing the available seats.
                        System.out.printf("\nThere are %d available seats\n", calculateAvailableSeats(availableSeats, occupiedSeats));
                    }

                    break;
                case 3:
                    // Showing the seat status.
                    showStatus(seats);
                    break;
                case 4:
                    // Ending the program.
                    System.out.println("End of the program. Thank you for using our services.");
                    break;
                default:
                    System.out.println("ERROR! Invalid option entered. Try again.");
                    break;
            }
        }

        return option;
    }
    // Method used for calculating the available seats.
    static int calculateAvailableSeats(int availableSeats, int occupiedSeats) {
        availableSeats = (availableSeats - occupiedSeats);
        return availableSeats;
    }

    // This method takes 2 values and returns other one within the range. Also, a prompt is introduced.
    static int readInRange(int lowerBound, int UpperBound, String request) {
        int n;
        isValid = false;
        do {
            System.out.printf(request + "[%d, %d]: ",lowerBound,UpperBound);

            n = KEYBOARD.nextInt();
            // Ensuring the value selected is out of bonds.
            if (n < lowerBound || n > UpperBound) {
                System.out.println("ERROR!, value selected is out of bonds, try again.");
                isValid = true;
            }
            else {
                isValid = false;
            }

        }while(isValid);

        return n;
    }
    // Method used for showing the current situaton of seats in the train.
    static void showStatus(char[][] seats) {

            for (int i = 0; i < (seats[0].length); i++) {
                System.out.printf("        %d", i + 1);
            }

            for (int i = 0; i < seats.length; i++) {
                System.out.printf("\nRow %d:", i + 1);
                for (int j = 0; j < seats[0].length; j++) {
                    System.out.print("  " + seats[i][j] + "      ");
                }
            }
            System.out.println("\n");
    }

    static double getTicketsPrice() {
        do {
            System.out.println("\nEnter the price of the ticket:");
            System.out.println("Remember that the ticket price must be greater than 0.");
            price = KEYBOARD.nextInt();

            if (price <= 0) {
                System.out.println("Invalid price entered. Please try again.");
            }
        } while (price <= 0);

        return price;
    }

    // Declaration of the method for changing tickets
    static int numTicketsChange(int availableSeats,int occupiedSeats) {

        int lowerBound = 1;
        int UpperBound = Math.min(availableSeats,occupiedSeats); // Ensuring the selected tickets can be changed

        int value = readInRange(lowerBound, UpperBound, """
        Enter number of tickets to change. Please note that to change tickets
        you must select a number of tickets that have been purchased in advance and for which there are enough seats available.""");
        
        return value;

    }

    static void changeSeats(int seatsToChange) {
        for (int i = 0; i < seatsToChange; i++) {
            int lowerBound = 1;
           
            // Ensuring the seats the user wants to change is occupied.
            do{

                positionRow = readInRange(lowerBound, ROWS, "Select the row of the seat you want to change");
                positionColumn = readInRange(lowerBound, COLUMNS, "Select the column of the seat you want to change");

                if (seats[positionColumn-1][positionRow-1] == 'A') {
                    System.out.println("ERROR, the seat selected is available. Try again.");
                }

            }while(seats[positionColumn-1][positionRow-1] == 'A');

            // Ensuring the seats the users wants to change to is available.
            do{

                positionChangeRow = readInRange(lowerBound, ROWS, "Select the row of the seat you want to change to");
                positionChangeColumn = readInRange(lowerBound, COLUMNS, "Select the column of the seat you want to change to");

                if (seats[positionChangeColumn-1][positionChangeRow-1] == 'O') {
                    System.out.println("ERROR, the seat selected is available. Try again.");
                }

            }while(seats[positionChangeRow-1][positionChangeColumn-1] == 'O');
            
            // Changing the seats.
            seats[positionRow-1][positionColumn-1] = 'A';
            seats[positionChangeRow-1][positionChangeColumn-1] = 'O';
        }
    }
}
