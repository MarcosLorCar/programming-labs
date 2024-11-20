import java.util.Scanner;

public class Exercise4 {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) {
        char[] seats= new char [20];
        int number, tickets, available=0, minors;
        double price=0, age, total;
        do{
            System.out.println("1. Establish the price for the train ticket");
            System.out.println("2. Purchase tickets");
            System.out.println("3. Exit the program");
            System.out.println("Please, select one of these options");
            number= KEYBOARD.nextInt();
            switch (number) {
                case 1:
                do {
                    System.out.println("Enter the exact price per ticket: ");
                    price = KEYBOARD.nextDouble();
                } while(price<=0);     
                    break;
                case 2:
                if(price != 0){
                    do{
                        System.out.println("Enter the amount of tickets desired: ");
                        tickets = KEYBOARD.nextInt();
                        while(tickets<=0) {
                            System.out.println("ERROR, retry...");
                            System.out.println("Please, enter the amount of tickets desired: ");
                            tickets = KEYBOARD.nextInt();
                        }
                        for(int i=0;i<seats.length;i++){
                            seats[i]='A';
                            available=available+1;
                        }
                    } while(available>tickets);
                    do{
                        minors=0;
                        for(int count=1;count<=tickets;count++) {
                            System.out.printf("How old is passenger %d?\n", count);
                            age = KEYBOARD.nextDouble();
                            int overage=0;
                            if(18>age){
                                minors=minors+1;
                            }
                            else{
                                overage=overage+1;
                            }
                        }
                        if(minors >= tickets){
                            System.out.println("ERROR, there can't be only minors, retry...");
                        }
                    } while(minors >= tickets);
                    System.out.printf("There are %d tickets for minors\n", minors);
                    System.out.println("This are the seats of the train");
                    for(int m=0;m<seats.length;m++){
                        System.out.print(seats[m]+" ");
                    }
                    System.out.println("Which seats do you wish to select?");
                    for(int n=0;n<tickets;n++){
                        System.out.println("Ticket "+n+": ");
                        int i;
                        do{
                            i=KEYBOARD.nextInt();
                            if(seats[i]=='O'){
                                System.out.println("This seat is occupied, choose a different one");
                                i=KEYBOARD.nextInt();
                                seats[i]='O';
                            }
                            seats[i]='O';
                        } while(seats[i]=='O');
                    }
                    System.out.println("This are the seats of the train");
                    for(int p=0;p<seats.length;p++){
                        System.out.print(seats[p]+" ");
                    }
                    total = price * tickets;
                    System.out.printf("The total price with no discounts is %.2f\n", total);        
                    if(minors!=0) {
                        total = price * tickets - price * 0.2 * minors;
                        System.out.printf("The total price with a 20 per cent discount per kid is %.2f\n", total);
                    }
                    if(tickets>12){
                        total = total - 0.1 * total;
                        System.out.printf("The total price with a 10 per cent extra discount for being a big group is %.2f", total);
                    }
                }
                    else{
                        System.out.println("The price of the ticktes must be established first");
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("ERROR, wrong number, press 1, 2 or 3");
            }
        } while(number!=3);
    }
}

