package com.patondekarjewellers.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patondekarjewellers.R;
import com.patondekarjewellers.utils.AppSession;
import com.patondekarjewellers.utils.Helper;
import com.patondekarjewellers.utils.Mail;

import javax.mail.MessagingException;


public class RegistrationActivity extends AppCompatActivity
{

    EditText etUsername, etEmail, etPhone, etCity, etShopName;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialise();
    }

    private void initialise()
    {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCity = (EditText) findViewById(R.id.etCity);
        etShopName = (EditText) findViewById(R.id.etShopName);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate();
            }
        });
    }

    private void validate()
    {

        if (TextUtils.isEmpty(etUsername.getText().toString().trim()))
        {
            Helper.showOkDialog(RegistrationActivity.this, getString(R.string.please_enter_username));
            etUsername.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(etEmail.getText().toString().trim())){
        if (!Helper.isValidEmailAddress(etEmail.getText().toString().trim()))
        {
            Helper.showOkDialog(this, getString(R.string.please_enter_valid_email));
            etEmail.requestFocus();
            return;
        }
    }


        if (TextUtils.isEmpty(etPhone.getText().toString().trim()))
        {
            Helper.showOkDialog(RegistrationActivity.this, getString(R.string.please_enter_contact_number));
            etPhone.requestFocus();
            return;
        }

        if (etPhone.length() < 10)
        {
            Helper.showOkDialog(RegistrationActivity.this, getString(R.string.please_enter_valid_contact_number));
            etPhone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(etShopName.getText().toString().trim()))
        {
            Helper.showOkDialog(RegistrationActivity.this, getString(R.string.please_enter_shop_name));
            etShopName.requestFocus();
            return;
        }

        // callNextIntent();
        //Creating SendMail object
        Mail sm = new Mail(this, "genknooz51@g" +
                "mail.com", "Registration Details", "Hello,\nGreetings for the day!  \n\n\nBelow are the details of Registered Users:" +
                " \n\n\nUser Name: " + etUsername.getText().toString() +
                "\n\nEmail Address: " + etEmail.getText().toString() +
                "\n\nPhone Number: " + etPhone.getText().toString() +
                "\n\nCurrent City: " + etCity.getText().toString() +
                "\n\nName of the Shop: " + etShopName.getText().toString() + "\n\n\nThanks & Regards, \n\nTest Team"
        );
        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}
