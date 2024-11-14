import java.util.Scanner;
public class dimesions2 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        //Declaring variables for the program
        int ticket, adult, minor, option, oPtionp, row, column, counter, jumprow,free;
        int pSeats = 0;
        float price, fp1;
        //Declaring constants for the array
        int R = 5,C = 4;
        char seats[][] = new char[R][C];
        int aSeats = seats.length * seats[0].length;
        //Showing the matrix and welcoming the users
        System.out.println("Welcome to the train \n" + "We currently have this seats");
        for (int i = 0; i < seats[0].length; i++) {
            if (i == 0) {
                System.out.printf("      ");
            }
            System.out.print(i + 1 + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("row" + (i + 1) + ":");
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 'A';
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }
        //Question to enter price that will be read from the keyboard
        System.out.println("Enter a price please");
            price = KEYBOARD.nextFloat();
            while (price <= 0) {
                System.out.println("Error, enter a price greater than 0 please");
                price = KEYBOARD.nextFloat();
            }
        //The dowhile here serves as a loop until you press 4, which ends the program
        do{
            //We show the display of seats and ask the user the option that he can choose
            System.out.println("There are currently available these number of seats: " + aSeats + "\n" );
            System.out.println("Choose an option please: \n" + "1. Purchase tickets \n" + "2. Change tickets \n" + "3. Show seat status \n" + "4. Exit the program");
            option = KEYBOARD.nextInt();
            switch (option) {
                //We choose the total tickets and the minors
                case 1:
                System.out.println("Choose a number of tickets greater than one, we remember you we have " + aSeats + " seats available");
                ticket = KEYBOARD.nextInt();
                System.out.println("Choose a number of tickets for minors");
                minor = KEYBOARD.nextInt();
                if (ticket == minor) {
                    System.out.println("ERROR. We need at least one adult");
                    System.out.println("Choose a number of tickets greater than one, we remember you we have " + aSeats + " seats available");
                    adult = KEYBOARD.nextInt();
                    System.out.println("Choose a number of tickets for minors");
                    minor = KEYBOARD.nextInt();
                    
                }    
                //Another menu for the selection of seating               
                System.out.println("Choose a ticket purchase option: \n" + "1. Manual selection \n" + "2. Automatic selection \n" + "3. Automatic selection of available seats \n" + "4. Exit purchase program");
                oPtionp = KEYBOARD.nextInt();
                switch (oPtionp) {
                    case 1:
                        //Manual selection of seats, which the user can choose the row and column where an available seat is
                        while (pSeats < ticket) {   
                            for (int i = 0; i < seats[0].length; i++) {
                                if (i == 0) {
                                    System.out.printf("      ");
                                }
                                System.out.print(i + 1 + " ");
                            }
                            System.out.print("\n");
                            for (int i = 0; i < seats.length; i++) {
                                System.out.print("row" + (i + 1) + ":");
                                for (int j = 0; j < seats[i].length; j++) {
                                    System.out.print(" " + seats[i][j]);
                                }
                                System.out.println();
                            }
                            System.out.println("Please, pick a row");
                            row = KEYBOARD.nextInt() - 1;
                            System.out.println("Please, pick a column");
                            column = KEYBOARD.nextInt() - 1;
                            if ((row < seats.length && row >= 0) && (column < seats[0].length && column >= 0)){
                                if (seats[row][column] == 'A') {
                                        seats[row][column] = 'O';
                                        pSeats++;
                                        aSeats--; 
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
                        //Calculate the price 
                        adult = ticket - minor;
                        fp1 = ticket * price;
                        System.out.printf("Here is your price: %.2f" , fp1);
                        if (minor > 0) {
                            fp1 = price * (adult + minor) * 0.8f;
                            System.out.printf("Here is your price applying the discount for minors: %.2f" ,fp1);
                        }
                        if (minor + ticket >= 12) {
                            if (minor > 12) {
                                fp1 = price * (adult + minor) * 0.8f * 0.9f;
                                System.out.printf("Here is your price with discounts for groups larger than 12 and minors:  %.2f " , fp1);
                            }
                            else{
                                fp1 = price * ticket * 0.9f;
                                System.out.printf("Here is your discount for groups larger than 12: %.2f" ,fp1);
                            }
                        }
                        break;
                    case 2:
                        //Automatic selection, the program chooses where you are going to seat starting from row 1 column 1 and so on
                        for (int i = 0; i < seats[0].length; i++) {
                            if (i == 0) {
                                System.out.printf("      ");
                            }
                            System.out.print(i + 1 + " ");
                        }
                        System.out.print("\n");
                        for (int i = 0; i < seats.length; i++) {
                            System.out.print("row" + (i + 1) + ":");
                            for (int j = 0; j < seats[i].length; j++) {
                                System.out.print(" " + seats[i][j]);
                            }
                            System.out.println();
                        }
                        while (pSeats < ticket) {
                            for (int i = 0; i < seats.length && pSeats <= ticket; i++) {
                                for (int j = 0; j < seats[i].length && j < ticket; j++) {
                                    if (seats[i][j]=='A') {
                                        seats[i][j]='O';
                                        pSeats++;
                                        aSeats--; 
                                        System.out.printf("your seat in row %d and column %d has been selected \n", i+1,j+1);
                                    }
                                }
                            }
                        }
                        System.out.println("your seats has been assigned");
                        adult = ticket - minor;
                        fp1 = ticket * price;
                        System.out.printf("Here is your price: %.2f" , fp1);
                        if (minor > 0) {
                            fp1 = price * (adult + minor) * 0.8f;
                            System.out.printf("Here is your price applying the discount for minors: %.2f" ,fp1);
                        }
                        if (minor + ticket >= 12) {
                            if (minor > 12) {
                                fp1 = price * (adult + minor) * 0.8f * 0.9f;
                                System.out.printf("Here is your price with discounts for groups larger than 12 and minors:  %.2f " , fp1);
                            }
                            else{
                                fp1 = price * ticket * 0.9f;
                                System.out.printf("Here is your discount for groups larger than 12: %.2f" ,fp1);
                            }
                        }
                        break;
                    case 3:
                    //Automatic selection with adjacent seats, the program selects adjacent seats (if is not full the columns) and places the people next to each other
                    if (ticket > C) {
                        System.out.println("ERROR. We can`t put adjacent seats");
                    }
                    else{
                        counter = 0;
                        jumprow = 0;
                        free = aSeats;
                        for (int i = 0; i < seats.length; i++) {
                            for (int j = 0; j < seats[i].length && counter != ticket; j++) {
                                if (seats[i][j] == 'A') {
                                    counter++;
                                    jumprow = j;
                                }
                                else{
                                    counter = 0;
                                }
                                
                            }
                            if (counter == ticket && free == aSeats) {
                                for (int k = 0; k < ticket; k++) {
                                    seats[i][jumprow - k] = 'O';
                                    aSeats--;
                                }
                                counter = 0;
                            }
                        }
                    }
                    for (int i = 0; i < seats[0].length; i++) {
                        if (i == 0) {
                            System.out.printf("      ");
                        }
                        System.out.print(i + 1 + " ");
                    }
                    System.out.print("\n");
                    for (int i = 0; i < seats.length; i++) {
                        System.out.print("row" + (i + 1) + ":");
                        for (int j = 0; j < seats[i].length; j++) {
                            System.out.print(" " + seats[i][j]);
                        }
                        System.out.println();
                    }
                    adult = ticket - minor;
                    fp1 = ticket * price;
                    System.out.printf("Here is your price: %.2f" , fp1);
                    if (minor > 0) {
                        fp1 = price * (adult + minor) * 0.8f;
                        System.out.printf("Here is your price applying the discount for minors: %.2f" ,fp1);
                    }
                    if (minor + ticket >= 12) {
                        if (minor > 12) {
                            fp1 = price * (adult + minor) * 0.8f * 0.9f;
                            System.out.printf("Here is your price with discounts for groups larger than 12 and minors:  %.2f " , fp1);
                        }
                        else{
                            fp1 = price * ticket * 0.9f;
                            System.out.printf("Here is your discount for groups larger than 12: %.2f" ,fp1);
                        }
                    }
                        break;
                    case 4:
                        //only a break because it ends the program
                        break;
                    default:
                        System.out.println("ERROR. That option is not in the program");
                        option = KEYBOARD.nextInt();
                        break;
                }
                    break;
                case 2:
                    //changing your position to another seat
                    System.out.println("Please, select the tickets that you want to change");
                    ticket = KEYBOARD.nextInt();
                    while (pSeats < ticket) {   
                        for (int i = 0; i < seats[0].length; i++) {
                            if (i == 0) {
                                System.out.printf("      ");
                            }
                            System.out.print(i + 1 + " ");
                        }
                        System.out.print("\n");
                        for (int i = 0; i < seats.length; i++) {
                            System.out.print("row" + (i + 1) + ":");
                            for (int j = 0; j < seats[i].length; j++) {
                                System.out.print(" " + seats[i][j]);
                            }
                            System.out.println();
                        }
                        System.out.println("Please, pick the row that you were seated");
                        row = KEYBOARD.nextInt() - 1;
                        System.out.println("Please, pick the column where you were seated");
                        column = KEYBOARD.nextInt() - 1;
                        if ((row < seats.length && row >= 0) && (column < seats[0].length && column >= 0)){
                            if (seats[row][column] == 'O') {
                                    seats[row][column] = 'A';            
                            }
                            else {
                                System.out.println("ERROR. That seat is occupied, choose another");
                            }
                        }
                        System.out.println("Please pick a row");
                        row = KEYBOARD.nextInt() - 1;
                        System.out.println("Please, pick a column");
                        column = KEYBOARD.nextInt();
                        if ((row < seats.length && row >= 0) && (column < seats[0].length && column >= 0)){
                            if (seats[row][column] == 'A') {
                                    seats[row][column] = 'O';            
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
            case 3:
                for (int i = 0; i < seats.length; i++) {
                    System.out.print("row" + (i + 1) + ":");
                    for (int j = 0; j < seats[i].length; j++) {
                        System.out.print(" " + seats[i][j]);
                    }
                    System.out.println();
                }
                break;
            case 4:     
                break;
            default:
                System.out.println("ERROR. This option is not available");
                option = KEYBOARD.nextInt();
                break;
            }
        } while(option != 4);
    }
}
