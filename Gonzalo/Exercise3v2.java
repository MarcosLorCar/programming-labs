import java.util.Scanner;

public class Exercise3v2 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    
    public static void main(String[] args) {
        int number;
        double price;
        do{
            number= KEYBOARD.nextInt();
            switch (number) {
                case 1:
                do {
                    System.out.println("Enter the exact price per ticket: ");
                    price = KEYBOARD.nextDouble();
                    } while(price<=0);     
                    break;
            
                case 2:
                    break;
                
                case 3:
                    break;
            }
        }   while();
    }
}
