import java.util.Scanner;
public class dimesions {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int adult, minor, option, oPtionp;
        float price, fp1;
        char seats[][] = new char[5][4];
        int aSeats = seats.length * seats[0].length;
        System.out.println("Welcome to the train \n" + "We currently have this seats");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("row" + (i + 1) + ":");
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 'A';
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }
        System.out.println("Enter a price please");
        price = KEYBOARD.nextFloat();
        while (price <= 0) {
            System.out.println("Error, enter a price greater than 0 please");
            price = KEYBOARD.nextFloat();
        }
        System.out.println("There are currently available these number of seats: " + aSeats + "\n" );
        System.out.println("Choose an option please: \n" + "1. Purchase tickets \n" + "2. Change tickets \n" + "3. Show seat status \n" + "4. Exit the program");
        option = KEYBOARD.nextInt();
        if (option == 1) {
            System.out.println("Choose a number of tickets greater than one, we remember you we have " + aSeats + " seats available");
            adult = KEYBOARD.nextInt();
            System.out.println("Choose a number of tickets for minors");
            minor = KEYBOARD.nextInt();
            System.out.println("Choose a ticket purchase option: \n" + "1. Manual selection \n" + "2. Automatic selection \n" + "3. Automatic selection of available seats \n" + "4. Exit purchase program");
            oPtionp = KEYBOARD.nextInt();
            switch (oPtionp) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:

                    break;
            }
        }
        if (option == 2) {
            
        }
        if (option == 3) {
            
        }
        if (option == 4) {
            
        }
    }
}
