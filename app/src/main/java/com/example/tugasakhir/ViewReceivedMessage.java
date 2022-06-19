package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ViewReceivedMessage extends AppCompatActivity {

    EditText txt_sender, txt_subject, txt_message, txt_key;
    String decryptedString;
    Button btn_Decrypt;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_received_message);

        txt_sender = findViewById(R.id.txt_sender);
        txt_subject = findViewById(R.id.txt_subject);
        txt_message = findViewById(R.id.txt_message);
        txt_key = findViewById(R.id.txt_key);
        btn_Decrypt = findViewById(R.id.btn_Decrypt);
        progressBar = findViewById(R.id.progress);

        Intent intent = getIntent();
        String sender_email = intent.getStringExtra("sender_email");
        String email_subject = intent.getStringExtra("email_subject");
        String email_message = intent.getStringExtra("email_message");

        if (intent != null) {
            txt_sender.setText(sender_email);
            txt_subject.setText(email_subject);
            txt_message.setText(email_message);
        }

        btn_Decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    decryptedString = decrypt (txt_message.getText().toString(), txt_key.getText().toString());
                    txt_message.setText(decryptedString);
                    btn_Decrypt.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    Toast.makeText(ViewReceivedMessage.this, "Wrong Key", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private String decrypt(String outputString, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decVal = c.doFinal(decodedValue);
        String decyptedValue = new String(decVal);
        return decyptedValue;
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