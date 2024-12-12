import java.util.Scanner;

public class Exercise6 {

    static final int ROWS = 5;
	static final int COLUMNS = 4;
	static final char AVAILABLE = 'A';
	static final char OCCUPIED = 'O';
	static final double DISCOUNT_MINORS = 20; // or multiply by 0,8
	static final double DISCOUNT_MAXTICKETS = 10; // or multiply by 0,9
	static final int MAX_TICKETS_NUMBER = 12;
	static final Scanner KEYBOARD = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        char[][] seats = new char[ROWS][COLUMNS];
        double price;
        int tickets=0, ticketsMinors=0;
        boolean showMenu = true;

        // Inicio del programa
        System.out.println("Welcome to the train ticket management software");
        System.out.println("The current seat status is as follows:");

        // The program starts by displaying the current seat status.
        // To do this, first of all, all the seats on the train are initialised as available. 
        // The matrix is traversed and each element is assigned the value AVAILABLE.
        AllSeatsA(COLUMNS, ROWS, seats);
        // Secondly, the current seat status is displayed.
        CurrentStatus(COLUMNS, ROWS, seats);
        
        // The user is prompted to enter the ticket price. Error checking is implemented
        price=TicketPrice();
        do{
            //The amount of available seats is calculated
            int availableSeats=AvailableSeats(ROWS, COLUMNS, seats);
            // The number of available seats is shown
            System.out.println("\n Currently there are " + availableSeats + " available seats.");
            MainMenu(availableSeats, seats, tickets, ticketsMinors, price, showMenu);
        }while(showMenu== true);
    }
    //-Ask ticket price
    public static double TicketPrice() {
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
    } 
    //-Show the current status of the seatsin the train
    public static char[][] CurrentStatus(int COLUMNS, int ROWS, char seats[][]){
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
        return seats;
    }
    //-Initialize the seats of the train
    public static char[][] AllSeatsA(int COLUMNS, int ROWS, char seats[][]){
        for(int i = 0;i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                seats[i][j] = AVAILABLE;
            }
        }
        return seats;
    }
    //-Read a number within a range
    public static int ReadNumber(int LB, int UB, int number){
        number=KEYBOARD.nextInt();
        while(LB>number || UB<number){
            System.out.print("Wrong number, choose again: ");
            number=KEYBOARD.nextInt();
        }
        return number;
    }
    //-Execute the main menu
    public static void MainMenu(int availableSeats, char seats[][], int tickets, int ticketsMinors, double price, boolean showMenu){
        // Show the main menu
        System.out.println("\n Choose one of the following options:");
        System.out.println("1. Purchase tickets");
        System.out.println("2. Change tickets");
        System.out.println("3. Show seat status");
        System.out.println("4. Exit the program");
        System.out.println("Enter the number of the option you wish to choose:");
        // Read the option selected by the user
        int option = KEYBOARD.nextInt();
        switch (option) {
            // Purchase tickets
            case 1:
                // Check if there are avabilable seats
                availableSeats=AvailableSeats(ROWS, COLUMNS, seats);
                if(availableSeats > 0){
                    // ask and read number of tickets
                    System.out.print("Choose the amount of tickets: ");
                    tickets=ReadNumber(1, availableSeats, tickets);
                    // ask number of tickets for minors
                    System.out.print("Choose the amount of tickets for minors: ");
                    ticketsMinors=ReadNumber(0, tickets, ticketsMinors);
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
                        int purchaseOption = KEYBOARD.nextInt();
                        switch (purchaseOption) {
                            // Manual seat selection
                            case 1: 
                                int seatRow=0, seatColumn=0;
                                // // for each passenger / ticket
                                for (int i = 0; i < tickets; i++){
                                    do{
                                        // ask seat row
                                        System.out.print("Choose a row: ");
                                        seatRow=ReadNumber(0, ROWS, seatRow);
                                        // ask seat column
                                        System.out.print("Choose a column: ");
                                        seatColumn=ReadNumber(0, COLUMNS, seatColumn);
                                        if(seats[seatRow-1][seatColumn-1] == OCCUPIED){
                                            System.out.println("Sorry, that seat is currently occupied. Please select another seat.");
                                        }
                                    }while(seats[seatRow-1][seatColumn-1] == OCCUPIED);
                                    System.out.println("Seat has been selected.");
                                    seats[seatRow-1][seatColumn-1] = OCCUPIED;
                                }
                                purchaseCompleted = true;
                                seatsAssigned = true;
                                break;
                                // Automatic seat selection
                            case 2:
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
                                    purchaseCompleted = true;
                                    seatsAssigned = true;
                                break;
                            // Automatic selection of adjacent seats
                            case 3:
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
                                            purchaseCompleted = true;
                                            seatsAssigned = true;
                                        }
                                    }
                                }
                                if(!adjacentSeats){
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
                        double totalPrice = price * tickets;
                        int ticketsAdults = tickets - ticketsMinors;
                        double totalPriceMinors, totalPriceAdults;
                        if (ticketsMinors> 0) {
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
            }else{
                System.out.println("Sorry, there are not enough seats available. Purchase could not be finished.");
            }
            break;
            // Change tickets
            case 2:
                TicketsChange(availableSeats, ROWS, COLUMNS, seats);
                break;
            // Show seat status
            case 3:
                CurrentStatus(COLUMNS, ROWS, seats);
                break;
            case 4:
                System.out.println("Exiting the program. \n Thank you for using our software");
                showMenu = false;
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }
    //-Obtain the number of available seats
    public static int AvailableSeats(int ROWS, int COLUMNS, char seats[][]){
        int AvailableSeats=0;
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMNS;j++){
                if(seats[i][j]=='A'){AvailableSeats++;}
            }
        }
        return AvailableSeats;
    }
    //-Ask the number of tickets to change
    public static int WhichTicketsChange(int changedTickets, int occupiedSeats, int availableSeats){
        System.out.println("Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.");
        changedTickets = KEYBOARD.nextInt();
        if(changedTickets > occupiedSeats || changedTickets > availableSeats){
            System.out.println("ERROR. It is not possible to change the selected number of tickets.");
        }
        return changedTickets;
    }//linea 197
    //-Change seat for N tickets
    public static void TicketsChange(int availableSeats, int ROWS, int COLUMNS, char seats[][]){
        availableSeats=AvailableSeats(ROWS, COLUMNS, seats);
        // Tickets can be changed when there is at least one ticket purchased and one seat available for exchange.
        int occupiedSeats = ROWS * COLUMNS - availableSeats;
        if(availableSeats > 0 && occupiedSeats > 0){
            // The current seat status is displayed first.
            // The first row with the column headers of the matrix is displayed.
            CurrentStatus(COLUMNS, ROWS, seats);
            // Ask amount of tickets to change
            int changedTickets=0;
            do{
                changedTickets=WhichTicketsChange(changedTickets, occupiedSeats, availableSeats);
            }while(changedTickets > occupiedSeats|| changedTickets > availableSeats);
            System.out.println(changedTickets + " seats wil be changed.");
            // For each ticket
            for(int i = 0; i < changedTickets; i++){
                int oldSeatRow, oldSeatColumn, newSeatRow, newSeatColumn;
                // Selects the seat whose ticket is to be changed
                do{
                    System.out.println("Select the row of the seat you want to change");
                    oldSeatRow = KEYBOARD.nextInt();
                    System.out.println("Select the column of the seat you want to change");
                    oldSeatColumn = KEYBOARD.nextInt();
                }while(oldSeatRow < 1 || oldSeatRow > ROWS || oldSeatColumn < 1 || oldSeatColumn > COLUMNS || seats[oldSeatRow-1][oldSeatColumn-1] == AVAILABLE);
                // Select the seat the user wants to change to
                do{
                    System.out.println("Select the row of the seat you want to change to");
                    newSeatRow = KEYBOARD.nextInt();
                    System.out.println("Select the column of the seat you want to change");
                    newSeatColumn = KEYBOARD.nextInt();
                }while(newSeatRow < 1 || newSeatRow > ROWS || newSeatColumn < 1 || newSeatColumn > COLUMNS || seats[newSeatRow-1][newSeatColumn-1] == OCCUPIED);
                // the old seat is available
                seats[oldSeatRow-1][oldSeatColumn-1] = AVAILABLE;
                // Se new seat is occupied
                seats[newSeatRow-1][newSeatColumn-1] = OCCUPIED;
            }//hasta aqui TicketsChange
            }else{
                System.out.println("It is not possible to change seats because there are no seats available or there are no occupied seats.");
            }
    }
}
