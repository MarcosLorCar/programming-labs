package Rafa;

import java.util.Scanner;
public class session1prog {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        //we declare our variables that we are going to use 
        float t, f, nf;
        int n;
        //ask the price of a ticket, is the ticket is less than 0, there will be an error
        System.out.println("what price?");
        t = KEYBOARD.nextFloat();
        if (t > 0) {
            //ask the number of tickets and again if its less tha 0, an error appears
            System.out.println("number of tickets?");
            n = KEYBOARD.nextInt();
            if (n >= 0) {
                //we calculate the final price
                f = t * n;
                System.out.printf("%.2f\n",f);
                System.out.println("with discount is: ");
                //we calculate the price with discount and print the final result
                nf = f * 8/10;
                System.out.printf("%.2f",nf);
            }
            else{
                System.out.println("error");
            }
        }
        else{
            System.out.println("error");
        }

    }
}
