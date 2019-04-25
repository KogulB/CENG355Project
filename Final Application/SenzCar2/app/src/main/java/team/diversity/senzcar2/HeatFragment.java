package team.diversity.senzcar2;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HeatFragment extends Fragment
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        final View v = inflater.inflate(R.layout.fragment_heat, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getInstance().getReference().child("Thermal");
        reference.addValueEventListener(new ValueEventListener()
        {
            RelativeLayout relativeLayout = v.findViewById(R.id.heatLayout);
            TextView textView = relativeLayout.findViewById(R.id.heatValue);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String aVal = ds.getValue().toString();
                    final float myVal = Math.round(Float.parseFloat(aVal));
                    if(myVal <= 35)
                    {

                        relativeLayout.setBackgroundResource(R.drawable.gradient1);
                        textView.setText(""+myVal+" Degrees Celsius");
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        relativeLayout.setBackgroundResource(R.drawable.gradient3);
                        textView.setText(""+myVal+" Degrees Celsius");
                        textView.setVisibility(View.VISIBLE);
                    }
                    final Button button1 = v.findViewById(R.id.heatButton);
                    button1.setOnClickListener(new View.OnClickListener()
                    {
                        int aVal = 1;
                        @Override
                        public void onClick(View v)
                        {

                            if(button1.isEnabled() && aVal == 1)
                            {
                                double myFran = (myVal * 1.8) + 32;
                                relativeLayout.setBackgroundResource(R.drawable.gradient1);
                                textView.setText(myFran + " Degrees Fahrenheit");
                                button1.setText("Celsius");
                                aVal++;
                            }
                            else
                            {
                                if(myVal <= 35)
                                {
                                    relativeLayout.setBackgroundResource(R.drawable.gradient1);
                                    textView.setText(""+myVal+ " Degrees Celsius");
                                    textView.setVisibility(View.VISIBLE);
                                }
                                else
                                {
                                    relativeLayout.setBackgroundResource(R.drawable.gradient3);
                                    textView.setText(""+myVal+" Degrees Celsius");
                                    textView.setVisibility(View.VISIBLE);
                                }
                                button1.setText("Farenheit");
                                aVal--;
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
        return v;
    }
}
