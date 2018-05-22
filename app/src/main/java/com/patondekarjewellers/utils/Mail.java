package com.patondekarjewellers.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.patondekarjewellers.R;
import com.patondekarjewellers.activites.HomeActivity;
import com.patondekarjewellers.activites.LanguageSelectionActivity;
import com.patondekarjewellers.activites.RegistrationActivity;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Akshay on 30-11-2017.
 */

public class Mail extends AsyncTask<Void, Void, Void>
{

    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public Mail(Context context, String email, String subject, String message)
    {
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Registering", "Please wait...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        if (AppSession.getInstance().getLoginStatus(context))
        {
            Intent home_intent = new Intent(context, HomeActivity.class);
            context.startActivity(home_intent);
            ((Activity) context).finish();
             AppSession.getInstance().saveLoginStatus(context, true);
        } else
        {
            Intent mainIntent = new Intent(context, LanguageSelectionActivity.class);
            context.startActivity(mainIntent);
            ((Activity) context).finish();
            AppSession.getInstance().saveLoginStatus(context, true);
        }
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
                {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try
        {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}