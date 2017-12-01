package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

import java.util.List;

public class UserProfileView extends AppCompatActivity {

    TextView nameUpdate,idUpdate,pwdUpdate;
    DatabaseHelper databaseHelper;
    UserCreation user;
    Button updateBtn;
    int id;
    String stdId;
    private final AppCompatActivity activity = UserProfileView.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view);
        final Context context=UserProfileView.this;
        nameUpdate=(TextView)findViewById(R.id.textInputEditTextNameUpdate);
        idUpdate=(TextView)findViewById(R.id.textInputEditTextEmailUpdate);
        pwdUpdate=(TextView)findViewById(R.id.textInputEditTextPasswordUpdate);
        updateBtn=(Button)findViewById(R.id.appCompatButtonRegisterUpdate);
        databaseHelper=new DatabaseHelper(activity);
        user=new UserCreation();
        Bundle extras = getIntent().getExtras();
        stdId= extras.getString("stdId").trim();

        loadUserData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {


                updateUserData();

                //Toast.makeText(context,nn,Toast.LENGTH_LONG).show();

            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



    }

    private void updateUserData() {

        String uname, uemail, upwd;
        uname = nameUpdate.getText().toString().trim();
        uemail = idUpdate.getText().toString().trim();
        upwd = pwdUpdate.getText().toString().trim();
       // int stdId;
        System.out.println("updateUserData----->"+id);
        if (!(uemail.isEmpty() || uname.isEmpty() || upwd.isEmpty())) {
            if (!databaseHelper.checkUser(uemail)) {

                user.setName(uname);
                user.setEmail(uemail);
                user.setPassword(upwd);
                user.setId(id);
                //user.setGendar(gandner);
                //user.setUserType(userType);
                databaseHelper.updateUser(user);

                // Snack Bar to show success message that record saved successfully
                //Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                //emptyInputEditText();

                Toast.makeText(this, "User " + uname + "Records Updated SuccessFully..!", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "User " + uname + " exists already..", Toast.LENGTH_LONG).show();
                // Snack Bar to show error message that record already exists
                //Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Name or id or pwd is missing..!", Toast.LENGTH_LONG).show();
        }
    }
    private void loadUserData() {
        //String sendId=stdId;
        List<UserCreation> name= databaseHelper.getAllUser(stdId);

        for(int i=0;i<name.size();i++)
        {
            UserCreation userCreation = name.get(i);
            nameUpdate.setText(userCreation.getName().toString().trim());
            pwdUpdate.setText(userCreation.getPassword().toString().trim());
            idUpdate.setText(userCreation.getEmail().toString().trim());
            id=userCreation.getId();

           // System.out.println("name-------->"+userCreation.getName());
            System.out.println("id-------->"+userCreation.getId());

        }
        //String tt=name

       // System.out.println("namesss......>"+name);
    }

}
