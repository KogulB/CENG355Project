package team.diversity.senzcar2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataActivity extends AppCompatActivity
{
    FirebaseAuth.AuthStateListener authStateListener;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        fireBaseListen();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_light:
                                selectedFragment = new LightFragment();
                                break;
                        case R.id.nav_pressure:
                            selectedFragment = new TempFragment();
                            break;

                        case R.id.nav_heat:
                            selectedFragment = new HeatFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }


            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_loggedin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.logoutButton:
                createLogOutDialog();
                break;

            case R.id.deleteAccount:

                AlertDialog.Builder dialog = new AlertDialog.Builder(DataActivity.this);
                dialog.setTitle("Are you Sure you want to Delete?");
                dialog.setMessage("Warning!! Deleting account will disable access to your Senz Car access");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReference.removeValue();
                        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(DataActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();


                                }
                                else
                                {
                                    Toast.makeText(DataActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                break;

            case R.id.changePass:
                AlertDialog.Builder myDialog = new AlertDialog.Builder(DataActivity.this);
                myDialog.setTitle("Are you Sure you want to chang your password?");
                myDialog.setMessage("A link will be sent to your account to change your password");
                myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(DataActivity.this, "Passowrd Reset link Sent!", Toast.LENGTH_LONG).show();
                                            FirebaseAuth.getInstance().signOut();
                                        }
                                        else
                                        {
                                            Toast.makeText(DataActivity.this, "Link sending failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                });
                myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog1 = myDialog.create();
                alertDialog1.show();
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    private void fireBaseListen()
    {
        authStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    Toast.makeText(DataActivity.this, "Signed in "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DataActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(authStateListener != null)
        {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onBackPressed()
    {
        createLogOutDialog();
    }
    public void createLogOutDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DataActivity.this);
        dialog.setTitle("Log out?");
        dialog.setMessage("You are about to log out");
        dialog.setPositiveButton("Log Out", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                FirebaseAuth.getInstance().signOut();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


}
