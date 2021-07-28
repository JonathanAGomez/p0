import javax.swing.event.MenuListener;
import java.util.Scanner;

public class AccountManager {

    static Account myAcc = new Account();
    static Account.Check_Save_Account mySavings = Account.Check_Save_Account.Savings;
    static Account.Check_Save_Account myChecking = Account.Check_Save_Account.Checkings;
    static void Registration(String usern, String pass, int flag){
        if(flag == 1)
            myAcc.Login(usern, pass);
        if(flag == 2)
            myAcc.RegisterAccount(usern, pass);
    }

    //Flag 0 marks account creation
    //Flag 1 marks existing account
    static void AccountLogic(Account.Check_Save_Account check,double amount, int accType, int actionFlag){

        if(actionFlag == 0){
            myAcc.CreateAccount(check, amount, accType);
        }
        if(actionFlag == 1){
            myAcc.AdjustAccount(check, amount, accType);
        }
    }

    static void DisplayBalance(){
        System.out.println("\n\nAccount Balances");
        System.out.println("Savings:  " + mySavings.amount);
        System.out.println("Checking: " + myChecking.amount);
    }

}