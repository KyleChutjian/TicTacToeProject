package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/*
Kyle Chutjian
2/18/2021
TicTacToe App
*/

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Log.d("StartActivity", "StartGame Method");
        EditText text = (EditText) findViewById(R.id.inputName);
        String playerName = text.getText().toString();
        Intent intent = new Intent(this, PlayScreen.class);
        if (playerName.equalsIgnoreCase("Type your Name Here:")) {
            playerName = "Player";
        }
        intent.putExtra("playerName", playerName);

        startActivity(intent);



    }
}