package Lab1;
import java.util.Scanner;
public class assignment1 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        //Creation of variables
        float price, result;
        int numTickets;
        final double DISCOUNT=0.8;

        //Definition of variables
        System.out.print("How much does your ticket cost? (Remember decimals are written with commas)");
        price= KEYBOARD.nextFloat();

        System.out.print("How many tickets have you purchased?");
        numTickets= KEYBOARD.nextInt();
        result= price*numTickets;
        

        //Output dislay
        System.out.printf("The final price is: %.2f %n", result  ); // %.2f %n se utiliza para poner con dos decimales en el float
        System.out.printf("However, we offer a discount of a 20%%, so you will have to pay the total of %.2f",  result*DISCOUNT);
        System.out.print("\nEnd of program...");
        

    } //End of method
} //End of class
