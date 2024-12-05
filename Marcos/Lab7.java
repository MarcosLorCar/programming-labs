import java.util.Scanner;

public class Lab7 {

    static final int ROWS = 5;
	static final int COLUMNS = 4;
	static final char AVAILABLE = 'A';
	static final char OCCUPIED = 'O';
	static final double DISCOUNT_MINORS = 20; // or multiply by 0,8
	static final double DISCOUNT_MAXTICKETS = 10; // or multiply by 0,9
	static final int MAX_TICKETS_NUMBER = 12;
	static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        double price;
        char[][] seats = new char[ROWS][COLUMNS];

        // Inicio del programa
        System.out.println("Welcome to the train ticket management software");

        // The program starts by displaying the current seat status.
        // To do this, first of all, all the seats on the train are initialised as available.
        // The matrix is traversed and each element is assigned the value AVAILABLE.
        initializeSeats(seats);

        // Secondly, the current seat status is displayed.
        showStatus(seats);

        // The user is prompted to enter the ticket price. Error checking is implemented
        price = getPrice();

        mainMenu(price, seats);
    }

    private static double getPrice() {
        double price;
        do{
            System.out.println("Enter the price for the train ticket.");
            System.out.println("Please note that the ticket price must be greater than 0.");
            price = KEYBOARD.nextDouble();
            if(price <= 0){
                System.out.println("ERROR. The price of the ticket must be greater than 0.");
            }
        }while(price <= 0);
        return price;
    } // Ask ticket price

    private static void showStatus(char[][] seats) {
        System.out.println("The current seat status is as follows:");

        // The first row with the column headers of the matrix is displayed.
        System.out.print("\t");
        for (int i = 1; i <= COLUMNS; i ++){
            System.out.print(i + "\t");
        }
        // Each row of seats is shown with its corresponding value (available or occupied).
        System.out.println();
        for(int i = 0; i < ROWS; i++){
            System.out.print("Row " + (i + 1) + ":  ");
            for(int j = 0; j < COLUMNS; j++){
                System.out.printf("%c \t", seats[i][j]);
            }
            System.out.println();
        }
    } // Show the current status of the seats in the train

    private static void initializeSeats(char[][] seats) {
        for(int i = 0;i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                seats[i][j] = AVAILABLE;
            }
        }
    } // Initialize the seats of the train

    private static int getValueInRange(int LB, int UB, String prompt) {
        int tickets;
        boolean valid;
        do{
            valid = true;
            System.out.printf(prompt + " [%d,%d]: ", LB, UB);
            tickets = KEYBOARD.nextInt();
            if(tickets < LB || tickets > UB){
                System.out.print("ERROR. ");
                valid = false;
            }
        }while(!valid);
        return tickets;
    } // Read a number within a range

    private static void mainMenu(double price, char[][] seats) {
        boolean showMenu = true;
        do {
            int option;
            // The number of available seats is shown
            System.out.println("\n Currently there are " + getAvailableCount(seats) + " available seats.");
            // Show the main menu
            System.out.println("\n Choose one of the following options:");
            System.out.println("1. Purchase tickets");
            System.out.println("2. Change tickets");
            System.out.println("3. Show seat status");
            System.out.println("4. Exit the program");
            System.out.println("Enter the number of the option you wish to choose:");
            // Read the option selected by the user
            option = KEYBOARD.nextInt();
            switch (option) {
                case 1:
                    // Purchase tickets

                    // Check if there are avabilable seats
                    if(getAvailableCount(seats) > 0){
                        purchaseTickets(price, seats);
                    }else{
                        System.out.println("Sorry, there are not enough seats available. Purchase could not be finished.");
                    }
                    break;
                // Change tickets
                case 2:
                    // Tickets can be changed when there is at least one ticket purchased and one seat available for exchange.
                    int availableSeats = getAvailableCount(seats);
                    int occupiedSeats = ROWS * COLUMNS - availableSeats;
                    if(availableSeats > 0 && occupiedSeats > 0){
                        // The current seat status is displayed first.
                        showStatus(seats);
                        // Ask amount of tickets to change
                        int changeCount = askForChangeCount(availableSeats, occupiedSeats);
                        System.out.println(changeCount + " seats wil be changed.");
                        changeTickets(changeCount, seats);
                    }else{
                        System.out.println("It is not possible to change seats because there are no seats available or there are no occupied seats.");
                    }
                    break;
                // Show seat status
                case 3:
                    showStatus(seats);
                    break;
                case 4:
                    System.out.println("Exiting the program. \n Thank you for using our software");
                    showMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (showMenu);
    } // Execute the main menu

    private static void purchaseTickets(double price, char[][] seats) {
        int ticketsMinors;
        int tickets;
        int purchaseOption;
        // ask and read number of tickets
        tickets = getValueInRange(1, getAvailableCount(seats), "Enter the number of tickets you want to buy. You must buy at least one, and there are " + getAvailableCount(seats) + " available seats left.");

        // ask number of tickets for minors
        ticketsMinors = getValueInRange(0, tickets - 1, "Enter the number of tickets for minors you want to buy. Remember that at least one adult must accompany underage travellers.");

        // Purchase options menu is displayed as long as the purchase has not been successfully completed.
        boolean purchaseCompleted = true;
        // check that the seats have been assigned and thus avoid indicating the number of tickets and to exit the purchase option menu without having selected the seats
        boolean seatsAssigned = false;
        do{
            System.out.println("Choose a ticket purchase option:");
            System.out.println("1. Manual seat selection.");
            System.out.println("2. Automatic seat selection.");
            System.out.println("3. Automatic selection of adjacent seats.");
            System.out.println("4. Exit purchase menu");
            System.out.println("Enter the number of the option you wish to choose:");
            // Read purchase option
            purchaseOption = KEYBOARD.nextInt();
            switch (purchaseOption) {
                // Manual seat selection
                case 1:
                    // For each passenger / ticket
                    for (int i = 0; i < tickets; i++){
                        int[] seat = selectSeat(seats, 'A',
                                "Enter row for seat of ticket number " + (i+1),
                                "Enter column for seat of ticket number " + (i+1));
                        System.out.println("Seat has been selected.");
                        seats[seat[0]][seat[1]] = OCCUPIED;
                    }
                    purchaseCompleted = true;
                    seatsAssigned = true;
                    break;
                // Automatic seat selection
                case 2:
                    assignSeatsAutomatically(seats, tickets);
                    purchaseCompleted = true;
                    seatsAssigned = true;
                    break;
                // Automatic selection of adjacent seats
                case 3:
                    boolean adjacentSeats = searchContiguousSeats(seats, tickets);
                    if(adjacentSeats){
                        purchaseCompleted = true;
                        seatsAssigned = true;
                    } else {
                        System.out.println("There are not enough adjacent seats available. Choose another type of seat selection");
                        purchaseCompleted = false;
                    }
                    break;
                case 4:
                    System.out.println("Exiting the ticket purchase menu.");
                    break;
                default:
                    System.out.println("Invalid purchase option. Try again.");
                    purchaseCompleted = false;
            }
        }while(!purchaseCompleted);

        // Generation of the invoice for the purchase of tickets if the seats were assigned
        if(seatsAssigned){
            generateInvoice(tickets, ticketsMinors, price);
        }
    }

    private static boolean searchContiguousSeats(char[][] seats, int tickets) {
        boolean adjacentSeats = false;
        // Traversal of the matrix in search of N contiguous seats. The traversal ends when the N contiguous seats have been found.
        for (int i = 0; i < ROWS && !adjacentSeats; i++) {
            int adjacent = 0; // adjacent seats counter
            for (int j = 0; j < COLUMNS && !adjacentSeats; j++) {
                if (seats[i][j] == AVAILABLE) {
                    adjacent++;
                } else {
                    adjacent = 0; // Restart the count when an occupied seat is found
                }
                // If, after traversing the matrix, N contiguous seats have been found....
                if (adjacent == tickets) {
                    // Adjacent seats found are marked  as occupied.
                    for (int k = j - adjacent + 1; k <= j; k++) {
                        seats[i][k] = OCCUPIED;
                        System.out.println("The seat in row " + (i+1) + " and column " + (k+1) + " has been selected.");
                    }
                    adjacentSeats = true;
                }
            }
        }
        return adjacentSeats;
    }

    private static void assignSeatsAutomatically(char[][] seats, int tickets) {
        int selectedSeats = 0;
        boolean completedSeatSelection = false;
        System.out.println("Seats will be selected automatically:");
        // Traverse through rows of the seats matrix in search of available seats
        // Traversal ends when the seat selection is complete, i.e. when as many seats have been selected as there are tickets to be purchased by the user.
        for(int i = 0; i < ROWS && !completedSeatSelection; i++){
            for(int j = 0; j < COLUMNS && !completedSeatSelection; j++){
                if(seats[i][j] == AVAILABLE){
                    seats[i][j] = OCCUPIED;
                    // The selected and available seat counts are updated.
                    selectedSeats++;
                    System.out.println("The seat in row " + (i+1) + " and column " + (j+1) + " has been selected.");
                    if(selectedSeats == tickets){
                        completedSeatSelection = true;
                    }
                }
            }
        }
    }

    private static int[] selectSeat(char[][] seats, char type, String promptRow, String promptCol) {
        int[] seat = new int[2];
        do{
            // ask seat row
            seat[0] = getValueInRange(1, ROWS, promptRow) - 1; // -1 so that its in the array index bounds [0,length-1]
            // ask seat column
            seat[1] = getValueInRange(1, COLUMNS, promptCol) - 1;

            if(seats[seat[0]][seat[1]] != type){
                System.out.printf("Sorry, that seat is currently %s. Please select another seat.\n", type == 'A' ? "occupied" : "available");
            }
        }while(seats[seat[0]][seat[1]] != type);
        return seat;
    }

    private static void generateInvoice(int tickets, int ticketsMinors, double price) {
        double totalPrice = price * tickets;
        int ticketsAdults = tickets - ticketsMinors;
        double totalPriceMinors, totalPriceAdults;
        if (ticketsMinors > 0) {
            totalPriceMinors = price * (100 - DISCOUNT_MINORS)/100  * ticketsMinors;
            totalPriceAdults = price * ticketsAdults;
            totalPrice = totalPriceAdults + totalPriceMinors;
        }
        if (tickets > MAX_TICKETS_NUMBER) {
            totalPrice = totalPrice * (100 - DISCOUNT_MAXTICKETS)/100;
        }
        System.out.println("Invoice:");
        System.out.println("  Number of tickets: " + tickets);
        System.out.println("  Price per ticket: " + price);
        System.out.println("  Discount applied for purchase in bulk: " + (tickets > MAX_TICKETS_NUMBER ? DISCOUNT_MAXTICKETS + "%": "None"));
        System.out.println("  Discount applied for tickets for minors: " + (ticketsMinors > 0 ? DISCOUNT_MINORS + "%" : "None"));
        System.out.printf("  Total ptice: %.2f", totalPrice);
    }

    private static int getAvailableCount(char[][] seats) {
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == AVAILABLE){
                    count++;
                }
            }
        }
        return count;
    } // Obtain the number of available seats

    private static int askForChangeCount(int availableSeats, int occupiedSeats) {
        int UB, count;
        UB = Math.min(availableSeats, occupiedSeats);
        count = getValueInRange(1, UB, "Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.");
        return count;
    } //

    private static void changeTickets(int changeCount, char[][] seats) {
        // For each ticket
        for(int i = 0; i < changeCount; i++){
            int[] oldSeat, newSeat;
            // Selects the seat whose ticket is to be changed
            do{
                oldSeat = selectSeat(seats, 'O',
                        "Select the row of the seat you want to change",
                        "Select the column of the seat you want to change");
            }while(seats[oldSeat[0]][oldSeat[1]] == AVAILABLE);

            // Select the seat the user wants to change to
            do{
                newSeat = selectSeat(seats, 'A',
                        "Select the row of the seat you want to change to",
                        "Select the column of the seat you want to change to");
            }while(seats[newSeat[0]][newSeat[1]] == OCCUPIED);
            // the old seat is available
            seats[oldSeat[0]][oldSeat[1]] = AVAILABLE;
            // The new seat is occupied
            seats[newSeat[0]][newSeat[1]] = OCCUPIED;
        }
    }
}
