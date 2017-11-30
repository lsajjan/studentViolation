package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputEditText email,pwd;
    boolean inputEmpty=false;
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

                //if(inputEmpty==true)
                inputValidationLogin();
                if (inputEmpty==true){
                    Intent voilationPage=new Intent(context,DashboardOptions.class);
                    startActivity(voilationPage);
                }
                else {
                    Toast.makeText(context,"Email or Password is missing",Toast.LENGTH_LONG).show();
                }

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

    private boolean inputValidationLogin() {
        email=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        pwd=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);

        String validEmail=email.getText().toString().trim();
        String validPwd=pwd.getText().toString().trim();

        if(validEmail.isEmpty() || validPwd.isEmpty() ){

            inputEmpty=false;
        }
        else {
            inputEmpty=true;
        }

        return inputEmpty;
    }
}
