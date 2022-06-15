package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class NewMessage extends AppCompatActivity {

    EditText txt_sender, txt_receiver, txt_subject, txt_message, txt_key;
    TextView cryptedMessage;
    String cryptedString;
    Button btn_Send;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        txt_sender = findViewById(R.id.txt_sender);
        txt_receiver = findViewById(R.id.txt_receiver);
        txt_subject = findViewById(R.id.txt_subject);
        txt_message = findViewById(R.id.txt_message);
        txt_key = findViewById(R.id.txt_key);
        cryptedMessage = findViewById(R.id.cryptedMessage);
        btn_Send = findViewById(R.id.btn_Send);
        progressBar = findViewById(R.id.progress);

        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    cryptedString = encrypt (txt_message.getText().toString(), txt_key.getText().toString());
                    cryptedMessage.setText(cryptedString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                final String sender_email, receiver_email, email_subject, email_message;
                sender_email = String.valueOf(txt_sender.getText());
                receiver_email = String.valueOf(txt_receiver.getText());
                email_subject    = String.valueOf(txt_subject.getText());
                email_message = String.valueOf(cryptedMessage.getText());

                if (!sender_email.equals("") && !receiver_email.equals("") && !email_subject.equals("") && !email_message.equals("")) {

                    if(!Patterns.EMAIL_ADDRESS.matcher(sender_email).matches()) {
                        txt_sender.setError("Check email format!");
                        txt_sender.requestFocus();
                        return;
                    }

                    if(!Patterns.EMAIL_ADDRESS.matcher(receiver_email).matches()) {
                        txt_receiver.setError("Check email format!");
                        txt_receiver.requestFocus();
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "sender_email";
                            field[1] = "receiver_email";
                            field[2] = "email_subject";
                            field[3] = "email_message";

                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = sender_email;
                            data[1] = receiver_email;
                            data[2] = email_subject;
                            data[3] = email_message;

                            PutData putData = new PutData("https://tugasakhirwilliam.000webhostapp.com/send_message.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Message Sent")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encyptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encyptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

}