import java.util.Scanner;

public class Assignment7 {

    static final int ROWS = 5;
	static final int COLUMNS = 4;
	static final char AVAILABLE = 'A';
	static final char OCCUPIED = 'O';
	static final double DISCOUNT_MINORS = 20; // or multiply by 0,8
	static final double DISCOUNT_MAXTICKETS = 10; // or multiply by 0,9
	static final int MAX_TICKETS_NUMBER = 12;
	static final Scanner KEYBOARD = new Scanner(System.in);
    
    
    static double askprice(){
        double price;
        do{
            System.out.println("Enter the price for the train ticket.");
            System.out.println("Please note that the ticket price must be greater than 0.");
            price = KEYBOARD.nextDouble();
            if(price <= 0){
                System.out.println("ERROR. The price of the ticket must be greater than 0.");
                price = KEYBOARD.nextDouble();
            }
        }while(price <= 0);

        return price;
    }
    static void showcurrentstatus(char seats[][]){
                // The current seat status is displayed first.
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
        return;
    }
    static void initialseats(char seats [][]){
        for(int i = 0;i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                seats[i][j] = AVAILABLE;
            }
        }
    }
    static int read(int LB, int UB, String prompt){
        int ticket;
        boolean grant = false;
        do {
            System.out.println(prompt + UB + LB);
            ticket = KEYBOARD.nextInt();
            if (LB < ticket && ticket > UB) {
                grant = true;
            }
            else{
                System.out.println("ERROR. Put again the value please");
                grant = false;
            }
        } while (grant = false);
        return ticket;
    }
    static int Obtainseats(char seats[][]){
        int availableSeats = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == AVAILABLE) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }
    static int askNforchange(int availableSeats, int occupiedSeats){
        int UB;
        UB = Math.min(availableSeats, occupiedSeats);
        read(1, UB, "Enter the tickets to change, select more than one please");
            
        return availableSeats;
    }
    static void changetheN(int changedTickets,char seats[][]){
        for(int i = 0; i < changedTickets; i++){
            int oldSeat[],newSeat[];
            // Selects the seat whose ticket is to be changed
            do{
                oldSeat = selectseats(seats, 'O', "Enter the row you were seated", "Enetr the column you were seated");
            }while(seats[oldSeat[0]][oldSeat[1]] == AVAILABLE);
            // Select the seat the user wants to change to
            do{
                newSeat = selectseats(seats, 'A',"Enter the new row", "Enter the new column");
                
            }while(seats[newSeat[0]][newSeat[1]] == OCCUPIED);
            // the old seat is available
            seats[oldSeat[0]][oldSeat[1]] = AVAILABLE;
            // Se new seat is occupied
            seats[newSeat[0]][newSeat[1]] = OCCUPIED;
        }
    }
    static boolean themenu(double price, char seats[][]){
        boolean showMenu = true;
        int availableSeats = ROWS * COLUMNS;
        int option;
        
        
            // The number of available seats is shown
            
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
                // Purchase tickets
                case 1:
                    // Check if there are avabilable seats
                    if(availableSeats > 0){
                        purchasingtickets(availableSeats, seats, price);
                        
                    }
                    else{
                        System.out.println("Sorry, there are not enough seats available. Purchase could not be finished.");
                    }
                break;
                // Change tickets
                case 2:
                    // Tickets can be changed when there is at least one ticket purchased and one seat available for exchange.
                    availableSeats = Obtainseats(seats);
                    int occupiedSeats = ROWS * COLUMNS - availableSeats;
                    if(availableSeats > 0 && occupiedSeats > 0){
                        // The current seat status is displayed first.
                        // The first row with the column headers of the matrix is displayed.
                        showcurrentstatus(seats);
                        // Ask amount of tickets to change
                        askNforchange(availableSeats, occupiedSeats);
                        int changeTickets = askNforchange(availableSeats, occupiedSeats);
                        System.out.println(changeTickets + " seats wil be changed.");
                        // For each ticket
                        changetheN(changeTickets, seats);
                    }
                    else{
                        System.out.println("It is not possible to change seats because there are no seats available or there are no occupied seats.");
                    }
                    break;
                // Show seat status
                case 3:
                    showcurrentstatus(seats);
                    break;
                case 4:
                    System.out.println("Exiting the program. \n Thank you for using our software");
                    showMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        
        return showMenu;
    }
    static void purchasingtickets(int availableSeats, char [][] seats, double price){
        int purchaseOption;
        int tickets, ticketsMinors;
        // ask and read number of tickets
        do{
            System.out.println("Enter the number of tickets you want to buy. You must buy at least one, and there are " +availableSeats+ " available seats left.");
            tickets = KEYBOARD.nextInt();
            if(tickets <= 0 || tickets > availableSeats){
                System.out.println("ERROR. The amount of tickets is not valid.");
            }
        }while(tickets <= 0 || tickets > availableSeats);
        
        // ask number of tickets for minors
        do{
            System.out.println("Enter the number of tickets for minors you want to buy. Remember that at least one adult must accompany underage travellers.");
            ticketsMinors = KEYBOARD.nextInt();
            if(ticketsMinors < 0 || ticketsMinors >= tickets){
                System.out.println("ERROR. The amount of tickets is not valid");
            }
        }while(ticketsMinors < 0 || ticketsMinors >= tickets);
        
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
                    // for each passenger / ticket
                    for (int i = 0; i < tickets; i++){
                         int seat[] = selectseats(seats, 'A', "Please pick the row for passenger" + (i+1), "Please pick the column for passenger" + (i+1));
                         System.out.println("Your seat has been selected");
                         seats[seat[0]][seat[1]] = OCCUPIED;
                    }
                    purchaseCompleted = true;
                    seatsAssigned = true;
                    break;
                // Automatic seat selection
                case 2:
                    selectauto(seats, availableSeats, ticketsMinors);
                    purchaseCompleted = true;
                    seatsAssigned = true;
                    break;
                // Automatic selection of adjacent seats
                case 3:
                    selectcontigous(ticketsMinors, seats, availableSeats);
                    purchaseCompleted = true;
                    seatsAssigned = true;
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
        invoice(price, tickets, ticketsMinors, seatsAssigned);
         
    }
    static void selectauto(char [][] seats, int availableSeats, int tickets){
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
                    availableSeats--;
                    System.out.println("The seat in row " + (i+1) + " and column " + (j+1) + " has been selected.");
                    if(selectedSeats == tickets){
                        completedSeatSelection = true;
                    }
                }
            }
        }
    }
    static void selectcontigous(int tickets, char [][] seats, int availableSeats){
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
                        // Update available seats
                        availableSeats--;
                        System.out.println("The seat in row " + (i+1) + " and column " + (k+1) + " has been selected.");
                    }
                    adjacentSeats = true;
                }
            }
        }
        if(!adjacentSeats){
            System.out.println("There are not enough adjacent seats available. Choose another type of seat selection");
        }
    }
    static void invoice(double price, int tickets, int ticketsMinors, boolean seatsAssigned){
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
    }
    static int[] selectseats(char [][]seats, char parameter, String promptR, String promptC){
        int [] seat = new int[2];
        do {
            seat[0] = read(1, ROWS, promptR) - 1;
            seat[1] = read(1, COLUMNS, promptC) - 1;
            if (seats[seat[0]][seat[1]] != parameter) {
                System.out.printf("That seat is %s, please pick another","\n", parameter == 'A' ? "occupied" : "available");
                
            }
        } while (seats[seat[0]][seat[1]] != parameter);
        return seat;
    }
    public static void main(String[] args) throws Exception {
        char [][] seats = new char[ROWS][COLUMNS];
        double price;
        // Initially all seats are available
        boolean showMenu = true;
        
        initialseats(seats);
        // Inicio del programa
        System.out.println("Welcome to the train ticket management software");
        System.out.println("The current seat status is as follows:");
        showcurrentstatus(seats);
        // The program starts by displaying the current seat status.
        // To do this, first of all, all the seats on the train are initialised as available. 
        // The matrix is traversed and each element is assigned the value AVAILABLE.

        

        // The user is prompted to enter the ticket price. Error checking is implemented
        price = askprice();
        do {

            showMenu = themenu(price,seats);
        } while (showMenu);

    }
}
