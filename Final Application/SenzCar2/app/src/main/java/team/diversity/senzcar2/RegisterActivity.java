package team.diversity.senzcar2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    TextInputLayout editTextFullName, editTextEmail, editTextPassword, editTextReEnterPass, editTextCode;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerB).setOnClickListener(this);

    }

    public void registerUser()
    {
        editTextFullName = findViewById(R.id.name);
        editTextCode = findViewById(R.id.authCode);
        editTextEmail = findViewById(R.id.emailF);
        editTextPassword = findViewById(R.id.passF);
        editTextReEnterPass = findViewById(R.id.reEnterPassF);
         String nameFull = editTextFullName.getEditText().getText().toString().trim();
         String username = editTextEmail.getEditText().getText().toString().trim();
        String password = editTextPassword.getEditText().getText().toString().trim();
        String rePass = editTextReEnterPass.getEditText().getText().toString().trim();
        String authCode = editTextCode.getEditText().getText().toString().trim();
        if(nameFull.isEmpty())
        {
            editTextFullName.setError("Name Required");
            editTextFullName.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches())
        {
           editTextEmail.setError("Please Enter a Valid Email");
           editTextEmail.requestFocus();
           return;
        }
        if(password.isEmpty())
        {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6)
        {
            editTextPassword.setError("Length of password must be 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        if(rePass.isEmpty())
        {
            editTextReEnterPass.setError("Re-Enter Password");
            editTextReEnterPass.requestFocus();
            return;
        }
        if(!rePass.equals(password))
        {
            editTextReEnterPass.setError("Re-Enter Password");
            editTextReEnterPass.requestFocus();
            return;
        }
        if(!authCode.equals("12345"))
        {
            editTextCode.setError("Authorization Code is incorrect");
            editTextCode.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    String user = mAuth.getCurrentUser().getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference currentUser = database.getInstance().getReference().child("Users").child(user);

                    String nameFull = editTextFullName.getEditText().getText().toString().trim();
                    String username = editTextEmail.getEditText().getText().toString().trim();

                    Map newPost = new HashMap();
                    newPost.put("name" , nameFull);
                    newPost.put("email" , username );

                    currentUser.setValue(newPost);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "This user is already registered", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.registerB:
                                registerUser();
                                break;
        }
    }
}
