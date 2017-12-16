package com.example.lesgo.a3t1;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    final Context context = this;
    Boolean dialogOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restoreState(savedInstanceState);

        if(dialogOpen == true)
        {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle(R.string.starry);

            ImageView img = (ImageView) dialog.findViewById(R.id.ImageView);
            img.setImageResource(R.drawable.sky);

            dialog.getWindow().setBackgroundDrawableResource(R.color.black);

            Button btn2 = (Button)dialog.findViewById(R.id.dialogBtn);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialogOpen = false;

                }
            });
            dialog.show();

        }

        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle(R.string.starry);

                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView);
                img.setImageResource(R.drawable.sky);

                dialog.getWindow().setBackgroundDrawableResource(R.color.black);

                Button btn2 = (Button)dialog.findViewById(R.id.dialogBtn);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialogOpen = false;

                    }
                });

                dialog.show();
                dialogOpen = true;
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("dialog",dialogOpen);

        super.onSaveInstanceState(outState);
    }

    public void restoreState(Bundle bundle)
    {
        if(bundle== null)
            return ;

        dialogOpen = (bundle.getBoolean("dialog"));

    }
}
