package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context=MainActivity.this;
        TextView textView=(TextView)findViewById(R.id.createAccoutTxt);
        Button loginbutton=(Button)findViewById(R.id.loginBtn);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voilationPage=new Intent(context,SecurityPerson.class);
                startActivity(voilationPage);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Class createAccout= new Class();
               // Toast.makeText(context,"next page",Toast.LENGTH_SHORT).show();
                Intent createAccount=new Intent(context,CreatAccount.class);
                startActivity(createAccount);
            }
        });
    }
}
