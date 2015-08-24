package com.example.eran.tic_tac_toe_test_3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    boolean turn = true; // true = X וגם false = O
    int turn_count = 0;
    Button[] bArray = null;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1 = (Button) findViewById(R.id.A1);
        b1 = (Button) findViewById(R.id.B1);
        c1 = (Button) findViewById(R.id.C1);
        a2 = (Button) findViewById(R.id.A2);
        b2 = (Button) findViewById(R.id.B2);
        c2 = (Button) findViewById(R.id.C2);
        a3 = (Button) findViewById(R.id.A3);
        b3 = (Button) findViewById(R.id.B3);
        c3 = (Button) findViewById(R.id.C3);
        bArray = new Button[] { a1, a2, a3, b1, b2, b3, c1, c2, c3 };

        for (Button b : bArray)
            b.setOnClickListener(this);

        Button bnew = (Button) findViewById(R.id.button1);
        bnew.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                turn = true;
                turn_count = 0;
                enableOrDisable(true);
            }
        });
    }

    @Override
    public void onClick(View v) {

        buttonClicked(v);
    }

    private void buttonClicked(View v) //מתודה זו קובעת את סדר התורות וקובעת ש-X מתחיל ראשון ושהקליק הבא יהיה של עיגול
    {
        Button b = (Button) v;
        if (turn) {
            // התור של שחקן X
            b.setText("X");

        } else {
            // התור של שחקן O
            b.setText("O");
        }
        turn_count++;
        b.setClickable(false);// מונע לחיצה נוספת על הכפתור ובכך נמנע החלפה נוספת מאיקס לעיגול ולהפך
        b.setBackgroundColor(Color.LTGRAY); // כשהכפתור נלחץ יש רקע לבן סביב הסימן (איקס/עיגול)
        turn = !turn; //המשתנה turn הופך לשקר ומגיע תור העיגול

        checkForWinner();
    }

    private void checkForWinner() { //המתודה בודקת מי מי ניצח

        boolean there_is_a_winner = false;

        // בודק בצורה אופקית מי ניצח:
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (b1.getText() == b2.getText() && b2.getText() == b3.getText()
                && !b1.isClickable())
            there_is_a_winner = true;
        else if (c1.getText() == c2.getText() && c2.getText() == c3.getText()
                && !c1.isClickable())
            there_is_a_winner = true;

            // בודק בצורה אנכית מי ניצח:
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (a2.getText() == b2.getText() && b2.getText() == c2.getText()
                && !b2.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b3.getText() && b3.getText() == c3.getText()
                && !c3.isClickable())
            there_is_a_winner = true;

            // בודק בצורה אלכסונית מי ניצח:
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText()
                && !b2.isClickable())
            there_is_a_winner = true;


        if (there_is_a_winner) {
            if (!turn)
                message("שחקן X ניצח");
            else
                message("שחקן O ניצח");
            enableOrDisable(false);
        } else if (turn_count == 9)
            message("תיקו!");

    }

    private void message(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
                .show();
    }

    private void enableOrDisable(boolean enable) {
        for (Button b : bArray) {
            b.setText("");
            b.setClickable(enable);
            if (enable) {
                b.setBackgroundColor(Color.parseColor("#33b5e5"));
            } else {
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}