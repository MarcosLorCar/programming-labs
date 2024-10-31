import java.util.Scanner;
public class Arrays {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int i = 0, ticket, age, adults, minors, Nseats = 20;
        float price, fp1, fp2;
        System.out.println("Menu: ");
        System.out.println("1. Establish a price for a ticket");
        System.out.println("2. Purchase tickets");
        System.out.println("3. Exit the program");
        i = KEYBOARD.nextInt();
        price = 0;
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
                if (ticket > Nseats) {
                    System.out.println("sorry, we don't have enough seats available");
                }
                char [] seats = new char[Nseats];
                for (int k = 0; k < seats.length; k++) {
                    seats[i] = 'A';
                    System.out.print(k + 1 + ":" + seats[i] + " \n");
                }
                minors = 0;
                adults = 0;
                for (int j = 0; j < ticket; j++) {
                    System.out.println("Age of passenger number: "+j);
                    age = KEYBOARD.nextInt();
                    if (age < 18 && age > 0) {
                        minors++;
                    }
                    if (age > 18) {
                        adults++;
                    }
                    while (j>=ticket && minors == ticket) {
                        System.out.println("There must be a passenger older than 18 years old");
                        j = 0;
                        minors = 0;
                    }
                }
                fp1 = price * adults * minors;
                System.out.printf("total price:%.2f "   ,fp1);
                if (minors > 0) {
                    fp2 = minors * 0.8f;
                    fp1 = (float) (price * adults * fp2);
                    System.out.println("here is the price with discounts for minors: %.2f " +fp1);
                }
                if (minors + adults > 12) {
                    if (minors>0) {
                        fp2 = minors * 8/10;
                        fp1 = fp2 * price * adults * 9/10;
                        System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",fp1); 
                    }
                    else{
                        fp2 = minors * 8/10;
                        fp1 = fp2 * price * adults * 9/10;
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
