import java.util.Scanner;
public class Exercise5 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    public static void main(String[] args) {
        char[][] array=new char [5][4];
        int i, j, freeseats=0, number, tickets=0, minors, number2;
        double price;
        System.out.println("Welcome to the train ticket management software");
        System.out.println("The current seat status is as follows:");
        System.out.println("       1  2  3  4");
        for(i=0;i<array.length;i++){
            System.out.printf("Row %d: ", i+1);
            for(j=0;j<4;j++){
                array[i][j]='A';
                System.out.print(array[i][j] + "  ");
            }
            System.out.println("");
        }
        do {
            System.out.println("Enter the exact price per ticket: ");
            price = KEYBOARD.nextDouble();
        } while(price<=0);
        for(i=0;i<array.length;i++){
            for(j=0;j<4;j++){
                if(array[i][j]=='A'){
                    freeseats=freeseats+1;
                }
            }
        }
        System.out.printf("There are %d free seats\n", freeseats);
        do{
            System.out.println("    Choose one of the following options:");
            System.out.println("1. Purchase tickets");
            System.out.println("2. Change tickets");
            System.out.println("3. Show seat status");
            System.out.println("4. Exit the program");
            System.out.println("Please, select one of these options");
            number= KEYBOARD.nextInt();
            switch (number) {
                case 1:
                    do{
                        do {
                            System.out.println("How many tickets do you wish to buy?");
                            tickets= KEYBOARD.nextInt();
                        } while(tickets<=0);  
                        System.out.println("How many tickets are for minors?");
                        minors= KEYBOARD.nextInt();
                    }while(tickets>freeseats);  
                    System.out.println("    Choose a ticket purchase option:");
                    System.out.println("1. Manual selection of N seats");
                    System.out.println("2. Automatic selection of N seats");
                    System.out.println("3. Selection of N adjacent seats");
                    System.out.println("4. Exit purchase menu");
                    System.out.println("Please, select one of these options");
                    number2= KEYBOARD.nextInt(); 
                    do{
                        switch (number2) {
                            case 1:
                                for(i=0;i<array.length;i++){
                                    System.out.printf("Row %d: ", i+1);
                                    for(j=0;j<4;j++){
                                        System.out.print(array[i][j] + "  ");
                                    }
                                    System.out.println("");
                                }
                                System.out.println("Choose your seats");
                                for(int k=0;k<tickets;k++){
                                    System.out.print("Row: ");
                                    i=KEYBOARD.nextInt();
                                    System.out.print("Column: ");
                                    j=KEYBOARD.nextInt();
                                    if(array[i-1][j-1]=='O'){
                                        System.out.println("This seat is occupied, try again");
                                        System.out.print("Row: ");
                                        i=KEYBOARD.nextInt();
                                        System.out.print("Column: ");
                                        j=KEYBOARD.nextInt();
                                    }
                                    array[i-1][j-1]='O';
                                } //entra en bucle por algún motivo el case 1 entero
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Wrong, choose an available option or number 4 if you want to exit this menu");
                                break;
                            }
                    }while(number2!=4);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("ERROR, wrong number, press 1, 2, 3 or 4");
            }
        }while(number!=4 && freeseats!=0);
    }
}
