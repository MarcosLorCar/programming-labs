package Marcos.Lab1;

import java.util.Scanner;

public class Lab1 {
    private static final Scanner SC = new Scanner(System.in);
    private static final float DISCOUNT = 0.2f;
    public static void main(String[] args) {
        System.out.print("Enter the price of a ticket: ");
        float ticketPrice = SC.nextFloat();

        System.out.print("Enter the number of tickets to buy: ");
        int numberOfTickets = SC.nextInt();

        float price = ticketPrice * numberOfTickets;
        System.out.printf("The price of the %d tickets is %.2f€\n", numberOfTickets, price);

        float finalPrice = price - price * DISCOUNT;
        System.out.printf("The price after the discount is %.2f€\n", finalPrice);
    }
}
