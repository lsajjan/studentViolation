package com.example.lingarajsajjan.studentviolation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardOptions extends AppCompatActivity {

    Button submitViolation,viewViolation,viewUserProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context contex=DashboardOptions.this;
        setContentView(R.layout.activity_dashboard_options);
        final Context context=DashboardOptions.this;
        submitViolation=(Button)findViewById(R.id.submitViolation);
        viewViolation=(Button)findViewById(R.id.view_violation) ;
        viewUserProfile=(Button)findViewById(R.id.viewProfile);
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
                startActivity(userProfile);
            }
        });
    }
}
