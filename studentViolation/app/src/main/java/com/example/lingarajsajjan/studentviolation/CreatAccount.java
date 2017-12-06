package com.example.lingarajsajjan.studentviolation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

import java.io.ByteArrayOutputStream;

// imported for take photo method

public class CreatAccount extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "values";
    private final AppCompatActivity activity = CreatAccount.this;
    SQLiteDatabase db;

    EditText username;
    EditText userid ;
    EditText pwd;
    RadioGroup gender;
    AppCompatTextView appCompatTextViewLoginLink;
    Button appCompatButtonRegister,takepic;
    boolean isemptyCheck=false;
    private DatabaseHelper databaseHelper;
    private UserCreation user;
    RadioGroup ganarGroup;
    RadioGroup userType;
    RadioButton userRadioBtn,ganderRadioBtn;
    Spinner spin, campusSpin;
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    private NestedScrollView nestedScrollView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=CreatAccount.this;
        setContentView(R.layout.activity_creat_account);

        appCompatTextViewLoginLink=(AppCompatTextView)findViewById(R.id.appCompatTextViewLoginLink);
        appCompatButtonRegister=(Button)findViewById(R.id.appCompatButtonRegister);
       // appCompatTextViewLoginLink=(Button)findViewById(R.id.appCompatTextViewLoginLink);
       // userType=(RadioGroup)findViewById(R.id.RadioBtnUserTypeGroup);
        //ganarGroup=(RadioGroup)findViewById(R.id.RadioBtnGanerGroup);
        spin=(Spinner)findViewById(R.id.user_type);
        campusSpin=(Spinner)findViewById(R.id.student_campus) ;
        takepic=(Button)findViewById(R.id.take_photo);
        imageView=(ImageView) findViewById(R.id.display_img);
        spin.setSelection(0);
        user =new UserCreation();// databaseHelper.createTable();
        databaseHelper =new DatabaseHelper(activity);
        username=(EditText)findViewById(R.id.textInputEditTextName);
        userid=(EditText)findViewById(R.id.textInputEditTextEmail);
        pwd=(EditText)findViewById(R.id.textInputEditTextPassword);

        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String Text = spin.getSelectedItem().toString();

                if(position != 1)
                    campusSpin.setVisibility(View.GONE);
                else
                    campusSpin.setVisibility(View.VISIBLE);


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String uri=data.
           // System.out.println("tempUri---->"+uri);
            //Toast.makeText(this,uri,Toast.LENGTH_LONG).show();
           // Bitmap path=(Bitmap)data.getExtras();
           // imageView.setImageBitmap(photo);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
           // Uri tempUri = getImageUri(getApplicationContext(), photo);
            //System.out.println("tempUri---->"+tempUri);
            // CALL THIS METHOD TO GET THE ACTUAL PATH
            //File finalFile = new File(getRealPathFromURI(tempUri));

            //System.out.println("mmlkl---->"+finalFile);
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
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
