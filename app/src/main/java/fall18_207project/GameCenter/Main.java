//package fall18_207project.GameCenter;
//
//import android.util.Log;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//class Main {
//    private final String APP_DATA = "app_data.ser";
//    private AccountManager accountManager;
//
//    Main() {
//        loadAppData();
//    }
//
//    private void loadAppData() {
//        try {
//            FileInputStream fileIn = new FileInputStream(APP_DATA);
//            BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
//            ObjectInputStream objIn = new ObjectInputStream(bufferIn);
//
//            accountManager = (AccountManager) objIn.readObject();
//
//            objIn.close();
//            bufferIn.close();
//            fileIn.close();
//        } catch (FileNotFoundException e) {
//            Log.e("Main", "Cannot find serialization file: " + e.toString());
//            accountManager = new AccountManager();
//        } catch (IOException e) {
//            Log.e("Main", "Cannot read serialization file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("Main", "Serialization file contained unexpected data type: " + e.toString());
//
//        }
//    }
//
//    void saveAppData() {
//        try {
//            FileOutputStream fileOut = new FileOutputStream(APP_DATA);
//            BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
//            ObjectOutputStream objOut = new ObjectOutputStream(bufferOut);
//
//            objOut.writeObject(accountManager);
//
//            objOut.close();
//            bufferOut.close();
//            fileOut.close();
//        } catch (FileNotFoundException e) {
//            Log.e("Main", "Cannot find serialization file: " + e.toString());
//            accountManager = new AccountManager();
//        } catch (IOException e) {
//            Log.e("Main", "Cannot write to serialization file: " + e.toString());
//        }
//    }
//
//
//    AccountManager getAccountManager() {
//        return accountManager;
//    }
//}
