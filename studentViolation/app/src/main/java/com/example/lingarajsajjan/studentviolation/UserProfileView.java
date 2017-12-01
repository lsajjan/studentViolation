package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

import java.util.List;

public class UserProfileView extends AppCompatActivity {

    TextView nameUpdate,idUpdate,pwdUpdate;
    DatabaseHelper databaseHelper;
    Button updateBtn;
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
        loadUserData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {




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

    private void loadUserData() {
        List<UserCreation> name= databaseHelper.getAllUser("ss");

        for(int i=0;i<name.size();i++)
        {
            UserCreation userCreation = name.get(i);
            nameUpdate.setText(userCreation.getName().toString().trim());
            pwdUpdate.setText(userCreation.getPassword().toString().trim());
            idUpdate.setText(userCreation.getEmail().toString().trim());
           // System.out.println("name-------->"+userCreation.getName());
           // System.out.println("name-------->"+userCreation.getEmail());

        }
        //String tt=name

       // System.out.println("namesss......>"+name);
    }

}
