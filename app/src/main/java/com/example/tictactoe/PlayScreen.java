package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
Kyle Chutjian
2/18/2021
TicTacToe App
*/

public class PlayScreen extends AppCompatActivity {

    private final int ROW = 5;
    private final int COL = 5;
    private int[][] board = new int[ROW][COL];
    private ArrayList<Integer> currentMoves = new ArrayList<Integer>();
    private boolean isGameOver = false;
    private Button[] buttons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        String playerName = getIntent().getStringExtra("playerName");
        TextView nameText = (TextView) findViewById(R.id.playerName);
        nameText.setText(playerName);
    }

    public void onClick(View view) {
        Button button = (Button) findViewById(view.getId());
        int location = Integer.valueOf(button.getTag().toString());
        if (!currentMoves.contains(location)) {
            currentMoves.add(location);
            setMove(location,1);
            if (checkForWinner()==1) {
                // Player wins
                TextView playerText = (TextView) findViewById(R.id.playerScore);
                playerText.setText(Integer.toString((Integer.valueOf(playerText.getText().toString()) + 1)));
                clearBoard();
            } else {
                // Computer goes
                int computerMove = getComputerMove();
                if (computerMove!=100) {
                    setMove(computerMove, 2);
                }
                if (checkForWinner()==2) {
                    // Computer Wins
                    TextView computerText = (TextView) findViewById(R.id.computerScore);
                    computerText.setText(Integer.toString((Integer.valueOf(computerText.getText().toString()) + 1)));
                    clearBoard();
                }
            }
        }
    }

    public void setMove(int location, int player) {
        board[location / ROW][location % COL] = player;
        buttons = new Button[]{
                (Button) findViewById(R.id.button0),
                (Button) findViewById(R.id.button1),
                (Button) findViewById(R.id.button2),
                (Button) findViewById(R.id.button3),
                (Button) findViewById(R.id.button4),
                (Button) findViewById(R.id.button5),
                (Button) findViewById(R.id.button6),
                (Button) findViewById(R.id.button7),
                (Button) findViewById(R.id.button8),
                (Button) findViewById(R.id.button9),
                (Button) findViewById(R.id.button10),
                (Button) findViewById(R.id.button11),
                (Button) findViewById(R.id.button12),
                (Button) findViewById(R.id.button13),
                (Button) findViewById(R.id.button14),
                (Button) findViewById(R.id.button15),
                (Button) findViewById(R.id.button16),
                (Button) findViewById(R.id.button17),
                (Button) findViewById(R.id.button18),
                (Button) findViewById(R.id.button19),
                (Button) findViewById(R.id.button20),
                (Button) findViewById(R.id.button21),
                (Button) findViewById(R.id.button22),
                (Button) findViewById(R.id.button23),
                (Button) findViewById(R.id.button24),
        };
        if (player==1) buttons[location].setText("X");
        if (player==2) buttons[location].setText("O");
        currentMoves.add(location);
    }

    public int getComputerMove() {
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
        for (int i = 0; i < 25; i++) {
            if (!currentMoves.contains(i)) {
                possibleMoves.add(i);
            }
        }

        if (!possibleMoves.isEmpty()) {
            Collections.shuffle(possibleMoves);
            currentMoves.add(possibleMoves.get(0));
            return possibleMoves.get(0);
        } else {
            return 100;
        }

    }

    public int checkForWinner() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j]==1) {
                    System.out.println(i + "," + j);
                    if (i+3<ROW && board[i+1][j]==1 && board[i+2][j]==1 && board[i+3][j] == 1) {
                        System.out.println("Vertical win at: " + i + ", " + j);
                        Toast.makeText(PlayScreen.this,"Congratulations, You Won! ", Toast.LENGTH_LONG).show();
                        return 1;
                    } else if (j+3<COL && board[i][j+1]==1 && board[i][j+2]==1 && board[i][j+3] == 1) {
                        Toast.makeText(PlayScreen.this,"Congratulations, You Won! ", Toast.LENGTH_LONG).show();
                        return 1;
                    } else if (i+3<ROW && j+3<COL && board[i+1][j+1]==1 && board[i+2][j+2]==1 && board[i+3][j+3]==1) {
                        Toast.makeText(PlayScreen.this,"Congratulations, You Won! ", Toast.LENGTH_LONG).show();
                        return 1;

                    } else if (i>2 && j+3<COL && board[i-1][j+1]==1 && board[i-2][j+2]==1 && board[i-3][j+3]==1) {
                        Toast.makeText(PlayScreen.this,"Congratulations, You Won! ", Toast.LENGTH_LONG).show();
                        return 1;
                    }

                }
                if (board[i][j]==2) {
                    if (i+3<ROW && board[i+1][j]==2 && board[i+2][j]==2 && board[i+3][j] == 2) {
                        Toast.makeText(PlayScreen.this,"The Computer Won, Try Again!", Toast.LENGTH_LONG).show();
                        return 2;
                    } else if (j+3<COL && board[i][j+1]==2 && board[i][j+2]==2 && board[i][j+3] == 2) {
                        Toast.makeText(PlayScreen.this,"The Computer Won, Try Again!", Toast.LENGTH_LONG).show();
                        return 2;
                    } else if (i+3<ROW && j+3<COL && board[i+1][j+1]==2 && board[i+2][j+2]==2 && board[i+3][j+3]==2) {
                        Toast.makeText(PlayScreen.this,"The Computer Won, Try Again!", Toast.LENGTH_LONG).show();
                        return 2;
                    } else if (i>2 && j+3<COL && board[i-1][j+1]==2 && board[i-2][j+2]==2 && board[i-3][j+3]==2) {
                        Toast.makeText(PlayScreen.this,"The Computer Won, Try Again!", Toast.LENGTH_LONG).show();
                        return 2;
                    }
                }
            }

        }



        return 0;
    }


    public void reset(View view) {
            clearBoard();
            TextView playerScore = (TextView) findViewById(R.id.playerScore);
            TextView computerScore = (TextView) findViewById(R.id.computerScore);
            playerScore.setText("0");
            computerScore.setText("0");
        }

    public void clearBoard() {
        if (buttons != null && buttons.length != 0) {
            System.out.println("RESET");
            for (Button button : buttons) {
                button.setText("");
            }
            currentMoves = new ArrayList<Integer>();
            board = new int[ROW][COL];
        }
    }
}