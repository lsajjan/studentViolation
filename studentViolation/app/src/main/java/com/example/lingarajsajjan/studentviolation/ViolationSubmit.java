package com.example.lingarajsajjan.studentviolation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.ViolationRegister;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

import java.text.DateFormat;
import java.util.Date;

public class ViolationSubmit extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    TextView showTimeStamp,currentLocation;
    EditText stdName,stdId,currentDate,violationDes;
    Button submitviolation;
    Button getlocationPage,takepic;
    CheckBox dresscode,littering,others,unpermitted;
    private DatabaseHelper databaseHelper;
    private ViolationRegister violation;
    Spinner violationspin;
    ImageView imageView;
    private final AppCompatActivity activity = ViolationSubmit.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_submit);

        final Context context=ViolationSubmit.this;
        stdName=(EditText)findViewById(R.id.textInputEditTextStdName);
        stdId=(EditText)findViewById(R.id.textInputEditTextStdId);
        currentLocation=(TextView)findViewById(R.id.locationTxt);
        currentDate=(EditText)findViewById(R.id.storeDateTime);
        violationDes=(EditText)findViewById(R.id.violation_descriptiontxt);
        submitviolation=(Button)findViewById(R.id.appCompatButtonRegisterViolation);
        getlocationPage=(Button)findViewById(R.id.getlocationBtn);
       // spin=(Spinner)findViewById(R.id.user_type);
        violationspin=(Spinner)findViewById(R.id.user_type);
        imageView=(ImageView) findViewById(R.id.display_img);
        takepic=(Button)findViewById(R.id.take_photo);
        //violationspin.setSelection(0);
        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        currentLocation.setText(getIntent().getStringExtra("Currentlocation"));
        getlocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent locationPage=new Intent(context,MyLocationUsingHelper.class);
                startActivity(locationPage);
            }
        });

        submitviolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidViolationSubmit();
            }
        });

        getCurrentDate();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri u = data.getData();

            System.out.println("tempUri---->"+u);

            imageView.setImageBitmap(photo);

        }
    }
    private void inputValidViolationSubmit() {

        String stdname, stdid, stdlocation, violationDescr, violationDate,checkVal;
        stdname = stdName.getText().toString().trim();
        stdid = stdId.getText().toString().trim();
        stdlocation = currentLocation.getText().toString().trim();
        violationDate = currentDate.getText().toString().trim();
        violationDescr = violationDes.getText().toString().trim();
        databaseHelper = new DatabaseHelper(activity);
        violation = new ViolationRegister();
        violation.setStdId(stdid);
        violation.setStdName(stdname);
        violation.setViolationDescription(violationDescr);
        violation.setViolationDate(violationDate);
        violation.setViolationLocation(stdlocation);
        violation.setViolationStatus("Submitted");
       // Toast.makeText(this, checkVal, Toast.LENGTH_LONG).show();
        // stdlocation=currentLocation.getText().toString().trim();

        if (stdname.isEmpty()) {
            Toast.makeText(this, "Student name is missing", Toast.LENGTH_LONG).show();
        } else if (stdid.isEmpty()) {
            Toast.makeText(this, "Student Id is missing", Toast.LENGTH_LONG).show();
        }

        else if (violationDescr.isEmpty()) {
            Toast.makeText(this, "Violation Description missing", Toast.LENGTH_LONG).show();
        }

        else {
            if (!databaseHelper.checkViolation(stdid)) {

                databaseHelper.submitViolation(violation);
                inputEmpty();
                Toast.makeText(this, "Successfully Registered Violation", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Same Student Id not allowed", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void inputEmpty() {

        stdId.setText(null);
        stdName.setText(null);
        violationDes.setText(null);
        currentLocation.setText(null);
    }

    private void getCurrentDate() {
        showTimeStamp=(TextView)findViewById(R.id.storeDateTime);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        showTimeStamp.setText(currentDateTimeString);
    }
//    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
//    {
//        View rootView = inflater.inflate (R.layout.fragment_hmc,container,false);
//
//        TextView tvOutput = (TextView) rootView.findViewById (R.id.textView4);
//        tvOutput.setText (savedInstanceState.getString ("output"));
//
//        return rootView;
//    }



}
