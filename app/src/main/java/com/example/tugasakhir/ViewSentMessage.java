package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ViewSentMessage extends AppCompatActivity {

    EditText txt_receiver, txt_subject, txt_message, txt_key;
    TextView textViewDecryptedMessage, decryptedMessage;
    String decryptedString;
    Button btn_Decrypt;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sent_message);

        txt_receiver = findViewById(R.id.txt_receiver);
        txt_subject = findViewById(R.id.txt_subject);
        txt_message = findViewById(R.id.txt_message);
        txt_key = findViewById(R.id.txt_key);
        textViewDecryptedMessage = findViewById(R.id.textViewDecryptedMessage);
        decryptedMessage = findViewById(R.id.decryptedMessage);
        btn_Decrypt = findViewById(R.id.btn_Decrypt);
        progressBar = findViewById(R.id.progress);

        Intent intent = getIntent();
        String receiver_email = intent.getStringExtra("receiver_email");
        String email_subject = intent.getStringExtra("email_subject");
        String email_message = intent.getStringExtra("email_message");

        if (intent != null) {
            txt_receiver.setText(receiver_email);
            txt_subject.setText(email_subject);
            txt_message.setText(email_message);
        }

        btn_Decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    textViewDecryptedMessage.setVisibility(View.VISIBLE);
                    decryptedMessage.setVisibility(View.VISIBLE);
                    decryptedString = decrypt (txt_message.getText().toString(), txt_key.getText().toString());
                    decryptedMessage.setText(decryptedString);
                } catch (Exception e) {
                    textViewDecryptedMessage.setVisibility(View.INVISIBLE);
                    decryptedMessage.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViewSentMessage.this, "Wrong Key", Toast.LENGTH_SHORT).show();
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