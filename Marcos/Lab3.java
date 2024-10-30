import java.util.Scanner;

public class Lab3 {
    private static final Scanner SC = new Scanner(System.in);
    private static final float MINORS_DISCOUNT = 0.2F;
    private static final float BULK_DISCOUNT = 0.1F;
    private static final int BULK_AMOUNT = 12;

    public static void main(String[] args) {
        int selectedOption;
        float ticketPrice = 0;
        boolean exit = false;
        do {
            do {
                System.out.print("""
                1-Set ticket price
                2-Purchase tickets
                3-Exit program
                """);
                System.out.print("Enter an option: ");
                selectedOption = SC.nextInt();
                if (selectedOption < 1 || selectedOption > 3) {
                    System.out.println("Invalid option\n");
                }
            } while (selectedOption < 1 || selectedOption > 3);

            switch (selectedOption) {
                case 1:
                    do {
                        System.out.print("Enter ticket price: ");
                        ticketPrice = SC.nextFloat();
                        if (ticketPrice <= 0) {
                            System.out.println("Invalid ticket price");
                        }
                    } while (ticketPrice <= 0);
                    break;
                case 2:
                    if (ticketPrice <= 0) {
                        System.out.println("You have to set a ticket price first.");
                    } else {
                        int ticketCount;
                        do {
                            System.out.print("Enter amount: ");
                            ticketCount = SC.nextInt();
                            if (ticketCount <= 0) {
                                System.out.println("Invalid amount");
                            }
                        } while (ticketCount <= 0);

                        int minorCount = 0;
                        do {
                            for (int i = 0; i < ticketCount; i++) {
                                int age;
                                do {
                                    System.out.printf("Enter passenger #%d age: ", i+1);
                                    age = SC.nextInt();
                                    if (age < 0) {
                                        System.out.println("Invalid age");
                                    }
                                } while (age < 0);
                                if (age<18) minorCount++;
                            }
                            if (ticketCount == minorCount) {
                                System.out.println("Error, There needs to be at least one adult");
                            }
                        } while (ticketCount == minorCount);

                        System.out.printf("There are %d minors\n", minorCount);

                        float price = ticketCount * ticketPrice;
                        System.out.printf("The price without discounts would be %.2f€\n", price);

                        if (minorCount > 0) {
                            float minorsPrice = minorCount * ticketPrice - minorCount * ticketPrice * MINORS_DISCOUNT;
                            price = (ticketCount - minorCount) * ticketPrice + minorsPrice;
                            System.out.printf("The price after the minors discount is %.2f€\n", price);
                        }

                        if (ticketCount > BULK_AMOUNT) {
                            price -= price * BULK_DISCOUNT;
                            System.out.printf("The price after the bulk discount is %.2f€", price);
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
