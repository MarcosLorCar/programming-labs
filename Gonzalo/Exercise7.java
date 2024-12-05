public class Exercise7 {
    public static void main(String[] args) {
        char[][] seats = new char[ROWS][COLUMNS];
        double price;
        System.out.println("Welcome to the train ticket management
        software");
        initializeSeats(seats);
        showSeats(seats);
        price = askTicketPrice();
        executeMainMenu(price, seats);
        }       
}
