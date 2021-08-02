import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    public static Scanner scanIn = new Scanner(System.in);
    static AccountManager account = new AccountManager();
    public static void main(String[] args) throws FileNotFoundException {

        //FileManager file = new FileManager();
        //file.TextLoadFile("TestFile.txt");
        account.AccountLogic(account.mySavings, 500, 0, 0);
        account.AccountLogic(account.myChecking, 500, 0, 0);

        MenuLogic();
    }
    static void MenuLogic(){


        System.out.println("1:Login\n2:Register with new Account");
        int choice = scanIn.nextInt();
        String username ;
        String password ;
        scanIn.nextLine();

        System.out.println("Enter Username");
        username = scanIn.nextLine();

        System.out.println("Enter Password");
        password = scanIn.nextLine();

        account.Registration(username, password, choice);

        double amount;
        boolean loop = true;
        while(loop) {
            System.out.println("\nMyBank");
            System.out.println("\nMENU");
            System.out.println("1: Make a Deposit");
            System.out.println("2: Make a Withdrawal");
            System.out.println("3: Check Balance");
            System.out.println("4: Sign Out");
            amount = 0;
            choice = scanIn.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Which Account:  ");
                    System.out.println("1: Savings");
                    System.out.println("2: Checking");
                    System.out.println("3: BACK");
                    amount = scanIn.nextDouble();
                    System.out.println("Enter the Deposit Amount: ");
                    if(amount == 1) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.mySavings, Math.abs(amount), 0, 1);
                    }
                    if(amount == 2) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.myChecking, Math.abs(amount), 1, 1);
                    }
                    else
                        continue;

                    break;
                case 2:
                    System.out.println("Which Account:  ");
                    System.out.println("1: Savings");
                    System.out.println("2: Checking");
                    System.out.println("3: BACK");
                    amount = scanIn.nextDouble();
                    System.out.println("Enter the Withdrawal Amount: ");
                    if(amount == 1) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.mySavings, -Math.abs(amount), 0, 1);
                    }
                    if(amount == 2) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.myChecking, -Math.abs(amount), 1, 1);
                    }
                    else
                        continue;

                    break;
                case 3:
                    account.DisplayBalance();
                    break;
                case 4:
                    System.out.println("Are you sure you want to sign out?\n1: Yes\n2: No");
                    amount = scanIn.nextDouble();
                    if(amount == 1){
                        System.out.println("Thank you for using MyBank!");
                        loop = false;
                    }
                    else
                        continue;
                default:
                    break;
            }
        }
    }
    }