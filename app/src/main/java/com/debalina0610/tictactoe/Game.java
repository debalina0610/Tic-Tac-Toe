package com.debalina0610.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener {

    public static final String PL1 = "Player1", PL2 = "Player2";
    private TextView player1, player2;
    private String p1, p2;
    private Button playagain;
    private Button home;
    private TextView playerturn;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    int playerX = 0, playerO = 1;
    int player = playerX;
    private boolean activegame = true;

    int[] position = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playagain = findViewById(R.id.playagain);
        home = findViewById(R.id.homep);
        player1 = findViewById(R.id.p1);
        player2 = findViewById(R.id.p2);
        playerturn = findViewById(R.id.turn);

        Intent intent = getIntent();
        p1 = intent.getStringExtra(PL1);
        player1.setText(p1);

        p2 = intent.getStringExtra(PL2);
        player2.setText(p2);

        playerturn.setText(p1+"'s turn");

        btn0 = findViewById(R.id.b0);
        btn1 = findViewById(R.id.b1);
        btn2 = findViewById(R.id.b2);
        btn3 = findViewById(R.id.b3);
        btn4 = findViewById(R.id.b4);
        btn5 = findViewById(R.id.b5);
        btn6 = findViewById(R.id.b6);
        btn7 = findViewById(R.id.b7);
        btn8 = findViewById(R.id.b8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartgame();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homepage();
            }
        });
    }

    public void homepage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(!activegame)
            return;

        Button clickedbtn = findViewById(view.getId());
        int clicktag = Integer.parseInt(view.getTag().toString());

        if(position[clicktag] != -1)
            return;

        position[clicktag] = player;

        if(player == playerO) {
            clickedbtn.setText("O");
            clickedbtn.setBackground(getDrawable(android.R.color.holo_orange_dark));
            player = playerX;
            playerturn.setText(p1+"'s turn");
        }
        else{
            clickedbtn.setText("X");
            clickedbtn.setBackground(getDrawable(android.R.color.holo_green_dark));
            player = playerO;
            playerturn.setText(p2+"'s turn");
        }
        winner();
    }

    private void winner(){
        int [][] winningpos = { {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i=0; i<8; i++){
            int val0 = winningpos[i][0];
            int val1 = winningpos[i][1];
            int val2 = winningpos[i][2];

            if(position[val0] == position[val1] && position[val1] == position[val2] && position[val0] != -1){
                activegame = false;
                if(position[val0] == playerX)
                    playerturn.setText(p1 + " is Winner!");
                else if(position[val0] == playerO)
                    playerturn.setText(p2 + " is Winner!");
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void restartgame(){
        player = playerX;
        playerturn.setText(p1+"'s turn");
        position = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(R.drawable.button));
        btn1.setBackground(getDrawable(R.drawable.button));
        btn2.setBackground(getDrawable(R.drawable.button));
        btn3.setBackground(getDrawable(R.drawable.button));
        btn4.setBackground(getDrawable(R.drawable.button));
        btn5.setBackground(getDrawable(R.drawable.button));
        btn6.setBackground(getDrawable(R.drawable.button));
        btn7.setBackground(getDrawable(R.drawable.button));
        btn8.setBackground(getDrawable(R.drawable.button));


        activegame = true;
    }

}