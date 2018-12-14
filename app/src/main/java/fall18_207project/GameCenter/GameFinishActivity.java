package fall18_207project.GameCenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//test comment here
public class GameFinishActivity extends AppCompatActivity {
//    private AccountManager accountManager;
//    public static String userEmail = "";
    private GameFinishController mController = new GameFinishController(GameFinishActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
        setContentView(R.layout.activity_game_finish);
        setScoreText();
        addReturnButtonListener();
    }

    public void setScoreText(){
        TextView tvResult = findViewById(R.id.textView2);
        String saveId = getIntent().getStringExtra("saveId");
//        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
        tvResult.setText(mController.updateFinishedGame(saveId));
    }

    private void addReturnButtonListener() {
        Button returnToScreen = findViewById(R.id.backToGamebtn);
        returnToScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add helper switchToGameCentre()
                Intent returnToMainScreen = new Intent(getApplicationContext(), GameCentreActivity.class);
                startActivity(returnToMainScreen);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
//        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
        updateCurrAccount();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCurrAccount();
//        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
    }

    private void updateCurrAccount() {
        mController.updateCurrAccount();
    }

//    public void saveToFile(String fileName) {
//        try {
//            ObjectOutputStream outputStream = new ObjectOutputStream(
//                    this.openFileOutput(fileName, MODE_PRIVATE));
//            outputStream.writeObject(accountManager);
//            outputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    private void readFromSer(String fileName) {
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) input.readObject();
//                mController = new GameFinishController(accountManager, userEmail);
//                inputStream.close();
//            }
//        } catch (FileNotFoundException e) {
//            Log.e("GameFinish activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("GameFinish activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("GameFinish activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
}
