import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

    ArrayList<AccountContainer> accList = new ArrayList<AccountContainer>();

    void TextLoadFile(String fileName) throws IOException {
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
            Menu.account.Registration(user, pass, 1);
            count = streamIn.read();
            for (int i = 0; i < count; i++) {
                int type = streamIn.read();
                double balance = streamIn.read();
                AccountContainer._account._type = type;
                AccountContainer._account._amount = balance;
                accList.add(AccountContainer._account);
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

        ////Now save info
        //Menu.account.Registration(user, pass, 1);
        //for (int i = 0; i < count; i++) {
        //    if (accList.get(i)._type == 0)
        //        Menu.account.AccountLogic(Menu.account.mySavings, accList.get(i)._amount, 0, 0);
        //    if (accList.get(i)._type == 1)
        //        Menu.account.AccountLogic(Menu.account.myChecking, accList.get(i)._amount, 1, 0);
        //}

    }

    String ValueParsing(String input) {
        String test;
        ArrayList<Character> newString = new ArrayList<Character>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                newString.add(input.charAt(i));
            }
        }
        test = newString.toString();
        return test;
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
                //This isnt writing out properly, only saves the latest out
                outStream.write(types[i]);
                outStream.write((int) amts[i]);
                //outStream.write(accList.get(i)._type);
                //outStream.write((int)accList.get(i)._amount);
            }
        }

        outStream.close();
    }
}