import java.util.Scanner;

public class Lab4 {
    private static final Scanner SC = new Scanner(System.in);
    private static final float MINORS_DISCOUNT = 0.2F;
    private static final float BULK_DISCOUNT = 0.1F;
    private static final int BULK_AMOUNT = 12;
    private static final int SEAT_COUNT = 20;

    public static void main(String[] args) {
        int selectedOption, availableSeats = SEAT_COUNT;
        float ticketPrice = 0;
        char[] seats = new char[SEAT_COUNT];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = 'A';
        }
        boolean exit = false;
        do {
            System.out.print("""
            Menu:
            1-Set ticket price
            2-Purchase tickets
            3-Exit program
            """);
            System.out.print("Enter an option: ");
            selectedOption = SC.nextInt();

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
                            System.out.print("Enter amount of tickets to buy: ");
                            ticketCount = SC.nextInt();
                            if (ticketCount <= 0) {
                                System.out.println("Invalid amount");
                            } else if (ticketCount > availableSeats) {
                                System.out.println("Not enough seats available");
                            }
                        } while (ticketCount <= 0 || ticketCount > availableSeats);

                        availableSeats -= ticketCount;

                        // Get ages
                        int minorCount;
                        do {
                            minorCount = 0;
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

                        // Get seat distribution
                        System.out.println("Current seat occupancy: ");
                        for (int i = 0; i < seats.length; i++) {
                            System.out.print(i+1 + ":" + seats[i] + " ");
                        }
                        System.out.print("\n");

                        for (int i = 0; i < ticketCount; i++) {
                            int seat;
                            boolean validSeat = false;
                            do {
                                System.out.printf("Enter the seat number for passenger %d: ", i+1);
                                seat = SC.nextInt();
                                if (seat <= 0 || seat > seats.length) {
                                    System.out.println("Invalid seat");
                                } else if (seats[seat-1] == 'O') {
                                    System.out.println("Sorry, that seat is currently occupied. Please select another seat.\n");
                                } else {
                                    seats[seat-1] = 'O';
                                    validSeat = true;
                                }
                            } while (!validSeat);
                        }

                        System.out.println("Seat occupancy after the purchase: ");
                        for (int i = 0; i < seats.length; i++) {
                            System.out.print(i+1 + ":" + seats[i] + " ");
                        }
                        System.out.print("\n");

                        // Show prices
                        float price = ticketCount * ticketPrice;
                        System.out.printf("The price of the %d rickets without discounts would be %.2f \n", ticketCount, price);

                        if (minorCount > 0) {
                            float minorsPrice = minorCount * ticketPrice - minorCount * ticketPrice * MINORS_DISCOUNT;
                            price = (ticketCount - minorCount) * ticketPrice + minorsPrice;
                            System.out.printf("The price of the %d tickets after the minors discount is %.2f \n", ticketCount, price);
                        }

                        if (ticketCount > BULK_AMOUNT) {
                            price -= price * BULK_DISCOUNT;
                            System.out.printf("The price of the %d tickets after the bulk discount is %.2f \n", ticketCount, price);
                        }
                    }
                    break;

                case 3:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option\n");
            }
        } while (!exit);
    }
}
