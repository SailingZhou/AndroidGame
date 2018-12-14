package fall18_207project.GameCenter;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

public abstract class GameStartController {
//    String userEmail;
//    AccountManager accountManager;
    FirebaseAuth firebaseAuth;
    protected Context mContext;

    GameStartController(Context context){
//        this.accountManager = accManager;
//        this.userEmail = User;
        this.mContext = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    String setUserTextViewTest(){
        if (CurrentAccountController.getCurrAccount() != null) {
            return "Hi, " + CurrentAccountController.getCurrAccount().getUserName();
        }
        return "";
    }

    void userSignOut(){
        firebaseAuth.signOut();
    }

    void updateCurrAccount() {
        CurrentAccountController.writeData(mContext);
    }

    Game addGameInAcc(Game game){
        CurrentAccountController.getCurrAccount().getAutoSavedGames().addGame(game);
        updateCurrAccount();
        return game;
    }
}
