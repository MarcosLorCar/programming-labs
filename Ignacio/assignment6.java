package Práctica.ProgrammingLabs.Ignacio;

import java.util.Scanner;

public class assignment6 {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) {
        // Defining the variables
        final int ROWS = 6, COLUMNS = 5;
        int option=0, optionPurchase, availableSeats = ROWS * COLUMNS, occupiedSeats = 0, cont = 0;
        int numTickets = 0, numTicketsminor, positionRow, positionColumn, seatsToChange, positionChangeRow = 0,
                positionChangeColumn = 0;
        double price = 0, result;
        final double GENERALDISCOUNT = 0.8, DISCOUNTMINORS = 0.9;
        char seats[][] = new char[ROWS][COLUMNS];
        boolean isValid;

        // At the start, all the seats are available
        seatsAtStart(seats, ROWS, COLUMNS);

        // Showing the current seat situation.

        do {
            System.out.println("Welcome to the train ticket management software");
            System.out.println("The current seat status is as follows:");

            for (int i = 0; i < (seats[0].length); i++) {
                System.out.printf("        %d", i + 1);
            }

            for (int i = 0; i < seats.length; i++) {
                System.out.printf("\nRow %d:", i + 1);
                for (int j = 0; j < seats[0].length; j++) {
                    System.out.print("  " + seats[i][j] + "      ");
                }
            }
            // Asking for the price of the ticket.
            do {
                System.out.println("\nEnter the price of the ticket:");
                System.out.println("Remember that the ticket price must be greater than 0.");
                price = KEYBOARD.nextInt();

                if (price <= 0) {
                    System.out.println("Invalid price entered. Please try again.");
                }
            } while (price <= 0);

            // Showing the available seats.
            showAvailableSeats(availableSeats, occupiedSeats);

            // Showing the menu of options.
            do {
                
                option = mainMenu(option);

                if (option == 1 && availableSeats == 0) {
                    System.out.println("Purchase of tickets is not possible because there are no more available seats.");
                } else {
                    switch (option) {
                        case 1:
                            // Asking the total number of tickets and number of tickets for minors.
                            do {
                                System.out.println("Enter the number of tickets you wish to purchase. You must buy at least 1.");
                                System.out.printf("There are %d available seats.\n", availableSeats);
                                numTickets = KEYBOARD.nextInt();
                                System.out.println(
                                        "\nIntroduce how many tickets are for minors. Remember a minimum of 1 adult is required.");
                                numTicketsminor = KEYBOARD.nextInt();

                                if (numTickets <= 0) {
                                    System.out.println("ERROR! Invalid number of tickets selected. Try again.");
                                }

                                if (numTicketsminor < 0) {
                                    System.out.println(
                                            "ERROR! Invalid number of tickets for minors selected. Try again ");
                                }

                                if (numTicketsminor >= numTickets) {
                                    System.out.println(
                                            "ERROR! Minors should be accompanied by an adult and the number of tickets for minors exceed the total number of tickets. Try again ");
                                }

                                if (numTickets > availableSeats) {
                                    System.out.println("Sorry, but there are not enough seats available. Try again");
                                }
                            } while (numTicketsminor >= numTickets || numTickets > availableSeats || numTickets <= 0
                                    || numTicketsminor < 0);

                            // Showing the menu for selecting the seats:
                            do {
                                System.out.println("Choose one of the following options for purchasing tickets:");
                                System.out.println("1. Manual seat selection.");
                                System.out.println("2. Automatic seat selection.");
                                System.out.println("3. Selection of adjacent seats.");
                                System.out.println("4. Exit the purchase menu.");

                                System.out.println("Enter the number of the option you want to choose");
                                optionPurchase = KEYBOARD.nextInt();

                                switch (optionPurchase) {
                                    case 1:
                                        // Showing again the seats available.
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

                                        // Manual selection of the seats.
                                        occupiedSeats = 0;
                                        isValid = false;
                                        for (int i = 1; i <= numTickets; i++) {
                                            do {
                                                System.out.printf("\nEnter the row for seat of ticket number: %d\n", i);
                                                positionRow = KEYBOARD.nextInt();

                                                System.out.printf("\nEnter the column for seat of ticket number: %d\n",
                                                        i);
                                                positionColumn = KEYBOARD.nextInt();

                                                if ((positionRow) > ROWS || (positionRow) <= 0 || (positionColumn) > ROWS || (positionColumn) <= 0) {
                                                    System.out.println(
                                                            "Sorry. The position selected is out of range. Try again.");
                                                }
                                                else if (seats[positionRow - 1][positionColumn - 1] == 'O') {
                                                    System.out.println("Sorry. This seat is already occupied. Select again");
                                                }
                                                else {
                                                    isValid = true;
                                                }

                                            } while (!isValid || (positionRow - 1) > ROWS || (positionRow - 1) < 0
                                                    || (positionColumn - 1) > ROWS || (positionColumn - 1) < 0);
                                            seats[positionRow - 1][positionColumn - 1] = 'O';
                                            occupiedSeats++;
                                            System.out.println("Seat has been selected.");
                                        }

                                        // Showing the invoice:
                                        result = price * numTickets;

                                        if (numTicketsminor > 0) {
                                            result = price * numTicketsminor * DISCOUNTMINORS
                                                    + price * (numTickets - numTicketsminor);
                                        }

                                        if (numTickets > 12) {
                                            result = result * GENERALDISCOUNT;
                                        }
                                        System.out.println("Invoice:");
                                        System.out.printf("  Number of tickets: %d\n", numTickets);
                                        System.out.printf("  Price per ticket: %.2f\n", price);

                                        result = price * numTickets;
                                        // Calculating the different prices with each discount.
                                        System.out.println("Invoice:");
                                        System.out.println("  Number of tickets: " + numTickets);
                                        System.out.println("  Price per ticket: " + price);
                                        System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT + "%" : "None"));
                                        System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS + "%" : "None"));
                                        System.out.printf("  Total ptice: %.2f", result);

                                        showAvailableSeats(availableSeats, occupiedSeats);

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
                                            System.out.printf("The seat in row %d and column %d has been selected.\n",
                                                    positionRow + 1, positionColumn + 1);
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

                                        result = price * numTickets;
                                        // Calculating the different prices with each discount.
                                        System.out.println("Invoice:");
                                        System.out.println("  Number of tickets: " + numTickets);
                                        System.out.println("  Price per ticket: " + price);
                                        System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT + "%" : "None"));
                                        System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS + "%" : "None"));
                                        System.out.printf("  Total ptice: %.2f", result);

                                        availableSeats = (availableSeats - occupiedSeats);
                                        System.out.printf("  Currently there are %d available seats.\n\n", availableSeats);
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
                                            result = price * numTicketsminor * DISCOUNTMINORS
                                                    + price * (numTickets - numTicketsminor);
                                        }

                                        if (numTickets > 12) {
                                            result = result * GENERALDISCOUNT;
                                        }
                                        System.out.println("Invoice:");
                                        System.out.printf("  Number of tickets: %d\n", numTickets);
                                        System.out.printf("  Price per ticket: %.2f\n", price);

                                        result = price * numTickets;
                                        // Calculating the different prices with each discount.
                                        System.out.println("Invoice:");
                                        System.out.println("  Number of tickets: " + numTickets);
                                        System.out.println("  Price per ticket: " + price);
                                        System.out.println("  Discount applied for purchase in bulk: " + (numTickets > 12 ? GENERALDISCOUNT + "%" : "None"));
                                        System.out.println("  Discount applied for tickets for minors: " + (numTicketsminor > 0 ? DISCOUNTMINORS + "%" : "None"));
                                        System.out.printf("  Total ptice: %.2f", result);
                                        }

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
                                // Asking for the number of tickets to be changed.
                                System.out.println(
                                        "Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.");
                                seatsToChange = KEYBOARD.nextInt();

                                if (seatsToChange > occupiedSeats) {
                                    System.out.println(
                                            "ERROR! Seats selected to change are greater than the number of seats occupied.");
                                } else if (seatsToChange > availableSeats) {
                                    System.out.println("ERROR! There are not enough seats to change");
                                } else {
                                    System.out.printf("%d seats will be changed.\n", seatsToChange);

                                    // Asking for the position of the tickets which are wanted to change.
                                    for (int i = 0; i < numTickets; i++) {
                                        do {
                                            System.out.println("Select the row of the seat you want to change:");
                                            positionRow = KEYBOARD.nextInt();
                                            System.out.println("Select the column of the seat you want to change:");
                                            positionColumn = KEYBOARD.nextInt();

                                            if (seats[positionRow - 1][positionColumn - 1] == 'A') {
                                                System.out.println(
                                                        "ERROR! This seat is available. Try selecting an occupied seat.");
                                            } else {
                                                System.out.println("Select the row of the seat you want to change to:");
                                                positionChangeRow = KEYBOARD.nextInt();
                                                System.out.println( "Select the column of the seat you want to change to:");
                                                positionChangeColumn = KEYBOARD.nextInt();

                                                if (seats[positionChangeRow - 1][positionChangeColumn - 1] == 'O') {
                                                    System.out.println("This seat is already occupied, try selecting an available one.");
                                                }
                                            }

                                        } while (seats[positionRow - 1][positionColumn - 1] == 'A'
                                                || seats[positionChangeRow - 1][positionChangeColumn - 1] == 'O');
                                        seats[positionRow - 1][positionColumn - 1] = 'A';
                                        seats[positionChangeRow - 1][positionChangeColumn - 1] = 'O';
                                    }
                                }

                                // Showing the available seats.
                                System.out.printf("  Currently there are %d available seats.\n\n", availableSeats);
                            }

                            break;
                        case 3:
                            // Showing the seat status.
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
                            break;
                        case 4:
                            // Ending the program.
                            System.out.println("End of the program. Thank you for using our services");
                            break;
                        default:
                            System.out.println("ERROR! Invalid option entered. Try again.");
                            break;
                    }
                }

            } while (option != 4);

        } while (option != 4);

    }
    static void seatsAtStart(char[][] seats, int ROWS , int COLUMNS) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seats[i][j] = 'A';
            }
        }
    }
    static int mainMenu(int option) {
        System.out.println("Choose one of the following options:");
        System.out.println("1. Purchase tickets");
        System.out.println("2. Change tickets");
        System.out.println("3. Show seat status");
        System.out.println("4. Exit the program");
        // Asking the option
        System.out.println("Enter the number of the option you wish to choose:");
        option = KEYBOARD.nextInt();

        return option;
    }

    static int showAvailableSeats(int availableSeats, int occupiedSeats) {

        availableSeats = (availableSeats - occupiedSeats);
        System.out.printf("  \nCurrently there are %d available seats.\n\n", availableSeats);
        return availableSeats;
    }

    static int readInRange(int lowerBound, int UpperBound) {
        int min = Math.min(lowerBound,UpperBound);
        int max = Math.max(lowerBound, UpperBound);

        do {
            System.out.println("Enter the number of tickets you wish to purchase. You must buy at least 1.");
       
        
        return min;

    }while(true);
    }
}
