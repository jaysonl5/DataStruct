package edu.uco.jlewis30.p3jaysonl;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends Activity{


    private TextView txtStatus;
    //private final int[] tileID = { R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    //private final Button[] tile = { (Button) findViewById(R.id.btn1) , (Button) findViewById(R.id.btn2), R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private Button[] tile;
    private Button btnLog;
    private Button btnNew;
    private String playerTurn = "O";
    private boolean gameOver = false;
    private int turnCounter = 0;
    ArrayList<String> results;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = (TextView) findViewById(R.id.txtStatus);

        results = new ArrayList<>();

        tile = new Button[9];

        for (int i = 0; i < 9; ++i) {
            String b = "btn" + (i + 1);
            tile[i] = (Button) findViewById(getResources().getIdentifier("btn" + (i + 1), "id", getPackageName()));
            tile[i].setTextSize(30);
            tile[i].setOnClickListener(new TileListener());
        }

        btnLog = (Button) findViewById(R.id.btnLog);
        btnNew = (Button) findViewById(R.id.btnNew);

        btnLog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                intent.putStringArrayListExtra("results", results);
                startActivity(intent);

            }
        });

        btnNew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTiles();
                turnCounter = 0;
                playerTurn = "O";
            }
        });

    }

    public void nextTurn()
    {
        if("O".equals(playerTurn)) {
            playerTurn = "X";
            txtStatus.setText(R.string.strXTurn);
        } else {
            playerTurn = "O";
            txtStatus.setText(R.string.strOTurn);
        }

    }

    public boolean checkWin() {
        return checkTiles(tile[0], tile[1], tile[2]) ||
        checkTiles(tile[3], tile[4], tile[5]) ||
        checkTiles(tile[6], tile[7], tile[8]) ||
        checkTiles(tile[0], tile[3], tile[6]) ||
        checkTiles(tile[1], tile[4], tile[7]) ||
        checkTiles(tile[2], tile[5], tile[8]) ||
        checkTiles(tile[0], tile[4], tile[8]) ||
        checkTiles(tile[2], tile[4], tile[6]);
    }

    public boolean checkTiles(Button tile1, Button tile2, Button tile3) {
        if(tile1.getText().equals(playerTurn) && tile2.getText().equals(playerTurn) && tile3.getText().equals(playerTurn)) {
            return true;
        } else {
            return false;
        }
    }

    public void clearTiles() {
        for(int i = 0; i < 9; i++) {
            tile[i].setText("");
            tile[i].setEnabled(true);
            txtStatus.setText(R.string.strStartTxt);
        }
    }


    public class TileListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {



            for(int i = 0; i < 9; i++) {

                if (view.getId() == tile[i].getId()) {

                    tile[i].setText(playerTurn);
                    tile[i].setEnabled(false);
                    turnCounter++;

                    checkWin();

                    int size = results.size();

                    if (checkWin() == true) {
                        if (playerTurn.equals("X")) {
                            txtStatus.setText(R.string.strXWin);
                            results.add(" X ");
                        } else {
                            txtStatus.setText(R.string.strOWin);
                            results.add(" O ");
                        }

                        for (int k = 0; k < 9; ++k) {
                            tile[k].setEnabled(false);

                        }

                    } else if (turnCounter == 9) {
                        txtStatus.setText(R.string.strTieGame);
                        for (int k = 0; k < 9; ++k) {
                            tile[k].setEnabled(false);
                        }
                        results.add("TIE");
                    } else {
                        nextTurn();
                    }

                }
            }

        }

    }


}
