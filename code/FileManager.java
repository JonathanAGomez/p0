import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    //Structure for a file will be
    //AccountID
    //Username
    //Password
    //Number of accounts (2 for the example)
    //Account 1 Type
    //Account 1 Balance
    //Account 2 Type
    //Account 2 Balance
    int id = 0;
    String user = "";
    String pass = "";
    int count = 2;

    enum AccountContainer {
        _account;
        int _type;
        double _amount;
    }

    int[] types = new int[2];
    double[] amts = new double[2];

    void TextLoadFile(String fileName, String _User, String _Pass) throws IOException {
        try {
            InputStream stream = new FileInputStream("E:\\Revature\\Work\\Projects\\p0\\code\\" + fileName);
            InputStreamReader streamIn = new InputStreamReader(stream, StandardCharsets.UTF_8);

            id = streamIn.read();
            StringBuilder sb = new StringBuilder();
            int c = 0;
            while((c = streamIn.read()) != 45){
                sb.append((char) c);
            }
            user = sb.toString();
            sb = new StringBuilder();
            while((c = streamIn.read()) != 45){
                sb.append((char) c);
            }
            pass = sb.toString();

            boolean entryFound = true;

            //I would perform a DB check to see if we have an entry for this user and pass combo
            if(entryFound)
                Menu.account.Registration(user, pass, 1);
            else
                return;
            count = streamIn.read();
            for (int i = 0; i < count; i++) {
                int type = streamIn.read();
                double balance = streamIn.read();
                types[i] = type;
                amts[i] = balance;
                if(type == 1)
                    Menu.account.AccountLogic(Menu.account.mySavings, balance, type, 0);
                else
                    Menu.account.AccountLogic(Menu.account.myChecking, balance, type, 0);
            }
        } catch (Exception ex) {
            System.out.println("Could not find file");
        }

    }

    void TextWriteFile(String fileName, int test) throws IOException {

        OutputStream out = new FileOutputStream("E:\\Revature\\Work\\Projects\\p0\\code\\" + fileName);
        OutputStreamWriter outStream = new OutputStreamWriter(out, StandardCharsets.UTF_8);

        //Reset Code
        if(test == 0) {
            outStream.write(403);
            outStream.write("John" + "-");
            outStream.write("1234" + "-");
            outStream.write(2);
            outStream.write(1);
            outStream.write(500);
            outStream.write(2);
            outStream.write(400);
        }


        else {
            outStream.write(id);
            outStream.write(user+"-");
            outStream.write(pass+"-");
            outStream.write(count);
            for (int i = 0; i < count; i++) {
                outStream.write(types[i]);
                outStream.write((int) amts[i]);
            }
        }

        outStream.close();
    }

    void UpdateFile(AccountManager acc){
        id = acc.myAcc.GetID();
        user = acc.myAcc.GetUser();
        pass = acc.myAcc.GetPass();
        count = acc.myAcc.Count();
        for (int i = 0; i < count; i++) {
            if(acc.myAcc.myAccount.get(i) == acc.mySavings) {
                types[i] = 1;
                amts[i] = acc.mySavings.amount;
            }
            else
            {
                types[i] = 2;
                amts[i] = acc.myChecking.amount;

            }
        }
    }
}