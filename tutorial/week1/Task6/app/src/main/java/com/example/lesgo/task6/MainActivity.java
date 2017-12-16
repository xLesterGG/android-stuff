package com.example.lesgo.task6;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int index=0;
    int question;
    TextView txtAns;
    TextView txtQ;
    private Question[] Q1 = new Question[]{
            new Question(R.string.oceans,true),
            new Question(R.string.mideast,false),
            new Question(R.string.africa,false),
            new Question(R.string.america,true),
            new Question(R.string.asia,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtAns = (TextView)findViewById(R.id.txtAns);
        txtQ = (TextView)findViewById(R.id.txtQ);

        question = Q1[index].getmQuestion();
        txtQ.setText(question);


        Button btnT = (Button)findViewById(R.id.btnT);
        Button btnF = (Button)findViewById(R.id.btnF);
        Button btnN = (Button)findViewById(R.id.btnN);

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q1[index].isAnswer()== true)
                    txtAns.setText("Correct");
                else
                    txtAns.setText("Wrong");

            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q1[index].isAnswer()== false)
                    txtAns.setText("Correct");
                else
                    txtAns.setText("Wrong");

            }
        });


        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                question = Q1[index].getmQuestion();
                txtQ.setText(question);
            }
        });




    }
}
