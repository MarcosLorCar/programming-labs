import java.util.Scanner;
public class Arrays {   

    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int ticket, age, adults, minors, Pseats;
        float price, fp1;
        System.out.println("Menu: ");
        System.out.println("1. Establish a price for a ticket");
        System.out.println("2. Purchase tickets");
        System.out.println("3. Exit the program");
        int i = KEYBOARD.nextInt();
        price = 0;
        char [] seats = {'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A'};
        while (i != 3) {
            if (i == 1) {
                System.out.println("please choose a price");
                price = KEYBOARD.nextFloat();
                while (price <= 0) {
                    System.out.println("ERROR, choose again");
                    price = KEYBOARD.nextFloat();
                }
                System.out.println("Menu: ");
                System.out.println("1. Establish a price for a ticket \n" + "2. Purchase tickets \n" + "3. Exit the program");
                i = KEYBOARD.nextInt();
            }
            if (i == 2) {
                if (price <= 0) {
                    System.out.println("you have to choose a price");
                    price = KEYBOARD.nextFloat();
                    while (price <= 0) {
                        System.out.println("ERROR, choose again");
                        price = KEYBOARD.nextFloat();
                    }
                }
                System.out.println("number of tickets please");
                ticket = KEYBOARD.nextInt();
                while (ticket <= 0) {
                    System.out.println("ERROR. enter a number of tickets");
                    ticket = KEYBOARD.nextInt();
                }
                if (ticket > seats.length) {
                    System.out.println("sorry, we don't have enough seats available");
                }
                for (int k = 0; k < seats.length; k++) {
                    System.out.print((k + 1) + ":" + seats[k] + " ");
                }
                minors = 0;
                adults = 0;
                for (int j = 0; j < ticket; j++) {      
                    System.out.println("\nAge of passenger number " + (j+1) + "?");
                    age = KEYBOARD.nextInt();
                    if (age < 18 && age > 0) {
                        minors++;
                    }
                    if (age > 18) {
                        adults++;
                    }
                    while (minors == ticket) {
                        System.out.println("There must be a passenger older than 18 years old");
                        j = -1;
                        minors = 0;
                        adults = 0;
                    }
                }
                for (int k = 0; k < seats.length; k++) {
                    System.out.print((k + 1) + ":" + seats[k] + " ");
                }
                for (int j = 0; j < ticket; j++) {
                    System.out.println("Please, pick a seat");
                    Pseats = KEYBOARD.nextInt();
                    Pseats--;
                    if (Pseats >= 0 && Pseats <= 19) {
                        if (seats[Pseats]=='A') {
                            seats[Pseats]='O';
                        }
                        else{
                            System.out.println("ERROR. That seat is occupied");
                            j--;
                        }
                    }
                    else{
                        System.out.println("That seat doesn't exist, pick another");
                        j--;
                    }
                }
                for (int k = 0; k < seats.length; k++) {
                    System.out.print((k + 1) + ":" + seats[k] + " ");
                }
                fp1 = price * (adults + minors);
                System.out.printf("total price:" + "%.2f ", fp1);
                if (minors > 0) {
                    fp1 = (float) (price * (adults + (minors * 0.8)));
                    System.out.printf("\nhere is the price with discounts for minors:" + "%.2f ", fp1);
                }
                if (minors + adults > 12) {
                    if (minors>0) {
                        fp1 = price * (adults + minors) * 9/10;
                        System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",fp1); 
                    }
                    else{ 
                        fp1 = price * adults * 9/10;
                        System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",fp1);
                    }
                }
                System.out.println("Menu: ");
                System.out.println("1. Establish a price for a ticket \n" + "2. Purchase tickets \n" + "3. Exit the program");
                i = KEYBOARD.nextInt();
            }
            if (i == 3){
                System.out.println("ending program");
            }
        }
    }
}
