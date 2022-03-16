package com.example.chattingpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingInActivity extends AppCompatActivity {
    private static final String TAG = "SingInActivity";
    private FirebaseAuth mAuth;

    private EditText emailEditText, nameEditText, passEditText, repeatPassEditText;
    private TextView toggleSingUpLogIn;
    private Button singUpLogInButton;

    private boolean isLoginModeActive;

    FirebaseDatabase database;
    DatabaseReference usersDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        usersDatabaseReference = database.getReference().child("users");

        emailEditText = findViewById(R.id.emailEditText);
        nameEditText = findViewById(R.id.nameEditText);
        passEditText = findViewById(R.id.passEditText);
        repeatPassEditText = findViewById(R.id.repeatPassEditText);
        toggleSingUpLogIn = findViewById(R.id.toLoginTextView);
        singUpLogInButton = findViewById(R.id.singuploginBtn);

        singUpLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInSingUpUser(emailEditText.getText().toString().trim(),
                        passEditText.getText().toString().trim());
            }
        });

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(SingInActivity.this, UserListActivity.class));
        }
    }

    private void logInSingUpUser(String email, String password) {

        if (isLoginModeActive) {
            if (passEditText.getText().toString().trim().length() < 7) {
                Toast.makeText(this, "Пароль должен быть длиннее 7-ми символов", Toast.LENGTH_SHORT).show();
            } else if (emailEditText.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Введите E-mail", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(SingInActivity.this, UserListActivity.class);
                                    intent.putExtra("userName", nameEditText.getText().toString().trim());
                                    startActivity(intent);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SingInActivity.this, "Авторизация не прошла",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        } else {
            if (!passEditText.getText().toString().trim().equals(
                    repeatPassEditText.getText().toString().trim())) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            } else if (passEditText.getText().toString().trim().length() < 7) {
                Toast.makeText(this, "Пароль должен быть длиннее 7-ми символов", Toast.LENGTH_SHORT).show();
            } else if (emailEditText.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Введите E-mail", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    createUser(user);
                                    Intent intent = new Intent(SingInActivity.this, UserListActivity.class);
                                    intent.putExtra("userName", nameEditText.getText().toString().trim());
                                    startActivity(intent);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SingInActivity.this, "Авторизация не прошла",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        }
    }

    private void createUser(FirebaseUser firebaseUser) {
        User user = new User();
        user.setUserID(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setName(nameEditText.getText().toString().trim());
        user.setProfileImageURL("   ");

        usersDatabaseReference.push().setValue(user);
    }

    public void toogleLoginMode(View view) {
        if (isLoginModeActive) {
            isLoginModeActive = false;
            singUpLogInButton.setText("Зарегистрироваться");
            toggleSingUpLogIn.setText("Нажмите, чтобы войти");
            repeatPassEditText.setVisibility(View.VISIBLE);
        } else {
            isLoginModeActive = true;
            repeatPassEditText.setVisibility(View.GONE);
            singUpLogInButton.setText("Войти");
            toggleSingUpLogIn.setText("Нажмите, чтобы зарегистрироваться");
        }
    }

}