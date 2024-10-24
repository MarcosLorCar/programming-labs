package Rafa;

import java.util.Scanner;
public class session3progv2 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int option, number, age, minors = 0, totals = 0;
        float price = 0, finalticket1, finalticket2;
        System.out.println("select an option please: \n");
        System.out.println("Option 1: Establish price for a ticket \n"+ "Option 2: Purchase tickets \n"+ "Option 3: Exit the program \n");
        option = KEYBOARD.nextInt();
        while (option != 3) {
            if (option == 1) {
                System.out.println("Price of ticket?");
                price = KEYBOARD.nextFloat();
                while (price <= 0 ) {
                    System.out.println("Price of ticket?");
                    price = KEYBOARD.nextFloat();
                }
                System.out.println("select an option please: \n");
                System.out.println("Option 1: Establish price for a ticket \n"+ "Option 2: Purchase tickets \n"+ "Option 3: Exit the program \n");
                option = KEYBOARD.nextInt();
            }
            if (option == 2){
                if (price == 0) {
                    System.out.println("you have to enter a price first");
                    price = KEYBOARD.nextFloat();
                    while (price <= 0) {
                        System.out.println("price of ticket?");
                        price = KEYBOARD.nextFloat();
                        
                    }
                }
                System.out.println("number of ticket?");
                number = KEYBOARD.nextInt();
                while (number <= 0 ) {
                    System.out.println("number of tickets?");
                    number = KEYBOARD.nextInt();
                }
                for (int i = 1; i<=number; i++){
                    System.out.println("age of the passenger number: "+i);
                    age = KEYBOARD.nextInt();
                    if (age < 18 && age >= 0) {
                        minors++;
                    }
                    else{
                        totals++;
                    }
                    while (i>=number && minors == number) {
                        System.out.println("There must be a passenger older than 18 years old");
                        i= 0;
                        minors = 0;
                    }
                }
                finalticket1 = totals * price;
                System.out.printf("here is your price for adults: %.2f", finalticket1);
                if (minors > 0) {
                    if (minors == number) {
                        System.out.println("error");
                    }
                    else{
                        finalticket2 = minors * 0.8f;
                        finalticket1 = (float) (price * totals * finalticket2);
                        System.out.printf(" minors come with a discount resulting in a price of: %.2f\n", finalticket1); 
                    }
                }
                if (minors + totals > 12) {
                    if (minors>0) {
                        finalticket2 = minors * 8/10;
                        finalticket1 = finalticket2 * price * totals * 9/10;
                        System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",finalticket1); 
                    }
                    else{
                        finalticket2 = minors * 8/10;
                        finalticket1 = finalticket2 * price * totals * 9/10;
                        System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",finalticket1);
                    }
                }
                System.out.println("thanks for using our services");
                break;
            }
            if (option == 3) {
                System.out.println("ending program...");
            }
        } 
    }
}