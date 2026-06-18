/*
MARCOS LORO CARRASCO
IGNACIO ROMERO TORRES
RAFAEL CAMILO RUBIO QUINTERO
GONZALO SOBRINO DE LA FLOR
*/
import java.util.Scanner;
public class Lab3 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        float ticket, finalticket1, finalticket2;
        int number, age, minors = 0,adults = 0;
        System.out.println("price of a ticket?");
        ticket = KEYBOARD.nextFloat();
        while (ticket <= 0) {
            System.out.println("price of a ticket?");
            ticket = KEYBOARD.nextFloat();
            if (ticket > 0) {
                break;
            }
        }
        System.out.println("number of tickets?");
        number = KEYBOARD.nextInt();
        while (number <= 0) {
            System.out.println("error, " + "number of tickets?");
            number = KEYBOARD.nextInt();
        } 

        for (int i = 1; i<=number; i++){
            System.out.println("Age of the passenger number: "+i);
            age = KEYBOARD.nextInt();
            if (age < 18) {
                minors ++;
            }
            else{
                adults ++;
            }
            while (i>=number && minors == number) {
                System.out.println("debe haber un pasajero mayor de 18");
                i = 0;
                minors = 0;
            }
        }
        finalticket1 = (adults + minors) * ticket;
        System.out.printf("here is your price: " + "%.2f\n", finalticket1);
        if (minors > 0) {
            if (minors == number) {
                System.out.println("error");
            }
            else{
                finalticket2 = minors * 0.8f;
                finalticket1 = (float) (ticket * adults * finalticket2);
                System.out.printf("minors come with a discount resulting in a price of: %.2f\n", finalticket1); 
            }
        }
        if (minors + adults > 12) {
            if (minors>0) {
                finalticket2 = minors * 8/10;
                finalticket1 = finalticket2 * ticket * adults * 9/10;
                System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",finalticket1); 
            }
            else{
                finalticket2 = minors * 8/10;
                finalticket1 = finalticket2 * ticket * adults * 9/10;
                System.out.printf("applying a discount for minors and a discount for more than 12 tickets, here is the price: "+ "%.2f",finalticket1);
            }
        }
    }   
}
