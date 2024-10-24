import java.util.Scanner;

public class Exercise1 {
    private static final Scanner KEYBOARD = new Scanner(System.in); 
    public static void main(String[] args) {
        double price, amount, total, discounted;
        System.out.println("Enter the exact price per ticket: ");
        price = KEYBOARD.nextDouble();
        System.out.println("Enter the amount of tickets you want");
        amount = KEYBOARD.nextDouble();
        total = price * amount;
        System.out.println("The price of all the ticktes is: " + total);
        discounted = total - total * 0.2;
        System.out.printf("The total price applying a 0.2 discount is: " + discounted);
    }     
    
}
