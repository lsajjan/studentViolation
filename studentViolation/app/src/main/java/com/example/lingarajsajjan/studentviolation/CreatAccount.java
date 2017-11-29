package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class CreatAccount extends AppCompatActivity {
    private static final String TAG = "values";
    SQLiteDatabase db;

    EditText username;
    EditText userid ;
    EditText userdob;
    RadioGroup gender;

    ListView userType;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        username=(EditText)findViewById(R.id.name);
        userid =(EditText)findViewById(R.id.userId);
        userdob=(EditText)findViewById(R.id.userdob);
        gender=(RadioGroup)findViewById(R.id.radioGroup);
        userType=(ListView)findViewById(R.id.userType);

        Button submit=(Button)findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbCreate();
                SaveUserInfo();
            }
        });


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
    public void DbCreate(){
       // Context context=MainActivity.this;
        db = openOrCreateDatabase("CollegeViolation", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS userTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, userid VARCHAR, dob VARCHAR,gender VARCHAR,userType VARCHAR);");

    }
    public  void SaveUserInfo(){

        String name=username.getText().toString();
        String id=userid.getText().toString();
        String dob=userdob.getText().toString();
       // int selected=RadioGroup.getCheckedRadioButtonId();
        //RadioButton gender=(RadioButton)findViewById(selected);
        //RadioButton gender=(RadioButton)findViewById(selected);
        //
        //Toast.makeText(getApplicationContext(),gender.getText(),5000).show();
       // Log.i("name"+name);
       //String userGendar=gender.getText().toString();
       // String sqlInsertquery;
        String  userGendar="Male";
        String  userType="security";
       // String sqlInsertquery="SELECT * FROM userTable";
      // String sqlInsertquery="INSERT INTO userTable (name,userid,dob,gender,userType) VALUES ('"+name+"','"+id+"','"+dob+"','"+userGendar+"','"+userType+"')";
        //db.execSQL(sqlInsertquery);

       // String sqlSelectQuery="SELECT * FROM userTable";
       //Cursor c= db.rawQuery(sqlSelectQuery);


        //Toast.makeText(this,"inserted successfully",Toast.LENGTH_LONG).show();



        ArrayList<String> friendsNames = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "select * from userTable";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            while (cursor.moveToNext()) {
                friendsNames.add(cursor.getString(cursor.getColumnIndex("name")));
                Log.i(TAG,"values"+friendsNames);
            }
        }catch(Exception ex){
            Log.e(TAG,"Erro in geting friends "+ex.toString());
        }




//       // long rowInserted = db.insert(AddNewPhysicalPerson, null, newValues);
//        if(checkdat != -1)
//            Toast.makeText(this, "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
    }

}
