package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView=(TextView)findViewById(R.id.createAccoutTxt);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=MainActivity.this;
               // Class createAccout= new Class();
               // Toast.makeText(context,"next page",Toast.LENGTH_SHORT).show();
                Intent createAccout=new Intent(context,CreatAccount.class);
                startActivity(createAccout);
            }
        });
    }
}
