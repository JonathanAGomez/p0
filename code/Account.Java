import java.lang.String;

@lombok.extern.slf4j.Slf4j
public class Account{

    private String _Username;
    private String _Password;
    private int _AccountID;


    //For this example of a banking app, I will only focus on basic Checking and Saving accounts
    //I can adjust the structure to allow for other account types in the future
    public enum Check_Save_Account {
        Savings(000.00),
        Checkings(000.00);

        double amount;

        Check_Save_Account(double amountToAdjust){
            this.amount = amountToAdjust;
        }
    }
    Check_Save_Account[] myAccount = new Check_Save_Account[]{};
    double overdraftLim = 200.00;

    void Account(){

    }

    public void RegisterAccount(String usern, String pass) {
        //Check database for duplicate entries on username
        _Username = usern;
        //Complexity algorithm for password strength?
        //Perform basic encryption
        _Password = pass;
        //Generate unique Account ID

        //Attempt Login once registration is done

        log.info("Registration successful for "+_Username);
        Login(usern, pass);
    }

    public boolean Login(String usern, String pass){

        boolean connected = true;
        //Find if user exists
        if(connected){
            //Make connection and grant access
            log.info("Login Successful");
            _Username = usern;
            _Password = pass;
            return true;
        }
        else{
            //Print out account with username and password does not exist
            log.info("Account does not exist");
            return false;
        }
    }

    //Create a new account with and initial amount
    //the flag specifies if it is a checking or a savings account
    public void CreateAccount(Check_Save_Account acc, double initAmount,int flag) {
        int newLen = myAccount.length + 1;
        acc.amount = AdjustAccount(acc, initAmount, flag);
        //Empty Account
        if (newLen == 1) {
            myAccount = new Check_Save_Account[newLen];
            myAccount[0] = acc;
        }
        //Non-Empty Account
        else {
            Check_Save_Account[] data = myAccount;
            myAccount = new Check_Save_Account[newLen];
            System.arraycopy(data, 0, myAccount, 0, myAccount.length - 2);
            myAccount[newLen - 1] = acc;
        }
    }

    //Adjust the passed in account based on the passed in amount
    //Negative Values are withdrawals
    //Positive Values are deposits
    //Will not allow a negative balance for savings account
    //Will warn of overdraft in a checking account
    public double AdjustAccount(Check_Save_Account type, double amount, int flag) {
        double adjust = type.amount + amount;
        //Handle cases where adjustment would result in a negative balance.
        //Reject adjustment for savings accounts
        //Calculate overdraft for checking accounts
        if(adjust < 0)
        {
            for (int i = 0; i < myAccount.length; i++){
                //Flag for Savings Account
                if(flag == 0){
                    //Log error, reject adjustment of negative balance to savings account

                    return type.amount;
                }
                //Flag for Checking Account
                if(flag == 1){
                    if(adjust <= overdraftLim){
                        //Overdraft limit reached. Reject adjustment and output response

                        return type.amount;
                    }
                    //Log activity. Warn of overdraft

                    return type.amount;
                }
            }
            //Cannot have negative balance. Reject adjustment. Return original amount of account
            //throw exception / log error

            return type.amount;
        }
        //If a negative value is input, it is a withdrawal
        //If a positive value is input, it is a deposit
        type.amount += amount;
        return type.amount;
        //Log activity for an itemized receipt
    }
}