package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;

public class DashboardOptions extends AppCompatActivity {

    Button submitViolation,viewViolation,viewUserProfile;
    AppCompatTextView welcomeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context contex=DashboardOptions.this;
        setContentView(R.layout.activity_dashboard_options);
        final Context context=DashboardOptions.this;
        submitViolation=(Button)findViewById(R.id.submitViolation);
        viewViolation=(Button)findViewById(R.id.view_violation) ;
        viewUserProfile=(Button)findViewById(R.id.viewProfile);
        welcomeTxt=(AppCompatTextView)findViewById(R.id.welcomeTxt);
        Bundle extras = getIntent().getExtras();
        final String newString= extras.getString("welcomeTxt").trim();

        welcomeTxt.setText("Hi "+newString+" Welcome");
        submitViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoViolationSubmit=new Intent(contex,ViolationSubmit.class);
                startActivity(gotoViolationSubmit);
            }
        });
        viewViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsIntent = new Intent(context, ViolationListActivity.class);
                startActivity(accountsIntent);
            }
        });
        viewUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfile=new Intent(context,UserProfileView.class);
                userProfile.putExtra("stdId",newString);
                startActivity(userProfile);
            }
        });
    }
}
