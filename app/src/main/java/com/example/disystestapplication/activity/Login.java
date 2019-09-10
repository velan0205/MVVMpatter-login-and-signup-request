package com.example.disystestapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disystestapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class Login extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;

    private TextView signup;
    private TextInputLayout nameInputLayout, mobilenoInputLayout;
    private TextInputEditText name, mobileNo;

    private Button btnlogin;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameInputLayout = findViewById(R.id.logNameTextInputLayout);
        name = findViewById(R.id.logNameTextInputEditText);

        mobilenoInputLayout = findViewById(R.id.logMobilenoTextInputLayout);
        mobileNo = findViewById(R.id.logMobilenoTextInputEditText);


        btnlogin = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnSignUP);

        btnlogin.setOnClickListener(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {
        submitForm();
    }


    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validateMobileno()) {
            return;
        }
        btnlogin.setEnabled(false);
        //save

        SharedPreferences preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);

        if (!TextUtils.isEmpty(preferences.getString("user_name", null))) {
            String usernam = preferences.getString("user_name", null);
            String mobileno = preferences.getString("mobileno", null);
            assert usernam != null;
            if (usernam.equals(name.getText().toString()) && (mobileno.equals(mobileNo.getText().toString()))) {
                Intent intent = new Intent(getApplicationContext(), DataDisplayActivity.class);
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "User Name and MobileNo is not matching", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validateName() {
        if (name.getEditableText().toString().isEmpty()) {
            nameInputLayout.setError("Enter Name");
            return false;
        } else {
            nameInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMobileno() {
        if (mobileNo.getEditableText().toString().length() != 12) {
            mobilenoInputLayout.setError("Enter Mobile Number");
            return false;
        } else {
            mobilenoInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}
