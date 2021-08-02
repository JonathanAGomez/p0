import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    enum AccountContainer {
        _account;
        int _type;
        double _amount;
    }
    ArrayList<AccountContainer> accList = new ArrayList<AccountContainer>();
    void TextLoadFile(String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (InputStream in = Files.newInputStream(Paths.get(fileName));
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int id = reader.read();
            String user = reader.readLine();
            String pass = reader.readLine();
            int count = reader.read();
            for(int i = 0; i < count; i++){
                int type = reader.read();
                double balance = reader.read();
                AccountContainer tempAcc = null;
                tempAcc._type = type;
                tempAcc._amount = balance;

                accList.add(tempAcc);
            }
            //All of the relevant data has been read in, now we process it
        }
        catch (IOException x) {

        }


    }

    void TextWriteFile(String fileName){

    }
}