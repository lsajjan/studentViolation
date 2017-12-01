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

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private final AppCompatActivity activity = MainActivity.this;
    TextInputEditText email,pwd;
    boolean inputEmpty=false;

    AppCompatTextView textViewLinkRegister;
    private DatabaseHelper databaseHelper;
    private UserCreation user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context=MainActivity.this;

       // TextView textView=(TextView)findViewById(R.id.textInputEditTextEmail);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        Button loginbutton=(Button)findViewById(R.id.ButtonLogin);
        user=new UserCreation();
        databaseHelper=new DatabaseHelper(activity);
        email=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        pwd=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidationLogin();
                if (inputEmpty==true){
                    Intent dashboardPage=new Intent(context,DashboardOptions.class);
                    email=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
                    pwd=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);

                    String validEmail=email.getText().toString().trim();
                    String validPwd=pwd.getText().toString().trim();
                    if(databaseHelper.checkUser(validEmail,validPwd)){

                        emptyInputEditText();
                        dashboardPage.putExtra("welcomeTxt",validEmail);
                        startActivity(dashboardPage);

                    }
                     else
                    {
                        Toast.makeText(context,"user not found",Toast.LENGTH_LONG).show();
                    }

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

    private void emptyInputEditText() {
        email.setText(null);
        pwd.setText(null);
    }

    private boolean inputValidationLogin() {


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
