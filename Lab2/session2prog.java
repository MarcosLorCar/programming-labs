package Lab2;

import java.util.Scanner;
public class session2prog {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {

        int number1;
        System.out.println("how many tickets for adults?");
        number1 = KEYBOARD.nextInt();
            if (number1 > 0) {
                //we declare the variables for every data we are using
                    float price, ticket;
                    int number2;
                //we ask for price
                    System.out.printf("price?");
                    price = KEYBOARD.nextFloat();   
                //we ask for minors tickets
                    System.out.println("how many tickets for minors?");
                    number2 = KEYBOARD.nextInt();
                    if (number2 <= 0) {
                         ticket = price * number1;
                        System.out.println("your final price is: ");
                        System.out.println(ticket);
                    }
                    else{
                        if (number2 > 0) {
                            ticket = price * number1 * number2 * 8/10;
                            if (number1 > 12) {
                                ticket = price * number1 * number2 * 8/10 * 9/10;
                                System.out.println("as you have bought more than 12 tickets, we apply another discount, resulting in a price of: ");
                                System.out.print(ticket);
                            }
                            else {
                                ticket = price * number1 * number2 * 8/10;
                                System.out.println("with discount is: ");
                                System.out.print(ticket);
                            }
                        } 
                    }
            }
        else{
            System.out.println("error mister");
        }
    
    }
    
}
