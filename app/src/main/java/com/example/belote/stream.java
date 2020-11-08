package com.example.belote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class stream extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        TextView score1 = findViewById(R.id.scoreT1);
        TextView score2 = findViewById(R.id.scoreT2);
        TextView affCode= findViewById(R.id.aff);
        EditText codeStream = findViewById(R.id.code);
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Games");
        codeStream.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int code = Integer.parseInt(codeStream.getText().toString());
                codeStream.setText("");
                if (isNetworkConnected()) {
                    Toast toast = Toast.makeText(stream.this, "Connected", Toast.LENGTH_SHORT);
                    toast.show();
                    affCode.setText("code : "+String.valueOf(code));
                    reff.child("game" + String.valueOf(code)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Map<String, String> map = (Map<String, String>) snapshot.getValue();
                                String s1 = map.get("score1");
                                String s2 = map.get("score2");
                                score1.setText(s1);
                                score2.setText(s2);
                            }else {
                                Toast toast = Toast.makeText(stream.this, "Code incorrect", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast toast = Toast.makeText(stream.this, "Connection Failed...", Toast.LENGTH_SHORT);
                    toast.show();
                }

                return false;
            }
        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}