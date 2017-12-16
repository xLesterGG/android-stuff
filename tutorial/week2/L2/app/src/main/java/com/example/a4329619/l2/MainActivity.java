package com.example.a4329619.l2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editTxt;
    TextView txtView;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.button);
        editTxt=(EditText)findViewById(R.id.editText);
        txtView=(TextView)findViewById(R.id.textView2);

        restoreState(savedInstanceState);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String celcius = editTxt.getText().toString();
                String fsh = convertFah(celcius);
                txtView.setText(fsh);
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView txtView = (TextView)findViewById(R.id.textView2);
        String temp = txtView.getText().toString();
        outState.putString("Fareinheit",temp);
        super.onSaveInstanceState(outState);
    }


    private void restoreState(Bundle bundle){
        if(bundle == null)
            return;

        String fah = bundle.getString("Fareinheit");
        TextView txtView = (TextView)findViewById(R.id.textView2) ;
        txtView.setText(fah);

    }




    private String convertFah(String cel)
    {
        Double c,f=0.0;
        try {
            c = Double.parseDouble(cel);
            f = c * (9.0 / 5.0) + 32.0;
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return String.format("%3.2f",f);

    }









}
