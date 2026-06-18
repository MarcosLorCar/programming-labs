/*
MARCOS LORO CARRASCO
IGNACIO ROMERO TORRES
RAFAEL CAMILO RUBIO QUINTERO
GONZALO SOBRINO DE LA FLOR
*/
import java.util.Scanner;

public class Lab5 {
    private static final Scanner sc = new Scanner(System.in);
    private static final float MINORS_DISCOUNT = 0.2F;
    private static final float BULK_DISCOUNT = 0.1F;
    private static final int BULK_AMOUNT = 12;

    public static void main(String[] args) {
        boolean exit = false;
        char[][] seats = (args.length != 2) ? new char[8][6] : new char[Integer.parseInt(args[0])][Integer.parseInt(args[1])];
        int availableSeats = seats.length * seats[0].length;
        float ticketPrice;
        int selectedOption;

        // Fill the seats matrix
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                seats[row][col] = 'A';
            }
        }

        System.out.println("Welcome to the train ticket management software");
        System.out.println("The current seat status is as follows:");

        // Print the current seat state formatted
      //System.out.print("Row 0:  "); For size reference
        System.out.print("        ");
        for (int i = 0; i < seats[0].length; i++) {
            String spacing = (i + 1) < 10 ? " " : "";
            System.out.printf("%d %s", i + 1, spacing); // The line with the columns numbers
        }
        System.out.println();
        for (int row = 0; row < seats.length; row++) {
            String spacing = (row + 1) < 10 ? " " : "";
            System.out.printf("Row %d: %s", row + 1, spacing); // Number of the row
            for (int col = 0; col < seats[row].length; col++) {
                System.out.printf("%s  ", seats[row][col]); // Contents of the row column per column
            }
            System.out.println();
        }

        // Set ticket price
        do {
            System.out.print("Enter ticket price: \n" +
                    "-> ");
            ticketPrice = sc.nextFloat();
            if (ticketPrice <= 0) {
                System.out.println("Invalid ticket price. \n");
            }
        } while (ticketPrice <= 0);

        while (!exit) {
            System.out.println();
            System.out.printf("\tThere are currently %d available seats.\n", availableSeats);
            System.out.println();

            // Main menu
            System.out.print("""
                    \tChoose one of the following options:
                    1. Purchase tickets
                    2. Change tickets
                    3. Show seat status
                    4. Exit the program
                    -> \s""");
            selectedOption = sc.nextInt();

            switch (selectedOption) {
                case 1:
                    // Purchase tickets

                    // Get total amount of tickets
                    int ticketsToBuy;
                    System.out.printf("""
                            Enter the number of tickets you want to buy.
                            You must buy at least one, and there are %d available seats left.
                            -> \s""", availableSeats);
                    do {
                        ticketsToBuy = sc.nextInt();
                        if (ticketsToBuy <= 0) {
                            System.out.print("Invalid amount, Try again. \n" +
                                    "-> ");
                        } else if (ticketsToBuy > availableSeats) {
                            System.out.print("Not enough available seats, Try again. \n" +
                                    "-> ");
                        }
                    } while (ticketsToBuy <= 0 || ticketsToBuy > availableSeats);

                    availableSeats -= ticketsToBuy;

                    // Get how many for minors
                    int minorCount;
                    System.out.print("""
                            Enter the number of tickets which are for minors.
                            Remember that at least one adult must accompany underage travellers.
                            -> \s""");
                    do {
                        minorCount = sc.nextInt();
                        if (minorCount < 0 || minorCount >= ticketsToBuy) {
                            System.out.print("Invalid amount, Try again. \n" +
                                    "-> ");
                        }
                    } while (minorCount < 0 || minorCount >= ticketsToBuy);

                    // Purchase menu
                    int purchaseOption;
                    boolean exitPurchaseMenu;
                    do {
                        exitPurchaseMenu = true;
                        System.out.print("""
                            Choose a ticket purchase option:
                            1. Manual seat selection.
                            2. Automatic seat selection.
                            3. Automatic selection of adjacent seats.
                            4. Exit purchase menu
                            Enter the number of the option you wish to choose:
                            -> \s""");
                        purchaseOption = sc.nextInt();

                        while (purchaseOption <= 0 || purchaseOption >= 5) {
                            System.out.print("Invalid option, try again.\n" +
                                    "-> ");
                            purchaseOption = sc.nextInt();
                        }

                        switch (purchaseOption) {
                            case 1:
                                // Manual seat selection.
                                boolean isValidSeat;
                                int row, col;
                                for (int i = 0; i < ticketsToBuy; i++) {
                                    int currSeat = i + 1;
                                    isValidSeat = false;
                                    do {
                                        System.out.printf("Enter row for seat of ticket number %d\n" +
                                                "-> ", currSeat);
                                        row = sc.nextInt();
                                        System.out.printf("Enter column for seat of ticket number %d\n" +
                                                "-> ", currSeat);
                                        col = sc.nextInt();

                                        if (row <= 0 || row > seats.length || col <= 0 || col > seats[0].length) {
                                            System.out.print("Invalid seat, try again. \n" +
                                                    "-> ");
                                        } else if (seats[row - 1][col - 1] == 'O')  {
                                            System.out.print("Sorry, that seat is currently occupied. Please select another seat.\n" +
                                                    "-> ");
                                        } else {
                                            isValidSeat = true;
                                        }
                                    } while (!isValidSeat);

                                    seats[row - 1][col - 1] = 'O';
                                    System.out.println("Seat has been selected.");
                                }

                                break;
                            case 2:
                                // Automatic seat selection.
                                int seatsToFind = ticketsToBuy;
                                for (int i = 0; i < seats.length; i++) {
                                    for (int j = 0; j < seats[i].length; j++) {
                                        if (seatsToFind > 0 && seats[i][j] == 'A') {
                                            seatsToFind--;
                                            seats[i][j] = 'O';
                                            System.out.printf("The seat in row %d and column %d has been selected.\n", i + 1, j + 1);
                                        }
                                    }
                                }
                                break;
                            case 3:
                                // Automatic selection of adjacent seats.
                                boolean existsAdjacentSeats = false;
                                int adjacentSeatsFound = 0;
                                int adjacentSeatsBegin = 0;
                                for (int i = 0; i < seats.length; i++) {
                                    for (int j = 0; j < seats[i].length; j++) {
                                        if (!existsAdjacentSeats && seats[i][j] == 'A') {
                                            adjacentSeatsFound++;
                                            if (adjacentSeatsFound == ticketsToBuy) {
                                                for (int h = adjacentSeatsBegin; h <= ticketsToBuy; h++) {
                                                    System.out.printf("The seat in row %d and column %d has been selected.\n", i + 1, h + 1);
                                                }
                                                existsAdjacentSeats = true;
                                            }
                                        } else if (!existsAdjacentSeats && seats[i][j] == 'O') {
                                            adjacentSeatsFound = 0;
                                            adjacentSeatsBegin = j + 1;
                                        }
                                    }
                                    if (!existsAdjacentSeats) {
                                        adjacentSeatsFound = 0;
                                        adjacentSeatsBegin = 0;
                                    }
                                }
                                if (!existsAdjacentSeats) {
                                    System.out.println("Not enough adjacent seats.");
                                    exitPurchaseMenu = false;
                                }
                                break;
                            case 4:
                                // Exit purchase menu
                                System.out.println("Purchase cancelled.");
                                availableSeats += ticketsToBuy;
                                break;
                        }
                    } while (!exitPurchaseMenu);

                    if (purchaseOption != 4) {
                        System.out.println("Invoice:");
                        System.out.printf("-Number of tickets: %d\n", ticketsToBuy);
                        System.out.printf("-Price per ticket: %.2f\n", ticketPrice);
                        System.out.printf("-Discount applied for purchase in bulk: %s\n", ticketsToBuy >= BULK_AMOUNT ? "10%" : "None");
                        System.out.printf("-Discount applied for tickets for minors: %s\n", minorCount > 0 ? "20%" : "None");
                        float totalPrice = ticketPrice * (ticketsToBuy - minorCount) + ticketPrice * minorCount * (1 - MINORS_DISCOUNT);
                        totalPrice *= ticketsToBuy < BULK_AMOUNT ? 1 : (1 - BULK_DISCOUNT);
                        System.out.printf("-Total price: %.2f\n", totalPrice);
                    }

                    break;
                case 2:
                    // Change seats

                    // Show state
                    System.out.print("        ");
                    for (int i = 0; i < seats[0].length; i++) {
                        String spacing = (i + 1) < 10 ? " " : "";
                        System.out.printf("%d %s", i + 1, spacing); // The line with the columns numbers
                    }
                    System.out.println();
                    for (int row = 0; row < seats.length; row++) {
                        String spacing = (row + 1) < 10 ? " " : "";
                        System.out.printf("Row %d: %s", row + 1, spacing); // Number of the row
                        for (int col = 0; col < seats[row].length; col++) {
                            System.out.printf("%s  ", seats[row][col]); // Contents of the row column per column
                        }
                        System.out.println();
                    }

                    // Ask for the number of tickets to change
                    int seatsToChange;
                    int occupiedSeats = seats.length * seats[0].length - availableSeats;
                    System.out.print("Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.\n" +
                            "-> ");
                    do {
                        seatsToChange = sc.nextInt();
                        if (seatsToChange <= 0) {
                            System.out.println("Invalid number of seats, try again.\n" +
                                    "-> ");
                        } else if (seatsToChange > occupiedSeats) {
                            System.out.println("Not enough occupied seats, try again.\n" +
                                    "-> ");
                        }
                    } while (seatsToChange <= 0 || seatsToChange > occupiedSeats);

                    // Changes
                    for (int i = 0; i < seatsToChange; i++) {
                        // Ask for the seat to change from
                        int fromSeatRow, fromSeatCol, toSeatRow, toSeatCol;
                        boolean valid;
                        do {
                            valid = true;
                            System.out.printf("(Change #%d) Select the row of the seat you want to change\n" +
                                    "-> ", i + 1);
                            fromSeatRow = sc.nextInt();
                            System.out.printf("(Change #%d) Select the column of the seat you want to change\n" +
                                    "-> ", i + 1);
                            fromSeatCol = sc.nextInt();
                            if (fromSeatRow <= 0 || fromSeatRow > seats.length || fromSeatCol <= 0 || fromSeatCol > seats[0].length || seats[fromSeatRow - 1][fromSeatCol - 1] == 'A') {
                                System.out.print("Invalid seat, try again.\n" +
                                        "-> ");
                                valid = false;
                            }
                        } while (!valid);

                        // Ask for the seat to change to
                        do {
                            valid = true;
                            System.out.printf("(Change #%d) Select the row of the seat you want to change to\n" +
                                    "-> ", i + 1);
                            toSeatRow = sc.nextInt();
                            System.out.printf("(Change #%d) Select the column of the seat you want to change to\n" +
                                    "-> ", i + 1);
                            toSeatCol = sc.nextInt();
                            if (toSeatRow <= 0 || toSeatRow > seats.length || toSeatCol <= 0 || toSeatCol > seats[0].length || seats[toSeatRow - 1][toSeatCol - 1] == 'O') {
                                System.out.print("Invalid seat, try again.\n" +
                                        "-> ");
                                valid = false;
                            }
                        } while (!valid);

                        // Perform the change
                        seats[fromSeatRow - 1][fromSeatCol - 1] = 'A';
                        seats[toSeatRow - 1][toSeatCol - 1] = 'O';
                        System.out.printf("Ticket #%d changed.\n", i + 1);
                    }

                    break;
                case 3:
                    // Show seat status
                    System.out.print("        ");
                    for (int col = 0; col < seats[0].length; col++) {
                        String spacing = (col + 1) < 10 ? " " : "";
                        System.out.printf("%d %s", col + 1, spacing); // The line with the columns numbers
                    }
                    System.out.println();
                    for (int row = 0; row < seats.length; row++) {
                        String spacing = (row + 1) < 10 ? " " : "";
                        System.out.printf("Row %d: %s", row + 1, spacing); // Number of the row
                        for (int col = 0; col < seats[row].length; col++) {
                            System.out.printf("%s  ", seats[row][col]); // Contents of the row column per column
                        }
                        System.out.println();
                    }
                    break;
                case 4:
                    // Exit the program
                    exit = true;
                    System.out.println("Exiting the program.");
                    System.out.println("Thank you for using our software");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
