import java.util.Scanner;

public class Exercise2 {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) {
        double price, tickets, minors, total, totaldisc;
        System.out.println("Enter the exact price per ticket: ");
        price = KEYBOARD.nextDouble();
        System.out.println("Enter the amount of tickets desired: ");
        tickets = KEYBOARD.nextDouble();
        if (tickets > 0) {
            System.out.println("Enter the amount of minors: ");
            minors = KEYBOARD.nextDouble();
            total = price * tickets;
            System.out.printf("The total price with no discounts is: %.2f\n", total);
            if (minors >= 0) {
                if (tickets > minors) {
                    double minorsprice = price * minors;
                    total = (tickets - minors) * price + (minorsprice - 0.2 * minorsprice);
                    if(minors > 0){
                        System.out.printf("The total price applying kids' discount is: %.2f\n", total);
                    }
                    if (tickets > 12) {
                        totaldisc = total - total * 0.1;
                        System.out.printf("The final price is: %.2f\n", totaldisc);
                    } 
                } else {
                    System.out.println("ERROR... The amount of minors can't be greater than the amount of ticktes");
                }
            } else{
                System.out.println("ERROR... The amount of minors can't be negative");
            }
            
        }
        else {
            System.out.println("ERROR... The amount of tickets must be over 0");
        }
    }
}