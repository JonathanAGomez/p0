import java.util.Scanner;

public class Menu {


    public static void main(String[] args) {
        AccountManager.AccountLogic(AccountManager.mySavings, 500, 0, 0);
        AccountManager.AccountLogic(AccountManager.myChecking, 250, 1, 0);

        MenuLogic();
    }
    static void MenuLogic(){
        Scanner scan = new Scanner(System.in);

        System.out.println("1:Login\n2:Register with new Account");
        int choice = scan.nextInt();
        String username = new String("");
        String password = new String("");
        scan.nextLine();
        {
            System.out.println("Enter Username");
            username = scan.nextLine();
        }

        {
            System.out.println("Enter Password");
            password = scan.nextLine();
        }
        AccountManager.Registration(username, password, choice);

        double amount = 0;
        boolean loop = true;
        while(loop) {
            System.out.println("\nMyBank");
            System.out.println("\nMENU");
            System.out.println("1: Make a Deposit");
            System.out.println("2: Make a Withdrawl");
            System.out.println("3: Check Balance");
            System.out.println("4: EXIT");
            choice = 0;
            amount = 0;
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Which Account:  ");
                    System.out.println("1: Savings");
                    System.out.println("2: Checking");
                    amount = scan.nextDouble();
                    System.out.println("Enter the Deposit Amount: ");
                    if(amount == 1) {
                        amount = scan.nextDouble();
                        AccountManager.AccountLogic(AccountManager.mySavings, Math.abs(amount), 0, 1);
                    }
                    if(amount == 2) {
                        amount = scan.nextDouble();
                        AccountManager.AccountLogic(AccountManager.myChecking, Math.abs(amount), 1, 1);
                    }
                    break;
                case 2:
                    System.out.println("Which Account:  ");
                    System.out.println("1: Savings");
                    System.out.println("2: Checking");
                    amount = scan.nextDouble();
                    System.out.println("Enter the Withdrawl Amount: ");
                    if(amount == 1) {
                        amount = scan.nextDouble();
                        AccountManager.AccountLogic(AccountManager.mySavings, -Math.abs(amount), 0, 1);
                        //myAcc.AdjustAccount(mySavings, -Math.abs(amount), 0);
                    }
                    if(amount == 2) {
                        amount = scan.nextDouble();
                        AccountManager.AccountLogic(AccountManager.myChecking, -Math.abs(amount), 1, 1);
                        //myAcc.AdjustAccount(myChecking, -Math.abs(amount), 1);
                    }
                    break;
                case 3:
                    AccountManager.DisplayBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using MyBank!");
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
    }