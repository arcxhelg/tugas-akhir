package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SentMessages extends AppCompatActivity {

    private static final String URL = "https://tugasakhirwilliam.000webhostapp.com/get_messages.php";
    private List<GetMessages> sentMessageList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_messages);

        recyclerView = findViewById(R.id.sentMessagesRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sentMessageList = new ArrayList<>();

        loadMessages();
    }

    private void loadMessages() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject message = array.getJSONObject(i);

                        sentMessageList.add(new GetMessages(
                                message.getString("sender_email"),
                                message.getString("receiver_email"),
                                message.getString("email_subject"),
                                message.getString("email_message"),
                                message.getString("email_datetime")
                        ));
                    }

                    SentMessageAdapter adapter = new SentMessageAdapter(SentMessages.this, sentMessageList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

}