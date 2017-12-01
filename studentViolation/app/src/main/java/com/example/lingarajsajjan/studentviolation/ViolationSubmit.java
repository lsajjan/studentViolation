package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingarajsajjan.studentviolation.model.ViolationRegister;
import com.example.lingarajsajjan.studentviolation.sql.DatabaseHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolationSubmit extends AppCompatActivity {
    TextView showTimeStamp,currentLocation;
    EditText stdName,stdId,currentDate,violationDes;
    Button submitviolation;
    Button getlocationPage;
    CheckBox dresscode,littering,others,unpermitted;
    private DatabaseHelper databaseHelper;
    private ViolationRegister violation;
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
        dresscode=(CheckBox)findViewById(R.id.checkDresscodeVal);
        littering=(CheckBox)findViewById(R.id.checkLitteringVal);
        unpermitted=(CheckBox)findViewById(R.id.checkunpermittedVal);
        others=(CheckBox)findViewById(R.id.checkOthersVal);


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

        String dateStr = "04/05/2010";

       // Toast.makeText(this,currentDateTimeString,Toast.LENGTH_LONG).show();

//        String newDateStr = postFormater.format(dateObj);
//
        //showTimeStamp.setText(currentDateTimeString);
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
        List<CheckBox> items = new ArrayList<CheckBox>();
        for (CheckBox item : items) {
            if (item.isChecked()) {
                checkVal = item.getText().toString().trim();
                violation.setViolationType(checkVal);
                Toast.makeText(this, checkVal, Toast.LENGTH_LONG).show();
            }
        }
        violation.setStdId(stdid);
        violation.setStdName(stdname);
        violation.setViolationDescription(violationDescr);
        violation.setViolationDate(violationDate);
        violation.setViolationLocation(stdlocation);
       // Toast.makeText(this, checkVal, Toast.LENGTH_LONG).show();
        // stdlocation=currentLocation.getText().toString().trim();

        if (stdname.isEmpty()) {
            Toast.makeText(this, "Student name is missing", Toast.LENGTH_LONG).show();
        } else if (stdid.isEmpty()) {
            Toast.makeText(this, "Student Id is missing", Toast.LENGTH_LONG).show();
        }
        else if(!(others.isChecked() || dresscode.isChecked() || unpermitted.isChecked() || littering.isChecked())){
            Toast.makeText(this, "Select Violation Type missing", Toast.LENGTH_LONG).show();
        }
        else {
            if (!databaseHelper.checkViolation(stdid)) {

                databaseHelper.submitViolation(violation);

                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show();
            }
        }
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
