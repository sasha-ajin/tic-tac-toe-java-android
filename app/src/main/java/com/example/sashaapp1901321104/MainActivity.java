package com.example.sashaapp1901321104;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView header_text;
    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int active_player = PLAYER_O;

    int[] fields = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean game_active = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header_text = findViewById(R.id.header_text);
        header_text.setText("O turn");


        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }

    private void check_winner(){
        int[][] winning_positions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i =0 ;i<8;i++){
            int val0  = winning_positions[i][0];
            int val1  = winning_positions[i][1];
            int val2  = winning_positions[i][2];

            if(fields[val0] == fields[val1] && fields[val1] == fields[val2]){
                if(fields[val0] != -1){
                    game_active = false;

                    if(fields[val0] == PLAYER_O) {
                        show_dialog("O is winner");
                        return;
                    }
                    else
                        show_dialog("X is winner");
                        return;
                }
            }
        }
        int counter_felt_in_fields =0;
        for(int i =0 ;i<8;i++){
            if(fields[i] != -1){
                counter_felt_in_fields+=1;
            }
        }
        if(counter_felt_in_fields==8){
            show_dialog("Draw");
        }

    }

    private void show_dialog(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart_game();
                    }
                })
                .show();
    }

    private void restart_game(){
        active_player = PLAYER_O;
        header_text.setText("O turn");
        fields = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        game_active = true;
    }

    @Override
    public void onClick(View v) {
        if(!game_active)
            return;
        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if(fields[clickedTag] != -1){
            return;
        }
        fields[clickedTag] = active_player;
        if(active_player == PLAYER_O){
            clickedBtn.setText("O");
            active_player = PLAYER_X;
            header_text.setText("X turn");
        }else{
            clickedBtn.setText("X");
            active_player = PLAYER_O;
            header_text.setText("O turn");
        }


        check_winner();
    }
}