package fall18_207project.GameCenter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*** the main view gamecentre activity. Show all games and the user profie, connect to scoreboards.
 *
 */

public class GameCentreActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

//    public static String userEmail = "";
    private FirebaseAuth firebaseAuth;
//    private AccountManager accountManager;
    private GameCentreController mController = new GameCentreController(GameCentreActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_centre);

        firebaseAuth = FirebaseAuth.getInstance();
        setViewProfile();
        setNavigationView();
        addSlidingTilesGameButtonListener();
        addMatchingCardsGameButtonListener();
        addGame2048ButtonListener();
        showProfile();
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null){
            Intent gotoLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(gotoLogin);
        }
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        readFromSer(LoginActivity.ACCOUNT_MANAGER_DATA);
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setNavigationView(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_game_center:
                    break;
                case R.id.navigation_user_history:
                    Intent tmp2 = new Intent(GameCentreActivity.this, UserHistoryActivity.class);
                    startActivity(tmp2);

                    break;
                case R.id.navigation_global_scoreboard:
                    Intent tmp3 = new Intent(GameCentreActivity.this, GlobalScoreBoardActivity.class);
                    startActivity(tmp3);
                    break;
            }
            return false;
        }
    };

    private void setViewProfile(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private Handler myHand = new Handler()
    {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what ==0){
                showProfile();
            }
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_image)
            showImageChooseDialog();
         else if (id == R.id.nav_intro)
            showEditProfileByDialog(id,"Editing Information" );
        else if (id == R.id.nav_reset)
            showEditProfileByDialog(id, "Editing User Name");
        else if (id == R.id.nav_password)
            showEditProfileByDialog(id,"Editing Password" );
        else if (id == R.id.nav_logout)
            switchToLogin();
        myHand.sendEmptyMessageDelayed(0, 1000);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showEditProfileByDialog(int id, String profTitle){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GameCentreActivity.this);
        dialogBuilder.setTitle(profTitle);
        final int i = id;
        @SuppressLint("InflateParams") final View profView = LayoutInflater.from(GameCentreActivity.this).
                inflate(R.layout.profile_dialog, null);
        dialogBuilder.setView(profView);
        dialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener(){
            @Override
                  public void onClick(DialogInterface dialogNeeded, int buttonId){
                EditText update = (EditText)profView.findViewById(R.id.update);
                mController.updateProfile( i, update.getText().toString());
//                saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
            }
                });
        dialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogNeeded, int buttonId){
                        dialogNeeded.dismiss();
                    }
                });
        dialogBuilder.create().show();

    }

    public void showImageChooseDialog(){
        AlertDialog.Builder imageDialog =
                new AlertDialog.Builder(GameCentreActivity.this);
        imageDialog.setTitle("Choose Image ").setMessage("Choose the image you want:");
        imageDialog.setNeutralButton("paul",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mController.updateImage(R.drawable.paulorange1);
//                        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
                    }
                });
        imageDialog.setNegativeButton("lindsey",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mController.updateImage(R.drawable.lindsey);
//                        saveToFile(LoginActivity.ACCOUNT_MANAGER_DATA);
                    }
                });
        imageDialog.setPositiveButton("david", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mController.updateImage(R.drawable.david);
            }
        });
        imageDialog.show();
    }

    // TODO: Change to showProfile()
    private void showProfile(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);
        TextView textUser = navHeader.findViewById(R.id.profileUser);
        if (CurrentAccountController.getCurrAccount() != null) {

            textUser.setText(CurrentAccountController.getCurrAccount().getUserName());

            TextView textIntro = navHeader.findViewById(R.id.profileIntro);
            textIntro.setText(CurrentAccountController.getCurrAccount().getProfile().getIntro());

            ImageView userImg = navHeader.findViewById(R.id.profileImg);
            userImg.setImageResource(CurrentAccountController.getCurrAccount().getProfile().getAvatarId());

            TextView textPlayTime = navHeader.findViewById(R.id.profileTime);
            textPlayTime.setText("Play Time in Total: " +
                    CurrentAccountController.getCurrAccount().getProfile().getTotalPlayTime());
        }

    }

    private void addSlidingTilesGameButtonListener() {

        ImageButton ButtonGame = findViewById(R.id.SlidingTileGame);
        ButtonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSlidingTilesStartingAct();
            }
        });
    }

    private void addMatchingCardsGameButtonListener() {

        ImageButton ButtonGame = findViewById(R.id.MatchingCardsGame);
        ButtonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToMatchingCardsStartingAct();
            }
        });
    }

    private void addGame2048ButtonListener() {

        ImageButton ButtonGame = findViewById(R.id.Game2048);
        ButtonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame2048StartingAct();
            }
        });
    }

    private void switchToSlidingTilesStartingAct() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
//        tmp.putExtra("userEmail", userEmail);
        startActivity(tmp);
    }

    private  void switchToMatchingCardsStartingAct(){
        Intent tmp = new Intent(this, MatchingCardStartActivity.class);
//        tmp.putExtra("userEmail", userEmail);
        startActivity(tmp);
    }

    private void switchToGame2048StartingAct(){
        Intent tmp = new Intent(this, Game2048StartActivity.class);
//        tmp.putExtra("userEmail", userEmail);
        startActivity(tmp);
    }

    private void switchToLogin() {
        firebaseAuth.signOut();
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

//    private void readFromSer(String fileName) {
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream in = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) in.readObject();
//                mController = new GameCentreController(accountManager, userEmail);
//            }
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            Log.e("GameCentre activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("GameCentre activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("GameCentre activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
//
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
