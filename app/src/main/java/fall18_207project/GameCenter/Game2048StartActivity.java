package fall18_207project.GameCenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game2048StartActivity extends AppCompatActivity implements
        MultiLoadStartActivity, GameStartingActivity{
//    public static String userEmail = "";
//    private AccountManager accountManager;
    private Game2048StartController mController = new Game2048StartController(Game2048StartActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
        setContentView(R.layout.activity_game2048_starting);

        addLoadGameButtonListener();
        addNewGameButtonListener();
        addLogOutButtonListener();
        addReturnToGameCenterListener();
        setUserTextView();
    }

    private void setUserTextView(){
        TextView account = findViewById(R.id.Hiuser);
        account.setText(mController.setUserTextViewTest());
    }


    /**
     * Activate new 2048 game.
     */
    private void addNewGameButtonListener() {
        Button Button4 = findViewById(R.id.StartButton);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
                switchToGame( mController.addGameInAcc(mController.createGame()).getSaveId());
            }
        });
    }

    /**
     * Activate the 4x4 new game board.
     */
    private void addLoadGameButtonListener(){
        Button Button4 = findViewById(R.id.loadGameButton);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadDialog();
            }
        });
    }

    /**
     * Log out of the current Account.
     */
    private void addLogOutButtonListener() {
        Button LogoutButton = findViewById(R.id.logOutBtn);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });

    }

    private void addReturnToGameCenterListener() {
        Button returnToGameCenter = findViewById(R.id.button);
        returnToGameCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGameCentre();
            }
        });
    }

    public void showLoadDialog(){
        AlertDialog.Builder loadDialog =
                new AlertDialog.Builder(Game2048StartActivity.this);
        loadDialog.setTitle("Load Game ").setMessage("Load From...");

        loadDialog.setNeutralButton("Load Saved game",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switchToSaveGames();
                    }
                });
        loadDialog.setNegativeButton("Load AutoSaved game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switchToAutoSaveGames();
            }
        });
        loadDialog.show();
    }

    /**
     * Switch to the Game2048Activity view to play the game.
     */
    public void switchToGame(String saveId) {
//        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
        Intent tmp = new Intent(this, Game2048Activity.class);
        tmp.putExtra("saveId", saveId);
        tmp.putExtra("saveType", "autoSave");
        startActivity(tmp);
    }

    public void switchToGameCentre() {
        Intent backToGameCenter = new Intent(getApplicationContext(), GameCentreActivity.class);
        startActivity(backToGameCenter);
    }

    public void switchToSaveGames(){

        Intent goToSavedGames = new Intent(getApplicationContext(), SavedGamesActivity.class);
        goToSavedGames.putExtra("saveType", "userSave");
        goToSavedGames.putExtra("gameType", "game2048");
        startActivity(goToSavedGames);
    }

    public void switchToAutoSaveGames(){

        Intent goToSavedGames = new Intent(getApplicationContext(), SavedGamesActivity.class);
        goToSavedGames.putExtra("saveType", "autoSave");
        goToSavedGames.putExtra("gameType", "game2048");
        startActivity(goToSavedGames);

    }

    public void switchToLogin() {
        mController.userSignOut();
        Intent tmp = new Intent(this, LoginActivity.class);
//        tmp.putExtra("userEmail", userEmail);
        startActivity(tmp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateCurrAccount();
        switchToGameCentre();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCurrAccount();

    }

    @Override
    protected void onStop() {
        super.onStop();
        updateCurrAccount();
    }

    private void updateCurrAccount() {
        mController.updateCurrAccount();
    }

//    private void readFromSer(String fileName) {
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream in = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) in.readObject();
//                mController = new Game2048StartController(accountManager, userEmail);
//            }
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            Log.e("Game2048Start activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("Game2048Start activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("Game2048Start activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
//
//    /**
//     * Save the accountmanager to fileName.
//     *
//     * @param fileName the name of the file
//     */
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
}
