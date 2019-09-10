package com.example.disystestapplication.activity;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.disystestapplication.NewsAdapter;
import com.example.disystestapplication.R;
import com.example.disystestapplication.ViewModel.NewsViewModel;
import com.example.disystestapplication.model.NewsModel;
import com.example.disystestapplication.model.PlayloadModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout eidInputLayout, nameInputLayout, idbarahnoInputLayout, emailaddressInputLayout, unifiednoInputLayout, mobilenoInputLayout;
    private TextInputEditText eid, name, idbarahno, emailaddress, unifiedno, mobileno;
    private Button btnsignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        eidInputLayout = findViewById(R.id.userIDTextInputLayout);
        eid = findViewById(R.id.userIDTextInputEditText);

        nameInputLayout = findViewById(R.id.nameTextInputLayout);
        name = findViewById(R.id.nameTextInputEditText);

        idbarahnoInputLayout = findViewById(R.id.idbarahnoTextInputLayout);
        idbarahno = findViewById(R.id.idbarahnoTextInputEditText);

        emailaddressInputLayout = findViewById(R.id.emailTextInputLayout);
        emailaddress = findViewById(R.id.emailTextInputEditText);

        unifiednoInputLayout = findViewById(R.id.unifiedNumberTextInputLayout);
        unifiedno = findViewById(R.id.unifiedNumberTextInputEditText);

        mobilenoInputLayout = findViewById(R.id.mobileNumberTextInputLayout);
        mobileno = findViewById(R.id.mobileNumberTextInputEditText);
        btnsignup = findViewById(R.id.signUP);

        btnsignup.setOnClickListener(this);
    }


    private void observable() {
        NewsViewModel model = ViewModelProviders.of(this).get(NewsViewModel.class);
        model.getSignUPResponse(Integer.valueOf(eid.getText().toString()),name.getText().toString(),Integer.valueOf(idbarahno.getText().toString()),
                emailaddress.getText().toString(),Integer.valueOf(unifiedno.getText().toString()),mobileno.getText().toString()).observe(this, new Observer<PlayloadModel>() {
            @Override
            public void onChanged(PlayloadModel playloadModel) {
                if (playloadModel.getSuccess().equals("true"))
                {
                    //save
                    SharedPreferences.Editor editor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
                    editor.putString("user_name", name.getText().toString().trim());
                    editor.putString("password", mobileno.getText().toString().trim());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), DataDisplayActivity.class);
                    startActivity(intent);
                }else{
                    //alert
                    ServerError();
                }
            }
        });
        model.getNews().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(@Nullable List<NewsModel> newsList) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        submitForm();
        observable();
    }

    private void submitForm() {
        if (!validateEID()) {
            return;
        }
        if (!validateName()) {
            return;
        }
        if (!validateIdbarahno()) {
            return;
        }
        if (!validateEmailaddress()) {
            return;
        }
        if (!validateUnifiedno()) {
            return;
        }
        if (!validateMobileno()) {
            return;
        }
        btnsignup.setEnabled(false);

    }

    private boolean validateEID() {
        if (eid.getEditableText().toString().isEmpty()) {
            unifiednoInputLayout.setError(" Enter EID number");
            return false;
        } else {
            unifiednoInputLayout.setErrorEnabled(false);
        }
        return true;
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


    private boolean validateUnifiedno() {
        if (unifiedno.getEditableText().toString().isEmpty()) {
            unifiednoInputLayout.setError("Enter Unified Number");
            return false;
        } else {
            unifiednoInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateIdbarahno() {
        if (idbarahno.getEditableText().toString().isEmpty()) {
            idbarahnoInputLayout.setError("Enter idbarahno");
            return false;
        } else {
            idbarahnoInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validateEmailaddress() {

        String email = emailaddress.getText().toString().trim();

        if (email.isEmpty() || !isEmailValid(email)) {
            emailaddressInputLayout.setError("Enter EmailID");
            return false;
        } else {
            emailaddressInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    public boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean validateMobileno() {
        if (mobileno.getText().toString().length() != 12) {
            mobilenoInputLayout.setError("Enter Mobile Number");
            return false;
        } else {
            mobilenoInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    //ALERT SERVER ERROR
    private void ServerError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Something went wrong while processing your request.Please try again later!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Alert !");
        alert.show();
    }
}
