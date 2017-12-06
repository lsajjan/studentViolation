package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

public class CreatAccount extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "values";
    private final AppCompatActivity activity = CreatAccount.this;
    SQLiteDatabase db;

    EditText username;
    EditText userid ;
    EditText pwd;
    RadioGroup gender;
    AppCompatTextView appCompatTextViewLoginLink;
    Button appCompatButtonRegister;
    boolean isemptyCheck=false;
    private DatabaseHelper databaseHelper;
    private UserCreation user;
    RadioGroup ganarGroup;
    RadioGroup userType;
    RadioButton userRadioBtn,ganderRadioBtn;
    Spinner spin, campusSpin;
    private NestedScrollView nestedScrollView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=CreatAccount.this;
        setContentView(R.layout.activity_creat_account);
        //getSupportActionBar().hide();
        appCompatTextViewLoginLink=(AppCompatTextView)findViewById(R.id.appCompatTextViewLoginLink);
        appCompatButtonRegister=(Button)findViewById(R.id.appCompatButtonRegister);
       // appCompatTextViewLoginLink=(Button)findViewById(R.id.appCompatTextViewLoginLink);
       // userType=(RadioGroup)findViewById(R.id.RadioBtnUserTypeGroup);
        //ganarGroup=(RadioGroup)findViewById(R.id.RadioBtnGanerGroup);
        spin=(Spinner)findViewById(R.id.user_type);
        campusSpin=(Spinner)findViewById(R.id.student_campus) ;

        spin.setSelection(0);
       // campusSpin.setVisibility(view.GONE);
        user =new UserCreation();
       // databaseHelper.createTable();
        databaseHelper =new DatabaseHelper(activity);


        username=(EditText)findViewById(R.id.textInputEditTextName);
        userid=(EditText)findViewById(R.id.textInputEditTextEmail);
        pwd=(EditText)findViewById(R.id.textInputEditTextPassword);
        //int selectedUserId = userType.getCheckedRadioButtonId();
       // int selectedganderId=ganarGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
       // userRadioBtn = (RadioButton) findViewById(selectedUserId);
        //ganderRadioBtn=(RadioButton)findViewById(selectedganderId);

        //initListeners();
        //Intent loginBk=new Intent(activity,MainActivity.class);
        //startActivity(loginBk);



        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String Text = spin.getSelectedItem().toString();

                if(position != 1)
                    campusSpin.setVisibility(View.GONE);
                else
                    campusSpin.setVisibility(View.VISIBLE);

//                if(Text=="SELECT USER TYPE"){
//
//                    campusSpin.setVisibility(view.VISIBLE);
//
//                }
//                else
//                {
//                    campusSpin.setVisibility(view.S);
//                }
                Toast.makeText(context,Text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputValidattionNewUser();

            }
        });
        appCompatTextViewLoginLink.setOnClickListener(new View.OnClickListener() {
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
        String gandner=ganderRadioBtn.getText().toString().trim();
        String userType=userRadioBtn.getText().toString().trim();
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
                user.setPassword(upwd);
                //user.setGendar(gandner);
                user.setUserType(userType);
                databaseHelper.addUser(user);

                // Snack Bar to show success message that record saved successfully
                //Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                //emptyInputEditText();
                emptyInputEditText();
                Toast.makeText(this,"New User "+uname+" created SuccessFully..!",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(this,"User "+uname+" exists already..",Toast.LENGTH_LONG).show();
                // Snack Bar to show error message that record already exists
                //Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }
        }

        //return isemptyCheck=true;
    }

    private void emptyInputEditText() {
        username.setText(null);
        userid.setText(null);
        pwd.setText(null);
        //user.setGendar(gandner);
        //user.setUserType(userType);

    }

    private void initListeners() {


        appCompatButtonRegister.setOnClickListener(this);
        //appCompatTextViewLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                //Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
                inputValidattionNewUser();
                break;

//            case R.id.appCompatTextViewLoginLink:
////                Intent loginBk=new Intent(activity,MainActivity.class);
////                startActivity(loginBk);
//                finish();
//
//                break;
        }
    }
}
