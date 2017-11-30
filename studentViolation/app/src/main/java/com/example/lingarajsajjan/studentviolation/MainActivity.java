package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context=MainActivity.this;
       // TextView textView=(TextView)findViewById(R.id.textInputEditTextEmail);
        AppCompatTextView textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        Button loginbutton=(Button)findViewById(R.id.ButtonLogin);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voilationPage=new Intent(context,DashboardOptions.class);
                startActivity(voilationPage);
            }
        });

        textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAccount=new Intent(context,CreatAccount.class);
                startActivity(createAccount);
            }
        });
    }
}
