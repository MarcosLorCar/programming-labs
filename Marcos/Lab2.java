import java.util.Scanner;

public class Lab2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final float MINOR_DISCOUNT = 0.2F;
    private static final float BULK_DISCOUNT = 0.1F;
    private static final int BULK_AMMOUNT = 12;

    public static void main(String[] args) {
        System.out.print("Price of train ticket: ");
        float basePrice = sc.nextFloat();
        if (basePrice > 0) {
            System.out.print("Number of total tickets: ");
            int numTickets = sc.nextInt();
            if (numTickets > 0) {
                System.out.print("Number of tickets for minors: ");
                int numMinors = sc.nextInt();
                if (numMinors >= 0 && numMinors <= numTickets) {
                    float adult_price = basePrice * (numTickets - numMinors);
                    float child_price = basePrice * numMinors;
                    System.out.printf("Price of the %d without discount: %.2f%n", numTickets, adult_price+child_price);
                    if (numTickets >= BULK_AMMOUNT)
                        adult_price *= 1 - BULK_DISCOUNT;
                    child_price *= 1 - MINOR_DISCOUNT;
                    System.out.printf("New prices: \nAdults: %.2f \nChildren: %.2f \nTotal: %.2f",
                            adult_price, child_price, adult_price+child_price);
                } else System.out.println("Not a valid number.");
            } else System.out.println("Not a valid number.");
        } else System.out.println("Not a valid price.");
        System.out.println("End of the program.");
    }
}