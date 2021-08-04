import java.io.IOException;
import java.util.Scanner;

public class Menu {

    static FileManager file = new FileManager();
    public static Scanner scanIn = new Scanner(System.in);
    static AccountManager account = new AccountManager();

    public static void main(String[] args) throws IOException {
        MenuLogic();
    }

    static void MenuLogic() throws IOException {

        double amount;
        boolean loop = true;
        int choice;
        String username ;
        String password ;
        System.out.println("Enter Username");
        username = scanIn.nextLine();

        System.out.println("Enter Password");
        password = scanIn.nextLine();

            try{
                //file.TextLoadFile("TestFile.txt");
                file.TextLoadFile("LogInfo_User_"+username+".txt", username, password);
            }
            catch(Exception ex){
                System.out.println("Username not found");
                loop = false;
            }

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
                        account.AccountLogic(account.mySavings, Math.abs(amount), 1, 1);
                    }
                    if(amount == 2) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.myChecking, Math.abs(amount), 2, 1);
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
                        account.AccountLogic(account.mySavings, -Math.abs(amount), 1, 1);
                    }
                    if(amount == 2) {
                        amount = scanIn.nextDouble();
                        account.AccountLogic(account.myChecking, -Math.abs(amount), 2, 1);
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
                        file.UpdateFile(account);
                        file.TextWriteFile("LogInfo_User_"+username+".txt", 1);
                        //file.TextWriteFile("TestFile.txt", 0);
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