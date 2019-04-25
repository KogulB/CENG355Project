package team.diversity.senzcar2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TempFragment extends Fragment
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.fragment_temp, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getInstance().getReference().child("Pressure");

        reference.addValueEventListener(new ValueEventListener()
        {
            RelativeLayout relativeLayout = v.findViewById(R.id.tempLayout);
            TextView textView = relativeLayout.findViewById(R.id.tempValue);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String aVal = ds.getValue().toString();
                    final double bVal = Math.round(Double.parseDouble(aVal) * 10)/10.0;
                    final double myVal = (Math.round(Double.parseDouble(aVal) * 10)/10.0) *0.0145;
                    if(myVal <= 35)
                    {

                        relativeLayout.setBackgroundResource(R.drawable.gradient1);
                        textView.setText(""+myVal+ " PSI");
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        relativeLayout.setBackgroundResource(R.drawable.gradient2);
                        textView.setText(""+myVal+" PSI");
                        textView.setVisibility(View.VISIBLE);
                    }

                    final Button button = v.findViewById(R.id.franButton);

                        button.setOnClickListener(new View.OnClickListener()
                        {
                            int aVal = 1;
                            @Override
                            public void onClick(View v)
                            {
                                if(button.isEnabled() && aVal == 1)
                                {
                                    relativeLayout.setBackgroundResource(R.drawable.gradient1);
                                    textView.setText( bVal +" HPA");
                                    button.setText("PSI");
                                    aVal++;
                                }
                                else
                                {
                                    if(myVal <= 35)
                                    {
                                        relativeLayout.setBackgroundResource(R.drawable.gradient1);
                                        textView.setText(""+myVal+ " PSI");
                                        textView.setVisibility(View.VISIBLE);
                                    }
                                    else
                                    {
                                        relativeLayout.setBackgroundResource(R.drawable.gradient2);
                                        textView.setText(""+myVal+" PSI");
                                        textView.setVisibility(View.VISIBLE);
                                    }
                                    button.setText("HPA");
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
