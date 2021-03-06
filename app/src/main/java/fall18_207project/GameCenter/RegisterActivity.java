package fall18_207project.GameCenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegisterActivity extends AppCompatActivity implements ValidateFormActivity{
    private FirebaseAuth firebaseAuth;
    private String emailValue;
    private String passwordValue;
    private  String userNameValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        addRegisterButtonListener();
    }

    /**
     * on click, register the account if username does not exist, else
     * notify the user that userName already exists.
     */
    private void addRegisterButtonListener() {
        Button register = findViewById(R.id.confirmRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmail = findViewById(R.id.EmailRegister);
                EditText editPassword = findViewById(R.id.passwordregister);
                EditText editUserName = findViewById(R.id.Usernameregister);
                emailValue = editEmail.getText().toString();
                passwordValue = editPassword.getText().toString();
                userNameValue = editUserName.getText().toString();
                createAccount();
            }
        });
    }

    private void createAccount(){
        if(validateForm()){
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(emailValue, passwordValue).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("RegisterActivity", "Successful!");
                    Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();

                    CurrentAccountController.getAccountManager().addAccount(new Account(emailValue, userNameValue, passwordValue));
                    CurrentAccountController.writeData(RegisterActivity.this);

                    Intent gotoLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(gotoLogin);
                }
                else{
                    Log.w("RegisterActivity", "sign up failed", task.getException());
                    Toast.makeText(RegisterActivity.this, "sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void loadFromFile(String fileName) {
//
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) input.readObject();
//                inputStream.close();
//            }
//        } catch (FileNotFoundException e) {
//            Log.e("Register activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("Register activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("Register activity", "File contained unexpected data type: " + e.toString());
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

    /**
     * partially cited from https://firebase.google.com/docs/auth/android/password-auth
     * @return return whether the form is validated
     */
    public boolean validateForm() {
        boolean valid = true;
        EditText emailValue = findViewById(R.id.EmailRegister);
        String email = emailValue.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailValue.setError("Required.");
            valid = false;
        } else {
            emailValue.setError(null);
        }

        EditText passwordValue = findViewById(R.id.passwordregister);
        String password = passwordValue.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordValue.setError("Required.");
            valid = false;
        } else {
            passwordValue.setError(null);
        }

        EditText userNameValue = findViewById(R.id.Usernameregister);
        String userName = userNameValue.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            userNameValue.setError("Required.");
            valid = false;
        } else {
            userNameValue.setError(null);
        }

        return !valid;
    }

}