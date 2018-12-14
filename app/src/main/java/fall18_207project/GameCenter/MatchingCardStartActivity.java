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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MatchingCardStartActivity extends AppCompatActivity implements
        MultiLoadStartActivity, GameStartingActivity {
//    private AccountManager accountManager;
//    public static String userEmail = "";
    private MatchingCardStartController mController = new MatchingCardStartController(MatchingCardStartActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
        setContentView(R.layout.activity_matching_card_starting);

        addLoadGameButtonListener();
        addNewGameButtonListener();
        addLogOutButtonListener();
        addReturnToGameCenterListener();
        setUserTextView();
    }

    // TODO: Rename Hiuser to hi_User
    // TODO: Generalize this
    private void setUserTextView(){
        TextView account = findViewById(R.id.Hiuser);
        account.setText(mController.setUserTextViewTest());
    }

    /**
     * Activate the Start button.
     */
    private void addNewGameButtonListener(){
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewGameDialog();
            }
        });
    }

    /**
     * Activate the load button.
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
                // TODO: Implement helper function switchToGameCentre()
                Intent backToGameCenter = new Intent(getApplicationContext(), GameCentreActivity.class);
                startActivity(backToGameCenter);
            }
        });
    }


    public void showLoadDialog(){
        AlertDialog.Builder loadDialog =
                new AlertDialog.Builder(MatchingCardStartActivity.this);
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


    public void showNewGameDialog(){
        AlertDialog.Builder newGameDialog =
                new AlertDialog.Builder(MatchingCardStartActivity.this);
        newGameDialog.setTitle("New Game ").setMessage("Choose the complexity you want:");
        newGameDialog.setNeutralButton("4 x 4",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switchGameByComplexity(4);
                    }
                });
        newGameDialog.setNegativeButton("5 X 5",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switchGameByComplexity(5);
                    }
                });
        newGameDialog.setPositiveButton("6 X 6", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switchGameByComplexity(6);
            }
        });
        newGameDialog.show();
    }

public void switchGameByComplexity(int num){
//    readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
    switchToGame( mController.addGameInAcc(mController.createGame(num)).getSaveId());

}

    /**
     * Switch to the MatchingCardsGameActivity view to play the game.
     */
    public void switchToGame(String saveId) {
//        saveToSer(LoginActivity.ACCOUNT_MANAGER_DATA);
        Intent tmp = new Intent(this, MatchingCardsGameActivity.class);
//        tmp.putExtra("userEmail", userEmail);
        tmp.putExtra("saveId", saveId);
        tmp.putExtra("saveType", "autoSave");
        startActivity(tmp);
    }

    public void switchToSaveGames(){

        Intent goToSavedGames = new Intent(getApplicationContext(), SavedGamesActivity.class);
        goToSavedGames.putExtra("saveType", "userSave");
        goToSavedGames.putExtra("gameType", "matchingCards");
        startActivity(goToSavedGames);
    }

    public void switchToAutoSaveGames(){

        Intent goToSavedGames = new Intent(getApplicationContext(), SavedGamesActivity.class);
        goToSavedGames.putExtra("saveType", "autoSave");
        goToSavedGames.putExtra("gameType", "matchingCards");
        startActivity(goToSavedGames);

    }

    public void switchToLogin() {
        mController.userSignOut();
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateCurrAccount();
        // TODO: Implement switchToGameCentre()
        Intent goToCenter = new Intent(getApplicationContext(), GameCentreActivity.class);
//        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
        startActivity(goToCenter);
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

//
//    /**
//     * Load the accountmanager from fileName.
//     *
//     * @param fileName the name of the file
//     */
//
//    private void readFromSer(String fileName) {
//
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) input.readObject();
//                mController = new MatchingCardStartController(accountManager, userEmail);
//                inputStream.close();
//            }
//        } catch (FileNotFoundException e) {
//            Log.e("MatchingCardsStart activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("MatchingCardsStart activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("MatchingCardsStart activity", "File contained unexpected data type: " + e.toString());
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
//
//    /**
//     * Save the accountmanager fileName.
//     *
//     * @param fileName the name of the file
//     */
//    public void saveToSer(String fileName) {
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
