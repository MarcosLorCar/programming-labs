import java.util.Scanner;
public class dimesions {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int ticket, adult, minor, option, oPtionp, row, column;
        int pSeats = 1;
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
        while (option != 4) {
            switch (option) {
                case 1:
                System.out.println("Choose a number of tickets greater than one, we remember you we have " + aSeats + " seats available");
                adult = KEYBOARD.nextInt();
                System.out.println("Choose a number of tickets for minors");
                minor = KEYBOARD.nextInt();
                if (adult == minor) {
                    System.out.println("ERROR. We need at least one adult");
                    System.out.println("Choose a number of tickets greater than one, we remember you we have " + aSeats + " seats available");
                    adult = KEYBOARD.nextInt();
                    System.out.println("Choose a number of tickets for minors");
                    minor = KEYBOARD.nextInt();
                    
                }
                ticket = adult;
                System.out.println("Choose a ticket purchase option: \n" + "1. Manual selection \n" + "2. Automatic selection \n" + "3. Automatic selection of available seats \n" + "4. Exit purchase program");
                oPtionp = KEYBOARD.nextInt();
                switch (oPtionp) {
                    case 1:
                        while (pSeats <= ticket) {
                            System.out.println("Please, pick a row");
                            row = KEYBOARD.nextInt();
                            row--;
                            System.out.println("Please, pick a column");
                            column = KEYBOARD.nextInt();
                            column--;
                            if ((row < seats.length && row >= 0) && (column < seats[0].length && column >= 0)){
                                if (seats[row][column] == 'A') {
                                     seats[row][column] = 'O';
                                     pSeats++;
                                     System.out.println("Your seat has been assigned");
                                }
                                else {
                                    System.out.println("ERROR. That seat is occupied, choose another");
                                }
                            }
                            else{
                                System.out.println("ERROR. The size doesn't match the seats");
                            }
                        }
                        break;
                    case 2:
                        while (pSeats <= ticket) {
                            for (int i = 0; i < seats.length && pSeats <= ticket; i++) {
                                for (int j = 0; j < seats[i].length && j < ticket; j++) {
                                    if (seats[i][j]=='A') {
                                        seats[i][j]='O';
                                        pSeats++;
                                        System.out.printf("your seat in row %d and column %d has been selected \n", i+1,j+1);
                                    }
                                }
                            }
                        }
                        System.out.println("your seats has been assigned");
                        break;
                    case 3:
    
                        break;
                    case 4:
                        System.out.println("Thanks for using our services \n");
                        System.out.println("There are currently available these number of seats: " + aSeats + "\n" );
                        System.out.println("Choose an option please: \n" + "1. Purchase tickets \n" + "2. Change tickets \n" + "3. Show seat status \n" + "4. Exit the program");
                        option = KEYBOARD.nextInt();
                        break;
                    default:
                        System.out.println("ERROR. That option is not in the program");
                        option = KEYBOARD.nextInt();
                        break;
                }
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
    }
}
