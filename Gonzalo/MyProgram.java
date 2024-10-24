import java.util.Scanner;

public class MyProgram {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        int num1, num2, result;
        System.out.println("Enter the first value: ");
        num1 = KEYBOARD.nextInt();
        System.out.println("Enter the second value: ");
        num2 = KEYBOARD.nextInt();
        result = num1 + num2;
        System.out.println("The result is: " + result);
    }
}
