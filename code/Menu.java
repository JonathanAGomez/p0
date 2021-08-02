import java.util.Scanner;

public class Menu {

    static AccountManager account = new AccountManager();
    public static void main(String[] args) {

        account.AccountLogic(account.mySavings, 500, 0, 0);
        account.AccountLogic(account.myChecking, 500, 0, 0);

        MenuLogic();
    }
    static void MenuLogic(){
        Scanner scan = new Scanner(System.in);

        System.out.println("1:Login\n2:Register with new Account");
        int choice = scan.nextInt();
        String username ;
        String password ;
        scan.nextLine();

        System.out.println("Enter Username");
        username = scan.nextLine();

        System.out.println("Enter Password");
        password = scan.nextLine();

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
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Which Account:  ");
                    System.out.println("1: Savings");
                    System.out.println("2: Checking");
                    System.out.println("3: BACK");
                    amount = scan.nextDouble();
                    System.out.println("Enter the Deposit Amount: ");
                    if(amount == 1) {
                        amount = scan.nextDouble();
                        account.AccountLogic(account.mySavings, Math.abs(amount), 0, 1);
                    }
                    if(amount == 2) {
                        amount = scan.nextDouble();
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
                    amount = scan.nextDouble();
                    System.out.println("Enter the Withdrawal Amount: ");
                    if(amount == 1) {
                        amount = scan.nextDouble();
                        account.AccountLogic(account.mySavings, -Math.abs(amount), 0, 1);
                    }
                    if(amount == 2) {
                        amount = scan.nextDouble();
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
                    amount = scan.nextDouble();
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