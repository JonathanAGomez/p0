import java.util.ArrayList;

public class Account{

    private String _Username;
    private String _Password;
    private int _AccountID;

    public String GetUser(){return _Username;}
    public String GetPass(){return _Password;}
    public int GetID(){return _AccountID;}
    java.util.logging.Logger log = java.util.logging.Logger.getLogger("Account.class");
    //For this example of a banking app, I will only focus on basic Checking and Saving accounts
    //I can adjust the structure to allow for other account types in the future
    public enum Check_Save_Account {
        Savings,
        Checkings;

        double amount;
    }
    public ArrayList<Check_Save_Account> myAccount = new ArrayList<Check_Save_Account>();
    public int Count(){return myAccount.size();}
    //Check_Save_Account[] myAccount = new Check_Save_Account[]{};
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
        double seed = Math.random() + 0.1;
        _AccountID = (int) ((Math.random() + 0.1) * 1000 * seed);
        //if _AccountId exists somewhere else in the DB, seed it differently

        //Attempt Login once registration is done

        log.info("Registration successful for "+_Username);
        Login(usern, pass);
    }

    public boolean Login(String usern, String pass){

        boolean connected = true;
        //Find if user exists
        if(connected){
            //Make connection and grant access
            _Username = usern;
            _Password = pass;
            log.info("Login Successful for "+_Username);
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
        log.info("Account created");
        acc.amount = AdjustAccount(acc, initAmount, flag);
        myAccount.add(acc);
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
            for (int i = 0; i < myAccount.size(); i++){
                //Flag for Savings Account
                if(flag == 1){
                    //Log error, reject adjustment of negative balance to savings account
                    log.info("WARNING! Balance cannot be a negative amount");
                    return type.amount;
                }
                //Flag for Checking Account
                if(flag == 2){
                    if(adjust <= overdraftLim){
                        //Overdraft limit reached. Reject adjustment and output response
                        log.info("WARNING! Balance cannot be a negative amount");
                        return type.amount;
                    }
                    //Log activity. Warn of overdraft
                    log.info("WARNING! Balance cannot be a negative amount");
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
        //Log activity for an itemized receipt
        log.info("Account "+type+" : "+type.amount);
        return type.amount;

    }
}