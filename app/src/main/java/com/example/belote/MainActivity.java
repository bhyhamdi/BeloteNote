package com.example.belote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button belote = findViewById(R.id.belote);
        Button capote = findViewById(R.id.capote);
        Button daklet = findViewById(R.id.daklet);
        Button beloteE = findViewById(R.id.beloteE);
        Button capoteE = findViewById(R.id.capoteE);
        Button dakletE = findViewById(R.id.dakletE);
        Button newgame = findViewById(R.id.newgame);
        TextView totale = findViewById(R.id.total);
        TextView totaleE = findViewById(R.id.totalE);
        EditText score = findViewById(R.id.score);
        ArrayList<Integer> scoreT1 = new ArrayList<>();
        ArrayList<Integer> scoreT2 = new ArrayList<>();
        scoreT1.add(0);
        scoreT2.add(0);
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.clear();
                scoreT1.add(0);
                scoreT2.clear();
                scoreT2.add(0);
                totale.setText("");
                totaleE.setText("");

            }
        });
        belote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 2);
                totale.setText("");
                totale.append(affiche(scoreT1));
            }
        });
        beloteE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 2);
                totaleE.setText("");
                totaleE.append(affiche(scoreT2));
            }
        });
        capote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 50);
                totale.setText("");
                totale.append(affiche(scoreT1));

            }
        });
        capoteE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 50);
                totaleE.setText("");
                totaleE.append(affiche(scoreT2));

            }
        });
        daklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT1.add(scoreT1.get(scoreT1.size() - 1) + 16);
                totale.setText("");
                totale.append(affiche(scoreT1));
            }
        });
        dakletE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 16);
                totaleE.setText("");
                totaleE.append(affiche(scoreT2));

            }
        });

        score.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int scoreAdded = Integer.parseInt(score.getText().toString());
                if (scoreAdded < 0) {
                    scoreT1.add(scoreT1.get(scoreT1.size() - 1) + scoreAdded * -1);
                    totale.setText("");
                    totale.append(affiche(scoreT1));
                    scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 17 - (scoreAdded * -1));
                    totaleE.setText("");
                    totaleE.append(affiche(scoreT2));
                    score.setText("");

                } else {
                    scoreT1.add(scoreT1.get(scoreT1.size() - 1) + scoreAdded);
                    totale.setText("");
                    totale.append(affiche(scoreT1));
                    scoreT2.add(scoreT2.get(scoreT2.size() - 1) + 16 - (scoreAdded));
                    totaleE.setText("");
                    totaleE.append(affiche(scoreT2));
                    score.setText("");

                }

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
                Log.d("liste", liste.get(i).toString() + "**");
                if (liste.get(i - 1) > liste.get(i)) {
                    ch = ch + "---" + "\n";
                    ch = ch + liste.get(i).toString() + "\n";
                } else {
                    ch = ch + liste.get(i).toString() + "\n";
                }
            }
        } else {
            for (int i = liste.size() - 18; i < liste.size(); i++) {
                Log.d("liste", liste.get(i).toString() + "**");
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
}