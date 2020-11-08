package com.example.belote;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static Games games;
    DatabaseReference reff;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();


        Button belote = findViewById(R.id.belote);
        Button capote = findViewById(R.id.capote);
        Button daklet = findViewById(R.id.daklet);
        Button beloteE = findViewById(R.id.beloteE);
        Button capoteE = findViewById(R.id.capoteE);
        Button dakletE = findViewById(R.id.dakletE);
        Button newgame = findViewById(R.id.newgame);
        TextView totale = findViewById(R.id.total);
        TextView totaleE = findViewById(R.id.totalE);
        TextView id = findViewById(R.id.id);
        EditText score = findViewById(R.id.score);
        ArrayList<Integer> scoreT1 = new ArrayList<>();
        ArrayList<Integer> scoreT2 = new ArrayList<>();
        scoreT1.add(0);
        scoreT2.add(0);
        reff = FirebaseDatabase.getInstance().getReference().child("Games");
        newgame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openStream();
                return false;
            }
        });
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.clear();
                scoreT1.add(0);
                scoreT2.clear();
                scoreT2.add(0);
                totale.setText("");
                totaleE.setText("");
                i = rand.nextInt(999);
                id.setText(String.valueOf(i));
                HashMap hashMap = new HashMap();

                hashMap.put("score1", "0");
                hashMap.put("score2", "0");
                reff.child("game" + i).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast toast = Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, "erreur to added", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });


            }
        });
        belote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 2);
                totale.setText(affiche(scoreT1));
                HashMap hashMap = new HashMap();
                hashMap.put("score1", affiche(scoreT1));
                reff.child("game" + i).updateChildren(hashMap);


            }
        });
        beloteE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 2);
                totaleE.setText(affiche(scoreT2));
                HashMap hashMap = new HashMap();
                hashMap.put("score2", affiche(scoreT2));
                reff.child("game" + i).updateChildren(hashMap);
            }
        });
        capote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 50);
                totale.setText(affiche(scoreT1));
                HashMap hashMap = new HashMap();
                hashMap.put("score1", affiche(scoreT1));
                reff.child("game" + i).updateChildren(hashMap);

            }
        });
        capoteE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 50);
                totaleE.setText(affiche(scoreT2));
                HashMap hashMap = new HashMap();
                hashMap.put("score2", affiche(scoreT2));
                reff.child("game" + i).updateChildren(hashMap);

            }
        });
        daklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 16);
                totale.setText(affiche(scoreT1));
                HashMap hashMap = new HashMap();
                hashMap.put("score1", affiche(scoreT1));
                reff.child("game" + i).updateChildren(hashMap);
            }
        });
        dakletE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 16);
                totaleE.setText(affiche(scoreT2));
                HashMap hashMap = new HashMap();
                hashMap.put("score2", affiche(scoreT2));
                reff.child("game" + i).updateChildren(hashMap);

            }
        });

        score.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int scoreAdded = Integer.parseInt(score.getText().toString());
                if (scoreAdded < 0) {
                    scoreT1.add(scoreT1.get(scoreT1.size() - 1) + scoreAdded * -1);
                    totale.setText(affiche(scoreT1));
                    scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 17 - (scoreAdded * -1));
                    totaleE.setText(affiche(scoreT2));
                    score.setText("");
                    HashMap hashMap = new HashMap();
                    hashMap.put("score1", affiche(scoreT1));
                    hashMap.put("score2", affiche(scoreT2));
                    reff.child("game" + i).updateChildren(hashMap);

                } else {
                    scoreT1.add(scoreT1.get(scoreT1.size() - 1) + scoreAdded);
                    totale.setText(affiche(scoreT1));
                    scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 16 - (scoreAdded));
                    totaleE.setText(affiche(scoreT2));
                    score.setText("");
                    HashMap hashMap = new HashMap();
                    hashMap.put("score1", affiche(scoreT1));
                    hashMap.put("score2", affiche(scoreT2));
                    reff.child("game" + i).updateChildren(hashMap);

                }

                return false;
            }
        });
    totale.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            scoreT1.remove(scoreT1.size()-1);
            totale.setText(affiche(scoreT1));
            HashMap hashMap = new HashMap();
            hashMap.put("score1", affiche(scoreT1));
            reff.child("game" + i).updateChildren(hashMap);

            return false;
        }
    });
    totaleE.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            scoreT2.remove(scoreT2.size()-1);
            totaleE.setText(affiche(scoreT2));
            HashMap hashMap = new HashMap();
            hashMap.put("score2", affiche(scoreT2));
            reff.child("game" + i).updateChildren(hashMap);
            return false;
        }
    });
    }


    public String affiche(ArrayList<Integer> liste) {
        String ch = "";
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) >= 100) {
                liste.set(i, liste.get(i) - 100);
            }

        }
        if (liste.size() <= 18) {
            for (int i = 1; i < liste.size(); i++) {
                if (liste.get(i - 1) > liste.get(i)) {
                    ch = ch + "---" + "\n";
                    ch = ch + liste.get(i).toString() + "\n";
                } else {
                    ch = ch + liste.get(i).toString() + "\n";
                }
            }
        } else {
            for (int i = liste.size() - 18; i < liste.size(); i++) {
                if (liste.get(i - 1) > liste.get(i)) {
                    ch = ch + "---" + "\n";
                    ch = ch + liste.get(i).toString() + "\n";
                } else {
                    ch = ch + liste.get(i).toString() + "\n";
                }

            }

        }
        return ch;

    }

    //Do finsh this update
    public void openStream() {
        Intent intent = new Intent(this, stream.class);
        startActivity(intent);

    }
}