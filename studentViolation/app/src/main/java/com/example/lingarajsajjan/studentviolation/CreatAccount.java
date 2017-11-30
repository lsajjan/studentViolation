package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

public class CreatAccount extends AppCompatActivity {
    private static final String TAG = "values";
    SQLiteDatabase db;

    EditText username;
    EditText userid ;
    EditText pwd;
    RadioGroup gender;
    TextView loginbk;
    Button submitViolation;
    boolean isemptyCheck=false;
    private DatabaseHelper databaseHelper;
    private UserCreation user;
    RadioGroup ganarGroup;
    RadioGroup userType;
    RadioButton userRadioBtn,ganderRadioBtn;
    private NestedScrollView nestedScrollView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=CreatAccount.this;
        setContentView(R.layout.activity_creat_account);
        loginbk=(TextView)findViewById(R.id.appCompatTextViewLoginLink);
        submitViolation=(Button)findViewById(R.id.appCompatButtonRegister);
        userType=(RadioGroup)findViewById(R.id.RadioBtnUserTypeGroup);
        ganarGroup=(RadioGroup)findViewById(R.id.RadioBtnGanerGroup);


        username=(EditText)findViewById(R.id.textInputEditTextName);
        userid=(EditText)findViewById(R.id.textInputEditTextEmail);
        pwd=(EditText)findViewById(R.id.textInputEditTextPassword);
        int selectedUserId = userType.getCheckedRadioButtonId();
        int selectedganderId=ganarGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        userRadioBtn = (RadioButton) findViewById(selectedUserId);
        ganderRadioBtn=(RadioButton)findViewById(selectedganderId);



        submitViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputValidattionNewUser();

            }
        });
        loginbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginpage=new Intent(context, MainActivity.class);

                startActivity(loginpage);

            }
        });

    }

    private void inputValidattionNewUser() {

       // String gendarmale=male.getText().toString().trim();
        String uname=username.getText().toString().trim();
        String uemail=userid.getText().toString().trim();
        String upwd=pwd.getText().toString().trim();
        //Toast.makeText(this,gendarmale,Toast.LENGTH_LONG).show();

        if (uname.isEmpty())
        {
            Toast.makeText(this,"User Name is Missing",Toast.LENGTH_LONG).show();
        }
        else if (uemail.isEmpty()){
            Toast.makeText(this,"Email id is Missing",Toast.LENGTH_LONG).show();
        }
        else if (upwd.isEmpty()){
            Toast.makeText(this,"User Password is Missing",Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(this,"Saved Success ",Toast.LENGTH_LONG).show();
            //isemptyCheck=true;

            if (!databaseHelper.checkUser(uemail)){

                user.setName(uname);
                user.setEmail(uemail);
                user.setGendar(ganderRadioBtn.getText().toString().trim());
                user.setUserType(userRadioBtn.getText().toString().trim());
                databaseHelper.addUser(user);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                //emptyInputEditText();
            }
            else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }
        }

        //return isemptyCheck=true;
    }


}
