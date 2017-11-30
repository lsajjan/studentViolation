package com.example.lingarajsajjan.studentviolation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
public class ViolationSubmit extends AppCompatActivity {
    TextView showTimeStamp;
    EditText stdName,stdId,currentLocation,currentDate,violationDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_submit);

        stdName=(EditText)findViewById(R.id.textInputEditTextStdName);
        stdId=(EditText)findViewById(R.id.textInputEditTextStdId);
        currentLocation=(EditText)findViewById(R.id.textInputEditTextLocation);
        currentDate=(EditText)findViewById(R.id.storeDateTime);
        violationDes=(EditText)findViewById(R.id.violation_descriptiontxt);


        getCurrentDate();
        inputValidViolationSubmit()
        String dateStr = "04/05/2010";

       // Toast.makeText(this,currentDateTimeString,Toast.LENGTH_LONG).show();

//        String newDateStr = postFormater.format(dateObj);
//
        //showTimeStamp.setText(currentDateTimeString);
    }

    private void inputValidViolationSubmit() {

        String stdname,stdid,stdlocation;
        stdname=stdName.getText().toString().trim();
        stdid=stdId.getText().toString().trim();
        stdlocation=currentLocation.getText().toString().trim();

        if (stdname.isEmpty()){
            Toast.makeText(this,"Student name is missing",Toast.LENGTH_LONG).show();
        }
        else if (stdid.isEmpty()){
            Toast.makeText(this,"Student Id is missing",Toast.LENGTH_LONG).show();
        }
        else if (stdlocation.isEmpty()){
            Toast.makeText(this,"Voilation location is missing",Toast.LENGTH_LONG).show();
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
