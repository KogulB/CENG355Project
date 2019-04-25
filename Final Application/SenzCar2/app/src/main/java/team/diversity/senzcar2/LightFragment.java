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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LightFragment extends Fragment
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.fragment_light, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getInstance().getReference().child("Light");
        reference.addValueEventListener(new ValueEventListener()
        {
            RelativeLayout relativeLayout = v.findViewById(R.id.lightBackground);
            TextView textView = relativeLayout.findViewById(R.id.valueLight);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String aVal = ds.getValue().toString();
                    float myVal = Math.round(Float.parseFloat(aVal));
                    if(myVal <= 100)
                    {

                        relativeLayout.setBackgroundResource(R.drawable.gradient2);
                        textView.setText(""+myVal+ " Lumens");
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        relativeLayout.setBackgroundResource(R.drawable.gradient3);
                        textView.setText(""+myVal+ " Lumens");
                        textView.setVisibility(View.VISIBLE);
                    }

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
